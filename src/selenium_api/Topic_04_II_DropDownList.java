package selenium_api;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_04_II_DropDownList {
	WebDriver driver;
	WebDriverWait waitExplicit;
	JavascriptExecutor javaExecutor;

	@BeforeClass
	public void beforeClass() {

		driver = new FirefoxDriver();
		waitExplicit = new WebDriverWait(driver, 30);
		javaExecutor = (JavascriptExecutor) driver;
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

	}

	
	public void TC_01_HTMLDropDownList() throws Exception {

		driver.get("https://daominhdam.github.io/basic-form/index.html");
		Select select = new Select(driver.findElement(By.xpath("//select[@id='job1']")));

		Assert.assertFalse(select.isMultiple());
		select.selectByVisibleText("Automation Tester");
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "Automation Tester");
		Thread.sleep(1000);

		select.selectByValue("manual");
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "Manual Tester");
		Thread.sleep(1000);

		select.selectByIndex(3);
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "Mobile Tester");
		Assert.assertEquals(select.getOptions().size(), 5);

	}

	@Test
	public void TC_02_HandleCustomDropDownList() throws Exception {

		//Jquery
		driver.get("http://jqueryui.com/resources/demos/selectmenu/default.html");
		
		selectItemInCustomDropDown("//span[@id='number-button']", "//ul[@id = 'number-menu']/li[@class='ui-menu-item']/div", "19");
		Assert.assertTrue(driver.findElement(By.xpath("//span[@id ='number-button']/span[@class= 'ui-selectmenu-text' and text() ='19'] ")).isDisplayed());
		Thread.sleep(2000);
		
		selectItemInCustomDropDown("//span[@id='number-button']", "//ul[@id = 'number-menu']/li[@class='ui-menu-item']/div", "15");
		Assert.assertTrue(driver.findElement(By.xpath("//span[@id ='number-button']/span[@class= 'ui-selectmenu-text' and text() ='15'] ")).isDisplayed());
		Thread.sleep(2000);
		
		selectItemInCustomDropDown("//span[@id='number-button']", "//ul[@id = 'number-menu']/li[@class='ui-menu-item']/div", "4");
		Assert.assertTrue(driver.findElement(By.xpath("//span[@id ='number-button']/span[@class= 'ui-selectmenu-text' and text() ='4'] ")).isDisplayed());
		Thread.sleep(2000);
		
		//Kendo-ui
		driver.get("https://demos.telerik.com/kendo-ui/dropdownlist/index");
		
		selectItemInCustomDropDown("//span[@aria-owns= 'color_listbox']", "//ul[@id ='color_listbox']/li", "Orange");
		Assert.assertTrue(driver.findElement(By.xpath("//span[@aria-owns= 'color_listbox']//span[@class = 'k-input' and text() = 'Orange']")).isDisplayed());
		Thread.sleep(2000);
		
		selectItemInCustomDropDown("//span[@aria-owns= 'color_listbox']", "//ul[@id ='color_listbox']/li", "Grey");
		Assert.assertTrue(driver.findElement(By.xpath("//span[@aria-owns= 'color_listbox']//span[@class = 'k-input' and text() = 'Grey']")).isDisplayed());
		Thread.sleep(2000);
		
		selectItemInCustomDropDown("//span[@aria-owns= 'color_listbox']", "//ul[@id ='color_listbox']/li", "Black");
		Assert.assertTrue(driver.findElement(By.xpath("//span[@aria-owns= 'color_listbox']//span[@class = 'k-input' and text() = 'Black']")).isDisplayed());
		Thread.sleep(2000);
		
		
		
	}

	public void selectItemInCustomDropDown(String parentXpath, String childXpath, String expectedItem) {

		// Click vào dropdown
		WebElement element = driver.findElement(By.xpath(parentXpath));
		javaExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
		element.click();

		// Get tất cả item trong dropdown vào 1 list element (List <WebElement>)
		List<WebElement> childList = driver.findElements(By.xpath(childXpath));

		// Wait để tất cả phần tử trong dropdown được hiển thị
		waitExplicit.until(ExpectedConditions.visibilityOfAllElements(childList));

		// Dùng vòng lặp for duyệt qua từng phần tử sau đó getText
		for (WebElement child : childList) {
			String textItem = child.getText();
			System.out.println("Text in drop dow =" + textItem);

			// Nếu actual text = expected text thì click vào phần tử đó và break khỏi vòng
			// lặp
			if (textItem.equals(expectedItem)) {
				javaExecutor.executeScript("arguments[0].scrollIntoView(true);", child);
				child.click();
				break;
			}
		}

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}