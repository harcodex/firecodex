package com.firecodex.harcodex.commons.har;

public class Creator {
	private String name;
	private String version;
	private String comment;
	
	public Creator() {
	}
	public Creator(String name, String version) {
		this.name = name;
		this.version = version;
	}
	public Creator(String name, String version, String comment) {
		this.name = name;
		this.version = version;
		this.comment = comment;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	@Override
	public String toString() {
		return "Creator [name=" + name + ", version=" + version + ", comment="
				+ comment + "]";
	}
	
}
