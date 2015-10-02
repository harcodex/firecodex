package com.firecodex.harcodex.commons.har;

public class QueryParameter {
	private String name;
	private String value;
	private String comment;
	
	public QueryParameter() {}
	public QueryParameter(String name, String value) {
		this.name = name;
		this.value = value;
	}
	public QueryParameter(String name, String value, String comment) {
		this.name = name;
		this.value = value;
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
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	@Override
	public String toString() {
		return "QueryParameter [name=" + name + ", value=" + value
				+ ", comment=" + comment + "]";
	}

}
