package vtiger.OpportunityTests;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import genericUtilities.BaseClass;
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

public class CreateOpportunityWithContactBaseClassTest extends BaseClass{
	
	@Test
	public void createOpportunityWithContact() throws EncryptedDocumentException, IOException
	{
        //Create object of utilities
		ExcelFileUtility eUtil=new ExcelFileUtility();
		
		//Read all data
		String LASTNAME = eUtil.readDataFromExcel("Opportunity", 4, 3);
		String OPPORTUNITYNAME = eUtil.readDataFromExcel("Opportunity", 4, 2);
		String RELATEDTO = eUtil.readDataFromExcel("Opportunity", 4, 4);
		
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
		
	}

}
