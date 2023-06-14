package vtiger.contactsTests;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import genericUtilities.ExcelFileUtility;
import genericUtilities.JavaUtility;
import genericUtilities.PropertyFileUtility;
import genericUtilities.WebDriverUtility;
import io.github.bonigarcia.wdm.WebDriverManager;
import objectRepository.LoginPage;

public class EndToEndTest {

	public static void main(String[] args) throws IOException {

WebDriver driver=null;
		
		//Create object for utilities
		PropertyFileUtility pUtil=new PropertyFileUtility();
		ExcelFileUtility eUtil=new ExcelFileUtility();
		JavaUtility jUtil=new JavaUtility();
		WebDriverUtility wUtil=new WebDriverUtility();
		
		//Read Required Data
		String BROWSER = pUtil.readDataFromPropertyFile("browser");
		String URL = pUtil.readDataFromPropertyFile("url");
		String USERNAME = pUtil.readDataFromPropertyFile("username");
		String PASSWORD = pUtil.readDataFromPropertyFile("password");
		
	    String LASTNAME = eUtil.readDataFromExcel("Contact", 4, 2);
	    String ORGNAME = eUtil.readDataFromExcel("Contact", 4, 3);
	    
	    //Launch Browser
	    if(BROWSER.equalsIgnoreCase("chrome"))
	    {
	    	WebDriverManager.chromedriver().setup();
	    	driver=new ChromeDriver();
	    	System.out.println("Browser Launched");
	    	
	    }
	    else if(BROWSER.equalsIgnoreCase("firefox"))
	    {
	    	WebDriverManager.firefoxdriver().setup();
	    	driver=new FirefoxDriver();
	    	System.out.println("Browser Launched");
	    }
	    else
	    {
	    	System.out.println("Invalid Browser Name");
	    }
	    
	    wUtil.maximizeWindow(driver);
	    wUtil.waitForPageLoad(driver);
	    driver.get(URL);
	    
	    //Login
	    LoginPage lp=new LoginPage(driver);
	    lp.getUsernameEdt().sendKeys(USERNAME);
	    lp.getPasswordEdt().sendKeys(PASSWORD);
	    lp.getLoginBtn().click();
	    System.out.println("Login Successfull");
	    
	    //click on organization
	    driver.findElement(By.linkText("Organizations")).click();
	    
	    //Click on look up image
	    driver.findElement(By.xpath("//img[@alt='Create Organization...']")).click();
	    
	    //Create organization with mandatory fields
	    driver.findElement(By.name("accountname")).sendKeys(ORGNAME+jUtil.getRandomNumber());
	    
	    //Save organization
	    driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
	    
	    //Verify
	    String orgHeader = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
	    if(orgHeader.contains(ORGNAME))
	    {
	    	System.out.println(orgHeader);
	    	System.out.println("Organization Created Successfully");
	    }
	    else
	    {
	    	System.out.println("Organization not created");
	    }
	    
	    //click on contact
	    driver.findElement(By.linkText("Contacts")).click();
	    
	    //click on look up image
	    driver.findElement(By.xpath("//img[@alt='Create Contact...']")).click();
	    
	    //create contact with mandatory field
	    driver.findElement(By.name("lastname")).sendKeys(LASTNAME);
	    
	    //click on look up image
	    driver.findElement(By.xpath("//input[@name='account_name']/following-sibling::img[@alt='Select']")).click();
	    
	    //select organization
	    wUtil.switchToWindow(driver, "Accounts");
	    driver.findElement(By.name("search_text")).sendKeys(ORGNAME);
	    driver.findElement(By.name("search")).click();
	    driver.findElement(By.xpath("//a[.='"+ORGNAME+"']")).click(); //dynamic xpath
	    wUtil.switchToWindow(driver, "Contacts");
	    
	    //Save
	    driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
	    
	    //verify
	    String contactHeader = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
	    if(contactHeader.contains(LASTNAME))
	    {
	    	System.out.println(contactHeader);
	    	System.out.println("Test Script Pass");
	    }
	    else
	    {
	    	System.out.println("Test Script Failed");
	    }
	    
	    //Logout
	    WebElement adImg = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
	    wUtil.mouseHoverAction(driver, adImg);
	    driver.findElement(By.linkText("Sign Out")).click();
	    System.out.println("Logout Successfully");
	    driver.close();
	}

}
