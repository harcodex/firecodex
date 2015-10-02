package com.firecodex.harcodex.commons.har;

import java.util.Date;

public class Page {
	private String id;
	private Date startedDateTime;
	private String title;
	private PageTimings pageTimings;
	private String comment;
	
	public Page() {
	}
	public Page(String id, Date startedDateTime, String title,
			PageTimings pageTimings) {
		this.id = id;
		this.startedDateTime = startedDateTime;
		this.title = title;
		this.pageTimings = pageTimings;
	}
	public Page(String id, Date startedDateTime, String title,
			PageTimings pageTimings, String comment) {
		this.id = id;
		this.startedDateTime = startedDateTime;
		this.title = title;
		this.pageTimings = pageTimings;
		this.comment = comment;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Date getStartedDateTime() {
		return startedDateTime;
	}
	public void setStartedDateTime(Date startedDateTime) {
		this.startedDateTime = startedDateTime;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public PageTimings getPageTimings() {
		return pageTimings;
	}
	public void setPageTimings(PageTimings pageTimings) {
		this.pageTimings = pageTimings;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	@Override
	public String toString() {
		return "Page [id=" + id + ", startedDateTime=" + startedDateTime
				+ ", title=" + title + ", pageTimings=" + pageTimings
				+ ", comment=" + comment + "]";
	}
	
}
