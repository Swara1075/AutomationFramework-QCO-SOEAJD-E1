package vtiger.OpportunityTests;

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
import objectRepository.CreateNewOpportunityPage;
import objectRepository.HomePage;
import objectRepository.LoginPage;
import objectRepository.OpportunitiesPage;
import objectRepository.OpportunityInformationPage;

public class EndToEndWithPomTest {

	public static void main(String[] args) throws IOException {

		WebDriver driver=null;
		
		//Create object of utilities
		PropertyFileUtility pUtil=new PropertyFileUtility();
		ExcelFileUtility eUtil=new ExcelFileUtility();
		WebDriverUtility wUtil=new WebDriverUtility();
		
		//Read all data
		String BROWSER = pUtil.readDataFromPropertyFile("browser");
		String URL = pUtil.readDataFromPropertyFile("url");
		String USERNAME = pUtil.readDataFromPropertyFile("username");
		String PASSWORD = pUtil.readDataFromPropertyFile("password");
		
		String LASTNAME = eUtil.readDataFromExcel("Opportunity", 4, 3);
		String OPPORTUNITYNAME = eUtil.readDataFromExcel("Opportunity", 4, 2);
		String RELATEDTO = eUtil.readDataFromExcel("Opportunity", 4, 4);
		
		
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
		
		//Click on Create contact look up image

		ContactsPage cp=new ContactsPage(driver);
		cp.clickOnContactsLookUpImage();
		
		//Create contact with mandatory fields

		CreateNewContactPage cncp=new CreateNewContactPage(driver);
		cncp.createNewContact(LASTNAME);
		
		//Verify
		ContactInformationPage cip=new ContactInformationPage(driver);
		String contactsHeader = cip.getContactHeader();
		if(contactsHeader.contains(LASTNAME))
		{
			System.out.println(contactsHeader);
			System.out.println("Contact created");
		}
		else
		{
			System.out.println("Failed");
		}
		
		//Navigate to Opportunities link

		hp.clickOnOppertunitiesLink();
		
		//Click on Create Opportunities look up image

		OpportunitiesPage op=new OpportunitiesPage(driver);
		op.clickOnOpportunitiesLookUpImage();
		
		//Create Opportunities with mandatory fields

		CreateNewOpportunityPage cnop=new CreateNewOpportunityPage(driver);
		cnop.createNewOpportunity(driver, OPPORTUNITYNAME, RELATEDTO, LASTNAME);
		
		//Verify
		OpportunityInformationPage oip=new OpportunityInformationPage(driver);
		String oppHeader = oip.getOpportunityHeader();
		if(oppHeader.contains(OPPORTUNITYNAME))
		{
			System.out.println(oppHeader);
			System.out.println("Test Script Passed");
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
