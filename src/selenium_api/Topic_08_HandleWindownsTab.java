package selenium_api;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;

public class Topic_08_HandleWindownsTab {
	WebDriver driver;
 
  @BeforeClass
  public void beforeClass() {
	  
	  driver = new FirefoxDriver();
	  driver.manage().window().maximize();
	  driver.manage().timeouts().implicitlyWait( 30, TimeUnit.SECONDS);
  }
  
  @Test
  public void TC01_Windown() {
	  driver.get("https://daominhdam.github.io/basic-form/index.html");
	  
	 
	  
	  
  }

  @AfterClass
  public void afterClass() {
  }

}
