package com.firecodex.harcodex.commons.har;

public class PostedParameter {
	private String name;
	private String value;
	private String fileName;
	private String contentType;
	private String comment;
	
	public PostedParameter() {}
	public PostedParameter(String name, String value) {
		this.name = name;
		this.value = value;
	}
	public PostedParameter(String name, String value, String fileName,
			String contentType, String comment) {
		this.name = name;
		this.value = value;
		this.fileName = fileName;
		this.contentType = contentType;
		this.comment = comment;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	@Override
	public String toString() {
		return "PostedParameter [name=" + name + ", value=" + value
				+ ", fileName=" + fileName + ", contentType=" + contentType
				+ ", comment=" + comment + "]";
	}
	
}
