package selenium_test;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import selenium.selenium_pages.SignUpPage;

public class SignupPageTest {
	private WebDriver driver;
	private SignUpPage signup;
	
	@BeforeClass
	public void OpenDriver() {
		driver = new FirefoxDriver();
		driver.get("https://user.wolfram.com/wolframid/registration/cloud?logo=wolfram_development_platform&product=Wolfram%20Development%20Platform&callback=https%3A%2F%2Fdevelop.wolframcloud.com%2Fapp&learnabout=http://www.wolfram.com/development-platform");
		signup = PageFactory.initElements(driver, SignUpPage.class);
	}
	
	@AfterClass
	public void CloseDriver() {
		driver.close();
		driver.quit();
	}
	
	@Test(priority=0)
	public void confirmIfSignUpPage(){
		// you want to run this in the beginning
		Assert.assertTrue(signup.isSignUpPageLoaded());
	}
	@Test(priority=1)
	public void testWhenFieldsAreEmpty() {
		signup.clearAllFields();
		signup.clickButton();
		List<String> errors = signup.getErrors();
		// when all fields are empty errors list is greater than 2
		// the best way to do it is to have a list of all expected values and compare it with the actual received strings
		Assert.assertNotEquals(2,errors.size());
	}
	
	@Test(priority=1)
	public void testEmailValidity() {
		signup.clearAllFields();
		signup.enterId("123@abc");
		signup.clickButton();
		List<String> errors = signup.getErrors();
		Assert.assertTrue(TestHelper.isStringPresent(errors, "Please enter a valid email address in the format someone@example.com."));
	}
	
	@Test(priority=1)
	public void testPasswordValidity() {
		// test the password size is less than 6
		signup.clearAllFields();
		signup.enterId("jane@doe.com");
		signup.enterPassword("1234567");
		signup.reenterPassword("12345");
		signup.clickButton();
		List<String> errors = signup.getErrors();
		
		// when all fields are empty errors list is greater than 2
		Assert.assertTrue(TestHelper.isStringPresent(errors, "Passwords do not match; please enter a password and reenter to confirm.\nPasswords must contain at least 6 characters and no spaces."));
	}
	@Test(priority=1)
	public void verifyPasswordsMatch() {
		// to check that programs returns an error when two passwords do not match
		signup.clearAllFields();
		signup.enterId("jane@doe.com");
		signup.enterPassword("12345");
		signup.reenterPassword("1235");
		signup.clickButton();
		List<String> errors = signup.getErrors();
		System.out.println("Helloooo"+errors+"  "+errors.size());
		
		Assert.assertTrue(TestHelper.isStringPresent(errors, "Passwords do not match; please enter a password and reenter to confirm.\nPasswords must contain at least 6 characters and no spaces."));
	}
	@Test(priority=1)
	public void checkFirstNameAndLastNameFieldValiditywithNumbers(){
		signup.clearAllFields();
		signup.enterId("jane@doe.com");
		signup.enterFirstName("12345");
		signup.enterLastName("56386");
		signup.clickButton();
		List<String> errors = signup.getErrors();
		// given page accepts numbers into the firstname and lastname fields
		Assert.assertFalse(TestHelper.isStringPresent(errors, "Please enter your last name."));
		Assert.assertFalse(TestHelper.isStringPresent(errors, "Please enter your first name."));
	}
	@Test(priority=1)
	public void checkFirstNameAndLastNameFieldValiditywithSpecialChars(){
		signup.clearAllFields();
		signup.enterId("jane@doe.com");
		signup.enterFirstName("%%&$&(^");
		signup.enterLastName("%%&$&(^");
		signup.clickButton();
		List<String> errors = signup.getErrors();
		// given page accepts numbers into the firstname and lastname fields
		Assert.assertFalse(TestHelper.isStringPresent(errors, "Please enter your last name."));
		Assert.assertFalse(TestHelper.isStringPresent(errors, "Please enter your first name."));
	}
	
	@Test(priority=2)
	public void completeSignUpProcess(){
		// you want to run this test in the last
		signup.clearAllFields();
		signup.enterId("manish.vr111@gmail.com");
		signup.enterFirstName("Manish Kumar");
		signup.enterLastName("Vuttunoori");
		signup.enterPassword("qwerty126");
		signup.reenterPassword("qwerty126");
		signup.clickButton();
		// must go to home page but wont as the mail id used here is already registered for an account, so just check for single error
		Assert.assertEquals(1, signup.getErrors().size());
	}

}