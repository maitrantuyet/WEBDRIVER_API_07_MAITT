package selenium_api;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_06_UserInteractions {
	WebDriver driver;
	Actions action;
		
	By numberRangeBy = By.xpath("//ol[@id = 'selectable']/li");
 
  @BeforeClass
  public void beforeClass() {

				driver = new FirefoxDriver();
				action = new Actions(driver);
				driver.manage().window().maximize();
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			
  }
  
  
  public void TC_01_MoveMouseToElement() {
	  driver.get("https://www.myntra.com/");
	  
	  WebElement accountAvatar = driver.findElement(By.xpath("//div[@class ='desktop-userIconsContainer']"));
	  action.moveToElement(accountAvatar).perform();
	  
	  WebElement signUpLink = driver.findElement(By.xpath("//a[text() = 'Sign up']"));
	  Assert.assertTrue(signUpLink.isDisplayed());
	  signUpLink.click();
	  
	  Assert.assertTrue(driver.findElement(By.xpath("//p[text() ='Signup with Myntra']")).isDisplayed());
	}
  
  
  public void TC_02_ClickAndHoldElement_SelectMultipleitem() {
	  driver.get("http://jqueryui.com/resources/demos/selectable/display-grid.html");
	  
	  List <WebElement> numberRange = driver.findElements(numberRangeBy);
	  
	  action.clickAndHold(numberRange.get(0)).moveToElement(numberRange.get(3)).release().perform();
	  
	  List <WebElement> numberRangeSelected = driver.findElements(By.xpath("//ol[@id = 'selectable']/li[contains(@class,'selected')]"));
	  Assert.assertEquals(numberRangeSelected.size(), 4);
	  driver.navigate().refresh();
	  
	  List <WebElement> numberRangeRandom = driver.findElements(numberRangeBy);
	  action.keyDown(Keys.CONTROL).build().perform();
	  numberRangeRandom.get(0).click();
	  numberRangeRandom.get(2).click();
	  numberRangeRandom.get(4).click();
	  numberRangeRandom.get(6).click();
	  numberRangeRandom.get(8).click();
	  action.keyUp(Keys.CONTROL).build().perform();
	  
	  List <WebElement> numberRangeRandomSelected = driver.findElements(By.xpath("//ol[@id = 'selectable']/li[contains(@class,'selected')]"));
	  Assert.assertEquals(numberRangeRandomSelected.size(), 5);
  }
  
  
  public void TC_03_DoubleClick() {
	  driver.get("http://www.seleniumlearn.com/double-click");
	  
	  WebElement doubleClick = driver.findElement(By.xpath("//button[text() =\"Double-Click Me!\"]"));
	  action.doubleClick(doubleClick).perform();
	  
	  Alert alert = driver.switchTo().alert();
	  Assert.assertEquals(alert.getText(), "The Button was double-clicked.");
	  alert.accept();
  }
  
  @Test
  public void TC_04_rightClickToElement() {
	  driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");
	  
	  WebElement rightClick = driver.findElement(By.xpath("//span[text() ='right click me']"));
	  action.contextClick(rightClick).perform();
	  
	  WebElement quitBefore  = driver.findElement(By.xpath("//li/span[text() = 'Quit']"));
	  action.moveToElement(quitBefore).perform();
	  
	  Assert.assertTrue(driver.findElement(By.xpath("//li[contains(@class,'visible')and contains(@class, 'hover')]/span[text() = 'Quit']")).isDisplayed());
	  action.click(quitBefore).perform();
	  
	  Alert alert = driver.switchTo().alert();
	  Assert.assertEquals(alert.getText(), "clicked: quit");
	  alert.accept();
	  
  }
  


  @AfterClass
  public void afterClass() {
	  driver.quit();
  }

}
