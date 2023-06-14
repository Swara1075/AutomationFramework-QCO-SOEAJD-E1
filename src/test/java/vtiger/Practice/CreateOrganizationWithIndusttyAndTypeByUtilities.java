package vtiger.Practice;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import genericUtilities.ExcelFileUtility;
import genericUtilities.JavaUtility;
import genericUtilities.PropertyFileUtility;
import genericUtilities.WebDriverUtility;
import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateOrganizationWithIndusttyAndTypeByUtilities {

	public static void main(String[] args) throws IOException {

WebDriver driver=null;
		
		//Create object of utilities
		JavaUtility jUtil=new JavaUtility();
		PropertyFileUtility pUtil=new PropertyFileUtility();
		ExcelFileUtility eUtil=new ExcelFileUtility();
		WebDriverUtility wUtil=new WebDriverUtility();
		
		String BROWSER = pUtil.readDataFromPropertyFile("browser");
		String URL = pUtil.readDataFromPropertyFile("url");
		String USERNAME = pUtil.readDataFromPropertyFile("username");
		String PASSWORD = pUtil.readDataFromPropertyFile("password");
		
		String ORGNAME = eUtil.readDataFromExcel("Organization", 7, 2)+jUtil.getRandomNumber();
		String INDUSTRY = eUtil.readDataFromExcel("Organization", 7, 3);
		String TYPE = eUtil.readDataFromExcel("Organization", 7, 4);
		
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
				
				//Login to application with valid credentials

				driver.findElement(By.name("user_name")).sendKeys(USERNAME);
				driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
				driver.findElement(By.id("submitButton")).click();
				
				//Navigate to Organizations link

				driver.findElement(By.linkText("Organizations")).click();
		         
		        //Step4:Click on Create Organization look Up Image 
		        driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();
		        
		        //Create Organization with Mandatory fields

		        driver.findElement(By.name("accountname")).sendKeys(ORGNAME);
		        WebElement industryDropDown = driver.findElement(By.name("industry"));
		        wUtil.handleDropDown(industryDropDown, INDUSTRY);
		        WebElement typeDropDown = driver.findElement(By.name("accounttype"));
		        wUtil.handleDropDown(typeDropDown, TYPE);
		        
		      //Save
				driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
				
				//Verify
				
				String orgHeader = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
				if(orgHeader.contains(ORGNAME))
				{
					System.out.println(ORGNAME);
					System.out.println("Test Script pass");
				}
				else
				{
					System.out.println("Test Script Failed");
				}
				
				//Logout
				WebElement adImg = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
				wUtil.mouseHoverAction(driver, adImg);
				driver.findElement(By.linkText("Sign Out")).click();
		        driver.close();
	}

}
