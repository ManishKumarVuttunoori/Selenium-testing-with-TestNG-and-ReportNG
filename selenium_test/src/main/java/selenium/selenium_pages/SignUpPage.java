package selenium.selenium_pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SignUpPage {
	// Page for the SignUp Page
	private WebDriver driver;
	private WebDriverWait wait;
	
	public SignUpPage(WebDriver driver){
		this.driver = driver;
		this.wait = new WebDriverWait(driver,20);
		PageFactory.initElements(this.driver, this);
		this.wait.until(ExpectedConditions.elementToBeClickable(signinButton));
	}
	//Locators
	@FindBy(css="#email")private WebElement wolframId;
	@FindBy(css="#firstname")private WebElement firstName;
	@FindBy(css="#lastname")private WebElement lastName;
	@FindBy(css="#password")private WebElement password;
	@FindBy(css="#password2")private WebElement confPassword;
	@FindBy(css="#signIn")private WebElement signinButton;
	@FindBy(css="#registration-form")private WebElement formButton;
	@FindBy(css=".error")private List<WebElement> validationErrors;
	
	public Boolean isSignUpPageLoaded(){

		return driver.getTitle().toString().contains("Sign Up - Wolfram Development Platform");
	}
	public void clickSubmit(){
		signinButton.click();
		formButton.submit();
	}
	
	public void enterId(String id){
		
		this.wolframId.clear();
		this.wolframId.sendKeys(id);
	}
	
	public void enterFirstName(String firstName)
	{
		this.firstName.clear();
		this.firstName.sendKeys(firstName);
	}
	public void enterLastName(String lastName)
	{
		this.lastName.clear();
		this.lastName.sendKeys(lastName);
	}
	public void enterPassword(String password)
	{
		this.password.clear();
		this.password.sendKeys(password);
	}
	
	public void reenterPassword(String password)
	{
		this.confPassword.clear();
		this.confPassword.sendKeys(password);
	}
	
	public List<String> getErrors(){
		List<String> errors = new ArrayList<String>();
		for(WebElement elem: validationErrors)
		{
			errors.add(elem.getText().toString());
		}
		return errors;
	}
	// clears all input fields
	public void clearAllFields(){
		this.firstName.clear();
		this.lastName.clear();
		this.wolframId.clear();
		this.password.clear();
		this.confPassword.clear();
	}
}
