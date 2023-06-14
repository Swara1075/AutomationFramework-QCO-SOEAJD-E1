package vtiger.OrganizationsTests;

import java.io.IOException;

import org.testng.annotations.Test;

import genericUtilities.BaseClass;
import genericUtilities.JavaUtility;
import objectRepository.CreateNewOrganizationPage;
import objectRepository.HomePage;
import objectRepository.OrganizationInformationPage;
import objectRepository.OrganizationsPage;

public class CeateOrganizationWithBaseClassTest extends BaseClass {

	@Test
	public void createOrg() throws IOException
	{
 
		JavaUtility jUtil=new JavaUtility();
		
        String ORGNAME = eUtil.readDataFromExcel("Organization", 1, 2)+jUtil.getRandomNumber();
        
        //Navigate to Organizations link

        HomePage hp=new HomePage(driver);
        hp.clickOnOrganizationLink();
        
        //Click on Create Organization look Up Image

        OrganizationsPage op=new OrganizationsPage(driver);
        op.clickOnOrgLookUpImage();
        
        //Create Organization with Mandatory fields

        CreateNewOrganizationPage cnop=new CreateNewOrganizationPage(driver);
        cnop.createNewOrganization(ORGNAME);
        
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
        
       
	}

}
