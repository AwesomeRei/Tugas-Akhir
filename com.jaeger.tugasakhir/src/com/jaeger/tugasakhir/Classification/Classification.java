package com.jaeger.tugasakhir.Classification;

public class Classification {

	private static final String KEYWORD_STATE = "Keyword_State";
	private static final String CLASS_STATE = "Class_State";
	private static final String NAME_IDENTIFIER_STATE = "Name_Identifier_State";
	private static final String EQUAL_STATE = "Equal_State";
	private static final String NEW_STATE = "New_State";
	private static final String BRACKET_STATE = "Bracket_State";
	private static final String IMPLEMENT_STATE = "Implements_State";
	private static final String EXTENDS_STATE = "Extends_State";
	private ClassificationAction action;
	private statemachineContext context;
	
	private boolean nameFlag;
	private boolean keywordFlag;
	private boolean classFlag;
	private boolean bracketFlag;
	private boolean curlyBracketFlag;
	private boolean semicolonFlag;
	private boolean equalsFlag;
	private boolean newFlag;
	private boolean extendsFlag;
	private boolean implementsFlag;
	
	public Classification() {
		// TODO Auto-generated constructor stub
		this.action = new ClassificationAction();
		this.context = new statemachineContext(this.action);
	}
	
	public void evKeyword(){
		this.context.evKeyword();
//		this.keywordFlag = true;
	}
	public void evClass(){
		this.context.evClass();
//		this.classFlag = true;
		
	}
	public void evUnknown(){
		this.context.evUnknown();
//		this.nameFlag = true;
	}
	public void evBrackets(){
		this.context.evBrackets();
//		this.bracketFlag = true;
	}
	public void evCurlyBrackets(){
		this.context.evCurlyBrackets();
		this.curlyBracketFlag = true;
	}
	public void evSemicolon(){
		this.context.evSemicolon();
		this.semicolonFlag = true;
	}
	public void evExtends(){
		this.context.evExtends();
//		this.extendsFlag = true;
	}
	public void evImplements(){
		this.context.evImplements();
//		this.implementsFlag = true;
	}
	public void evEquals(){
		this.context.evEquals();
//		this.equalsFlag = true;
	}
	public void evNew(){
		this.context.evNew();
//		this.newFlag = true;
	}
	
	public String getCurrentState(){
		
		return this.context.getStateCurrent().getName();
	}
	
	public String calculation(){
		String code="";
		if(this.classFlag){
			// add an empty class class
			code = "AC";
			if(this.extendsFlag){
				code = code+"_EC";
			}
			if(this.implementsFlag){
				code = code+"_II";
			}
		}else if(this.implementsFlag) {
			// implements an interface
			code ="II";
		}else if(this.extendsFlag){
			// extends a class
			code = "EC";
		}else {
			if(this.equalsFlag){
				//initialize a variable
				code ="IV";
				if(this.newFlag){
					//instantiate an object
					code="IO";
				}
			}else {
				if(this.bracketFlag){
					if(this.curlyBracketFlag){
						//add an empty method
						code="AM";
					}else if(this.semicolonFlag){
						//call a method
						code="CM";
					}
				}else{
					// add variable
					if(this.semicolonFlag){
						code="AV";
					}
				}
			}
			
		}
		this.bracketFlag = this.implementsFlag = this.semicolonFlag = 
				this.newFlag = this.nameFlag = this.keywordFlag = this.extendsFlag = 
				this.equalsFlag = this.curlyBracketFlag = this.classFlag = false;
		
		return code;
	}
	
	public void getHistory(){
//		System.out.println(this.getHistory());
	}
	
	public void state(){
//		System.out.println(this.getCurrentState());
		if(this.getCurrentState().equals(KEYWORD_STATE)){
			this.keywordFlag = true;
//			System.out.println("keywordFlag");
		}
		else if(this.getCurrentState().equals(CLASS_STATE)){
			this.classFlag = true;
//			System.out.println("classFlag");
		}
		else if(this.getCurrentState().equals(NAME_IDENTIFIER_STATE)){
			this.nameFlag = true;
//			System.out.println("nameFlag");
		}
		else if(this.getCurrentState().equals(EQUAL_STATE)){
			this.equalsFlag = true;
//			System.out.println("equalFlag");
		}
		else if(this.getCurrentState().equals(NEW_STATE)){
			this.newFlag = true;
//			System.out.println("newFlag");
		}
		else if(this.getCurrentState().equals(BRACKET_STATE)){
			this.bracketFlag = true;
//			System.out.println("bracketFlag");
		}
		else if(this.getCurrentState().equals(IMPLEMENT_STATE)){
			this.implementsFlag = true;
//			System.out.println("implementFlag");
		}
		else if(this.getCurrentState().equals(EXTENDS_STATE)){
			this.extendsFlag =true;
//			System.out.println("extendFlag");
		}
		
	}	
}
