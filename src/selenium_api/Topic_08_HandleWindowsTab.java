package selenium_api;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;

public class Topic_08_HandleWindowsTab {
	WebDriver driver;
 
  @BeforeClass
  public void beforeClass() {
	  
	  driver = new FirefoxDriver();
	  driver.manage().window().maximize();
	  driver.manage().timeouts().implicitlyWait( 30, TimeUnit.SECONDS);
  }
  
  
  public void TC01_WindowID() {
	  driver.get("https://daominhdam.github.io/basic-form/index.html");
	  
	  String parentWindowID = driver.getWindowHandle();
	  System.out.println("Parent ID = " + parentWindowID);
	  
	  //Click "Opening a new windown"
	  driver.findElement(By.xpath("//a[text() = 'Click Here']")).click();
	  
	  switchToWindowByID(parentWindowID);
	  
	  //Verify navigate to Google page success
	  Assert.assertEquals(driver.getTitle(), "Google");
	  
	  switchToWindowByTitle("SELENIUM WEBDRIVER FORM DEMO");
	  Assert.assertEquals(driver.getTitle(), "SELENIUM WEBDRIVER FORM DEMO");
	  
	  switchToWindowByTitle("Google");
	  Assert.assertEquals(driver.getTitle(), "Google");
	  
	  switchToWindowByTitle("SELENIUM WEBDRIVER FORM DEMO");
	  Assert.assertEquals(driver.getTitle(), "SELENIUM WEBDRIVER FORM DEMO");
  }
  
  
  public void TC02_WindowTitle() {
	  driver.get("http://www.hdfcbank.com/");
	  String parentID = driver.getWindowHandle();
	  
	  //Kiem tra popup xuat hien
	  List <WebElement> notification = driver.findElements(By.xpath("//iframe[@id ='vizury-notification-template']"));
	  System.out.println("Number of element =" + notification.size()); 
	  if(notification.size() >0) {
		  driver.switchTo().frame(notification.get(0));
		  clickElementByJavascript(driver.findElement(By.cssSelector("#div-close")));
		  System.out.println("Close popup success");
		  driver.switchTo().defaultContent();
	  }
	  //Click Agri link
	  driver.findElement(By.xpath("//a[text() ='Agri']")).click();
	  switchToWindowByTitle("HDFC Bank Kisan Dhan Vikas e-Kendra");
	  Assert.assertEquals(driver.getTitle(), "HDFC Bank Kisan Dhan Vikas e-Kendra");
	  
	  //Click Account Details link
	  driver.findElement(By.xpath("//a[contains(., 'Account Details')]")).click();
	  switchToWindowByTitle("Welcome to HDFC Bank NetBanking");
	  Assert.assertEquals(driver.getTitle(), "Welcome to HDFC Bank NetBanking");
	  
	  //Click Privacy Policy link
	  //Switch qua frame chua Privacy Policy link
	  driver.switchTo().frame(driver.findElement(By.xpath("//frame[@name ='footer']")));
	  driver.findElement(By.xpath("//a[text() = 'Privacy Policy']")).click();
	  switchToWindowByTitle("HDFC Bank - Leading Bank in India, Banking Services, Private Banking, Personal Loan, Car Loan");
	  Assert.assertEquals(driver.getTitle(), "HDFC Bank - Leading Bank in India, Banking Services, Private Banking, Personal Loan, Car Loan");
	  
	  
	  //Click CSR link on Privacy Policy page
	  driver.findElement(By.xpath("//a[text() = 'CSR']")).click();
	  Assert.assertEquals(driver.getTitle(), "HDFC BANK - CSR - Homepage");
	  
	  //Close add different popup  - just keep parent window
	  closeAllWindowsWithoutParentWindows(parentID);
  }
  
  @Test
  public void TC03_AddToCompare() {
	  driver.get("http://live.guru99.com/index.php/");
	  String parentID = driver.getWindowHandle();
	  
	  driver.findElement(By.xpath("//a[text() ='Mobile']")).click();
	  
	  //Add Sony Xperia and Samsung to compare
	  driver.findElement(By.xpath("//a[text() ='Sony Xperia']/parent::h2/following-sibling::div[@class ='actions']//a[text() ='Add to Compare']")).click();
	  driver.findElement(By.xpath("//a[text() ='Samsung Galaxy']/parent::h2/following-sibling::div[@class ='actions']//a[text() ='Add to Compare']")).click();
	  
	  driver.findElement(By.xpath("//button[@title = 'Compare']")).click();
	  switchToWindowByTitle("Products Comparison List - Magento Commerce");
	  Assert.assertEquals(driver.getTitle(), "Products Comparison List - Magento Commerce");
	  
	  closeAllWindowsWithoutParentWindows(parentID);
	  
  }
  
  //Dung trong truong hop co duy nhat 2 cua so
  public void switchToWindowByID(String parentID) {
	  //Get ra tat ca cac cua so dang co
      Set<String> allWindows = driver.getWindowHandles();
      // dung vong lap de kiem tra. Vong lap for x duyet qua tat ca cua so
      for (String runWindow : allWindows) {
    	  System.out.println(runWindow);
    	  // Moi lan duyet kiem tra dieu kien neu ID khac vs parentID thi no switch qua
                  if (!runWindow.equals(parentID)) {
                              driver.switchTo().window(runWindow);
                              //break khoi vong lap for khong kiem tra nua
                              break;
                  }
      }
  }
  
  //Dung trong truong hop co tu 2 cua so tro len
  public void switchToWindowByTitle(String expectedTitle) {
	//Get ra tat ca cac cua so dang co
      Set<String> allWindows = driver.getWindowHandles();
      // Dung vong lap duyet qua tat ca cac ID
      for (String runWindows : allWindows) {
    	  
    	  			//Switch qua tung cua so truoc sau do ms kiem tra
                  driver.switchTo().window(runWindows);
                  //Get ra title cua page moi switch
                  String actualTitle = driver.getTitle();
                  // Kiem tra neu title cua page = title truyen vao
                  if (actualTitle.equals(expectedTitle)) {
                	  //Thoat khoi vong lap- ko kiem tra nhung thang khac nua
                        break;
                  }
      }
  }
  
  public void closeAllWindowsWithoutParentWindows(String parentWindowID) {
	  //Get ra tat ca cac ID cua cac cua so
      Set<String> allWindows = driver.getWindowHandles();
      //Dung vong lap for de duyet qua tung ID
      for (String runWindows : allWindows) {
    	  // Neu ID khong bang parentID 
                  if (!runWindows.equals(parentWindowID)) {
                	  // No se switch qua
                              driver.switchTo().window(runWindows);
                              //Dong tab hien tai
                              driver.close();
                  }
      }
      //Chi con lai 1 cua so duy nhat (parent)
      driver.switchTo().window(parentWindowID);
      
  }
  
  public void clickElementByJavascript(WebElement element) {
	    JavascriptExecutor je = (JavascriptExecutor) driver;
	    je.executeScript("arguments[0].click();", element);
  }


  @AfterClass
  public void afterClass() {
	  driver.quit();
  }

}
