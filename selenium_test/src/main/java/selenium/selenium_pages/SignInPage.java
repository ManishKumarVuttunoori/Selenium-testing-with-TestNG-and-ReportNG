package selenium.selenium_pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SignInPage{

	private WebDriver driver;
	private WebDriverWait wait;
	
	@FindBy(css="#createAccount")private WebElement createButton;
	@FindBy(css="#email")private WebElement signinEmail;
	@FindBy(css="#password")private WebElement signinPassword;
	@FindBy(css="#signIn")private WebElement signinButton;
	@FindBy(css="#signInform")private WebElement formButton;
	
	public SignInPage(WebDriver driver){
		this.driver = driver;
		this.wait = new WebDriverWait(driver,20);
		PageFactory.initElements(driver, this);
		wait.until(ExpectedConditions.elementToBeClickable(createButton));
	}
	public void enterId(String id)
	{
		this.signinEmail.clear();
		this.signinEmail.sendKeys(id);
	}
	public void enterPassword(String password)
	{
		this.signinPassword.clear();
		this.signinPassword.sendKeys(password);
	}
	
	public Boolean isSignInPageLoaded(){
		return driver.getTitle().contains("Sign In - Wolfram Development Platform");
	}
	
	public void goTologinPage(){
		
		signinButton.click();
		signinButton.submit();
		
	}
	public void clickSubmit() throws Exception{
		
		wait.until(ExpectedConditions.elementToBeClickable(createButton));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("var evt = document.createEvent('MouseEvents');" + "evt.initMouseEvent('click',true, true, window, 0, 0, 0, 0, 0, false, false, false, false, 0,null);" + "arguments[0].dispatchEvent(evt);",createButton);
	}
}
