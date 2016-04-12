package selenium_test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import selenium.selenium_pages.HomePage;
import selenium.selenium_pages.MainPage;
import selenium.selenium_pages.SignInPage;

public class HomePageTest {
	private WebDriver driver;
	private HomePage homePage;
	private String id;
	private String password;
	
	@BeforeClass
	public void OpenDriver() {
		driver = new FirefoxDriver();
		//must sign in to perform the tests
		driver.get("http://www.wolframcloud.com/");
		MainPage mainPage = PageFactory.initElements(driver, MainPage.class);
		mainPage.goToSignInPage();
		SignInPage signIn = PageFactory.initElements(driver,SignInPage.class);
		this.id = "manish.vr111@gmail.com";
		this.password = "pinkman111";
		signIn.enterId(this.id);
		signIn.enterPassword(this.password);
		signIn.goTologinPage();
		homePage = PageFactory.initElements(driver,HomePage.class);
	}
	
	@AfterClass
	public void CloseDriver() {
		//signout of the homePage
		homePage.signOut();
		// close the drivers
		driver.close();
		driver.quit();
		driver = null;
		homePage=null;
	}
	@Test(priority=0)
	public void confirmHomePage(){
		Assert.assertTrue(homePage.isHomePageLoaded());
	}
	@Test(priority=1)
	public void isCorrectAccountOpened(){
		Assert.assertEquals(homePage.getAccountMailId(), this.id);
	}
	
}
