package com.firecodex.harcodex.commons.har;

import java.util.List;

public class Request {
	private String method;
	private String url;
	private String httpVersion;
	private List<Cookie> cookies;
	private List<Header> headers;
	private List<QueryParameter> queryString;
	private PostData postData;
	private Long headersSize;
	private Long bodySize;
	private String comment;
	
	public Request() {}
	public Request(String method, String url, String httpVersion,
			List<Cookie> cookies, List<Header> headers,
			List<QueryParameter> queryString) {
		this.method = method;
		this.url = url;
		this.httpVersion = httpVersion;
		this.cookies = cookies;
		this.headers = headers;
		this.queryString = queryString;
	}
	public Request(String method, String url, String httpVersion,
			List<Cookie> cookies, List<Header> headers,
			List<QueryParameter> queryString, Long headersSize, Long bodySize) {
		this.method = method;
		this.url = url;
		this.httpVersion = httpVersion;
		this.cookies = cookies;
		this.headers = headers;
		this.queryString = queryString;
		this.headersSize = headersSize;
		this.bodySize = bodySize;
	}
	public Request(String method, String url, String httpVersion,
			List<Cookie> cookies, List<Header> headers,
			List<QueryParameter> queryString, PostData postData,
			Long headersSize, Long bodySize) {
		this.method = method;
		this.url = url;
		this.httpVersion = httpVersion;
		this.cookies = cookies;
		this.headers = headers;
		this.queryString = queryString;
		this.postData = postData;
		this.headersSize = headersSize;
		this.bodySize = bodySize;
	}
	public Request(String method, String url, String httpVersion,
			List<Cookie> cookies, List<Header> headers,
			List<QueryParameter> queryString, PostData postData,
			Long headersSize, Long bodySize, String comment) {
		this.method = method;
		this.url = url;
		this.httpVersion = httpVersion;
		this.cookies = cookies;
		this.headers = headers;
		this.queryString = queryString;
		this.postData = postData;
		this.headersSize = headersSize;
		this.bodySize = bodySize;
		this.comment = comment;
	}

	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getHttpVersion() {
		return httpVersion;
	}
	public void setHttpVersion(String httpVersion) {
		this.httpVersion = httpVersion;
	}
	public List<Cookie> getCookies() {
		return cookies;
	}
	public void setCookies(List<Cookie> cookies) {
		this.cookies = cookies;
	}
	public List<Header> getHeaders() {
		return headers;
	}
	public void setHeaders(List<Header> headers) {
		this.headers = headers;
	}
	public List<QueryParameter> getQueryString() {
		return queryString;
	}
	public void setQueryString(List<QueryParameter> queryString) {
		this.queryString = queryString;
	}
	public PostData getPostData() {
		return postData;
	}
	public void setPostData(PostData postData) {
		this.postData = postData;
	}
	public Long getHeadersSize() {
		return headersSize;
	}
	public void setHeadersSize(Long headersSize) {
		this.headersSize = headersSize;
	}
	public Long getBodySize() {
		return bodySize;
	}
	public void setBodySize(Long bodySize) {
		this.bodySize = bodySize;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	@Override
	public String toString() {
		return "Request [method=" + method + ", url=" + url + ", httpVersion="
				+ httpVersion + ", cookies=" + cookies + ", headers=" + headers
				+ ", queryString=" + queryString + ", postData=" + postData
				+ ", headersSize=" + headersSize + ", bodySize=" + bodySize
				+ ", comment=" + comment + "]";
	}
	
}
