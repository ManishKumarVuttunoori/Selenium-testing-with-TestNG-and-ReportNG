package selenium_test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import selenium.selenium_pages.FilePage;
import selenium.selenium_pages.HomePage;
import selenium.selenium_pages.MainPage;
import selenium.selenium_pages.SignInPage;
import selenium.selenium_pages.SignUpPage;

/**
 * Hello world!
 *
 */
public class App_Test
{
    public static void main( String[] args )
    {
    	WebDriver driver = new FirefoxDriver();
        MainPage page1 = new MainPage(driver);
        page1.goToSignInPage();
        SignInPage signIn = new SignInPage(driver);
        try {
        	signIn.enterId("manish.vr111@gmail.com");
        	signIn.enterPassword("pinkman111");
        	signIn.goTologinPage();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
        HomePage home = new HomePage(driver);
        home.createNoteBook();
        FilePage file = new FilePage(driver);
        System.out.println(file.verifyNBExists());
        file.signOut();
        driver.close();
        
        /*SignUpPage signUp =null;
        try {
        	signUp = new SignUpPage(driver);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
       if(signUp!=null){
    	   
	        signUp.enterId("manish.vr111@gmail.com");
	        signUp.enterFirstName("Manish Kumar");
	        signUp.enterLastName("Vuttunoori");
	        signUp.enterPassword("pinkman111");
	        signUp.reenterPassword("pinkman111");
	        signUp.clickButton();   
       }  */

    }
}
