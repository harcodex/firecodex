package com.firecodex.harcodex.api.service;

import java.net.URI;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;

import javax.swing.JOptionPane;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.firecodex.harcodex.api.model.Detail;
import com.firecodex.harcodex.api.model.Harcodex;
import com.firecodex.harcodex.api.model.Overview;
import com.firecodex.harcodex.api.model.PagespeedScore;
import com.firecodex.harcodex.api.model.Score;
import com.firecodex.harcodex.api.model.view.ViewHarcodex;
import com.firecodex.harcodex.commons.format.PageId;
import com.firecodex.harcodex.commons.har.Entry;
import com.firecodex.harcodex.commons.har.Har;
import com.firecodex.harcodex.commons.har.Log;
import com.mongodb.DBCollection;

@Service
public class HarcodexService {
	@Value("${ps.url}") private String psUrl;
	
	@Autowired MongoOperations mongoOperations;
	@Autowired PageSpeedService pageSpeedService;
	@Autowired YSlowService ySlowService;

	public void uploadHar(String url, byte[] harAsBytes) throws Exception{
		uploadHar(url, new String(harAsBytes));
	}
	public void uploadHar(String url, String harAsString) throws Exception{
		Har har =new ObjectMapper().readValue(harAsString, Har.class);
		uploadHarlog(url, har.getLog());
	}
	public void uploadHar(String url, Har har) throws Exception{
		uploadHarlog(url, har.getLog());
	}
	
	public void uploadLog(String url, byte[] logAsBytes) throws Exception{
		uploadLog(url, new String(logAsBytes));
	}
	public void uploadLog(String url, String logAsString) throws Exception{
		Log log =new ObjectMapper().readValue(logAsString, Log.class);
		uploadHarlog(url, log);
	}
	
	public void uploadHarlog(String url, Log log) throws Exception{
		Harcodex harcodex =new Harcodex();
		harcodex.setLabel(log.getPages().get(0).getId());
		harcodex.setUrl(url);
		harcodex.setLog(log);
		
		Future<PagespeedScore> psFuture = pageSpeedService.getScores(psUrl.replace("<url>", url), 45*1000);

		List<Score> ysScores =new ArrayList<Score>();
		ysScores.addAll(ySlowService.getScores(log));
		harcodex.setYslowScores(ysScores);
		
		while(true) {
			if (psFuture.isDone()) {
				harcodex.setPagespeedScore(psFuture.get());
				break;
			}
			try{Thread.sleep(1000);}catch(Exception e){}
		}
		
		mongoOperations.save(harcodex);
	}

	public List<ViewHarcodex> getHarcodexes(String label) {
		List<ViewHarcodex> harcodexes =new ArrayList<ViewHarcodex>();
		Query query =new Query();
		query.addCriteria(Criteria.where("label").is(label));
		List<Harcodex> hars = mongoOperations.find(query, Harcodex.class);
		for(Harcodex har: hars){
			ViewHarcodex viewHar =new ViewHarcodex();
			viewHar.setId(har.getId());
			viewHar.setLog(har.getLog());
			viewHar.setPagespeedScore(har.getPagespeedScore());
			viewHar.setYslowScores(har.getYslowScores());
			viewHar.setOverview(getOverview(har.getLog(), Long.valueOf(hars.size())));
			viewHar.setDetail(getDetail(har.getLog()));
			viewHar.setResourceRequests(getResourceRequests(har.getLog()));
			viewHar.setDomainRequests(getDomainRequests(har.getLog()));
			viewHar.setResourceSizes(getResourceSizes(har.getLog()));
			viewHar.setDomainSizes(getDomainSizes(har.getLog()));
			harcodexes.add(viewHar);
		}
		return harcodexes;
	}
	public List<Overview> getHarOverviews(){
		List<Overview> overviews =new ArrayList<Overview>();
		DBCollection harcodex = mongoOperations.getCollection("harcodex");
		List<String> pages = harcodex.distinct("label");
		for(String page: pages){
			Query query =new Query();
			query.addCriteria(Criteria.where("label").is(page));
			query.with(new Sort(Sort.Direction.DESC, "log.pages.startedDateTime"));
			Long count = mongoOperations.count(query, Harcodex.class);
			query.limit(1);
			Harcodex entry = mongoOperations.findOne(query, Harcodex.class);
			overviews.add(getOverview(entry.getLog(), count));
		}
		return overviews;
	}
	
