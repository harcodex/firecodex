package com.firecodex.harcodex.listener;

import java.io.File;
import java.net.URI;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;

import com.firecodex.harcodex.commons.har.Browser;
import com.firecodex.harcodex.commons.har.Cache;
import com.firecodex.harcodex.commons.har.Content;
import com.firecodex.harcodex.commons.har.Creator;
import com.firecodex.harcodex.commons.har.Entry;
import com.firecodex.harcodex.commons.har.Header;
import com.firecodex.harcodex.commons.har.Log;
import com.firecodex.harcodex.commons.har.Page;
import com.firecodex.harcodex.commons.har.PageTimings;
import com.firecodex.harcodex.commons.har.PostData;
import com.firecodex.harcodex.commons.har.PostedParameter;
import com.firecodex.harcodex.commons.har.QueryParameter;
import com.firecodex.harcodex.commons.har.Request;
import com.firecodex.harcodex.commons.har.Response;
import com.firecodex.harcodex.commons.har.Timings;
import com.firecodex.harcodex.commons.performance.Performance;
import com.firecodex.harcodex.commons.performance.PerformanceMemory;
import com.firecodex.harcodex.commons.performance.PerformanceNavigation;
import com.firecodex.harcodex.commons.performance.PerformanceResourceTiming;
import com.firecodex.harcodex.commons.performance.PerformanceTiming;

public class HarcodexBuilder {
	/**
	 * It follows HAR 1.2 Spec
	 * http://www.softwareishard.com/blog/har-12-spec/
	 * https://dvcs.w3.org/hg/webperf/raw-file/tip/specs/HAR/Overview.html
	 * http://www.w3.org/TR/resource-timing/
	 */
	final static Logger logger = Logger.getLogger(HarcodexBuilder.class);
	
	private WebDriver driver;
	private Capabilities capabilities;
	private HarcodexScriptEngine executor;
	private Performance performance;
	private Log log;
	private List<Page> pages;
	private List<Entry> entries;
	private boolean buildable;
	private List<com.firecodex.harcodex.commons.har.Cookie> requestCookies;
	private List<com.firecodex.harcodex.commons.har.Cookie> responseCookies;
	
	public HarcodexBuilder(WebDriver driver){
		this.driver = driver;
		executor =new HarcodexScriptEngine(driver);
		setPerformance();
		log =new Log();
		log.setCreator(new Creator("Harcodex Listener", getVersion(), "Harcodex is a subsidiary of Firecodex"));
		log.setVersion(getVersion());
		log.setComment("This har log is created with the help of performance object provided by the browser");
		pages =new ArrayList<Page>();
		entries =new ArrayList<Entry>();
	}
	
	public HarcodexBuilder browser(Capabilities capabilities){
		this.capabilities = capabilities;
		log.setBrowser(new Browser(capabilities.getBrowserName(), capabilities.getVersion(), "This browser details are captured from web driver capabilities"));
		return this;
	}
	
	public HarcodexBuilder page(String id, Set<Cookie> requestCookies){
		this.requestCookies = getHarCookies(requestCookies);
		this.responseCookies = getHarCookies(driver.manage().getCookies());
		pages.add(new Page(id, new Date(performance.getTiming().getNavigationStart()), driver.getTitle(), getHarPageTimings()));
		return this;
	}
	
	public HarcodexBuilder entries(String id){
		setEntries(id);
		return this;
	}
	
	public HarcodexBuilder build(){
		log.setPages(pages);
		log.setEntries(entries);
		return this;
	}
	
	public boolean isBuildable(){
		return buildable;
	}
	public boolean isResourceable(){
		return performance.getEntries().size()>0;
	}
	
	public void save(String path) throws Exception{
		new ObjectMapper().writeValue(new File(path), log);
	}
	
