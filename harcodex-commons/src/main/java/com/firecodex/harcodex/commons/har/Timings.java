package com.firecodex.harcodex.commons.har;

public class Timings {
	private Double blocked;
	private Double dns;
	private Double connect;
	private Double send;
	private Double wait;
	private Double receive;
	private Double ssl;
	private String comment;
	
	public Timings() {}
	public Timings(Double blocked, Double dns, Double connect, Double send,
			Double wait, Double receive, Double ssl, String comment) {
		this.blocked = blocked;
		this.dns = dns;
		this.connect = connect;
		this.send = send;
		this.wait = wait;
		this.receive = receive;
		this.ssl = ssl;
		this.comment = comment;
	}

	public Double getBlocked() {
		return blocked;
	}
	public void setBlocked(Double blocked) {
		this.blocked = blocked;
	}
	public Double getDns() {
		return dns;
	}
	public void setDns(Double dns) {
		this.dns = dns;
	}
	public Double getConnect() {
		return connect;
	}
	public void setConnect(Double connect) {
		this.connect = connect;
	}
	public Double getSend() {
		return send;
	}
	public void setSend(Double send) {
		this.send = send;
	}
	public Double getWait() {
		return wait;
	}
	public void setWait(Double wait) {
		this.wait = wait;
	}
	public Double getReceive() {
		return receive;
	}
	public void setReceive(Double receive) {
		this.receive = receive;
	}
	public Double getSsl() {
		return ssl;
	}
	public void setSsl(Double ssl) {
		this.ssl = ssl;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	@Override
	public String toString() {
		return "Timings [blocked=" + blocked + ", dns=" + dns + ", connect="
				+ connect + ", send=" + send + ", wait=" + wait + ", receive="
				+ receive + ", ssl=" + ssl + ", comment=" + comment + "]";
	}
	
}
