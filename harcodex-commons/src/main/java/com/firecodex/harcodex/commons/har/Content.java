package com.firecodex.harcodex.commons.har;

public class Content {
	private Long size;
	private Long compression;
	private String mimeType;
	private String text;
	private String comment;
	
	public Content() {}
	public Content(Long size, String mimeType) {
		this.size = size;
		this.mimeType = mimeType;
	}
	public Content(Long size, Long compression, String mimeType, String text,
			String comment) {
		this.size = size;
		this.compression = compression;
		this.mimeType = mimeType;
		this.text = text;
		this.comment = comment;
	}

	public Long getSize() {
		return size;
	}
	public void setSize(Long size) {
		this.size = size;
	}
	public Long getCompression() {
		return compression;
	}
	public void setCompression(Long compression) {
		this.compression = compression;
	}
	public String getMimeType() {
		return mimeType;
	}
	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	@Override
	public String toString() {
		return "Content [size=" + size + ", compression=" + compression
				+ ", mimeType=" + mimeType + ", text=" + text + ", comment="
				+ comment + "]";
	}
	
}
