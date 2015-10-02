package com.firecodex.harcodex.listener;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

import org.apache.commons.lang3.text.WordUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverEventListener;

import com.firecodex.harcodex.commons.format.PageId;

public class HarcodexListener implements WebDriverEventListener{
	final static Logger logger = Logger.getLogger(HarcodexListener.class);
	
	private WebDriver driver;
	private Capabilities capabilities;
	private String beforeUrl;
	private Set<Cookie> beforeCookies;
	private PageId pageId;
	private String harFilePath;
	private String screenshotPath;
	private String uploadUrl;
	
	public HarcodexListener(WebDriver driver, Capabilities capabilities) {
		this.driver = driver;
		this.capabilities = capabilities;
		setScenario("NoScenario");
	}
	
	public void setScenario(String scenario) {
		if(pageId!=null){
			pageId.setScenario(scenario);
		}else{
			pageId =new PageId(scenario, "NoSite", "NoEnv", "NoPage");
		}
		logger.info("New scenario is set: "+scenario);
	}
	public void setPageId(PageId pageId) {
		this.pageId = pageId;
	}
	public void setHarDir(String harDir) {
		if(harDir!=null){
			String path = harDir.equals("")?System.getProperty("java.io.tmpdir"):harDir;
			if(!(path.trim().endsWith("/") || path.trim().endsWith("\\"))){path = path.concat(File.separator);}
			harFilePath = path.concat("hars");
			screenshotPath = path.concat("screenshots");
			if(new File(harFilePath).mkdir()){harFilePath = harFilePath.concat(File.separator);}else{harFilePath = path;}
			if(new File(harFilePath).mkdir()){screenshotPath = screenshotPath.concat(File.separator);}else{screenshotPath = path;}
		}
	}
	public void setUploadUrl(String uploadUrl) {
		this.uploadUrl = uploadUrl;
	}

	private void doBefore(){
		if(beforeUrl==null || !beforeUrl.equals(driver.getCurrentUrl())){
			beforeUrl = driver.getCurrentUrl();
			beforeCookies = driver.manage().getCookies();
			logger.info("Navigating to a new page: "+beforeUrl);
		}
	}
	
	private void doAfter(){
		String afterUrl = driver.getCurrentUrl();
		if(!beforeUrl.equals(afterUrl)){
			logger.warn("Capturing HAR of the page: "+afterUrl);
			HarcodexBuilder builder =new HarcodexBuilder(driver);
			if(builder.isBuildable()){
				pageId.setPage(WordUtils.capitalize(driver.getTitle()).replaceAll("[^a-zA-Z0-9]+", ""));
				String id = pageId.toString();
				builder = builder.browser(capabilities).page(id, beforeCookies).entries(id).build();
				if(harFilePath!=null){
					String path = getHarPath();
					try{
						builder.save(path);
						logger.info("Har is saved as: "+path);
					}catch(Exception e){
						logger.error("Error in saving "+path, e);
					}
				}
			}
			if(uploadUrl!=null && !uploadUrl.equals("")){
				try{
					builder.upload(uploadUrl+"?url="+driver.getCurrentUrl());
					logger.info("Har is uploaded to "+uploadUrl);
				}catch(Exception e){
					logger.error("Error in uploading to "+uploadUrl, e);
				}
			}
		}
	}
	
	private String getHarPath(){
		String name = driver.getCurrentUrl().replaceAll("[^a-zA-Z0-9]+", "");
		return harFilePath.concat(name).concat("_").concat(new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date())).concat(".har");
	} 
	
	public void afterChangeValueOf(WebElement arg0, WebDriver arg1) {
		doAfter();
	}
	public void afterClickOn(WebElement arg0, WebDriver arg1) {
		doAfter();
	}
	public void afterFindBy(By arg0, WebElement arg1, WebDriver arg2) {
		doAfter();
	}
	public void afterNavigateBack(WebDriver arg0) {
		doAfter();
	}
	public void afterNavigateForward(WebDriver arg0) {
		doAfter();
	}
	public void afterNavigateTo(String arg0, WebDriver arg1) {
		doAfter();
	}
	public void afterScript(String arg0, WebDriver arg1) {
		doAfter();
	}
	
	public void beforeChangeValueOf(WebElement arg0, WebDriver arg1) {
		doBefore();
	}
	public void beforeClickOn(WebElement arg0, WebDriver arg1) {
		doBefore();
	}
	public void beforeFindBy(By arg0, WebElement arg1, WebDriver arg2) {
		doBefore();
	}
	public void beforeNavigateBack(WebDriver arg0) {
		doBefore();
	}
	public void beforeNavigateForward(WebDriver arg0) {
		doBefore();
	}
	public void beforeNavigateTo(String arg0, WebDriver arg1) {
		doBefore();
	}
	public void beforeScript(String arg0, WebDriver arg1) {
		doBefore();
	}
	
	public void onException(Throwable arg0, WebDriver arg1) {
		doAfter();
	}
}
