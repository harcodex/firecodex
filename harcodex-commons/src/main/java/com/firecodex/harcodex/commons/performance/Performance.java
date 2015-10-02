package com.firecodex.harcodex.commons.performance;

import java.util.List;

public class Performance {

	/**
	 * Few reference for Performance Spec:
	 * http://www.w3.org/2010/webperf/
	 * http://www.w3.org/TR/navigation-timing/#sec-navigation-info-interface
	 * http://www.w3.org/TR/resource-timing/#resources-included
	 */
	
	private PerformanceNavigation navigation;
	private PerformanceTiming timing;
	private PerformanceMemory memory;
	private List<PerformanceResourceTiming> entries;
	private Double now;
	
	public PerformanceNavigation getNavigation() {
		return navigation;
	}
	public void setNavigation(PerformanceNavigation navigation) {
		this.navigation = navigation;
	}
	public PerformanceTiming getTiming() {
		return timing;
	}
	public void setTiming(PerformanceTiming timing) {
		this.timing = timing;
	}
	public PerformanceMemory getMemory() {
		return memory;
	}
	public void setMemory(PerformanceMemory memory) {
		this.memory = memory;
	}
	public List<PerformanceResourceTiming> getEntries() {
		return entries;
	}
	public void setEntries(List<PerformanceResourceTiming> entries) {
		this.entries = entries;
	}
	public Double getNow() {
		return now;
	}
	public void setNow(Double now) {
		this.now = now;
	}
	
	@Override
	public String toString() {
		return "Performance [navigation=" + navigation + ", timing=" + timing
				+ ", memory=" + memory + ", entries=" + entries + ", now="
				+ now + "]";
	}
	
}
