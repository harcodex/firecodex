package com.firecodex.harcodex.api.model;

public class Detail {
	private Integer redirects;
	private Long renderStartTime;
	private Long textSize;
	private Long mediaSize;
	private Long cacheSize;
	private Long onloadEvent;
	private Long totalDnsTime;
	private Long totalSize;
	private Long badRequests;
	private Long totalServerTime;
	private Long avgConnectingTime;
	private Long avgBlockingTime;
	private Long timeToFirstByte;
	private Long totalTransferTime;
	
	public Detail() {}
	public Detail(Integer redirects, Long renderStartTime, Long textSize,
			Long mediaSize, Long cacheSize, Long onloadEvent,
			Long totalDnsTime, Long totalSize, Long badRequests,
			Long totalServerTime, Long avgConnectingTime, Long avgBlockingTime,
			Long timeToFirstByte, Long totalTransferTime) {
		this.redirects = redirects;
		this.renderStartTime = renderStartTime;
		this.textSize = textSize;
		this.mediaSize = mediaSize;
		this.cacheSize = cacheSize;
		this.onloadEvent = onloadEvent;
		this.totalDnsTime = totalDnsTime;
		this.totalSize = totalSize;
		this.badRequests = badRequests;
		this.totalServerTime = totalServerTime;
		this.avgConnectingTime = avgConnectingTime;
		this.avgBlockingTime = avgBlockingTime;
		this.timeToFirstByte = timeToFirstByte;
		this.totalTransferTime = totalTransferTime;
	}
	
	public Integer getRedirects() {
		return redirects;
	}
	public void setRedirects(Integer redirects) {
		this.redirects = redirects;
	}
	public Long getRenderStartTime() {
		return renderStartTime;
	}
	public void setRenderStartTime(Long renderStartTime) {
		this.renderStartTime = renderStartTime;
	}
	public Long getTextSize() {
		return textSize;
	}
	public void setTextSize(Long textSize) {
		this.textSize = textSize;
	}
	public Long getMediaSize() {
		return mediaSize;
	}
	public void setMediaSize(Long mediaSize) {
		this.mediaSize = mediaSize;
	}
	public Long getCacheSize() {
		return cacheSize;
	}
	public void setCacheSize(Long cacheSize) {
		this.cacheSize = cacheSize;
	}
	public Long getOnloadEvent() {
		return onloadEvent;
	}
	public void setOnloadEvent(Long onloadEvent) {
		this.onloadEvent = onloadEvent;
	}
	public Long getTotalDnsTime() {
		return totalDnsTime;
	}
	public void setTotalDnsTime(Long totalDnsTime) {
		this.totalDnsTime = totalDnsTime;
	}
	public Long getTotalSize() {
		return totalSize;
	}
	public void setTotalSize(Long totalSize) {
		this.totalSize = totalSize;
	}
	public Long getBadRequests() {
		return badRequests;
	}
	public void setBadRequests(Long badRequests) {
		this.badRequests = badRequests;
	}
	public Long getTotalServerTime() {
		return totalServerTime;
	}
	public void setTotalServerTime(Long totalServerTime) {
		this.totalServerTime = totalServerTime;
	}
	public Long getAvgConnectingTime() {
		return avgConnectingTime;
	}
	public void setAvgConnectingTime(Long avgConnectingTime) {
		this.avgConnectingTime = avgConnectingTime;
	}
	public Long getAvgBlockingTime() {
		return avgBlockingTime;
	}
	public void setAvgBlockingTime(Long avgBlockingTime) {
		this.avgBlockingTime = avgBlockingTime;
	}
	public Long getTimeToFirstByte() {
		return timeToFirstByte;
	}
	public void setTimeToFirstByte(Long timeToFirstByte) {
		this.timeToFirstByte = timeToFirstByte;
	}
	public Long getTotalTransferTime() {
		return totalTransferTime;
	}
	public void setTotalTransferTime(Long totalTransferTime) {
		this.totalTransferTime = totalTransferTime;
	}
	
}
