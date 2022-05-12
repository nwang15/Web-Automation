package com.test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.seljup.SeleniumJupiter;
import io.github.bonigarcia.seljup.SingleSession;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Order;

@ExtendWith(SeleniumJupiter.class)
@TestMethodOrder(OrderAnnotation.class)
@SingleSession
class ctomsAutoTest {

    WebDriver driver;

    ctomsAutoTest(ChromeDriver driver) {
        this.driver = driver;
    }
    @Test
    @Order(1)
    void init() {
        
        driver.get("https://www.ctomsinc.com/");
        driver.manage().window().maximize();
    }
    @Test
    void getStatusCode () throws MalformedURLException, IOException
    {
    	 HttpURLConnection cont=
			      (HttpURLConnection)new URL("https://www.ctomsinc.com/")
			      .openConnection();
		// pass HEAD as parameter to setRequestMethod
	      cont.setRequestMethod("HEAD");
	      // obtain Response code
	      cont.connect();
	      int rs = cont.getResponseCode();
	      System.out.println("Http response code: " + rs);	
    }
  
    @Nested
    @Order(2)
    @DisplayName("Check Home Page Info")
    class homePageInfo {
 
    	@Test 	
    	@Order(2)
        void getMeta() {
            
        	 String content = driver.findElement(By.xpath("//meta[@name='description']"))
    		      .getAttribute("content");
    		      System.out.println("Meta Description Content: " + content);	      
        }
    	@Test  
    	@Order(2)
         void CheckImage() throws Exception {
        	
        	WebElement ImageFile = driver.findElement(By.xpath("//img[@src='//cdn.shopify.com/s/files/1/0111/5966/6788/files/FireflySlideshowV2_1800x1000_crop_center.png?v=1647456343']"));
                
                Boolean ImagePresent = (Boolean) ((JavascriptExecutor)driver).executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", ImageFile);
                if (!ImagePresent)
                {
                     System.out.println("Image not displayed.");
                }
                else
                {
                    System.out.println("Image displayed.");
                }
        	} 
    }

    @Test
    @Order(3)
    @DisplayName("Click Shop Now Button")
    void ShopNow() throws InterruptedException {
        
        WebElement button = driver.findElement(By.linkText("SHOP NOW"));
        assertTrue(button.isDisplayed());
        button.click();
        Thread.sleep(3000);
        //driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
    	}  
    
    @Nested
    @Order(4)
    @DisplayName("Check product page info")
    class productInfo {
 
     
      @Test
      @DisplayName("Click Product by Product Title")
      void clickProduct() throws InterruptedException {
          
          WebElement firflyProduct = driver.findElement(By.linkText("Paraglider Kit, FireFly Kit"));
          assertTrue(firflyProduct.isDisplayed());
          firflyProduct.click();
          Thread.sleep(5000);
          //driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
      	}   
      @Test
      @DisplayName("Click Product Title if it displays")
      void clickProductTitle() throws InterruptedException {
    	  
    	// check product title element if displayed in page
	      Boolean Display = driver.findElement(By.xpath("//*[@class='product-title']")).isDisplayed();
	      System.out.println("Title element displayed is:" + Display);
          Thread.sleep(5000);
          //driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
      	}  
    }
}
