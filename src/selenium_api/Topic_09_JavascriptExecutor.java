package selenium_api;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic_09_JavascriptExecutor {
	WebDriver driver;
  
  @BeforeClass
  public void beforeClass() {
	  
	  driver = new FirefoxDriver();
	  driver.manage().window().maximize();
	  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
  }
  
  
  public void TC01_JE() {
	  driver.get("http://live.guru99.com/");
	  String domainName =   (String) executeForBrowser("return document.domain");
	  Assert.assertEquals(domainName, "live.guru99.com");
	  
	  String homePageURL = (String) executeForBrowser("return document.URL");
	  Assert.assertEquals(homePageURL, "http://live.guru99.com/");
	  
	  WebElement mobilePageLink = driver.findElement(By.xpath("//a[text() = 'Mobile']"));
	  clickToElementByJS(mobilePageLink);
	  
	  WebElement samsungGalaxyButton = driver.findElement(By.xpath("//a[text() = 'Samsung Galaxy']/parent::h2/following-sibling::div[@class ='actions']//button"));
	  clickToElementByJS(samsungGalaxyButton);
	  
	  String pageText = (String) executeForBrowser("return document.documentElement.innerText");
	  Assert.assertTrue(pageText.contains("Samsung Galaxy was added to your shopping cart."));
	  
	  WebElement privacyPolicyLink = driver.findElement(By.xpath("//a[text() = 'Privacy Policy']"));
	  clickToElementByJS(privacyPolicyLink);
	  
	  String privacyPolicyTitle = (String) executeForBrowser("return document.title");
	  Assert.assertEquals(privacyPolicyTitle, "Privacy Policy");
	  
	  scrollToBottomPage();
	  
	  WebElement wishListRow = driver.findElement(By.xpath("//th[text() = 'WISHLIST_CNT']/following-sibling::td[text() ='The number of items in your Wishlist.']"));
	  Assert.assertTrue(wishListRow.isDisplayed());
	  
	  navigateToUrlByJS("http://demo.guru99.com/v4/ ");
	  String domainDemoName =   (String) executeForBrowser("return document.domain");
	  Assert.assertEquals(domainDemoName, "demo.guru99.com");
  }
  
  
  public void TC02_RemoveAttribute() {
	  driver.get("https://www.w3schools.com/tags/tryit.asp?filename=tryhtml_input_disabled");
	  driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id ='iframeResult']")));
	  
	  WebElement lastNameTxt = driver.findElement(By.xpath("//input[@name ='lname']"));
	  WebElement firstNameTxt = driver.findElement(By.xpath("//input[@name ='fname']"));
	  WebElement submitBtn = driver.findElement(By.xpath("//input[@value = 'Submit']"));
	  removeAttributeInDOM(lastNameTxt, "disabled");
	  
	  sendkeyToElementByJS(firstNameTxt, "Selenium");
	  sendkeyToElementByJS(lastNameTxt, "Class");
	  
	  clickToElementByJS(submitBtn);
	  
	  WebElement textResult = driver.findElement(By.xpath("//h2[text() ='Your input was received as:']/following-sibling::div[contains(text(),'fname')]"));
	  Assert.assertTrue(textResult.getText().contains("Selenium") && textResult.getText().contains("Class"));
  }
   
  @Test
  public void TC03_CreateAnAccount() {
	  driver.get("http://live.guru99.com/");
	  String firstName = "Automation", lastname ="Testing", 
			  email = "autotest" + Commons.randomEmail() + "@gmail.com", password = "123456";
	  
	  clickToElementByJS(driver.findElement(By.xpath("//div[@class = 'footer']//a[text() = 'My Account']")));
	  clickToElementByJS(driver.findElement(By.xpath("//a[@title = 'Create an Account']")));
	  
	  sendkeyToElementByJS(driver.findElement(By.xpath("//input[@id='firstname']")), firstName);
	  sendkeyToElementByJS(driver.findElement(By.xpath("//input[@id='lastname']")), lastname);
	  sendkeyToElementByJS(driver.findElement(By.xpath("//input[@id='email_address']")), email);
	  sendkeyToElementByJS(driver.findElement(By.xpath("//input[@id='password']")), password);
	  sendkeyToElementByJS(driver.findElement(By.xpath("//input[@id='confirmation']")), password);
	  clickToElementByJS(driver.findElement(By.xpath("//button[@title= 'Register']")));
	  
	  String messageText = (String) executeForBrowser("return document.documentElement.innerText");
	  Assert.assertTrue(messageText.contains("Thank you for registering with Main Website Store."));
	  Assert.assertTrue(messageText.contains(firstName));
	  Assert.assertTrue(messageText.contains(lastname));
	  
	  clickToElementByJS(driver.findElement(By.xpath("//span[text() = 'Account']")));
	  clickToElementByJS(driver.findElement(By.xpath("//a[text() = 'Log Out']")));
	  
	  Assert.assertTrue(driver.findElement(By.xpath("//h2[contains(text(),'This is demo site for')]")).isDisplayed());
  }
  
  public void highlightElement(WebElement element) {
      JavascriptExecutor js = (JavascriptExecutor) driver;
      js.executeScript("arguments[0].style.border='6px groove red'", element);
  }

  public Object executeForBrowser(String javaSript) {
      try {
          JavascriptExecutor js = (JavascriptExecutor) driver;
          return js.executeScript(javaSript);
      } catch (Exception e) {
          e.getMessage();
          return null;
      }
  }

  public Object clickToElementByJS(WebElement element) {
      try {
          JavascriptExecutor js = (JavascriptExecutor) driver;
          return js.executeScript("arguments[0].click();", element);
      } catch (Exception e) {
          e.getMessage();
          return null;
      }
  }

  public Object sendkeyToElementByJS(WebElement element, String value) {
      try {
          JavascriptExecutor js = (JavascriptExecutor) driver;
          return js.executeScript("arguments[0].setAttribute('value', '" + value + "')", element);
      } catch (Exception e) {
          e.getMessage();
          return null;
      }
  }

  public Object removeAttributeInDOM(WebElement element, String attribute) {
      try {
          JavascriptExecutor js = (JavascriptExecutor) driver;
          return js.executeScript("arguments[0].removeAttribute('" + attribute + "');", element);
      } catch (Exception e) {
          e.getMessage();
          return null;
      }
  }

  public Object scrollToBottomPage() {
      try {
          JavascriptExecutor js = (JavascriptExecutor) driver;
          return js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
      } catch (Exception e) {
          e.getMessage();
          return null;
      }
  }

  public Object navigateToUrlByJS(String url) {
      try {
          JavascriptExecutor js = (JavascriptExecutor) driver;
          return js.executeScript("window.location = '" + url + "'");
      } catch (Exception e) {
          e.getMessage();
          return null;
      }
  }

  @AfterClass
  public void afterClass() {
	  driver.quit();
  }

}
