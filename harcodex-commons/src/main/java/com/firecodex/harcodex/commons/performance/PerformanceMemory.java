package com.firecodex.harcodex.commons.performance;

public class PerformanceMemory {
	private Long jsHeapSizeLimit;
	private Long usedJSHeapSize;
	private Long totalJSHeapSize;
	
	public Long getJsHeapSizeLimit() {
		return jsHeapSizeLimit;
	}
	public void setJsHeapSizeLimit(Long jsHeapSizeLimit) {
		this.jsHeapSizeLimit = jsHeapSizeLimit;
	}
	public Long getUsedJSHeapSize() {
		return usedJSHeapSize;
	}
	public void setUsedJSHeapSize(Long usedJSHeapSize) {
		this.usedJSHeapSize = usedJSHeapSize;
	}
	public Long getTotalJSHeapSize() {
		return totalJSHeapSize;
	}
	public void setTotalJSHeapSize(Long totalJSHeapSize) {
		this.totalJSHeapSize = totalJSHeapSize;
	}
	
}
