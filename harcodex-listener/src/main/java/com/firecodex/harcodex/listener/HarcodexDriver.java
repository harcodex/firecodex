package com.firecodex.harcodex.listener;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.HasCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.firecodex.harcodex.commons.format.PageId;

public class HarcodexDriver extends EventFiringWebDriver implements HasCapabilities{
	private WebDriver driver;
	private HarcodexListener listener;
	
	public HarcodexDriver(WebDriver driver) {
		super(driver);
		this.driver = driver;
		this.listener =new HarcodexListener(driver, getCapabilities());
	}
	
	public Capabilities getCapabilities() {
		return ((RemoteWebDriver) driver).getCapabilities();
	}
	
	public HarcodexDriver scenario(String scenario){
		listener.setScenario(scenario);
		return this;
	}
	public HarcodexDriver pageId(PageId pageId){
		listener.setPageId(pageId);
		return this;
	}
	public HarcodexDriver path(String dir){
		listener.setHarDir(dir);
		return this;
	}
	public HarcodexDriver upload(String url){
		listener.setUploadUrl(url);
		return this;
	}
	
	public HarcodexDriver register(){
		super.register(listener);
		return this;
	}
	
	public HarcodexDriver unregister(){
		super.unregister(listener);
		return this;
	}

}
