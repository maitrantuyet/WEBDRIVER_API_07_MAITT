package selenium_api;

import org.testng.annotations.Test;

import com.google.common.base.Function;

import org.testng.annotations.BeforeClass;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
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
  
  
  public void TC_06_AjaxIconLoading() {
	  driver.get("http://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");
	  //ImplicitWait
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  //ExplicitWait
	  waitExplicit = new WebDriverWait(driver, 25);
	  
	  //Step 02
	  waitExplicit.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='ctl00_ContentPlaceholder1_Panel1']")));
	  
	  //Step 03
	  WebElement selectedDatesBefore = driver.findElement(By.xpath("//span[@id='ctl00_ContentPlaceholder1_Label1']"));
	  Assert.assertEquals(selectedDatesBefore.getText(), "No Selected Dates to display.");
	  
	  //Step 04
	  driver.findElement(By.xpath("//a[text()= '27']")).click();
	  
	  //Step 05 (su dung cach 2 thi step 05 ko can)
	 // waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='raDiv']")));
	  
	  //Step 06
	  Assert.assertTrue(driver.findElement(By.xpath("//td[@class='rcSelected']//a[text()='27']")).isDisplayed());
	  
	  //Step 07- cach 1
	 // WebElement selectedDatesAfter = driver.findElement(By.xpath("//span[@id='ctl00_ContentPlaceholder1_Label1']"));
	//  Assert.assertEquals(selectedDatesAfter.getText(), "Thursday, December 27, 2018");
	  
	//Step 07- cach 2
	  WebElement selectedDatesAfterDisplayed = driver.findElement(By.xpath("//span[@id='ctl00_ContentPlaceholder1_Label1' and contains(text(), 'Thursday, December 27, 2018')]"));
	  Assert.assertTrue(selectedDatesAfterDisplayed.isDisplayed());
	  
	  
  }
  
  @Test
  public void TC_07_FluentWait() {
	  driver.get("https://daominhdam.github.io/fluent-wait/");
	  //ImplicitWait
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  //ExplicitWait
	  waitExplicit = new WebDriverWait(driver, 25);
	  
	  WebElement countdount =  driver.findElement(By.xpath("//div[@id='javascript_countdown_time']"));
	  waitExplicit.until(ExpectedConditions.visibilityOf(countdount));

	  // Khởi tạo Fluent wait
	  new FluentWait<WebElement>(countdount)
	             // Tổng time wait là 15s
	             .withTimeout(15, TimeUnit.SECONDS)
	              // Tần số mỗi 1s check 1 lần
	              .pollingEvery(1, TimeUnit.SECONDS)
	             // Nếu gặp exception là find ko thấy element sẽ bỏ  qua
	              .ignoring(NoSuchElementException.class)
	              // Kiểm tra điều kiện
	              .until(new Function<WebElement, Boolean>() {
	                  public Boolean apply(WebElement element) {
	                             // Kiểm tra điều kiện countdount = 00
	                             boolean flag =  element.getText().endsWith("00");
	                             System.out.println("Time = " +  element.getText());
	                             // return giá trị cho function apply
	                             return flag;
	                  }
	             });
  }
  
  @AfterClass
  public void afterClass() {
	  driver.quit();
  }

}
