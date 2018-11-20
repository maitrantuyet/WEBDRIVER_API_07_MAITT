package selenium_api;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
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

  
  public void TC_02_RadioButton_HTML() {
	 driver.get("https://daominhdam.github.io/basic-form/index.html"); 
	 
	 //Click Under18_radioButton and development_checkbox
	 WebElement under18Radio = driver.findElement(By.xpath("//input[@id='under_18']"));
	 WebElement developmentCheckbox = driver.findElement(By.xpath("//input[@id='development']"));
	 under18Radio.click();
	 developmentCheckbox.click();
	 
	 //Verify selected
	 Assert.assertTrue(under18Radio.isSelected());
	 Assert.assertTrue(developmentCheckbox.isSelected());
	 
	 
  }
  
  
  public void TC_03_HandleCheckbox_Custom() {
	 driver.get("http://demos.telerik.com/kendo-ui/styling/checkboxes"); 
	 //Click to 'Dual-zone air conditioning' checkbox
	 WebElement dualZoneCheckbox = driver.findElement(By.xpath("//label[text()= 'Dual-zone air conditioning']/preceding-sibling::input"));
	 clickElementByJavascript(dualZoneCheckbox);

	 //Verify duaZone Checkbox selected
	 Assert.assertTrue(dualZoneCheckbox.isSelected());
	 
	 //Uncheck
	 clickElementByJavascript(dualZoneCheckbox);
	 
	//Verify duaZone Checkbox un-selected
	Assert.assertFalse(dualZoneCheckbox.isSelected());
  }
  
 
  public void TC_04_HandleRadioButton() {
	 driver.get("http://demos.telerik.com/kendo-ui/styling/radios"); 
	 
	 WebElement twoPetrolRadio = driver.findElement(By.xpath("//label[text() ='2.0 Petrol, 147kW']/preceding-sibling::input"));
	 WebElement oneDotEightPetrolRadio = driver.findElement(By.xpath("//label[text() ='1.8 Petrol, 118kW']/preceding-sibling::input"));
	 clickElementByJavascript(twoPetrolRadio);

	 //Verify duaZone Checkbox selected
	 Assert.assertTrue(twoPetrolRadio.isSelected());
	 
	 //Uncheck
	 clickElementByJavascript(oneDotEightPetrolRadio);
	 Assert.assertTrue(oneDotEightPetrolRadio.isSelected());
	 
	//Verify duaZone Checkbox un-selected
	Assert.assertFalse(twoPetrolRadio.isSelected());
  }

  @Test
  public void TC_05_Alert() {
	  driver.get("https://daominhdam.github.io/basic-form/index.html");
	  driver.findElement(By.xpath("//button[text() ='Click for JS Alert']")).click();
	  
	  Alert alert = driver.switchTo().alert();
	  Assert.assertEquals(alert.getText(), "I am a JS Alert");
	  alert.accept();
	  
	  
	  
  }
  
  @AfterClass
  public void afterClass() {
	  driver.quit();
  }
  
  // h�m n�y kh�ng quan t�m element c� visible hay ko
  public void clickElementByJavascript(WebElement element) {
	    JavascriptExecutor je = (JavascriptExecutor) driver;
	    je.executeScript("arguments[0].click();", element);
	    System.out.println("Click sucess!");
	    try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
