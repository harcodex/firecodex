package com.firecodex.harcodex.commons.har;

import java.util.List;

public class Log{
	private String version;
	private Creator creator;
	private Browser browser;
	private List<Page> pages;
	private List<Entry> entries;
	private String comment;
	
	public Log() {}
	public Log(String version, Creator creator) {
		this.version = version;
		this.creator = creator;
	}
	public Log(String version, Creator creator, Browser browser) {
		this.version = version;
		this.creator = creator;
		this.browser = browser;
	}
	public Log(String version, Creator creator, Browser browser,
			List<Page> pages, List<Entry> entries) {
		this.version = version;
		this.creator = creator;
		this.browser = browser;
		this.pages = pages;
		this.entries = entries;
	}
	public Log(String version, Creator creator, Browser browser,
			List<Page> pages, List<Entry> entries, String comment) {
		this.version = version;
		this.creator = creator;
		this.browser = browser;
		this.pages = pages;
		this.entries = entries;
		this.comment = comment;
	}
	
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public Creator getCreator() {
		return creator;
	}
	public void setCreator(Creator creator) {
		this.creator = creator;
	}
	public Browser getBrowser() {
		return browser;
	}
	public void setBrowser(Browser browser) {
		this.browser = browser;
	}
	public List<Page> getPages() {
		return pages;
	}
	public void setPages(List<Page> pages) {
		this.pages = pages;
	}
	public List<Entry> getEntries() {
		return entries;
	}
	public void setEntries(List<Entry> entries) {
		this.entries = entries;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	@Override
	public String toString() {
		return "Log [version=" + version + ", creator=" + creator
				+ ", browser=" + browser + ", pages=" + pages + ", entries="
				+ entries + ", comment=" + comment + "]";
	}
	
}
