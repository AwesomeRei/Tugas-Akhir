package com.jaeger.tugasakhir.file;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.jface.text.DocumentEvent;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import com.jaeger.tugasakhir.Classification.Classification;

public class WriteFile {
	private String pathRaw;
	private String pathActivities;
	private int sequenceNum;
	private int sequenceNumRaw;
	private String directory;

	public WriteFile(){
		this.sequenceNum =this.setSequenceNumRaw(0);
		IWorkspace workspace = ResourcesPlugin.getWorkspace();
		File workspaceDirectory = workspace.getRoot().getLocation().toFile();
		this.directory = workspaceDirectory+"";
	}
	
	protected int getSequence(){
		return this.sequenceNum;
	}

	public void createFile(String x){
//		System.out.println(x);
		this.pathRaw=directory+"\\"+x+"-raw.xml";
		this.pathActivities=directory+"\\"+x+"-activities.xml";
//		System.out.println(pathRaw);
//		System.out.println(pathActivities);
		File f = new File(pathRaw);
		File c = new File(pathActivities);
		if(!f.exists() || f.isDirectory() || !c.exists() || c.isDirectory()) { 
			// do something
			createXML(pathRaw);
			createXML(pathActivities);
		}
	}

	public void classifying(String string) {
//		this.sequenceNum++;
		String classified = null;
		Classification main = new Classification();
		String keyword[] = {
				"abstract","continue","for","new","switch"
				,"assert","default","package","synchronized"
				,"boolean","do","if","private"
				,"break","double","implements","protected","throw"
				,"byte","else",	"import","public","throws"
				,"case","enum",	"instanceof","return","transient"
				,"catch","extends","int","short","try"
				,"char","final","interface","static","void"
				,"class","finally","long","strictfp","volatile"
				,"const","float","native","super","while","String"};
		List<String> keywords = Arrays.asList(keyword); 
		String testSplit[] = string.split(" ");
		List<String> testSplit2 = Arrays.asList(testSplit);
		//System.out.println(testSplit2);
		for(String currString: testSplit){
			System.out.println(currString);
			if(currString.contains("if(")){
				// if block
				classified = "if";
//				System.out.println(classified);
				break;
			}else if(currString.equals("switch")||currString.contains("switch(")){
				//switch block
				classified = "switch";
//				System.out.println(classified);
				break;
			}else if(currString.equals("for")||currString.contains("for(")){
				// for block
				classified = "for";
//				System.out.println(classified);
				break;
			}else if(currString.equals("try")|| currString.contains("try{")){
				// try block
				classified = "try";
//				System.out.println(classified);
				break;
			}else if(currString.contains("case")|| currString.equals("case")){
				// case block
				classified = "case";
//				System.out.println(classified);
				break;
			}else if(currString.contains("else{")|| currString.equals("else")){
				// else block
				classified = "else";
//				System.out.println(classified);
				break;
			}else if(currString.contains("while(")){
				classified = "while";
//				System.out.println(classified);
				break;
			}else if(currString.contains("do{")){
				classified = "do";
//				System.out.println(classified);
				break;
			}else if(currString.contains("break;")){
				//break block
				classified = "break";
//				System.out.println(classified);
				break;
			}else if(currString.equals("return")){ 	
				classified = "return";
//				System.out.println(classified);
				break;
			}else{
				currString = currString.replaceAll("\\s+", "");
				if(keywords.contains(currString)){
					main.evKeyword();
					if(currString.equals("class")|| currString.equals("enum")){
						main.evClass();
						if(currString.equals("enum")){
							classified = "enum";	
						}
					}
					if(currString.equals("new")){
						main.evNew();
						main.state();	
					}
					if(currString.equals("extends")){
						main.evExtends();
						main.state();
					}
					if(currString.equals("implements")){
						main.evImplements();
						main.state();
					}
					if(currString.equals("import")){
						classified = "import";
					}
				}else if(currString.contains("=")){
					main.evEquals();
				}else{
					main.evUnknown();
					main.state();
					if(currString.contains("(")){
						main.evBrackets();
						main.state();
					}
					if(currString.contains(";")){
						main.evSemicolon();
					}
					if(currString.contains("{")){
						main.evCurlyBrackets();
					}
				}
			}
			main.state();
		}
		if(classified!=null){
			try {
				this.writeXMLActivities(classified, string);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		 else if(main.getCurrentState().equals("Final_State")){
			String word = main.calculation();
			try {
				this.writeXMLActivities(word, string);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public String getName(String name){
		String title = name.split("\\.")[0];
		return title;
	}

	public void writeXMLActivities(String typeContent,String textContent) throws IOException{
		this.sequenceNum++;
		String textContentInside = textContent.replaceAll("&#13;", " ");
		try {
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(this.pathActivities);
			Node activities = doc.getFirstChild();
			Element activity = doc.createElement("activity");
			Attr attrType = doc.createAttribute("type");
			Attr attrSequence = doc.createAttribute("sequence");
			attrSequence.setValue(String.valueOf(this.getSequence()));
			attrType.setValue(typeContent);
			activity.setAttributeNode(attrSequence);
			activity.setAttributeNode(attrType);
			Element text = doc.createElement("text");
			text.appendChild(doc.createTextNode(textContentInside));
			activity.appendChild(text);
			activities.appendChild(activity);
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = null;
			result = new StreamResult(new File(this.pathActivities));
			transformer.transform(source, result);

		} catch (SAXException | ParserConfigurationException | TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public void writeXMLRaw(DocumentEvent event) throws IOException{
		this.setSequenceNumRaw(this.getSequenceNumRaw() + 1);
		try {
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(this.pathRaw);
			Node activities = doc.getFirstChild();
			Element raw = doc.createElement("raw");
			Attr attrSequence = doc.createAttribute("sequence");
			attrSequence.setValue(String.valueOf(this.getSequenceNumRaw()));
			Attr attrOffset = doc.createAttribute("offset");
			attrOffset.setValue(String.valueOf(event.getOffset()));
			Attr attrLength = doc.createAttribute("length");
			attrLength.setValue(String.valueOf(event.getLength()));
			raw.setAttributeNode(attrSequence);
			raw.setAttributeNode(attrOffset);
			raw.setAttributeNode(attrLength);
			Element text = doc.createElement("text");
			text.appendChild(doc.createTextNode(event.getText()));
			raw.appendChild(text);
			activities.appendChild(raw);	
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = null;
			result = new StreamResult(new File(this.pathRaw));	
			transformer.transform(source, result);
		} catch (SAXException | ParserConfigurationException | TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void createXML(String x){
		try {
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.newDocument();
			Element rootElement = doc.createElement("activities");
			doc.appendChild(rootElement);
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(x);
			transformer.transform(source, result);		
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public int getSequenceNumRaw() {
		return sequenceNumRaw;
	}

	public int setSequenceNumRaw(int sequenceNumRaw) {
		this.sequenceNumRaw = sequenceNumRaw;
		return sequenceNumRaw;
	}	
}




