package selenium_api;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic_10_UploadFile {
	WebDriver driver;
	
	/*Get ra duong dan tai thu muc dang dung
	 * E:\Mai\Automation testing\Setup\Eclipse oxygen\workspace\WEBDRIVER_API_07_MAITT*/
	String rootFolder = System.getProperty("user.dir");
	
	String fileName01 ="01.png";
	String fileName02 ="02.png";
	String fileName03 ="03.png";
	String filePath01 = rootFolder + "\\uploadFile\\" + fileName01;
	String filePath02 = rootFolder + "\\uploadFile\\" + fileName02;
	String filePath03 = rootFolder + "\\uploadFile\\" + fileName03;
 
  @BeforeClass
  public void beforeClass() {
	
		/*System.setProperty("webdriver.chrome.driver", ".\\lib\\chromedriver.exe");
		driver = new ChromeDriver();*/
			
	  	System.out.println(rootFolder);
	  	System.out.println(filePath01);
	  	System.out.println(filePath02);
	  	System.out.println(filePath03);
	  
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			
  }
  
  
  public void TC_01_SingleUpload() throws Exception {
	  driver.get("http://blueimp.github.io/jQuery-File-Upload/");
	  
	  WebElement addFileButton = driver.findElement(By.xpath("//input[@type ='file']"));
	  addFileButton.sendKeys(filePath01);
	  
	 Thread.sleep(4000);
	}
  
  @Test
  public void TC_02_MultipleUpload() throws Exception {
	  driver.get("http://blueimp.github.io/jQuery-File-Upload/");
	  
	  WebElement addFileButton = driver.findElement(By.xpath("//input[@type ='file']"));
	  addFileButton.sendKeys(filePath01 + "\n" + filePath02  + "\n" + filePath03);
	  
	 Thread.sleep(4000);
	}


  @AfterClass
  public void afterClass() {
	  driver.quit();
  }

}
