package vtiger.OrganizationsTests;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import genericUtilities.ExcelFileUtility;
import genericUtilities.JavaUtility;
import genericUtilities.PropertyFileUtility;
import genericUtilities.WebDriverUtility;
import io.github.bonigarcia.wdm.WebDriverManager;
import objectRepository.CreateNewOrganizationPage;
import objectRepository.HomePage;
import objectRepository.LoginPage;
import objectRepository.OrganizationInformationPage;
import objectRepository.OrganizationsPage;

public class CreateOrganizationWithDropDownsTest {

	public static void main(String[] args) throws IOException {

         WebDriver driver=null;
          
         //Create Object
         JavaUtility jUtil=new JavaUtility();
         PropertyFileUtility pUtil=new PropertyFileUtility();
         ExcelFileUtility eUtil=new ExcelFileUtility();
         WebDriverUtility wUtil=new WebDriverUtility();
         
         //Read Data
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
             System.out.println("Chrome Launched");
         }
         else if(BROWSER.equalsIgnoreCase("firefox"))
         {
        	 WebDriverManager.firefoxdriver().setup();
             driver=new FirefoxDriver();
             System.out.println("Firefox Launched");
         }
         else
         {
        	 System.out.println("Invalid Browser Name");
         }
         
         wUtil.maximizeWindow(driver);
         wUtil.waitForPageLoad(driver);
         driver.get(URL);
         
         //Login to application with valid credentials
         LoginPage lp=new LoginPage(driver);
         lp.loginToApp(USERNAME, PASSWORD);
         
         //Navigate to Organizations link
 
         HomePage hp=new HomePage(driver);
         hp.clickOnOrganizationLink();
         
         //Click on Create Organization look Up Image

         OrganizationsPage op=new OrganizationsPage(driver);
         op.clickOnOrgLookUpImage();
         
         //Create Organization with Mandatory fields

         CreateNewOrganizationPage cnop=new CreateNewOrganizationPage(driver);
         cnop.createNewOrganization(ORGNAME, INDUSTRY, TYPE);
         
         //Verify
         OrganizationInformationPage oip=new OrganizationInformationPage(driver);
         String orgHeader = oip.getOrgHeader();
         if(orgHeader.contains(ORGNAME))
         {
        	 System.out.println(orgHeader);
        	 System.out.println("Test Script Pass");
         }
         else
         {
        	 System.out.println("Test Script Fail");
         }
         
         //Logout
         hp.logoutOfApplication(driver);
         driver.close();
         System.out.println("Logout Scuccessfully");
         
	}

}
