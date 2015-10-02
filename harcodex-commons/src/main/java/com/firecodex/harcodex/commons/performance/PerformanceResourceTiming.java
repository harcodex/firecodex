package com.firecodex.harcodex.commons.performance;

public class PerformanceResourceTiming {
	private String initiatorType;
	private String nextHopProtocol;
    private Double workerStart;
    private Double redirectStart;
    private Double redirectEnd;
    private Double fetchStart;
    private Double domainLookupStart;
    private Double domainLookupEnd;
    private Double connectStart;
    private Double connectEnd;
    private Double secureConnectionStart;
    private Double requestStart;
    private Double responseStart;
    private Double responseEnd;
    private Long transferSize;
    private Long encodedBodySize;
    private Long decodedBodySize;
    
    //Browser Specific Params
	private String name;
	private String entryType;
	private Double duration;
    
    
	public String getInitiatorType() {
		return initiatorType;
	}
	public void setInitiatorType(String initiatorType) {
		this.initiatorType = initiatorType;
	}
	public String getNextHopProtocol() {
		return nextHopProtocol;
	}
	public void setNextHopProtocol(String nextHopProtocol) {
		this.nextHopProtocol = nextHopProtocol;
	}
	public Double getWorkerStart() {
		return workerStart;
	}
	public void setWorkerStart(Double workerStart) {
		this.workerStart = workerStart;
	}
	public Double getRedirectStart() {
		return redirectStart;
	}
	public void setRedirectStart(Double redirectStart) {
		this.redirectStart = redirectStart;
	}
	public Double getRedirectEnd() {
		return redirectEnd;
	}
	public void setRedirectEnd(Double redirectEnd) {
		this.redirectEnd = redirectEnd;
	}
	public Double getFetchStart() {
		return fetchStart;
	}
	public void setFetchStart(Double fetchStart) {
		this.fetchStart = fetchStart;
	}
	public Double getDomainLookupStart() {
		return domainLookupStart;
	}
	public void setDomainLookupStart(Double domainLookupStart) {
		this.domainLookupStart = domainLookupStart;
	}
	public Double getDomainLookupEnd() {
		return domainLookupEnd;
	}
	public void setDomainLookupEnd(Double domainLookupEnd) {
		this.domainLookupEnd = domainLookupEnd;
	}
	public Double getConnectStart() {
		return connectStart;
	}
	public void setConnectStart(Double connectStart) {
		this.connectStart = connectStart;
	}
	public Double getConnectEnd() {
		return connectEnd;
	}
	public void setConnectEnd(Double connectEnd) {
		this.connectEnd = connectEnd;
	}
	public Double getSecureConnectionStart() {
		return secureConnectionStart;
	}
	public void setSecureConnectionStart(Double secureConnectionStart) {
		this.secureConnectionStart = secureConnectionStart;
	}
	public Double getRequestStart() {
		return requestStart;
	}
	public void setRequestStart(Double requestStart) {
		this.requestStart = requestStart;
	}
	public Double getResponseStart() {
		return responseStart;
	}
	public void setResponseStart(Double responseStart) {
		this.responseStart = responseStart;
	}
	public Double getResponseEnd() {
		return responseEnd;
	}
	public void setResponseEnd(Double responseEnd) {
		this.responseEnd = responseEnd;
	}
	public Long getTransferSize() {
		return transferSize;
	}
	public void setTransferSize(Long transferSize) {
		this.transferSize = transferSize;
	}
	public Long getEncodedBodySize() {
		return encodedBodySize;
	}
	public void setEncodedBodySize(Long encodedBodySize) {
		this.encodedBodySize = encodedBodySize;
	}
	public Long getDecodedBodySize() {
		return decodedBodySize;
	}
	public void setDecodedBodySize(Long decodedBodySize) {
		this.decodedBodySize = decodedBodySize;
	}
    
    //Browser Specific Params
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEntryType() {
		return entryType;
	}
	public void setEntryType(String entryType) {
		this.entryType = entryType;
	}
	public Double getDuration() {
		return duration;
	}
	public void setDuration(Double duration) {
		this.duration = duration;
	}
    
}
