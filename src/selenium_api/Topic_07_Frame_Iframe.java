package selenium_api;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic_07_Frame_Iframe {
	WebDriver driver;
	
 
  @BeforeClass
  public void beforeClass() {
	
				driver = new FirefoxDriver();
				driver.manage().window().maximize();
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			
  }
  
  @Test
  public void TC_01_CheckTitle() {
		driver.get("http://www.hdfcbank.com/");
		
		List <WebElement> notification = driver.findElements(By.xpath("//iframe[@id='vizury-notification-template']"));
		if (notification.size() > 0) {
			driver.switchTo().frame(notification.get(0));
			driver.findElement(By.xpath("//div[@id ='div-close']")).click();
		}
	}


  @AfterClass
  public void afterClass() {
	  driver.quit();
  }

}
