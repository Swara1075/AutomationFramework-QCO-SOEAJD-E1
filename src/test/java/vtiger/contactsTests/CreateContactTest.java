package vtiger.contactsTests;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import genericUtilities.ExcelFileUtility;
import genericUtilities.PropertyFileUtility;
import genericUtilities.WebDriverUtility;
import io.github.bonigarcia.wdm.WebDriverManager;
import objectRepository.ContactInformationPage;
import objectRepository.ContactsPage;
import objectRepository.CreateNewContactPage;
import objectRepository.HomePage;
import objectRepository.LoginPage;

public class CreateContactTest {

	public static void main(String[] args) throws IOException {

        WebDriver driver=null;
        
        //Create Object
        PropertyFileUtility pUtil=new PropertyFileUtility();
        ExcelFileUtility eUtil=new ExcelFileUtility();
        WebDriverUtility wUtil=new WebDriverUtility();
        
        //Read Data
        String BROWSER = pUtil.readDataFromPropertyFile("browser");
        String URL = pUtil.readDataFromPropertyFile("url");
        String USERNAME = pUtil.readDataFromPropertyFile("username");
        String PASSWORD = pUtil.readDataFromPropertyFile("password");
        
        String LASTNAME = eUtil.readDataFromExcel("Contact", 1, 2);
        
        
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
        
        //Navigate to Contacts link

        HomePage hp=new HomePage(driver);
        hp.clickOnContactsLink();
        
        //Click on Create Contacts look Up Image

       ContactsPage cp=new ContactsPage(driver);
       cp.clickOnContactsLookUpImage();
        
        //Create Contacts with Mandatory fields

        CreateNewContactPage cncp=new CreateNewContactPage(driver);
        cncp.createNewContact(LASTNAME);
        
        //Verify
        ContactInformationPage cip=new ContactInformationPage(driver);
        String contactHeader = cip.getContactHeader();
        if(contactHeader.contains(LASTNAME))
        {
       	 System.out.println(contactHeader);
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
