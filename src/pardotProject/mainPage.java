package pardotProject;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

public class mainPage {
	
	public static WebElement element = null;
	
	
	public static String generateRandomName(String Name){
		Reporter.log("generating a random name .... \n");
		int randNum = (int) (Math.random()*10000 + 1);
		String name = Name+String.valueOf(randNum);
		return name;
	}
	
	public static void navigateToListPage(WebDriver driver) throws Exception{
		
		Actions action = new Actions(driver);
		WebDriverWait wait = new WebDriverWait(driver, 30);
		
		Thread.sleep(1000);
		// click on Marketing icon
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("mark-tog")));
		WebElement marketingElement = driver.findElement(By.id("mark-tog"));
		action.moveToElement(marketingElement).click().perform();
		
		// click on Segmentation
		WebElement segmentationElement = driver.findElement(By.cssSelector(".dropdown-submenu>a[href='/segmentation']"));
		action.moveToElement(segmentationElement).perform();
		
		// click on List Element
		// oh my goodness this element is killing me!!!!!
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[text()='Lists']")));
		
		// Strangely by only we don't need to click on anything here!!!
		element = driver.findElement(By.xpath("//a[text()='Lists']"));
		element.click();
		
//		WebElement listsElement = driver.findElement(By.cssSelector("a[href='/list']"));
//		action.moveToElement(listsElement).click().perform();
		Reporter.log("navigated to 'Lists' page \n");		
	}
	
	public static void navigateToMainPage(WebDriver driver){
		
		Actions action = new Actions(driver);
		// finds the salesForce cloud icons and click it
		element = driver.findElement(By.cssSelector("a[class='logo brand']"));
		
		action.moveToElement(element).click().build().perform();
	}
	
	public static void createNewList(WebDriver driver, String fileName) throws Exception{
		
		Actions action = new Actions(driver);
		WebDriverWait wait = new WebDriverWait(driver, 30);
		
		// click on "Add List" button
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("listxistx_link_create")));
		element = driver.findElement(By.id("listxistx_link_create"));
		Thread.sleep(2000);
		action.moveToElement(element).click().perform();
		
		// input name
		element = driver.findElement(By.id("name"));
		element.clear();
		element.sendKeys(fileName);
		
		// click on folder field
		element = driver.findElement(By.cssSelector("#li_form_update span[style='cursor: pointer;']"));
		element.click();
		
		// click on "email test" folder
		element = driver.findElement(By.cssSelector("a[href='/7726']"));
		element.click();
		
		// click on "reza" folder
		element = driver.findElement(By.cssSelector("a[href='/10576']"));
		element.click();
		
		// click choose folder
		driver.findElement(By.id("select-asset")).click();
		
		// click create list
		driver.findElement(By.id("save_information")).click();
	}
	
	public static boolean isExistError(WebDriver driver){
		// find all the elements and count them
		List<WebElement> elems = driver.findElements(By.cssSelector(".icon-warning-sign"));
		if (elems.size() >0 ){
			return true;
		}
		return false;
	}
	
	public static void cancelListInformation(WebDriver driver){
		driver.findElement(By.cssSelector(".btn.btn-default")).click();
	}
	
	/** fills the "Filter" field in "Lists" page to search for existing lists
	 * @param driver
	 * @param listName
	 */
	public static void fillSearchText(WebDriver driver, String listName){
		driver.findElement(By.id("listx_table_filter")).sendKeys(listName);
	}
	
	public static void renameOldTest(WebDriver driver, String listName) throws Exception{
		Actions action = new Actions(driver);
		
		// search for the list in this page
		mainPage.fillSearchText(driver, listName);
		
		// find the oldList and click on it
		WebDriverWait wait = new WebDriverWait(driver, 30);
		String xpathSelectorString = "//a[text()='"+listName+"']";
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpathSelectorString)));
		element = driver.findElement(By.xpath(xpathSelectorString));
		action.moveToElement(element).click().perform();
		
		// click on "Edit" button
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[text()='Edit']")));
		action.moveToElement(driver.findElement(By.xpath("//a[text()='Edit']"))).click().perform();
		
		// fill in the new name 
		action.moveToElement(driver.findElement(By.id("name"))).click().sendKeys("New");
		
		// click on the save button
		action.moveToElement(driver.findElement(By.id("save_information"))).click().build().perform();
	}
	
	public static void createNewProspect(WebDriver driver, String emailAddress) throws Exception{
		Actions action = new Actions(driver);
		
		// NOTE!!!! could not find the element to click on it! navigating to the prospect page directly !!!!
//		WebDriverWait wait = new WebDriverWait(driver, 30);
//		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#pro-tog>span")));
//		action.moveToElement(driver.findElement(By.xpath("//span[text()='Prospects']"))).click().perform();
//		action.moveToElement(driver.findElement(By.xpath("//a[@href='/prospect']"))).click().perform();
		driver.navigate().to("https://pi.pardot.com/prospect");
				
		// NOTE!!!!! Could not find the element to click on it and add the new prospect
		// explicit wait to see if I can catch the element
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='Add prospect']")));
		// implicit wait to see if the element I can find the element
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		WebElement element2 = driver.findElement(By.xpath("//a[text()='Add prospect']"));
//		WebElement element2 = driver.findElement(By.xpath("//a[@id='pr_link_create']"));
//		WebElement element2 = driver.findElement(By.cssSelector("#pr_module #pr_link_create"));
//		WebElement element2 = driver.findElement(By.id("pr_link_create"));
//		WebElement element2 = driver.findElement(By.linkText("Add prospect"));
		action.moveToElement(element2).click().perform();
		
		// fill in Email address
		driver.findElement(By.id("email")).sendKeys(emailAddress);
		
		// select Campaign
		Select sel1 = new Select(driver.findElement(By.id("campaign_id")));
		sel1.selectByValue("22961");
		
		// select profile
		Select sel2 = new Select(driver.findElement(By.id("profile_id")));
		sel2.selectByValue("3231");
		
		// input score
		int score = (int) Math.random()*10 + 1;
		String scoreStr = String.valueOf(score);
		element = driver.findElement(By.id("score"));
		element.click();
		element.clear();
		element.sendKeys(scoreStr);
		
		// click on the "Create prospect" button
		driver.findElement(By.xpath("//div[@class='form-actions']//input[@name='commit']")).click();
	}
	
	public static void sendEmail(WebDriver driver) throws Exception{
		Actions action = new Actions(driver);
		Thread.sleep(1000);
		// click on Marketing icon
		WebDriverWait wait = new WebDriverWait(driver, 30);
//		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("mark-tog")));
		WebElement marketingElement = driver.findElement(By.cssSelector("#mark-tog"));
//		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//a/span[text()='Marketing']")));
//		WebElement marketingElement = driver.findElement(By.id("//a/span[text()='Marketing']"));
		action.moveToElement(marketingElement).perform();
		
		// click on Email
		WebElement segmentationElement = driver.findElement(By.cssSelector(".dropdown-submenu>a[href='/email']"));
		action.moveToElement(segmentationElement).perform();
		
		// click on New Element
//		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@href='/email/draft/edit']")));
		driver.findElement((By.xpath("//a[@href='/email/draft/edit']"))).click();
		
		// fill Name
		String nameString = generateRandomName("Rezi");
		element = driver.findElement(By.id("name"));
		element.clear();
		element.sendKeys(nameString);
		
		// select folder
		element.findElement(By.xpath("//span[text()='/Uncategorized/Emails']")).click();
		
		// click on "email test" folder
		element = driver.findElement(By.cssSelector("a[href='/7726']"));
		element.click();

		// click on "reza" folder
		element = driver.findElement(By.cssSelector("a[href='/10576']"));
		element.click();

		// click choose folder
		driver.findElement(By.id("select-asset")).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='information_form']/div[4]/div/div/button")));
		
		// click on "Choose a Campaign" folder
		element = driver.findElement(By.xpath("//*[@id='information_form']/div[4]/div/div/button"));
		element.click();

		// choose a random campaing
		element = driver.findElement(By.xpath("//*[@class='ember-list-container']/div[1]//h4"));
		element.click();
		
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

		// click choose folder
		element = driver.findElement(By.id("select-asset"));
		action.moveToElement(element).click().build().perform();
//		driver.findElement(By.id("select-asset")).click();
		
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		
		// click on save button
		driver.findElement(By.id("save_information")).click();
		
		// choose the first template
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='template_select_list']/div[2]/ul/li[1]")));
		driver.findElement(By.xpath("//*[@id='template_select_list']/div[2]/ul/li[1]")).click();
		
		// click on Apply button
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("template_confirm")));
		driver.findElement(By.id("template_confirm")).click();
		
//		//click on save button
//		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("save_footer")));
//		driver.findElement(By.id("save_footer")).click();
		
		Reporter.log("An Email has been sent \n");	
	}
	
	public static void logOut(WebDriver driver){
		Actions action = new Actions(driver);
		WebDriverWait wait = new WebDriverWait(driver, 30);
		// move to Acc icons and click on it
		action.moveToElement(driver.findElement(By.id("acct-tog"))).click().perform();
		element = driver.findElement(By.xpath("//*[@id='dropmenu-account']//a[@href='/user/logout']"));
//		wait.until(ExpectedConditions.elem(element));
		action.moveToElement(element).click().perform();
		
		
	}
}
