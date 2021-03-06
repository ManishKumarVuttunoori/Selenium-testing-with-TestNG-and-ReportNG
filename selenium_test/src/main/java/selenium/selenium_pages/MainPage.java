package selenium.selenium_pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage {
	
	private WebDriver driver;
	private static String PAGE_URL = "http://www.wolframcloud.com/";
	private WebDriverWait wait;
	
	public MainPage(WebDriver driver)
	{
		this.driver = driver;
		this.driver.get(PAGE_URL);
		wait = new WebDriverWait(driver,20);
		PageFactory.initElements(driver,this);
		
	}
	public Boolean isMainPageLoaded(){
		return driver.getTitle().contains("Wolfram Cloud");
	}
	//locators needed for the page
	@FindBy(tagName="iframe")private WebElement iframe;
	@FindBy(id="wdp-tile")private WebElement devButton;
	
	public void goToSignInPage(){
		driver.switchTo().frame(iframe);   // switches to iframe
		wait.until(ExpectedConditions.elementToBeClickable(devButton));
		devButton.click();
		
		
	}
	
}
