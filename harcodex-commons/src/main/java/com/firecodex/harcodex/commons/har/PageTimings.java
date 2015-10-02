package com.firecodex.harcodex.commons.har;

public class PageTimings {
	private Double onContentLoad;
	private Double onLoad;
	private String comment;
	
	public PageTimings() {
	}
	public PageTimings(Double onContentLoad, Double onLoad) {
		this.onContentLoad = onContentLoad;
		this.onLoad = onLoad;
	}
	public PageTimings(Double onContentLoad, Double onLoad, String comment) {
		this.onContentLoad = onContentLoad;
		this.onLoad = onLoad;
		this.comment = comment;
	}
	
	public Double getOnContentLoad() {
		return onContentLoad;
	}
	public void setOnContentLoad(Double onContentLoad) {
		this.onContentLoad = onContentLoad;
	}
	public Double getOnLoad() {
		return onLoad;
	}
	public void setOnLoad(Double onLoad) {
		this.onLoad = onLoad;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	@Override
	public String toString() {
		return "PageTimings [onContentLoad=" + onContentLoad + ", onLoad="
				+ onLoad + ", comment=" + comment + "]";
	}
	
}
