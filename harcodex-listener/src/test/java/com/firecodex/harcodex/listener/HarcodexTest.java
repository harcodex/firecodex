package com.firecodex.harcodex.listener;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.firecodex.harcodex.commons.format.PageId;

public class HarcodexTest {
	private WebDriver driver;
	
	@Before
	public void init(){
		System.out.println("started...");
		driver =new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver =new HarcodexDriver(driver).pageId(new PageId("LoyolaSanity", "Loyola", "Prod")).upload("http://localhost:8181/harcodex-api/upload/harlogfile").register();
	}
	
	@Test
	public void test(){
		driver.get("https://www.google.com/");
//		driver.get("http://www.luc.edu");
//		System.out.println("search CS...");
//		WebElement element = driver.findElement(By.name("criteria"));
//		element.sendKeys("Computer Science");
//		element.submit();
//
//		System.out.println("select CSD...");
//		element = driver.findElement(By.linkText("Computer Science Department"));
//		element.click();
//
//		System.out.println("select project...");
//		element = driver.findElement(By.xpath("//*[@id='main-nav']/li[6]/a"));
//		element.click();
//		element = driver.findElement(By.xpath("//*[@id='main-nav']/li[6]/ul/li[2]/a"));
//		element.click();
//
//		System.out.println("go to ETL...");
//		element = driver.findElement(By.xpath("//*[@id='parent-fieldname-text']/p[1]/a/span"));
//		element.click();
//
//		System.out.println("validate ETL...");
//		Assert.assertEquals("Page title is not expected!", "Emerging Technologies Laboratory", driver.getTitle());
//		Assert.assertEquals("Icon is not found!", true, driver.findElement(By.xpath("//*[@id='logo-img-id']")).isDisplayed());
//
//		System.out.println("go to home page...");
//		driver.navigate().back();
//		element = driver.findElement(By.xpath("html/body/header/div/h1/a"));
//		element.click();
//		
//		System.out.println("test firecodex...");
//		((HarcodexDriver)driver).pageId(new PageId("FirecodexCheck", "Firecodex", "Prod"));
//		driver.get("http://firecodex.com");
//		element = driver.findElement(By.linkText("AngularJS & SemanticUI: Made for Each Other"));
//		element.click();
	}
	
	@After
	public void uninit(){
		driver = ((HarcodexDriver)driver).unregister();
		driver.quit();
		System.out.println("completed...");
	}
}
