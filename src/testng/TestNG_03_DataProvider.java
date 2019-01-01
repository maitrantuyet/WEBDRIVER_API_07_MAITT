package testng;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


public class TestNG_03_DataProvider {
	WebDriver driver;
	
	@BeforeClass
	public void setupBrowser() {
		driver = new FirefoxDriver();
		driver.get("http://live.guru99.com/index.php/customer/account/login/");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	
	@Test(dataProvider = "dp")
	public void TC_01_LoginToSystem() {
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("automationclass07@gmail.com");
		driver.findElement(By.xpath("//*[@id='pass']")).sendKeys("123123");
		driver.findElement(By.xpath("//*[@id='send2']")).click();
		
		Assert.assertTrue(driver.findElement(By.xpath("//h1[text()='My Dashboard']")).isDisplayed());

		
	}

	@DataProvider
	public Object[][] dp() {
		return new Object[][] {
			new Object[] { 1, "a" },
			new Object[] { 2, "b" },
		};
	}
 

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
