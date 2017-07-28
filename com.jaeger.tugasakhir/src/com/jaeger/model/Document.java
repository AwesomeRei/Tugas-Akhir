package com.jaeger.model;

public class Document {
	int offset;
	String text;
	
	
	public Document(int offset, String text) {
		super();
		this.offset = offset;
		this.text = text;
	}
	public int getOffset() {
		return offset;
	}
	public void setOffset(int offset) {
		this.offset = offset;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	
}
