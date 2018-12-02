package selenium_api;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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
				driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			
  }
  
  @Test
  public void TC_01_CheckTitle() {
		driver.get("http://www.hdfcbank.com/");
		
		List <WebElement> notification = driver.findElements(By.xpath("//iframe[@id='vizury-notification-template']"));
		
		if (notification.size() > 0) {
			driver.switchTo().frame(notification.get(0));
			clickElementByJavascript(driver.findElement(By.cssSelector("#div-close")));
			System.out.println("Close popup success");
			driver.switchTo().defaultContent();
			
		}
		WebElement lookingForIframe = driver.findElement(By.xpath("//div[@class ='flipBannerWrap']//iframe"));
		driver.switchTo().frame(lookingForIframe);
		String messageText = driver.findElement(By.xpath("//span[@id ='messageText']")).getText();
		Assert.assertEquals(messageText, "What are you looking for?");
		
		driver.switchTo().defaultContent();
		//Switch to iframe (sliding image)
		WebElement slidingIframe = driver.findElement(By.xpath("//div[@class='slidingbanners']//iframe"));
		driver.switchTo().frame(slidingIframe);
		
		List <WebElement> banerImg = driver.findElements(By.xpath("//div[@id ='bannercontainer']//img"));
		int banerImgNumber = banerImg.size();
		Assert.assertEquals(banerImgNumber, 6);
		
		driver.switchTo().defaultContent();
		//Verify flipper banner được hiển thị và có 8 items
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='flipBanner']")).isDisplayed());
		
		List <WebElement> fligBannerImg = driver.findElements(By.xpath("//div[@class='flipBanner']//img[@class='front icon']"));
		Assert.assertEquals(fligBannerImg.size(), 8);
		
		//Check displayed 8 images
		for(WebElement img: fligBannerImg) {
			System.out.println("Filpping banner = "+ img.isDisplayed());
			Assert.assertTrue(img.isDisplayed());
		}
		
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
