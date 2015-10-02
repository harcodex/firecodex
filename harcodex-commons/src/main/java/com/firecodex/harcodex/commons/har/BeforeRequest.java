package com.firecodex.harcodex.commons.har;

import java.util.Date;

public class BeforeRequest {
	private Date expires;
	private Date lastAccess;
	private String eTag;
	private Long hitCount;
	private String comment;
	
	public Date getExpires() {
		return expires;
	}
	public void setExpires(Date expires) {
		this.expires = expires;
	}
	public Date getLastAccess() {
		return lastAccess;
	}
	public void setLastAccess(Date lastAccess) {
		this.lastAccess = lastAccess;
	}
	public String geteTag() {
		return eTag;
	}
	public void seteTag(String eTag) {
		this.eTag = eTag;
	}
	public Long getHitCount() {
		return hitCount;
	}
	public void setHitCount(Long hitCount) {
		this.hitCount = hitCount;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	@Override
	public String toString() {
		return "BeforeRequest [expires=" + expires + ", lastAccess="
				+ lastAccess + ", eTag=" + eTag + ", hitCount=" + hitCount
				+ ", comment=" + comment + "]";
	}
	
}
