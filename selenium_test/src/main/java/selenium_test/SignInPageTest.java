package selenium_test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import selenium.selenium_pages.MainPage;
import selenium.selenium_pages.SignInPage;

public class SignInPageTest {
	
	private WebDriver driver;
	private SignInPage signIn;
	
	@BeforeClass
	public void openDriver(){
		driver = new FirefoxDriver();
		driver.get("http://www.wolframcloud.com");
		MainPage mainPage = PageFactory.initElements(driver,MainPage.class);
		mainPage.goToSignInPage();
		signIn = PageFactory.initElements(driver, SignInPage.class);
	}
	
	@AfterClass
	public void closeDriver(){
		driver.close();
		driver.quit();
	}
	@Test(priority=0)
	public void confirmSignInPage(){
	Assert.assertTrue(signIn.isSignInPageLoaded());	
	}
	
}
