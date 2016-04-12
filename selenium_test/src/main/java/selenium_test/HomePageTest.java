package selenium_test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import selenium.selenium_pages.FilePage;
import selenium.selenium_pages.HomePage;
import selenium.selenium_pages.MainPage;
import selenium.selenium_pages.SignInPage;

public class HomePageTest {
	// contains tests to check that user is logged in into correct page and account.
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
		//write the email here
		this.id = "jane.doe@gmail.com";
		// write the password here
		this.password = "*******";
		signIn.enterId(this.id);
		signIn.enterPassword(this.password);
		signIn.goTologinPage();
		// create HomePage's object using PageFactory
		homePage = PageFactory.initElements(driver,HomePage.class);
	}
	
	@AfterClass
	public void CloseDriver() {
		//signout of the homePage
		homePage.signOut();
		// close the drivers
		driver.close();
		driver.quit();
	}
	@Test(priority=0)
	public void confirmHomePage(){
		// isHomePageLoaded returns a boolean value after comparing page's current title with the expected title within the class.
		Assert.assertTrue(homePage.isHomePageLoaded());
	}
	@Test(priority=1)
	public void isCorrectAccountOpened(){
		//getAccountMailId() returns the mailid of the account whose session is currently running 
		Assert.assertEquals(homePage.getAccountMailId(), this.id);
	}
	
}
