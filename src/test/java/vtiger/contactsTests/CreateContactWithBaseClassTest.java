package vtiger.contactsTests;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.annotations.Test;

import genericUtilities.BaseClass;
import io.opentelemetry.exporter.logging.SystemOutLogRecordExporter;
import objectRepository.ContactInformationPage;
import objectRepository.ContactsPage;
import objectRepository.CreateNewContactPage;
import objectRepository.HomePage;

public class CreateContactWithBaseClassTest extends BaseClass{
	
	@Test
	public void createContact() throws EncryptedDocumentException, IOException
	{
		String LASTNAME = eUtil.readDataFromExcel("Contact", 1, 2);
		//Navigate to Contacts
		HomePage hp=new HomePage(driver);
		hp.clickOnContactsLink();
		
		//click on look up image
		ContactsPage cp=new ContactsPage(driver);
		cp.clickOnContactsLookUpImage();
		
		//create contact
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
			System.out.println("Fail");
		}
	}

	@Test
	public void demo()
	{
		System.out.println("Demo");
	}
}
