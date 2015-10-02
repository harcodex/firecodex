package com.firecodex.harcodex.commons.har;

import java.util.List;

public class Response {
	private Integer status;
	private String statusText;
	private String httpVersion;
	private List<Cookie> cookies;
	private List<Header> headers;
	private Content content;
	private String redirectURL;
	private Long headersSize;
	private Long bodySize;
	private String comment;
	
	public Response() {}
	public Response(Integer status, String statusText, String httpVersion,
			List<Cookie> cookies, List<Header> headers, Content content,
			String redirectURL, Long headersSize, Long bodySize) {
		this.status = status;
		this.statusText = statusText;
		this.httpVersion = httpVersion;
		this.cookies = cookies;
		this.headers = headers;
		this.content = content;
		this.redirectURL = redirectURL;
		this.headersSize = headersSize;
		this.bodySize = bodySize;
	}
	public Response(Integer status, String statusText, String httpVersion,
			List<Cookie> cookies, List<Header> headers, Content content,
			String redirectURL, Long headersSize, Long bodySize, String comment) {
		this.status = status;
		this.statusText = statusText;
		this.httpVersion = httpVersion;
		this.cookies = cookies;
		this.headers = headers;
		this.content = content;
		this.redirectURL = redirectURL;
		this.headersSize = headersSize;
		this.bodySize = bodySize;
		this.comment = comment;
	}

	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getStatusText() {
		return statusText;
	}
	public void setStatusText(String statusText) {
		this.statusText = statusText;
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
	public Content getContent() {
		return content;
	}
	public void setContent(Content content) {
		this.content = content;
	}
	public String getRedirectURL() {
		return redirectURL;
	}
	public void setRedirectURL(String redirectURL) {
		this.redirectURL = redirectURL;
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
		return "Response [status=" + status + ", statusText=" + statusText
				+ ", httpVersion=" + httpVersion + ", cookies=" + cookies
				+ ", headers=" + headers + ", content=" + content
				+ ", redirectURL=" + redirectURL + ", headersSize="
				+ headersSize + ", bodySize=" + bodySize + ", comment="
				+ comment + "]";
	}
	
}
