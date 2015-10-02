package com.firecodex.harcodex.api.model;

import java.util.List;
import java.util.Map;

public class PagespeedScore {
	private List<Score> scores;
	private Map<String, Long> stats;
	
	public PagespeedScore() {}
	public PagespeedScore(List<Score> scores, Map<String, Long> stats) {
		this.scores = scores;
		this.stats = stats;
	}

	public List<Score> getScores() {
		return scores;
	}
	public void setScores(List<Score> scores) {
		this.scores = scores;
	}
	public Map<String, Long> getStats() {
		return stats;
	}
	public void setStats(Map<String, Long> stats) {
		this.stats = stats;
	}
	
	@Override
	public String toString() {
		return "PagespeedScore [scores=" + scores + ", stats=" + stats + "]";
	}
	
}
