package com.test;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class CTOMSWebTest {
	public static void main (String []args) throws 
			InterruptedException, MalformedURLException, IOException
	{
		// Chrome
		
		System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		//Thread.sleep(5000);	
		// Get Status Code
		driver.get("https://www.ctomsinc.com/");
		driver.manage().window().maximize();
		 HttpURLConnection cont=
			      (HttpURLConnection)new URL("https://www.ctomsinc.com/")
			      .openConnection();
		// pass HEAD as parameter to setRequestMethod
	      cont.setRequestMethod("HEAD");
	      // obtain Response code
	      cont.connect();
	      int rs = cont.getResponseCode();
	      System.out.println("Http response code: " + rs);		
	      // Get Meta Description Content
	      String content = driver.findElement(By.xpath("//*[@name='description']"))
	      .getAttribute("content");
	      System.out.println("Meta Description Content: " + content);	      
	      //Click SHOP NOW on banner img
	      driver.findElement(By.linkText("SHOP NOW")).click();
	      // waiting for 3s
	      Thread.sleep(3000);
	      // click product = "Paraglider Kit, FireFly Kit"
	      driver.findElement(By.linkText("Paraglider Kit, FireFly Kit")).click();     
	      // check product title element if displayed in page
	      Boolean Display = driver.findElement(By.xpath("//*[@class='product-title']")).isDisplayed();
	      System.out.println("Title element displayed is:" + Display);
	      //Assert.assertEquals(Display, "PARAGLIDER KIT, FIREFLY KIT");
	      //check if button exists
	      boolean button;
	      try
	      {
	    	  driver.findElement(By.className("product-form--atc-button"));
	    	  driver.findElement(By.className("shopify-payment-button"));
	    	  button = true;	  
		      System.out.println("button exists.");
	      }
	      catch  (NoSuchElementException e)
	      {
	    	  button = false;	  
		      System.out.println("button doesnt exists.");
	      }	        
	      //back to home page
	      driver.navigate().to("https://www.ctomsinc.com/");
	      // waiting for 3s and click LOGIN
	      driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);	      
	      driver.findElement(By.linkText("LOGIN")).click();  
	      //
	      driver.findElement(By.id("customer_email")).sendKeys("nawang1019@gmail.com");
	      driver.findElement(By.id("customer_password")).sendKeys("Nwang195320");
	      driver.findElement(By.xpath("//button[contains(text(),'Sign in')]")).click();
	      
		//driver.close();
		  //driver.quit();
	      
	      /////

	}
}
