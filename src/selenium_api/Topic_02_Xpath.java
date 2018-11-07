package selenium_api;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_02_Xpath {
	WebDriver driver;
	
 
  @BeforeClass
  public void beforeClass() {

				// Firefox
				driver = new FirefoxDriver();
				driver.manage().window().maximize();
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			
  }
  
  @Test
  public void TC_01_VerifyUrlAndTitle() {
	  
	  //Step 01 - Truy cập vào trang: http://live.guru99.com/
	  driver.get("http://live.guru99.com/");
	 
	  //Step 02 - Kiểm tra title của page là: "Home page"
	  String homePageTitle = driver.getTitle();
	  Assert.assertEquals(homePageTitle, "Home page");
	  Assert.assertTrue(homePageTitle.equals("Home page"));
	  
	  //Step 03 - Click vào link "My Account" để tới trang đăng nhập
	  driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
	  
	  //Step 04 - Click CREATE AN ACCOUNT button để tới trang đăng kí tài khoản
	  driver.findElement(By.xpath("//span[text()='Create an Account']")).click();
	  
	  //Step 05 - Back lại trang đăng nhập
	  driver.navigate().back();
	  
	  //Step 06 - Kiểm tra url của page đăng nhập là: http://live.guru99.com/index.php/customer/account/login/
	  String loginUrl = driver.getCurrentUrl();
	  Assert.assertEquals(loginUrl, "http://live.guru99.com/index.php/customer/account/login/");
	  
	  //Step 07 - Forward tới trang tạo tài khoản
	  driver.navigate().forward();
	  
	  //Step 08 - Kiểm tra url của page tạo tài khoản là: http://live.guru99.com/index.php/customer/account/create/
	  String createAccountUrl = driver.getCurrentUrl();
	  Assert.assertEquals(createAccountUrl, "http://live.guru99.com/index.php/customer/account/create/");
	}

  @Test
  public void TC_02_LoginEmpty() {
	  
	  //Step 01 - Truy cập vào trang: http://live.guru99.com/
	  driver.get("http://live.guru99.com/");
	  
	  //Step 02 - Click vào link "My Account" để tới trang đăng nhập
	  driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
	  
	  //Step 03 - Để trống Username/ Password
	  driver.findElement(By.name("login[username]")).sendKeys("");
	  driver.findElement(By.name("login[password]")).sendKeys("");
	  
	 
	  //Step 04 - Click Login button
	  driver.findElement(By.id("send2")).click();
	  
	  //Step 05 - Verify error message xuất hiện tại 2 field:  This is a required field.
	  String emailErrorMessage = driver.findElement(By.xpath("//div[@id='advice-required-entry-email']")).getText();
	  Assert.assertEquals(emailErrorMessage, "This is a required field");
	  
	  String passwordErrorMessage = driver.findElement(By.id("advice-required-entry-pass")).getText();
	  Assert.assertEquals(passwordErrorMessage, "This is a required field");
	}
  
  @Test
  public void TC_03_EmailIncorrectAndPasswordValid() {
	  
	  //Step 01 - Truy cập vào trang: http://live.guru99.com/
	  driver.get("http://live.guru99.com/");
	  
	  //Step 02 - Click vào link "My Account" để tới trang đăng nhập
	  driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
	  
	  //Step 03 - Nhập email invalid: 123434234@12312.123123
	  driver.findElement(By.name("login[username]")).sendKeys("123434234@12312.123123");
	  driver.findElement(By.name("login[password]")).sendKeys("");
	  
	  //Step 04 - Click Login button
	  driver.findElement(By.id("send2")).click();
	  
	  //Step 05 - Verify error message xuất hiện:  Please enter a valid email address. For example johndoe@domain.com.
	  String emailInvalidMessage = driver.findElement(By.xpath("//div[@id='advice-validate-email-email']")).getText();
	  Assert.assertEquals(emailInvalidMessage, "Please enter a valid email address. For example johndoe@domain.com.");
	  
	}


  @Test
  public void TC_04_EmailCorrectAndPasswordLessThan6Chars() {
	 
	  
	}
  
  @Test
  public void TC_05_EmailCorrectAndPasswordIncorrect() {
	 
	  
	}
  
  @Test
  public void TC_06_RegisterToSystem() {
	 
	  
	}



  @AfterClass
  public void afterClass() {
	  driver.quit();
  }

}
