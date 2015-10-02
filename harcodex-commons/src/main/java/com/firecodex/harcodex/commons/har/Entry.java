package com.firecodex.harcodex.commons.har;

import java.util.Date;

public class Entry {
	private String pageref;
	private Date startedDateTime;
	private Double time;
	private Request request;
	private Response response;
	private Cache cache;
	private Timings timings;
	private String serverIPAddress;
	private String connection;
	private String comment;
	
	public Entry() {
	}
	public Entry(String pageref, Date startedDateTime, Double time) {
		this.pageref = pageref;
		this.startedDateTime = startedDateTime;
		this.time = time;
	}
	public Entry(String pageref, Date startedDateTime, Double time,
			Request request, Response response) {
		this.pageref = pageref;
		this.startedDateTime = startedDateTime;
		this.time = time;
		this.request = request;
		this.response = response;
	}
	public Entry(String pageref, Date startedDateTime, Double time,
			Request request, Response response, Cache cache, Timings timings) {
		this.pageref = pageref;
		this.startedDateTime = startedDateTime;
		this.time = time;
		this.request = request;
		this.response = response;
		this.cache = cache;
		this.timings = timings;
	}
	public Entry(String pageref, Date startedDateTime, Double time,
			Request request, Response response, Cache cache, Timings timings,
			String serverIPAddress, String connection) {
		this.pageref = pageref;
		this.startedDateTime = startedDateTime;
		this.time = time;
		this.request = request;
		this.response = response;
		this.cache = cache;
		this.timings = timings;
		this.serverIPAddress = serverIPAddress;
		this.connection = connection;
	}
	public Entry(String pageref, Date startedDateTime, Double time,
			Request request, Response response, Cache cache, Timings timings, String connection) {
		this.pageref = pageref;
		this.startedDateTime = startedDateTime;
		this.time = time;
		this.request = request;
		this.response = response;
		this.cache = cache;
		this.timings = timings;
		this.connection = connection;
	}
	public Entry(String pageref, Date startedDateTime, Double time,
			Request request, Response response, Cache cache, Timings timings,
			String serverIPAddress, String connection, String comment) {
		this.pageref = pageref;
		this.startedDateTime = startedDateTime;
		this.time = time;
		this.request = request;
		this.response = response;
		this.cache = cache;
		this.timings = timings;
		this.serverIPAddress = serverIPAddress;
		this.connection = connection;
		this.comment = comment;
	}
	
	public String getPageref() {
		return pageref;
	}
	public void setPageref(String pageref) {
		this.pageref = pageref;
	}
	public Date getStartedDateTime() {
		return startedDateTime;
	}
	public void setStartedDateTime(Date startedDateTime) {
		this.startedDateTime = startedDateTime;
	}
	public Double getTime() {
		return time;
	}
	public void setTime(Double time) {
		this.time = time;
	}
	public Request getRequest() {
		return request;
	}
	public void setRequest(Request request) {
		this.request = request;
	}
	public Response getResponse() {
		return response;
	}
	public void setResponse(Response response) {
		this.response = response;
	}
	public Cache getCache() {
		return cache;
	}
	public void setCache(Cache cache) {
		this.cache = cache;
	}
	public Timings getTimings() {
		return timings;
	}
	public void setTimings(Timings timings) {
		this.timings = timings;
	}
	public String getServerIPAddress() {
		return serverIPAddress;
	}
	public void setServerIPAddress(String serverIPAddress) {
		this.serverIPAddress = serverIPAddress;
	}
	public String getConnection() {
		return connection;
	}
	public void setConnection(String connection) {
		this.connection = connection;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	@Override
	public String toString() {
		return "Entry [pageref=" + pageref + ", startedDateTime="
				+ startedDateTime + ", time=" + time + ", request=" + request
				+ ", response=" + response + ", cache=" + cache + ", timings="
				+ timings + ", serverIPAddress=" + serverIPAddress
				+ ", connection=" + connection + ", comment=" + comment + "]";
	}
	
}
