package vtiger.contactsTests;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import genericUtilities.BaseClass;
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

public class CreateContactWithOrganizationWithBaseClassTest extends BaseClass{
	
	@Test(groups = "RegressionSuite")
	public void createContactWithOrg() throws EncryptedDocumentException, IOException
	{

		//create object
		JavaUtility jUtil=new JavaUtility();
		ExcelFileUtility eUtil=new ExcelFileUtility();
		
		//Read data
	    String LASTNAME = eUtil.readDataFromExcel("Contact", 4, 2);
		String ORGNAME = eUtil.readDataFromExcel("Contact", 4, 3)+jUtil.getRandomNumber();
		
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
	}
}
