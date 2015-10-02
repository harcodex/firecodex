package com.firecodex.harcodex.api.model;

import java.util.Date;

public class Overview {
	private String environment;
	private String site;
	private String scenario;
	private String label;
	private String page;
	private Date timestamp;
	private Integer domains;
	private Integer requests;
	private Long totalSize;
	private Double loadTime;
	private Long count;
	
	public Overview() {}
	public Overview(String environment, String site, String scenario, String label, String page, Date timestamp,
			Integer domains, Integer requests, Long totalSize, Double loadTime, Long count) {
		this.environment = environment;
		this.site = site;
		this.scenario = scenario;
		this.label = label;
		this.page = page;
		this.timestamp = timestamp;
		this.domains = domains;
		this.requests = requests;
		this.totalSize = totalSize;
		this.loadTime = loadTime;
		this.count = count;
	}

	public String getEnvironment() {
		return environment;
	}
	public void setEnvironment(String environment) {
		this.environment = environment;
	}
	public String getSite() {
		return site;
	}
	public void setSite(String site) {
		this.site = site;
	}
	public String getScenario() {
		return scenario;
	}
	public void setScenario(String scenario) {
		this.scenario = scenario;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	public Integer getDomains() {
		return domains;
	}
	public void setDomains(Integer domains) {
		this.domains = domains;
	}
	public Integer getRequests() {
		return requests;
	}
	public void setRequests(Integer requests) {
		this.requests = requests;
	}
	public Long getTotalSize() {
		return totalSize;
	}
	public void setTotalSize(Long totalSize) {
		this.totalSize = totalSize;
	}
	public Double getLoadTime() {
		return loadTime;
	}
	public void setLoadTime(Double loadTime) {
		this.loadTime = loadTime;
	}
	public String getPage() {
		return page;
	}
	public void setPage(String page) {
		this.page = page;
	}
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
	}
	
}
