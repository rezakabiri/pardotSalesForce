package pardotProject;

import org.testng.annotations.Test;

import pardotProject.logInPage;
import pardotProject.mainPage;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.Assert;
import org.testng.Reporter;

public class pardotTestSuite {
	
	private WebDriver driver;
	private String baseURL;
	private String firstListName;
		
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
//		System.setProperty("webdriver.chrome.driver", "D:\\CourseWorks\\Selenium\\workSpace\\selleniumPractice\\bin\\chromedriver.exe");
//		driver = new ChromeDriver();
		// define the base URL here
		baseURL = "https://pi.pardot.com/";
		
		// manage browser window
//		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		
		// load the baseURL
		driver.get(baseURL);
	}
	
	@Test
	public void logIn() {
		
		Reporter.log("logging in ... \n");
		String userName = "pardot.applicant@pardot.com";
		String passWord = "Applicant2012";
		
		logInPage.fillUserName(driver,  userName);
		logInPage.fillPassWord(driver, passWord);
		logInPage.clickLogin(driver);
		
		Assert.assertFalse(logInPage.alertExists(driver));
	}
	
	@Test(dependsOnMethods={"logIn"})
	public void createFirstList() throws Exception{
				
		this.firstListName = mainPage.generateRandomName("Jezi");
		
		mainPage.navigateToListPage(driver);
		mainPage.createNewList(driver, firstListName);
		Thread.sleep(1000);
		mainPage.navigateToMainPage(driver);
	}
	
	@Test(dependsOnMethods={"createFirstList"})
	public void attempToCreateAnotherList() throws Exception{
				
		mainPage.navigateToListPage(driver);
		mainPage.createNewList(driver, firstListName);
		Thread.sleep(1000);
		Assert.assertTrue(mainPage.isExistError(driver));
		
		Thread.sleep(1000);
		mainPage.cancelListInformation(driver);
	}
	
	@Test(dependsOnMethods={"attempToCreateAnotherList"})
	public void renameTheOriginalList() throws Exception{
		mainPage.navigateToListPage(driver);
		mainPage.renameOldTest(driver, firstListName);
	}
	
	// This step is not working properly due to one of the issue that WebDriver is not able to find an element
	@Test(dependsOnMethods={"renameTheOriginalList"})
	public void createNewProspet() throws Exception{
		Thread.sleep(2000);
		String emailAddress = "pardot.applicant.2610@pardot.com";
		mainPage.createNewProspect(driver, emailAddress);
	}
	
	@Test(dependsOnMethods={"renameTheOriginalList"})
	public void sendEmail() throws Exception{
		mainPage.sendEmail(driver);
	}
	
	@Test(dependsOnMethods={"sendEmail"})
	public void logOut() throws Exception{
		mainPage.logOut(driver);
	}
	
}