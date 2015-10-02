package com.firecodex.harcodex.commons.har;

public class Cache {
	private BeforeRequest beforeRequest;
	private AfterRequest afterRequest;
	private String comment;
	
	public Cache() {}
	public Cache(BeforeRequest beforeRequest, AfterRequest afterRequest) {
		this.beforeRequest = beforeRequest;
		this.afterRequest = afterRequest;
	}
	public Cache(BeforeRequest beforeRequest, AfterRequest afterRequest,
			String comment) {
		this.beforeRequest = beforeRequest;
		this.afterRequest = afterRequest;
		this.comment = comment;
	}

	public BeforeRequest getBeforeRequest() {
		return beforeRequest;
	}
	public void setBeforeRequest(BeforeRequest beforeRequest) {
		this.beforeRequest = beforeRequest;
	}
	public AfterRequest getAfterRequest() {
		return afterRequest;
	}
	public void setAfterRequest(AfterRequest afterRequest) {
		this.afterRequest = afterRequest;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	@Override
	public String toString() {
		return "Cache [beforeRequest=" + beforeRequest
				+ ", afterRequest=" + afterRequest + ", comment=" + comment
				+ "]";
	}
}
