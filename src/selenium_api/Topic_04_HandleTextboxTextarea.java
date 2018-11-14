package selenium_api;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_04_HandleTextboxTextarea {
	WebDriver driver;
	private String newName, newDob, newAddress, newCity, newState, newPin, newMobile, newEmail, password;
	private String editAddress, editCity, editState, editPin, editMobile, editEmail;
	
	
	By nameByTextbox = By.xpath("//input[@name='name']");
	By dobByTextbox = By.xpath("//input[@name='dob']");
	By addrByTextbox = By.xpath("//textarea[@name='addr']");
	By cityByTextbox = By.xpath("//input[@name='city']");
	By stateByTextbox = By.xpath("//input[@name='state']");
	By pinByTextbox = By.xpath("//input[@name='pinno']");
	By mobileByTextbox = By.xpath("//input[@name='telephoneno']");
	By emailByTextbox = By.xpath("//input[@name='emailid']");
	By passwordByTextbox = By.xpath("//input[@name='password']");
	By submitByButton = By.xpath("//input[@name='sub']");
	
 
  @BeforeClass
  public void beforeClass() {

		driver = new FirefoxDriver();
		driver.get("http://demo.guru99.com/v4/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				
		newName ="Selenium Online";
		newDob ="2000-10-01";
		newAddress ="123 Address";
		newCity ="Ho Chi Minh";
		newState ="Thu Duc";
		newPin ="123456";
		newMobile ="0123456789";
		newEmail ="autotest" + Commons.randomEmail() +"@gmail.com";
		password ="123456";
			
		editAddress ="789 Edit Address";
		editCity ="Ha Noi";
		editState ="Thanh Xuan";
		editPin ="987654";
		editMobile ="0987456123";
		editEmail ="edittest" + Commons.randomEmail() +"@gmail.com";
  }
  
  @Test
  public void TC_01_NewCustomer() {
	  driver.findElement(By.xpath("//input[@name='uid']")).sendKeys("mngr161493");
	  driver.findElement(By.xpath("//input[@name='password']")).sendKeys("harErAh");
	  driver.findElement(By.xpath("//input[@name='btnLogin']")).click();
	  Assert.assertTrue(driver.findElement(By.xpath("//marquee[text()=\"Welcome To Manager's Page of Guru99 Bank\"]")).isDisplayed());
	  
	  driver.findElement(By.xpath("//a[text()='New Customer']")).click();
	  
	  driver.findElement(nameByTextbox).sendKeys(newName);
	  driver.findElement(dobByTextbox).sendKeys(newDob);
	  driver.findElement(addrByTextbox).sendKeys(newAddress);
	  driver.findElement(cityByTextbox).sendKeys(newCity);
	  driver.findElement(stateByTextbox).sendKeys(newState);
	  driver.findElement(pinByTextbox).sendKeys(newPin);
	  driver.findElement(mobileByTextbox).sendKeys(newMobile);
	  driver.findElement(emailByTextbox).sendKeys(newEmail);
	  driver.findElement(passwordByTextbox).sendKeys(password);
	  driver.findElement(submitByButton).click();
	}

  @Test
  public void TC_02_EditCustomer() {
	  
	}


  @AfterClass
  public void afterClass() {
	  driver.quit();
  }

}