	public void upload(String url) throws Exception{
		String path = System.getProperty("java.io.tmpdir").concat(File.separator).concat("har-to-be-deleted_").concat(new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss.SSS").format(new Date())).concat(".har");
		save(path);
		HttpClient client =new DefaultHttpClient();
		try{
			HttpPost post =new HttpPost(url);
			MultipartEntity entity =new MultipartEntity();
			entity.addPart("file", new FileBody(new File(path)));
//			System.out.println(new Gson().toJson(log));
//			StringEntity entity =new StringEntity(new Gson().toJson(log), "UTF-8");
//			ByteArrayEntity entity =new ByteArrayEntity(new Gson().toJson(log).getBytes());
//			entity.setContentType("application/json");
			post.setEntity(entity);
			HttpResponse response = client.execute(post);
			if(response.getStatusLine().getStatusCode()==200){
				logger.info("Har file is successfully uploaded: ".concat(log.getPages().get(0).getId()).concat(" > Status: ").concat(response.getStatusLine().toString()));
			}else{
				logger.error(log.getPages().get(0).getId().concat(" > Har upload failed: ").concat(response.getStatusLine().toString()));
			}
		}catch(Exception e){
			throw e;
		}finally{
			new File(path).delete();
			client.getConnectionManager().shutdown();
		}
	}
	
	private void setPerformance(){
		buildable = executor.executeScriptToBoolean(HarcodexScriptEngine.MEASURABLE_SCRIPT);
		performance =new Performance();
		performance.setNavigation(getPerformanceNavigation());
		performance.setTiming(getPerformanceTiming());
		performance.setMemory(getPerformanceMemory());
		performance.setEntries(getPerformanceResourceTimings());
		performance.setNow(executor.getNow());
	}
	
	public List<PerformanceResourceTiming> getPerformanceResourceTimings(){
		List<PerformanceResourceTiming> resources =new ArrayList<PerformanceResourceTiming>();
		try{
			for(Map<String, Object> entry: executor.getEntries()){
				PerformanceResourceTiming resource =new PerformanceResourceTiming();
				try{resource.setInitiatorType(String.valueOf(entry.get("initiatorType")));}catch(Exception e){resource.setInitiatorType("");}
				try{resource.setNextHopProtocol(String.valueOf(entry.get("nextHopProtocol")));}catch(Exception e){resource.setNextHopProtocol("");}
				try{resource.setWorkerStart(((Number)entry.get("workerStart")).doubleValue());}catch(Exception e){resource.setWorkerStart(-1D);}
				try{resource.setRedirectStart(((Number)entry.get("redirectStart")).doubleValue());}catch(Exception e){resource.setRedirectStart(-1D);}
				try{resource.setRedirectEnd(((Number)entry.get("redirectEnd")).doubleValue());}catch(Exception e){resource.setRedirectEnd(-1D);}
				try{resource.setFetchStart(((Number)entry.get("fetchStart")).doubleValue());}catch(Exception e){resource.setFetchStart(-1D);}
				try{resource.setDomainLookupStart(((Number)entry.get("domainLookupStart")).doubleValue());}catch(Exception e){resource.setDomainLookupStart(-1D);}
				try{resource.setDomainLookupEnd(((Number)entry.get("domainLookupEnd")).doubleValue());}catch(Exception e){resource.setDomainLookupEnd(-1D);}
				try{resource.setConnectStart(((Number)entry.get("connectStart")).doubleValue());}catch(Exception e){resource.setConnectStart(-1D);}
				try{resource.setConnectEnd(((Number)entry.get("connectEnd")).doubleValue());}catch(Exception e){resource.setConnectEnd(-1D);}
				try{resource.setSecureConnectionStart(((Number)entry.get("secureConnectionStart")).doubleValue());}catch(Exception e){resource.setSecureConnectionStart(-1D);}
				try{resource.setRequestStart(((Number)entry.get("requestStart")).doubleValue());}catch(Exception e){resource.setRequestStart(-1D);}
				try{resource.setResponseStart(((Number)entry.get("responseStart")).doubleValue());}catch(Exception e){resource.setResponseStart(-1D);}
				try{resource.setResponseEnd(((Number)entry.get("responseEnd")).doubleValue());}catch(Exception e){resource.setResponseEnd(-1D);}
				try{resource.setTransferSize(((Number)entry.get("transferSize")).longValue());}catch(Exception e){resource.setTransferSize(-1L);}
				try{resource.setEncodedBodySize(((Number)entry.get("encodedBodySize")).longValue());}catch(Exception e){resource.setEncodedBodySize(-1L);}
				try{resource.setDecodedBodySize(((Number)entry.get("decodedBodySize")).longValue());}catch(Exception e){resource.setDecodedBodySize(-1L);}
				//Browser Specific Params
				try{resource.setName(String.valueOf(entry.get("name")));}catch(Exception e){resource.setName("");}
				try{resource.setEntryType(String.valueOf(entry.get("entryType")));}catch(Exception e){resource.setEntryType("resource");}
				try{resource.setDuration(((Number)entry.get("duration")).doubleValue());}catch(Exception e){resource.setDuration(-1D);}
				resources.add(resource);
			}
		}catch(Exception ex){
			logger.error("No entries found: "+ex);
		}
		return resources;
	}
	private PerformanceMemory getPerformanceMemory(){
		PerformanceMemory memory =new PerformanceMemory();
		try{memory.setJsHeapSizeLimit(executor.getMemoryValue("jsHeapSizeLimit"));}catch(Exception e){memory.setJsHeapSizeLimit(-1L);}
		try{memory.setUsedJSHeapSize(executor.getMemoryValue("usedJSHeapSize"));}catch(Exception e){memory.setUsedJSHeapSize(-1L);}
		try{memory.setTotalJSHeapSize(executor.getMemoryValue("totalJSHeapSize"));}catch(Exception e){memory.setTotalJSHeapSize(-1L);}
		return memory;
	}
	private PerformanceTiming getPerformanceTiming(){
		PerformanceTiming timing =new PerformanceTiming();
		try{timing.setNavigationStart(executor.getTimingValue("navigationStart"));}catch(Exception e){timing.setNavigationStart(-1L);}
		try{timing.setUnloadEventStart(executor.getTimingValue("unloadEventStart"));}catch(Exception e){timing.setUnloadEventStart(-1L);}
		try{timing.setUnloadEventEnd(executor.getTimingValue("unloadEventEnd"));}catch(Exception e){timing.setUnloadEventEnd(-1L);}
		try{timing.setRedirectStart(executor.getTimingValue("redirectStart"));}catch(Exception e){timing.setRedirectStart(-1L);}
		try{timing.setRedirectEnd(executor.getTimingValue("redirectEnd"));}catch(Exception e){timing.setRedirectEnd(-1L);}
		try{timing.setFetchStart(executor.getTimingValue("fetchStart"));}catch(Exception e){timing.setFetchStart(-1L);}
		try{timing.setDomainLookupStart(executor.getTimingValue("domainLookupStart"));}catch(Exception e){timing.setDomainLookupStart(-1L);}
		try{timing.setDomainLookupEnd(executor.getTimingValue("domainLookupEnd"));}catch(Exception e){timing.setDomainLookupEnd(-1L);}
		try{timing.setConnectStart(executor.getTimingValue("connectStart"));}catch(Exception e){timing.setConnectStart(-1L);}
		try{timing.setConnectEnd(executor.getTimingValue("connectEnd"));}catch(Exception e){timing.setConnectEnd(-1L);}
		try{timing.setSecureConnectionStart(executor.getTimingValue("secureConnectionStart"));}catch(Exception e){timing.setNavigationStart(-1L);}
		try{timing.setRequestStart(executor.getTimingValue("requestStart"));}catch(Exception e){timing.setRequestStart(-1L);}
		try{timing.setResponseStart(executor.getTimingValue("responseStart"));}catch(Exception e){timing.setResponseStart(-1L);}
		try{timing.setResponseEnd(executor.getTimingValue("responseEnd"));}catch(Exception e){timing.setResponseEnd(-1L);}
		try{timing.setDomLoading(executor.getTimingValue("domLoading"));}catch(Exception e){timing.setDomLoading(-1L);}
		try{timing.setDomInteractive(executor.getTimingValue("domInteractive"));}catch(Exception e){timing.setDomInteractive(-1L);}
		try{timing.setDomContentLoadedEventStart(executor.getTimingValue("domContentLoadedEventStart"));}catch(Exception e){timing.setDomContentLoadedEventStart(-1L);}
		try{timing.setDomContentLoadedEventEnd(executor.getTimingValue("domContentLoadedEventEnd"));}catch(Exception e){timing.setDomContentLoadedEventEnd(-1L);}
		try{timing.setDomComplete(executor.getTimingValue("domComplete"));}catch(Exception e){timing.setDomComplete(-1L);}
		try{timing.setLoadEventStart(executor.getTimingValue("loadEventStart"));}catch(Exception e){timing.setLoadEventStart(-1L);}
		try{timing.setLoadEventEnd(executor.getTimingValue("loadEventEnd"));}catch(Exception e){timing.setLoadEventEnd(-1L);}
		return timing;
	}
	private PerformanceNavigation getPerformanceNavigation(){
		PerformanceNavigation navigation =new PerformanceNavigation();
		try{navigation.setTYPE_NAVIGATE(executor.getNavigationValue("TYPE_NAVIGATE"));}catch(Exception e){navigation.setTYPE_NAVIGATE(-1);}
		try{navigation.setTYPE_RELOAD(executor.getNavigationValue("TYPE_RELOAD"));}catch(Exception e){navigation.setTYPE_RELOAD(-1);}
		try{navigation.setTYPE_BACK_FORWARD(executor.getNavigationValue("TYPE_BACK_FORWARD"));}catch(Exception e){navigation.setTYPE_BACK_FORWARD(-1);}
		try{navigation.setTYPE_RESERVED(executor.getNavigationValue("TYPE_RESERVED"));}catch(Exception e){navigation.setTYPE_RESERVED(-1);}
		try{navigation.setType(executor.getNavigationValue("type"));}catch(Exception e){navigation.setType(-1);}
		try{navigation.setRedirectCount(executor.getNavigationValue("redirectCount"));}catch(Exception e){navigation.setRedirectCount(-1);}
		return navigation;
	}
	private void setEntries(String id){
		if(isResourceable()){
			for(PerformanceResourceTiming timing: performance.getEntries()){
				entries.add(getHarEntry(id, timing));
			}
		}else{
			entries.add(getHarEntry(id));
		}
	}
	private Entry getHarEntry(String id, PerformanceResourceTiming entry){
		return new Entry(id, new Date(Math.round(performance.getTiming().getNavigationStart()+entry.getFetchStart())), (entry.getFetchStart()-entry.getResponseEnd()), getRequest(entry), getResponse(entry), new Cache(), getTimings(entry), getCapabilityString("host"), getCapabilityString("webdriver.remote.sessionid"), getEntryTimingsInfo(entry));
	}
	private Entry getHarEntry(String id){
		return new Entry(id, new Date(performance.getTiming().getNavigationStart()), new Double(performance.getTiming().getNavigationStart()+performance.getTiming().getResponseEnd()));
	}
	private Request getRequest(PerformanceResourceTiming entry){
		Request request =new Request();
		request.setMethod(getMethod(entry.getInitiatorType()));
		request.setUrl(entry.getName());
		request.setHttpVersion(getHttpVersion(entry.getInitiatorType()));
		request.setCookies(requestCookies);
		request.setHeaders(new ArrayList<Header>());
		request.setQueryString(getQueryString(entry.getName()));
		request.setPostData(getPostData(entry));
		request.setHeadersSize(toKiloByte(entry.getTransferSize()));
		request.setBodySize(toKiloByte(entry.getEncodedBodySize()));
		request.setComment("Few fields are not captured as of now");
		return request;
	}
	private Response getResponse(PerformanceResourceTiming entry){
		Response response =new Response();
		response.setStatus(entry.getResponseEnd()-entry.getResponseStart() > 0? 200 : 400);
		response.setStatusText(entry.getResponseEnd()-entry.getResponseStart() > 0? "OK" : "Error");
		response.setHttpVersion(getHttpVersion(entry.getInitiatorType()));
		response.setCookies(responseCookies);
		response.setHeaders(new ArrayList<Header>());
		response.setContent(new Content(-1L, getMimeType(entry.getInitiatorType(), entry.getName())));
		response.setRedirectURL(entry.getName());
		response.setHeadersSize(toKiloByte(entry.getTransferSize()));
		response.setBodySize(toKiloByte(entry.getDecodedBodySize()));
		response.setComment("Few fields are not captured as of now");
		return response;
	}
	private Timings getTimings(PerformanceResourceTiming entry){
		Timings timings =new Timings();
		timings.setBlocked(entry.getDomainLookupStart()-entry.getRedirectStart());
		timings.setDns(entry.getDomainLookupEnd()-entry.getDomainLookupStart());
		timings.setConnect(entry.getConnectEnd()-entry.getConnectStart());
		timings.setSend(entry.getResponseStart()-entry.getRequestStart());
		timings.setWait(entry.getResponseEnd()-entry.getRequestStart());
		timings.setReceive(entry.getResponseEnd()-entry.getResponseStart());
		timings.setSsl(entry.getResponseEnd()-entry.getSecureConnectionStart());
		timings.setComment("EntryTimings are captured with Performance object");
		return timings;
	}
	private PostData getPostData(PerformanceResourceTiming entry){//change the logic
		List<PostedParameter> params =new ArrayList<PostedParameter>();
		params.add(new PostedParameter("entryType", entry.getEntryType()));
		params.add(new PostedParameter("initiatorType", entry.getInitiatorType()));
		params.add(new PostedParameter("url", entry.getName()));
		return new PostData(getMimeType(entry.getInitiatorType(), entry.getName()), params, "Post data captured from PerformanceResourceTiming", "It would be inaccurate if Performance object is not available");
	}
	private List<QueryParameter> getQueryString(String url){
		List<QueryParameter> queryString =new ArrayList<QueryParameter>();
		try{
			List<NameValuePair> params = URLEncodedUtils.parse(new URI(url), "UTF-8");
			for (NameValuePair param : params) {
				try{queryString.add(new QueryParameter(param.getName(), param.getValue()));}catch(Exception ex){}
			}
		}catch(Exception e){}
		return queryString;
	}
	private List<com.firecodex.harcodex.commons.har.Cookie> getHarCookies(Set<Cookie> seleniumCookies){
		List<com.firecodex.harcodex.commons.har.Cookie> cookies =new ArrayList<com.firecodex.harcodex.commons.har.Cookie>();
		for(Cookie cookie: seleniumCookies){
			cookies.add(new com.firecodex.harcodex.commons.har.Cookie(cookie.getName(), cookie.getValue(), cookie.getPath(), cookie.getDomain(), cookie.getExpiry(), cookie.isHttpOnly(), cookie.isSecure(), "Harcodex captured the cookie!"));
		}
		return cookies;
	}
	private PageTimings getHarPageTimings(){
		return new PageTimings(new Double(performance.getTiming().getDomContentLoadedEventEnd()-performance.getTiming().getDomContentLoadedEventStart()), new Double(performance.getTiming().getLoadEventEnd()-performance.getTiming().getLoadEventStart()));
	}
	private String getCapabilityString(String key){
		return (String)capabilities.getCapability(key);
	}
	private Integer getCapabilityInt(String key){
		return (Integer)capabilities.getCapability(key);
	}
	private Long getCapabilityLong(String key){
		return (Long)capabilities.getCapability(key);
	}
	private Double getCapabilityDouble(String key){
		return (Double)capabilities.getCapability(key);
	}
	private String getVersion(){
		String version = null;
		try{
			version = HarcodexBuilder.class.getPackage().getImplementationVersion();
		}catch(Exception e){}
		if(version==null) version = "1.0";
		return version;
	}
	private String getHttpVersion(String initiatorType){
		//String[] types = {"css", "embed", "img", "link", "object", "script", "subdocument", "svg", "xmlhttprequest", "other"};
		String version = "HTTP/1.1";
		//find logic to find the version from the type
		return version;
	}
	private String getMethod(String initiatorType){
		//String[] types = {"css", "embed", "img", "link", "object", "script", "subdocument", "svg", "xmlhttprequest", "other"};
		String method = "GET";
		//find logic to find the method from the type
		return method;
	}
	private String getMimeType(String initiatorType, String url){
		//String[] types = {"css", "embed", "img", "link", "object", "script", "subdocument", "svg", "xmlhttprequest", "other"};
		String mimeType = "application/other"; //"text/html; charset=utf-8";
		initiatorType = initiatorType==null?"":initiatorType.trim().toLowerCase();
		url = url==null?"":url.trim().toLowerCase();
		if(url.endsWith(".gif") || url.endsWith(".jpg") || url.endsWith(".jpeg") || url.endsWith(".png") || url.endsWith(".bmp") || url.endsWith(".ico") || url.endsWith(".json") || url.endsWith(".xml") || url.endsWith(".html") || url.endsWith(".pix")){
			mimeType = "text/"+url.substring(url.lastIndexOf(".")+1);
		}else if(initiatorType.contains("script") || url.endsWith(".js")){
			mimeType = "text/javascript";
		}else if(initiatorType.contains("css") || url.endsWith(".css")){
			mimeType = "text/css";
		}else if(initiatorType.contains("img")){
			mimeType = "text/img";
		}else if(initiatorType.contains("link")){
			mimeType = "text/link";
		}else if(initiatorType.contains("object")){
			mimeType = "application/object";
		}else if(initiatorType.contains("subdocument")){
			mimeType = "text/html";
		}else if(initiatorType.contains("svg")){
			mimeType = "text/svg";
		}else if(initiatorType.contains("xmlhttprequest")){
			mimeType = "text/xml";
		}else if(initiatorType.contains("embed")){
			mimeType = "application/embed";
		}
		return mimeType;
	}
	private Long toKiloByte(Long bytes){
		if(bytes<=0) return bytes;
		return Math.round(bytes/1024.0);
	}

	public String getEntryTimingsInfo(PerformanceResourceTiming entry){
		if(entry==null){
			return "No resource timing information is found";
		}else{
			try{
				return "The resource details are captured from PerformanceResourceTiming object of browser. These are few general resource timings in millis ["
						.concat(", Request time: ").concat(String.valueOf(entry.getResponseEnd()-entry.getRequestStart()))
						.concat(", Fetch time: ").concat(String.valueOf(entry.getResponseEnd()-entry.getFetchStart()))
						.concat(", Connection time: ").concat(String.valueOf(entry.getConnectEnd()-entry.getConnectStart()))
						.concat(", DNS time: ").concat(String.valueOf(entry.getDomainLookupEnd()-entry.getDomainLookupStart()))
						.concat("Initiator: ").concat(entry.getInitiatorType())
						.concat(", Next Hop Protocol: ").concat(entry.getNextHopProtocol())
						.concat(", Transfer Size: ").concat(String.valueOf(entry.getTransferSize()))
						.concat(", Encoded Body Size: ").concat(String.valueOf(entry.getEncodedBodySize()))
						.concat(", Decoded Body Size: ").concat(String.valueOf(entry.getDecodedBodySize()))
						.concat("].");
			}catch(Exception e){
				return "No resource timing information is found";
			}
		}
	}
	public String getPageTimingsInfo(){
		try{
			return "The page details are captured from PerformanceTiming object of browser. These are few general page timings in millis ["
					.concat("Page load time: ").concat(String.valueOf((performance.getTiming().getLoadEventEnd()-performance.getTiming().getNavigationStart())/1000))
					.concat(", User time: ").concat(String.valueOf(performance.getTiming().getLoadEventEnd()-performance.getTiming().getNavigationStart()))
					.concat(", Request time: ").concat(String.valueOf(performance.getTiming().getResponseEnd()-performance.getTiming().getRequestStart()))
					.concat(", Fetch time: ").concat(String.valueOf(performance.getTiming().getResponseEnd()-performance.getTiming().getFetchStart()))
					.concat(", Connection time: ").concat(String.valueOf(performance.getTiming().getConnectEnd()-performance.getTiming().getConnectStart()))
					.concat(", DNS time: ").concat(String.valueOf(performance.getTiming().getDomainLookupEnd()-performance.getTiming().getDomainLookupStart()))
					.concat("].");
		}catch(Exception e){
			return "No page timing information is found";
		}
	}
	public String getNavigationInfo(){
		try{
			int redirectCount = performance.getNavigation().getRedirectCount();
			int type = performance.getNavigation().getType();
			String info = "The navigation request was redirected ".concat(String.valueOf(redirectCount)).concat(" times");
			switch(type){
				case 1:{
					info = info.concat(" and it was a page reload");
					break;
				}case 2:{
					info = info.concat(" and it was a navigation by moving back or forward through history");
					break;
				}default:{
					info = info.concat(" and it was an action by the user such as clicking a link or entering a URL in the browser address bar");
				}
			}
			return info;
		}catch(Exception e){
			return "No navigation information is found";
		}
	}
}
