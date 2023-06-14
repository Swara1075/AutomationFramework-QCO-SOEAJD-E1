package vtiger.OrganizationsTests;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import genericUtilities.BaseClass;
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

@Listeners(genericUtilities.ListenersImplementation.class)
public class CreateNewOrganizationWithDropDownWithBaseClassTest extends BaseClass{
	
	@Test(groups = "SmokeSuite")
	public void createOrganizatioWithDropDowns() throws EncryptedDocumentException, IOException
	 {
        //Create Object
        JavaUtility jUtil=new JavaUtility();
        ExcelFileUtility eUtil=new ExcelFileUtility();
        
        //Read Data
        String ORGNAME = eUtil.readDataFromExcel("Organization", 7, 2)+jUtil.getRandomNumber();
        String INDUSTRY = eUtil.readDataFromExcel("Organization", 7, 3);
        String TYPE = eUtil.readDataFromExcel("Organization", 7, 4);
        
        //Navigate to Organizations link

        HomePage hp=new HomePage(driver);
        hp.clickOnOrganizationLink();
        Reporter.log("Navigated to Organizations link",true);
        
        //Click on Create Organization look Up Image

        OrganizationsPage op=new OrganizationsPage(driver);
        op.clickOnOrgLookUpImage();
        Reporter.log("Clicked on Create Organization look Up Image",true);
        
        //Assert.fail();
        //Create Organization with Mandatory fields

        CreateNewOrganizationPage cnop=new CreateNewOrganizationPage(driver);
        cnop.createNewOrganization(ORGNAME, INDUSTRY, TYPE);
        Reporter.log("Created Organization with Mandatory fields",true);
        
        //Verify
        OrganizationInformationPage oip=new OrganizationInformationPage(driver);
        String orgHeader = oip.getOrgHeader();
        Assert.assertTrue(orgHeader.contains(ORGNAME));
//        if(orgHeader.contains(ORGNAME))
//        {
//       	 System.out.println(orgHeader);
//       	 System.out.println("Test Script Pass");
//        }
//        else
//        {
//       	 System.out.println("Test Script Fail");
//        }
}
	
	@Test
	public void demo()
	{
		System.out.println("Pass");
	}
}