package com.firecodex.harcodex.api.model;

import java.util.List;

public class Score {
	private String rule;
	private Integer score;
	private Float impact;
	private List<String> offenders;
	
	public Score() {}
	public Score(String rule, Integer score, Float impact, List<String> offenders) {
		this.rule = rule;
		this.score = score;
		this.impact = impact;
		this.offenders = offenders;
	}

	public String getRule() {
		return rule;
	}
	public void setRule(String rule) {
		this.rule = rule;
	}
	public Integer getScore() {
		return score;
	}
	public void setScore(Integer score) {
		this.score = score;
	}
	public Float getImpact() {
		return impact;
	}
	public void setImpact(Float impact) {
		this.impact = impact;
	}
	public List<String> getOffenders() {
		return offenders;
	}
	public void setOffenders(List<String> offenders) {
		this.offenders = offenders;
	}


	@Override
	public String toString() {
		return "Score [rule=" + rule + ", score=" + score + ", impact=" + impact + ", offenders=" + offenders + "]";
	}
	
}	
