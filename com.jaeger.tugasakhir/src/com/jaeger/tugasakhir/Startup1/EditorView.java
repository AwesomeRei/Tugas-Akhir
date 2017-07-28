package com.jaeger.tugasakhir.Startup1;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.jface.text.DocumentEvent;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IDocumentListener;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.IPartListener2;
import org.eclipse.ui.IStartup;
import org.eclipse.ui.IWindowListener;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchPartReference;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.FileEditorInput;
import org.eclipse.ui.texteditor.ITextEditor;

import com.jaeger.model.Document;
import com.jaeger.tugasakhir.file.WriteFile;

public class EditorView implements IStartup{
	WriteFile myFile = new WriteFile();
	private static String title;

	IWorkbenchWindow ww;

	public void earlyStartup() {
		IWorkbench wb = PlatformUI.getWorkbench();
		ww = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
		wb.addWindowListener(generateWindowListener());
		// TODO Auto-generated method stub

	}

	private IWindowListener generateWindowListener() {
		// TODO Auto-generated method stub
		return new IWindowListener(){

			public void windowActivated(IWorkbenchWindow window) {
				// TODO Auto-generated method stub
				IWorkbenchPage activePage = window.getActivePage();
				activePage.addPartListener(generateIPartListener2());
			}

			public void windowDeactivated(IWorkbenchWindow window) {
				// TODO Auto-generated method stub

			}

			public void windowClosed(IWorkbenchWindow window) {
				// TODO Auto-generated method stub

			}

			public void windowOpened(IWorkbenchWindow window) {
				// TODO Auto-generated method stub

			}

		};
	}
	IDocumentListener docX = new IDocumentListener(){

		Deque<Document> deque = new LinkedList<>();

		public void documentAboutToBeChanged(DocumentEvent event) {
			// TODO Auto-generated method stub
		}

		public void documentChanged(DocumentEvent event) {
			if(event.getLength()>=1 && deque.isEmpty()){
				try {
					myFile.writeXMLRaw(event);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else {
				dequeOperation(event);
			}
		}
		private void dequeOperation(DocumentEvent event){
			// DECK OPERATION
			try {
				myFile.writeXMLRaw(event);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//System.out.println("rampage "+event.getOffset() +" "+event.getLength()+" "+event.getText());
			if(event.getLength()>=1){

//				System.out.println("Deletion");
				if(deque.isEmpty()){
//					System.out.println("Deck 2 is empty");
				}else {
					if(event.getLength()==1 && event.getText().length()==0){
						// Backspace/deletion event 
						if(event.getOffset()<=deque.peekLast().getOffset()){
							// Autocomplete or undo action 
//							System.out.println("top offset "+ deque.peekLast().getOffset()+" event "+event.getOffset());
							List<Document> temp = new ArrayList<>();
							int move = deque.peekLast().getOffset() - event.getOffset();
							for(int i=0;i<move;i++){
								temp.add(deque.removeLast());
							}
							deque.removeLast();
//							System.out.println("last item deleted2 : "+ deque.removeLast().getText());
							
							for(int j = move-1;j>=0;j--){
								Document tempDoc = new Document(deque.peekLast().getOffset()+1,temp.get(j).getText());
								deque.add(tempDoc);
							}
						}else {
							deque.removeLast();
//							System.out.println("last item deleted2 : "+ deque.removeLast());		
						}

					}else if(event.getOffset()<=deque.peekLast().getOffset()){
						// Autocomplete or undo action 
//						System.out.println("Autocomplete detected or Undo detected");
						List<Document> temp = new ArrayList<>();
						int move = deque.peekLast().getOffset()-(event.getOffset()+event.getLength());
						
//						System.out.println("deque offset "+ deque.peekLast().getOffset()+" "+deque.peekLast().getText()+ " event offset "+ event.getOffset()+" "+event.getLength()+" move "+move );
						if(move>=0){
							for(int i=0;i<=move;i++){		
								temp.add(deque.removeLast());
							}
						}
						for(int i=0;i<event.getLength();i++){
							//replace the string
							deque.removeLast();
//							System.out.println("out from queue2 : "+ deque.removeLast().getText());
						}
						String x = event.getText();
						for(int i = 0;i<x.length();i++){
							String in = String.valueOf(x.charAt(i));
							if(deque.isEmpty()){
								deque.add(new Document(event.getOffset()+i,in));
							}else {
								deque.add(new Document(deque.peekLast().getOffset()+1,in));		
							}
						}
						if(move>=0){
							for(int j = move;j>=0;j--){
								Document tempDoc = new Document(deque.peekLast().getOffset()+1,temp.get(j).getText());
								deque.add(tempDoc);
							}
						}

					}else if(event.getLength()==1 && event.getText().length()>1){
//						System.out.println("Copy Paste event");
					}else{
//						System.out.println("uncatched delete");
					}
				}
				
				// deletion, copy paste, undo
			}else {	
				//insertion
//				System.out.println("Insert");
				if(event.getText().length()>1 && event.getText().length()!=3){
					// template or Redo Action
//					System.out.println("Insert 1");
					String x = event.getText();
					for(int i = 0;i<x.length();i++){
						String in = String.valueOf(x.charAt(i));
						deque.add(new Document(event.getOffset()+i,in));
//						System.out.println("top offset "+ deque2.peekLast().getOffset()+" top text "+deque2.peekLast().getText()+" event "+event.getOffset());
					}
				}else{
//					System.out.println("Insert 2");
					//	System.out.println("top offset "+ deque2.peekLast().getOffset()+" event "+event.getOffset());
					if(deque.isEmpty()){
						deque.add(new Document(event.getOffset(),event.getText()));
					}else if(event.getOffset()<=deque.peekLast().getOffset()){

//						System.out.println("Insert 2 1");
						// 						Autocomplete or undo action 
//						System.out.println("top offset "+ deque2.peekLast().getOffset()+" top text "+deque2.peekLast().getText()+" event "+event.getOffset()+" event text "+event.getText());
						List<Document> temp = new ArrayList<>();
						int move = deque.peekLast().getOffset() - event.getOffset();
						for(int i=0;i<=move;i++){
							temp.add(deque.removeLast());
						}
						String x = event.getText();
						for(int i = 0;i<x.length();i++){
							String in = String.valueOf(x.charAt(i));
							deque.add(new Document(event.getOffset()+i,in));
//							System.out.println("top offset "+ deque2.peekLast().getOffset()+" top text "+deque2.peekLast().getText()+" event "+event.getOffset());
						}
//						deque2.add(new Document(event.getOffset(),event.getText()));
						for(int j = move;j>=0;j--){
							Document tempDoc = new Document(temp.get(j).getOffset()+1,temp.get(j).getText());
							deque.add(tempDoc);
						}
					}else {  
//						System.out.println("Insert 2 2");
						String x = event.getText();
						for(int i = 0;i<x.length();i++){
							String in = String.valueOf(x.charAt(i));
							deque.add(new Document(event.getOffset()+i,in));
//							System.out.println("top offset "+ deque2.peekLast().getOffset()+" top text "+deque2.peekLast().getText()+" event "+event.getOffset());
						}
					}
				}
			}
			if(deque.peekLast().getText().contains(";")||deque.peekLast().getText().contains("}")){
				// detecting end of statement
				StringBuilder builder = new StringBuilder();
				while(deque.size()>0){
					String str = deque.pop().getText();
					builder.append(str);
				}
//				System.out.println("Deque2 "+builder.toString());
				myFile.classifying(builder.toString());
			}

		}
	};


	protected IPartListener2 generateIPartListener2() {
		return newListener;
	}

	IPartListener2 newListener = new IPartListener2(){
		private void checkPart(IWorkbenchPartReference partRef){
			IWorkbenchPart part = partRef.getPart(false);
			if(part instanceof IEditorPart){
				IEditorPart editor = (IEditorPart) part;
				title = editor.getTitle();
				IEditorInput input = editor.getEditorInput();
				IFile file = ((IFileEditorInput) input).getFile();
				IProject activeProject = file.getProject();
				String activeProjectName = activeProject.getName();
				myFile.createFile(activeProjectName+"\\"+title);
//				System.out.println(activeProjectName+" activated");
				if(editor instanceof ITextEditor && input instanceof FileEditorInput){
					IDocument document = (((ITextEditor)editor).getDocumentProvider()).getDocument(input);
					document.addDocumentListener(docX);
				}
			}
		}

		@Override
		public void partActivated(IWorkbenchPartReference partRef) {
			// TODO Auto-generated method stub
			checkPart(partRef);
		}

		@Override
		public void partBroughtToTop(IWorkbenchPartReference partRef) {
			// TODO Auto-generated method stub
			checkPart(partRef);
		}

		@Override
		public void partClosed(IWorkbenchPartReference partRef) {
			// TODO Auto-generated method stub

		}

		@Override
		public void partDeactivated(IWorkbenchPartReference partRef) {
			// TODO Auto-generated method stub

		}

		@Override
		public void partOpened(IWorkbenchPartReference partRef) {
			// TODO Auto-generated method stub
			checkPart(partRef);
		}

		@Override
		public void partHidden(IWorkbenchPartReference partRef) {
			// TODO Auto-generated method stub

		}

		@Override
		public void partVisible(IWorkbenchPartReference partRef) {
			// TODO Auto-generated method stub

		}

		@Override
		public void partInputChanged(IWorkbenchPartReference partRef) {
			// TODO Auto-generated method stub

		}

	};
}








