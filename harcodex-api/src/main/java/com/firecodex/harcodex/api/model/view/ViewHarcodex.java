package com.firecodex.harcodex.api.model.view;

import java.util.List;
import java.util.Map;

import com.firecodex.harcodex.api.model.Detail;
import com.firecodex.harcodex.api.model.Overview;
import com.firecodex.harcodex.api.model.PagespeedScore;
import com.firecodex.harcodex.api.model.Score;
import com.firecodex.harcodex.commons.har.Log;

public class ViewHarcodex {
	private String id;
	private Overview overview;
	private Log log;
	private Detail detail;
	private Map<String, Integer> domainRequests;
	private Map<String, Long> domainSizes;
	private Map<String, Integer> resourceRequests;
	private Map<String, Long> resourceSizes;
	private PagespeedScore pagespeedScore;
	private List<Score> yslowScores;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Overview getOverview() {
		return overview;
	}
	public void setOverview(Overview overview) {
		this.overview = overview;
	}
	public Log getLog() {
		return log;
	}
	public void setLog(Log log) {
		this.log = log;
	}
	public Detail getDetail() {
		return detail;
	}
	public void setDetail(Detail detail) {
		this.detail = detail;
	}
	public Map<String, Integer> getDomainRequests() {
		return domainRequests;
	}
	public void setDomainRequests(Map<String, Integer> domainRequests) {
		this.domainRequests = domainRequests;
	}
	public Map<String, Long> getDomainSizes() {
		return domainSizes;
	}
	public void setDomainSizes(Map<String, Long> domainSizes) {
		this.domainSizes = domainSizes;
	}
	public Map<String, Integer> getResourceRequests() {
		return resourceRequests;
	}
	public void setResourceRequests(Map<String, Integer> resourceRequests) {
		this.resourceRequests = resourceRequests;
	}
	public Map<String, Long> getResourceSizes() {
		return resourceSizes;
	}
	public void setResourceSizes(Map<String, Long> resourceSizes) {
		this.resourceSizes = resourceSizes;
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
	
	
}
