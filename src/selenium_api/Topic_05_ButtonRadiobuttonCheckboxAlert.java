package selenium_api;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic_05_ButtonRadiobuttonCheckboxAlert {
	WebDriver driver;
	
 
  @BeforeClass
  public void beforeClass() {

				driver = new FirefoxDriver();
				driver.manage().window().maximize();
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
					
  }
  
  
  public void TC_01_HandleButton() {
	  
	  driver.get("http://live.guru99.com/");
	  //Click to my account link
	  driver.findElement(By.xpath("//div[@class= 'footer']//a[text()= 'My Account']")).click();
	  //Verify page URL
	  Assert.assertEquals(driver.getCurrentUrl(), "http://live.guru99.com/index.php/customer/account/login/");
	  //Click to Create An Account button
	  WebElement createAnAccountButton = driver.findElement(By.xpath("//a[@title = 'Create an Account']"));
	  clickElementByJavascript(createAnAccountButton);
	  //Verify page URL
	  Assert.assertEquals(driver.getCurrentUrl(), "http://live.guru99.com/index.php/customer/account/create/");
	}

  @Test
  public void TC_02_RadioButton_HTML() {
	 driver.get("https://daominhdam.github.io/basic-form/index.html"); 
  }
  @Test
  public void TC_03_HandleCheckbox_Custom() {
	 driver.get("http://demos.telerik.com/kendo-ui/styling/checkboxes"); 
	 //Click to 'Dual-zone air conditioning' checkbox
	 WebElement dualZoneCheckbox = driver.findElement(By.xpath("//label[text()= 'Dual-zone air conditioning']"));
	 dualZoneCheckbox.click();
	 
	 //Verify duaZone Checkbox selected
	 Assert.assertTrue(dualZoneCheckbox.isSelected());
	 
	 //Uncheck
	 dualZoneCheckbox.click();
	 
	//Verify duaZone Checkbox un-selected
	Assert.assertFalse(dualZoneCheckbox.isSelected());
  }
  
  @Test
  public void TC_04_HandleRadioButton() {
	 driver.get("http://demos.telerik.com/kendo-ui/styling/radios"); 
	 
  }

  @AfterClass
  public void afterClass() {
	  driver.quit();
  }
  
  public void clickElementByJavascript(WebElement element) {
	    JavascriptExecutor je = (JavascriptExecutor) driver;
	    je.executeScript("arguments[0].click();", element);
	}

}
