package selenium_test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import selenium.selenium_pages.MainPage;


public class MainPageTest {
	private WebDriver driver;
	private MainPage mainPage;
	
	@BeforeClass
	public void OpenDriver() {
		driver = new FirefoxDriver();
		mainPage = PageFactory.initElements(driver,MainPage.class);
	}
	
	@AfterClass
	public void CloseDriver() {
		driver.close();
		driver = null;
		mainPage=null;
	}
	
	@Test(priority=0)
	public void confirmMainPage(){		
		Assert.assertTrue(mainPage.isMainPageLoaded());
	}
}