	private Detail getDetail(Log log){
		Detail detail =new Detail();
		detail.setRedirects(getRedirects(log));
		//complete
		return detail;
	}
	private Integer getRedirects(Log log){
		int redirects = 0;
		for(Entry entry: log.getEntries()){
			if(entry.getResponse().getRedirectURL()!=null && entry.getResponse().getRedirectURL()!=""){
				redirects++;
			}
		}
		return redirects;
	}
	private Overview getOverview(Log log, Long count){
		Overview overview =new Overview();
		overview.setLabel(log.getPages().get(0).getId());
		PageId pageId =new PageId(log.getPages().get(0).getId());
		overview.setScenario(pageId.getScenario());
		overview.setEnvironment(pageId.getEnv());
		overview.setSite(pageId.getSite());
		overview.setPage(pageId.getPage());
		overview.setTimestamp(log.getPages().get(0).getStartedDateTime());
		overview.setDomains(getDomainRequests(log).size());
		overview.setRequests(log.getEntries().size());
		overview.setTotalSize(getTotalSize(log));
		overview.setLoadTime(log.getPages().get(0).getPageTimings().getOnLoad());
		overview.setCount(count);
		return overview;
	}
	private Map<String, Long> getResourceSizes(Log log){
		Map<String, Long> resourceRequests =new HashMap<String, Long>();
		for(Entry entry: log.getEntries()){
			String resource = entry.getResponse().getContent().getMimeType();
			if(resource!=null){
				if(resourceRequests.containsKey(resource)){
					resourceRequests.put(resource, resourceRequests.get(resource)+(increment(0L, entry.getResponse().getBodySize())+increment(0L, entry.getResponse().getHeadersSize())));
				}else{
					resourceRequests.put(resource, (increment(0L, entry.getResponse().getBodySize())+increment(0L, entry.getResponse().getHeadersSize())));
				}
			}
		}
		return resourceRequests;
	}
	private Map<String, Long> getDomainSizes(Log log){
		Map<String, Long> domainRequests =new HashMap<String, Long>();
		for(Entry entry: log.getEntries()){
			try{
				String domain =new URI(entry.getRequest().getUrl()).getHost();
				if(domainRequests.containsKey(domain)){
					domainRequests.put(domain, domainRequests.get(domain)+(increment(0L, entry.getResponse().getBodySize())+increment(0L, entry.getResponse().getHeadersSize())));
				}else{
					domainRequests.put(domain, (increment(0L, entry.getResponse().getBodySize())+increment(0L, entry.getResponse().getHeadersSize())));
				}
			}catch(Exception e){}
		}
		return domainRequests;
	}
	private Map<String, Integer> getResourceRequests(Log log){
		Map<String, Integer> resourceRequests =new HashMap<String, Integer>();
		for(Entry entry: log.getEntries()){
			String resource = entry.getResponse().getContent().getMimeType();
			if(resource!=null){
				if(resourceRequests.containsKey(resource)){
					resourceRequests.put(resource, resourceRequests.get(resource)+1);
				}else{
					resourceRequests.put(resource, 1);
				}
			}
		}
		return resourceRequests;
	}
	private Map<String, Integer> getDomainRequests(Log log){
		Map<String, Integer> domainRequests =new HashMap<String, Integer>();
		for(Entry entry: log.getEntries()){
			try{
				String domain =new URI(entry.getRequest().getUrl()).getHost();
				if(domainRequests.containsKey(domain)){
					domainRequests.put(domain, domainRequests.get(domain)+1);
				}else{
					domainRequests.put(domain, 1);
				}
			}catch(Exception e){}
		}
		return domainRequests;
	}
	private Long getTotalSize(Log log){
		Long totalSize = 0L;
		for(Entry entry: log.getEntries()){
			totalSize = increment(totalSize, entry.getResponse().getBodySize()>0?entry.getResponse().getBodySize():entry.getResponse().getContent().getSize());
			totalSize = increment(totalSize, entry.getResponse().getHeadersSize());
		}
		return totalSize;
	}
	private Long increment(Long total, Long inc){
		return inc>0?total+inc:total;
	}
	
	public ObjectMapper getDateSafeObjectMapper(){
		ObjectMapper mapper =new ObjectMapper();
		DateFormat df =new SimpleDateFormat("YYYY-MM-DDThh:mm:ss.sTZD");
		mapper.configure(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS, true);
        mapper.setDateFormat(df);
        mapper.getDeserializationConfig().with(df);
		return mapper;
	}
}
