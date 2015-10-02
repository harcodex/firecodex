package com.firecodex.harcodex.listener;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.firecodex.harcodex.commons.format.PageId;

public class SearsPrTest {
	private WebDriver driver;
	
	@Before
	public void init(){
		System.out.println("started...");
		driver =new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver =new HarcodexDriver(driver).pageId(new PageId("SearsPrTest", "SearsPR", "Prod")).upload("http://localhost:8181/harcodex-api/upload/harlogfile").register();
	}
	@After
	public void uninit(){
		driver = ((HarcodexDriver)driver).unregister();
		driver.quit();
		System.out.println("completed...");
	}
	
	@Test
	public void test(){
		launch();
		search("hammer");
		selectShipAvailable();
		addToCart();
		goToCart();
		proceedToCheckout();
		signinAsMember("prtest13@gmail.com", "sears2go");
		continueToPayment();
		enterDefaultCvv("123");
		placeOrder();
	}
	
	public void placeOrder(){
		driver.findElement(By.xpath("//*[@id='checkout']/div[3]/div[1]/div/div[2]/div[8]/button[1]")).click();
	}
	public void enterDefaultCvv(String cvv){
		List<WebElement> wes = driver.findElements(By.xpath("//input[starts-with(@class, 'cvv')]"));
		for(WebElement we: wes){
			if(we.isEnabled()){
				we.sendKeys(cvv);
				break;
			}
		}
	}
	public void continueToPayment(){
		driver.findElement(By.xpath("//*[@id='checkout']/div[3]/div[1]/div/div[2]/div[2]/button")).click();
	}
	public void signinAsMember(String username, String password){
		driver.findElement(By.id("pass_yes")).click();
		driver.findElement(By.id("email_01")).sendKeys(username);
		driver.findElement(By.id("signInPassword")).sendKeys(password);
		driver.findElement(By.id("loginCheckoutcont")).click();
	}
	public void proceedToCheckout(){
		driver.findElement(By.xpath("//*[@id='cart-footer']/div[3]/div/button")).click();
	}
	public void goToCart(){
		driver.findElement(By.xpath("//*[@id='atc-mc-modal']/div[4]/a[1]")).click();
	}
	public void addToCart(){
		driver.findElement(By.xpath("//*[@id='addToCart']/button")).click();
	}
	public void selectShipAvailable(){
		driver.findElement(By.xpath("//*[@id='cards-holder']/div[1]/div[2]/div[2]/h2/a")).click();
	}
	public void search(String keyword){
		driver.findElement(By.id("keyword")).clear();
		driver.findElement(By.id("keyword")).sendKeys(keyword);
		driver.findElement(By.id("keyword")).submit();
	}
	public void launch(){
		driver.get("http://www.sears.com.pr/");
//		driver.findElement(By.xpath("//*[@id='logo']/a")).click();
	}
}
