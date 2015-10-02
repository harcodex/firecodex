package com.firecodex.harcodex.api.model;

import java.util.List;

import com.firecodex.harcodex.commons.har.Log;

public class Harcodex {
	private String id;
	private String label;
	private String url;
	private Log log;
	private PagespeedScore pagespeedScore;
	private List<Score> yslowScores;
	
	public Harcodex() {}
	public Harcodex(Log log, String url) {
		try{this.label = log.getPages().get(0).getId();}catch(Exception e){}
		this.url = url;
		this.log = log;
	}
	public Harcodex(String id, String label, String url, Log log) {
		this.id = id;
		this.label = label;
		this.url = url;
		this.log = log;
	}
	public Harcodex(Log log, String url, PagespeedScore pagespeedScore, List<Score> yslowScores) {
		try{this.label = log.getPages().get(0).getId();}catch(Exception e){}
		this.url = url;
		this.log = log;
		this.pagespeedScore = pagespeedScore;
		this.yslowScores = yslowScores;
	}
	public Harcodex(String id, String label, String url, Log log, PagespeedScore pagespeedScore, List<Score> yslowScores) {
		this.id = id;
		this.label = label;
		this.url = url;
		this.log = log;
		this.pagespeedScore = pagespeedScore;
		this.yslowScores = yslowScores;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public Log getLog() {
		return log;
	}
	public void setLog(Log log) {
		this.log = log;
	}
	public PagespeedScore getPagespeedScore() {
		return pagespeedScore;
	}
	public void setPagespeedScore(PagespeedScore pagespeedScore) {
		this.pagespeedScore = pagespeedScore;
	}
	public List<Score> getYslowScores() {
		return yslowScores;
	}
	public void setYslowScores(List<Score> yslowScores) {
		this.yslowScores = yslowScores;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	@Override
	public String toString() {
		return "Harcodex [id=" + id + ", label=" + label + ", url=" + url + ", log=" + log + ", pagespeedScore="
				+ pagespeedScore + ", yslowScores=" + yslowScores + "]";
	}
	
}
