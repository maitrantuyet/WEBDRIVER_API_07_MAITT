package selenium_api;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic_10_UploadFile {
	WebDriver driver;
	
 
  @BeforeClass
  public void beforeClass() {
	
			System.setProperty("webdriver.chrome.driver", ".\\lib\\chromedriver.exe");
			driver = new ChromeDriver();
			
			driver = new FirefoxDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			
  }
  
  @Test
  public void TC_01_CheckTitle() {
	  driver.get("http://blueimp.github.io/jQuery-File-Upload/");
	}


  @AfterClass
  public void afterClass() {
	  driver.quit();
  }

}
