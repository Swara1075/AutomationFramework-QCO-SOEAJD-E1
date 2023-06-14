package vtiger.contactsTests;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import genericUtilities.ExcelFileUtility;
import genericUtilities.JavaUtility;
import genericUtilities.PropertyFileUtility;
import genericUtilities.WebDriverUtility;
import io.github.bonigarcia.wdm.WebDriverManager;
import objectRepository.ContactInformationPage;
import objectRepository.ContactsPage;
import objectRepository.CreateNewContactPage;
import objectRepository.CreateNewOrganizationPage;
import objectRepository.HomePage;
import objectRepository.LoginPage;
import objectRepository.OrganizationInformationPage;
import objectRepository.OrganizationsPage;

public class EndToEndWithPOMTest {

	public static void main(String[] args) throws IOException {

		WebDriver driver=null;
		
		//create object
		JavaUtility jUtil=new JavaUtility();
		PropertyFileUtility pUtil=new PropertyFileUtility();
		ExcelFileUtility eUtil=new ExcelFileUtility();
		WebDriverUtility wUtil=new WebDriverUtility();
		
		//Read data
		String BROWSER = pUtil.readDataFromPropertyFile("browser");
		String URL = pUtil.readDataFromPropertyFile("url");
		String USERNAME = pUtil.readDataFromPropertyFile("username");
		String PASSWORD = pUtil.readDataFromPropertyFile("password");
		
		String LASTNAME = eUtil.readDataFromExcel("Contact", 4, 2);
		String ORGNAME = eUtil.readDataFromExcel("Contact", 4, 3)+jUtil.getRandomNumber();
		
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
		
		//Click on Create Organizations look up image
		OrganizationsPage op=new OrganizationsPage(driver);
		op.clickOnOrgLookUpImage();
		
		//Create Organizations with mandatory fields
		CreateNewOrganizationPage cnop=new CreateNewOrganizationPage(driver);
		cnop.createNewOrganization(ORGNAME);
		
		//Verify
		OrganizationInformationPage oip=new OrganizationInformationPage(driver);
		String orgHeader = oip.getOrgHeader();
		if(orgHeader.contains(ORGNAME))
		{
			System.out.println(orgHeader);
			System.out.println("Organization created");
			
		}
 
		else
		{
			System.out.println("Failed");
		}

		//Navigate to Contacts link

		hp.clickOnContactsLink();
		
		//Click on Create contact look up image

		ContactsPage cp=new ContactsPage(driver);
		cp.clickOnContactsLookUpImage();
		
		//Create contact with mandatory fields

		CreateNewContactPage cncp=new CreateNewContactPage(driver);
		cncp.createNewContact(driver, LASTNAME, ORGNAME);
		
		//Verify
		ContactInformationPage cip=new ContactInformationPage(driver);
		String contactsHeader = cip.getContactHeader();
		if(contactsHeader.contains(LASTNAME))
		{
			System.out.println(contactsHeader);
			System.out.println("Organization created");
			
		}
 
		else
		{
			System.out.println("Failed");
		}
		
		//Logout
		hp.logoutOfApplication(driver);
		System.out.println("Logout Successfully");
		driver.close();
		
		
		
	}

}
