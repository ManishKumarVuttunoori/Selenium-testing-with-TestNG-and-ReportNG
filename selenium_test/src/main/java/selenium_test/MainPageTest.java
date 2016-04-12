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
		//load mainPage using PageFactory
		mainPage = PageFactory.initElements(driver,MainPage.class);
	}
	
	@AfterClass
	public void CloseDriver() {
		//close the drivers
		driver.close();
		driver.quit();
	}
	
	@Test(priority=0)
	public void confirmMainPage(){
		// isMainPageLoaded returns a boolean value after comparing page's current title with the expected title within the class.
		Assert.assertTrue(mainPage.isMainPageLoaded());
	}
}
