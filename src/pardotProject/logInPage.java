package pardotProject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

/**
 * @author Reza Kabiri
 *
 */
public class logInPage {
	
	static private WebElement element = null;
		
	/** fills the user name field in the log in page
	 * @param driver
	 * @param userName
	 */
	public static void fillUserName(WebDriver driver, String userName){
		element = driver.findElement(By.id("email_address"));
		element.clear();
		element.sendKeys(userName);
		Reporter.log("User Name is now entered! \n");}
	
	/** fills the pass word field in the log in page
	 * @param driver
	 * @param passWord
	 */
	public static void fillPassWord(WebDriver driver, String passWord){
		element = driver.findElement(By.id("password"));
		element.clear();
		element.sendKeys(passWord);
		Reporter.log("Password is now entered!! \n");}
	
	
	
	/** clicks on the 'Log in' button
	 * @param driver
	 */
	public static void clickLogin(WebDriver driver){
		driver.findElement(By.cssSelector(".btn.btn-primary")).click();
		Reporter.log("'Log in' button is now clicked!!! \n");}
	
	
	/** checks if the 
	 * @param driver
	 * @return
	 */
	public static boolean alertExists(WebDriver driver){
		Reporter.log("checking for alerts ...");
		if (driver.findElements(By.cssSelector(".alert.alert-error")).size() == 0){
			Reporter.log("no alerts were found! Log In was successful. \n");
			return false;			
		}
		Reporter.log("alerts were found! Log In was not successful. \n");
		return true;
		
	}

}

