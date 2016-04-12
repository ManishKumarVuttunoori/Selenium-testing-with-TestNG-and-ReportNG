package selenium.selenium_pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {
	private WebDriver driver;
	private WebDriverWait wait;
	
	public HomePage(WebDriver driver){
		this.driver = driver;
		this.wait = new WebDriverWait(driver,20);
		PageFactory.initElements(this.driver, this);
		this.wait.until(ExpectedConditions.elementToBeClickable(dropdown));
	}
	
	@FindBy(css="#newNotebookBtn > div.newNotebookBtn-dropdown")private WebElement dropdown;
	@FindBy(css="#nb")private WebElement noteBook;
	@FindBy(css="")private WebElement fileField;
	@FindBy(css="#header-account")private WebElement accountButton;
	@FindBy(css="#account-signout")private WebElement signoutButton;
	@FindBy(css="div.account-email")private WebElement accountMail;
	public Boolean isHomePageLoaded(){
		return driver.getTitle().toString().contains("Home - Wolfram Development Platform");
	}
	
	public void clickDropDown(){
		clickElement(dropdown);
	}
	
	public void createNoteBook(){
		clickDropDown();
		clickElement(this.noteBook);
	}
	private void clickElement(WebElement element){
		wait.until(ExpectedConditions.elementToBeClickable(element));
		element.click();
	}
	public void signOut(){
		clickElement(accountButton);
		clickElement(signoutButton);
	}
	public String getAccountMailId(){
		clickElement(accountButton);
		String mailId= accountMail.getAttribute("title").toString().trim();
		clickElement(accountButton);
		return mailId;
	}
}
