package com.firecodex.harcodex.commons.format;

public class PageId {
	private final String pageIdFormat = "%s|%s|%s|%s";
	
	private String scenario;
	private String site;
	private String env;
	private String page;
	
	public PageId() {
		this("%s", "%s", "%s", "%s");
	}
	public PageId(String formattedPageId) {
		String[] params = formattedPageId.split("\\|");
		if(params.length==4){
			this.scenario = params[0];
			this.site = params[1];
			this.env = params[2];
			this.page = params[3];
		}else{
			this.scenario = "%s";
			this.site = "%s";
			this.env = "%s";
			this.page = "%s";
		}
	}
	public PageId(String scenario, String site, String env) {
		this.scenario = scenario;
		this.site = site;
		this.env = env;
	}
	public PageId(String scenario, String site, String env, String page) {
		this.scenario = scenario;
		this.site = site;
		this.env = env;
		this.page = page;
	}
	
	public String getScenario() {
		return scenario;
	}
	public void setScenario(String scenario) {
		this.scenario = scenario;
	}
	public String getSite() {
		return site;
	}
	public void setSite(String site) {
		this.site = site;
	}
	public String getEnv() {
		return env;
	}
	public void setEnv(String env) {
		this.env = env;
	}
	public String getPage() {
		return page;
	}
	public void setPage(String page) {
		this.page = page;
	}
	
	@Override
	public String toString() {
		return String.format(pageIdFormat, scenario, site, env, page);
	}
}
