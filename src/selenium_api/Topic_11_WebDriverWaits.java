package selenium_api;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.Date;
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
	Date date;
 
  @BeforeClass
  public void beforeClass() {
	
		driver = new FirefoxDriver();
		// khoi tao wait explicit
		
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

  
  public void TC_02_ExplicitWait_Invisible() {
	  driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
	  driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
	  
	  		//check visible
	  waitExplicit.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//*[@id='start']/button")));
	  driver.findElement(By.xpath("//*[@id='start']/button")).click();
	  
	  
	  //check invisible
	  waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@id='loading']")));
	  
	  Assert.assertTrue(driver.findElement(By.xpath("//div[@id='finish']/h4[text() = 'Hello World!']")).isDisplayed()); 
  }
  
  public void TC_03_ExplicitWait_Visible() {
	  driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
	  driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
	  
	  		//check visible
	  waitExplicit.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//*[@id='start']/button")));
	  driver.findElement(By.xpath("//*[@id='start']/button")).click();
	  
	  
	  //check visible
	  waitExplicit.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@id='finish']/h4[text() = 'Hello World!']")));
	  Assert.assertTrue(driver.findElement(By.xpath("//div[@id='finish']/h4[text() = 'Hello World!']")).isDisplayed()); 
  }
  
  public void TC_04_DontAppearInDom_Presence() {
	  driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
	  driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
	  
	//check presence (ko co trong DOM) -> failed testcase
	  waitExplicit.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//div[@id='finish']/h4[text() = 'Hello World!']")));
	    
  }
  
  
  public void TC_05_InvisibleInDomAndNot() {
	  driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
	  driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
	  
	  waitExplicit = new WebDriverWait (driver, 10);
	  
	  System.out.println("---Start time Hello World---");
	  System.out.println(date = new Date());
	  //check invisible (Hello world) -> ko co trong DOM -> pass
	  waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@id='finish']/h4[text() = 'Hello World!']")));
	  System.out.println("---End time Hello World---");
	  System.out.println(date = new Date());
	  
	  System.out.println("---Start time Loading---");
	  System.out.println(date = new Date());
	  //check invisible (Hello world) -> ko co trong DOM -> pass
	  waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@id='loading']")));
	  System.out.println("---End time Loading---");
	  System.out.println(date = new Date());
	  
	  driver.findElement(By.xpath("//*[@id='start']/button")).click(); 
	  waitExplicit.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@id='finish']/h4[text() = 'Hello World!']")));
	  Assert.assertTrue(driver.findElement(By.xpath("//div[@id='finish']/h4[text() = 'Hello World!']")).isDisplayed());
	  
	  
	  System.out.println("---Start time Loading---");
	  System.out.println(date = new Date());
	  //check invisible (Hello world) -> co trong DOM -> pass
	  waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@id='loading']")));
	  System.out.println("---End time Loading---");
	  System.out.println(date = new Date());
  }
  
  @Test
  public void TC_06_AjaxIconLoading() {
	  driver.get("http://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");
	  //ImplicitWait
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  //ExplicitWait
	  waitExplicit = new WebDriverWait (driver, 10);
  }
  
  @AfterClass
  public void afterClass() {
	  driver.quit();
  }

}
