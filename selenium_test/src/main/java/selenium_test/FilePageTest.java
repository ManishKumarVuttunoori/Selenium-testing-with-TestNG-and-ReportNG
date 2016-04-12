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

public class FilePageTest { // contains tests that verify the .nb extension as per given procedure
	private WebDriver driver;
	private FilePage filePage;
	private String id;
	private String password;
	@BeforeClass
	public void openDriver(){
		driver = new FirefoxDriver();
		driver.get("http://www.wolframcloud.com");
		MainPage mainPage = PageFactory.initElements(driver, MainPage.class);
		mainPage.goToSignInPage();
		SignInPage signIn = PageFactory.initElements(driver,SignInPage.class);
		// write the email here to login
		this.id = "jane.doe@gmail.com";
		// write the password here
		this.password = "********";   
		signIn.enterId(this.id);
		signIn.enterPassword(this.password);
		// click the signIn button
		signIn.goTologinPage();
		// create HomePage's object using PageFactory
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		homePage.createNoteBook();
		// create FilePage's object using PageFactory
		filePage = PageFactory.initElements(driver,FilePage.class);
	}
	@AfterClass
	public void closeDriver(){
		//sign out from the account
		filePage.signOut();
		// close the drivers
		driver.close();
		driver.quit();
	}
	
	@Test(priority=0)
	public void confirmFilePage(){
		// isFilePageLoaded returns a boolean value after comparing page's current title with the expected title within the class.
		Assert.assertTrue(filePage.isFilePageLoaded());
	}
	
	@Test(priority=1)
	public void checkIfNewBookIsCreated()
	{	// verifyNBExists returns a boolean value by comparing with ".nb" with the value it gets from the file name field
		Assert.assertTrue(filePage.verifyNBExists());
	}
}
