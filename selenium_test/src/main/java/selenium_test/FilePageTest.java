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

public class FilePageTest {
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
		this.id = "manish.vr111@gmail.com";
		this.password = "pinkman111";
		signIn.enterId(this.id);
		signIn.enterPassword(this.password);
		signIn.goTologinPage();
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		homePage.createNoteBook();
		filePage = PageFactory.initElements(driver,FilePage.class);
	}
	@AfterClass
	public void closeDriver(){
		filePage.signOut();
		driver.close();
		driver.quit();
	}
	
	@Test(priority=0)
	public void confirmFilePage(){
		Assert.assertTrue(filePage.isFilePageLoaded());
	}
	
	@Test(priority=1)
	public void checkIfNewBookIsCreated()
	{
		Assert.assertTrue(filePage.verifyNBExists());
	}
}
