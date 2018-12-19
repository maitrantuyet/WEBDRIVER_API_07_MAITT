package selenium_api;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic_11_WebDriverWaits {
	WebDriver driver;
	//khai bao
	WebDriverWait waitExplicit;
 
  @BeforeClass
  public void beforeClass() {
	
		driver = new FirefoxDriver();
		// khoi tao wait explicit
		waitExplicit = new WebDriverWait (driver, 30);
		driver.manage().window().maximize();		
  }
  
  
  public void TC_01_ImplicitWait() {
	  //set timeout = 10s
	  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	  //step 01
	  driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
	  //step 02
	  driver.findElement(By.xpath("//*[@id='start']/button")).click();
	  //step 03
	  Assert.assertTrue(driver.findElement(By.xpath("//div[@id='finish']/h4[text() = 'Hello World!']")).isDisplayed()); 
	
	}


  @Test
  public void TC_02_ExplicitWait() {
	  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	  driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
	  
	  driver.findElement(By.xpath("//*[@id='start']/button")).click();
  }
  
  @Test
  public void TC_03_Visible_Presence() {
	  driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
	  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	  
	  driver.findElement(By.xpath("//*[@id='start']/button")).click();
	  Assert.assertTrue(driver.findElement(By.xpath("//div[@id='finish']/h4[text() = 'Hello World!']")).isDisplayed()); 
	  
	  waitExplicit.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("")));
  }
  
  @AfterClass
  public void afterClass() {
	  driver.quit();
  }

}
