package com.firecodex.harcodex.commons.har;

import java.util.List;

public class PostData {
	private String mimeType;
	private List<PostedParameter> params;
	private String text;
	private String comment;
	
	public PostData() {}
	public PostData(String mimeType, List<PostedParameter> params, String text,
			String comment) {
		this.mimeType = mimeType;
		this.params = params;
		this.text = text;
		this.comment = comment;
	}

	public String getMimeType() {
		return mimeType;
	}
	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}
	public List<PostedParameter> getParams() {
		return params;
	}
	public void setParams(List<PostedParameter> params) {
		this.params = params;
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
		return "PostData [mimeType=" + mimeType + ", params=" + params
				+ ", text=" + text + ", comment=" + comment + "]";
	}
	
}
