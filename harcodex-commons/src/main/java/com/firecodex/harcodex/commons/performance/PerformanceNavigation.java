package com.firecodex.harcodex.commons.performance;

public class PerformanceNavigation {
	private Integer TYPE_NAVIGATE;
	private Integer TYPE_RELOAD;
	private Integer TYPE_BACK_FORWARD;
	private Integer TYPE_RESERVED;
	private Integer type;
	private Integer redirectCount;
	
	public Integer getTYPE_NAVIGATE() {
		return TYPE_NAVIGATE;
	}
	public void setTYPE_NAVIGATE(Integer tYPE_NAVIGATE) {
		TYPE_NAVIGATE = tYPE_NAVIGATE;
	}
	public Integer getTYPE_RELOAD() {
		return TYPE_RELOAD;
	}
	public void setTYPE_RELOAD(Integer tYPE_RELOAD) {
		TYPE_RELOAD = tYPE_RELOAD;
	}
	public Integer getTYPE_BACK_FORWARD() {
		return TYPE_BACK_FORWARD;
	}
	public void setTYPE_BACK_FORWARD(Integer tYPE_BACK_FORWARD) {
		TYPE_BACK_FORWARD = tYPE_BACK_FORWARD;
	}
	public Integer getTYPE_RESERVED() {
		return TYPE_RESERVED;
	}
	public void setTYPE_RESERVED(Integer tYPE_RESERVED) {
		TYPE_RESERVED = tYPE_RESERVED;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getRedirectCount() {
		return redirectCount;
	}
	public void setRedirectCount(Integer redirectCount) {
		this.redirectCount = redirectCount;
	}
	
}
