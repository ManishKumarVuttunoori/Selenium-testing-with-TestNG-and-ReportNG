package selenium.selenium_pages;

import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FilePage {
	private WebDriver driver;
	private WebDriverWait wait;
	
	public FilePage(WebDriver driver)
	{
		
		PageFactory.initElements(driver, this);
		String tempHandle = driver.getWindowHandle();
		String  handle =""; 
		Set<String> handles = driver.getWindowHandles();
		for(String hnd: handles)
		{
			if(!hnd.equals(tempHandle))
				handle = hnd;
		}
		driver.switchTo().window(handle);
		this.driver = driver;
		this.wait = new WebDriverWait(driver,20);
		wait.until(ExpectedConditions.elementToBeClickable(divField));
		
	}
	@FindBy(css="#header-account")private WebElement accountButton;
	@FindBy(css="#account-signout")private WebElement signoutButton;
	@FindBy(css="#renameButton")private WebElement divField;
	@FindBy(css="#toolbarRenameInputField")private WebElement renameField;
	
	public Boolean isFilePageLoaded(){
		return driver.getTitle().toString().contains("(unnamed) - Wolfram Development Platform");
	}
	
	public Boolean verifyNBExists(){
		clickElement(divField);
		return renameField.getAttribute("value").equals(".nb");
	}
	
	private void clickElement(WebElement element){
		wait.until(ExpectedConditions.elementToBeClickable(element));
		element.click();
	}
	public void signOut(){
		clickElement(accountButton);
		clickElement(signoutButton);
	}
}
