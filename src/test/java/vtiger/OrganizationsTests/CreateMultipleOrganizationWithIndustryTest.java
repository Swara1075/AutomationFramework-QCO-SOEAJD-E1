package vtiger.OrganizationsTests;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

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

public class CreateMultipleOrganizationWithIndustryTest {
	
	JavaUtility jUtil=new JavaUtility();
	PropertyFileUtility pUtil=new PropertyFileUtility();
	ExcelFileUtility eUtil=new ExcelFileUtility();
	WebDriverUtility wUtil=new WebDriverUtility();
	
	@Test(dataProvider = "getData")
	public void createMultipleOrg(String ORG,String INDUSTRY) throws IOException
	{
		WebDriver driver=null;
		String ORGNAME = ORG+jUtil.getRandomNumber();
		//read data
		String BROWSER = pUtil.readDataFromPropertyFile("browser");
		String URL = pUtil.readDataFromPropertyFile("url");
		String USERNAME = pUtil.readDataFromPropertyFile("username");
		String PASSWORD = pUtil.readDataFromPropertyFile("password");
		
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
		
		//Login to application
		LoginPage lp=new LoginPage(driver);
		lp.loginToApp(USERNAME, PASSWORD);
		
		//navigate to organizations
		HomePage hp=new HomePage(driver);
		hp.clickOnOrganizationLink();
		
		//click on create new organizations
		OrganizationsPage op=new OrganizationsPage(driver);
		op.clickOnOrgLookUpImage();
		
		//create new organizations with industry
		CreateNewOrganizationPage cnop=new CreateNewOrganizationPage(driver);
		cnop.createNewOrganization(ORGNAME, INDUSTRY);
		
		//Verify
		OrganizationInformationPage oip=new OrganizationInformationPage(driver);
		String ORGHEADER = oip.getOrgHeader();
		if(ORGHEADER.contains(ORGNAME))
		{
			System.out.println(ORGHEADER);
			System.out.println("Test Script Pass");
		}
		else
		{
			System.out.println("Fail");
		}
		
		//Logout
		hp.logoutOfApplication(driver);
		System.out.println("Logout Scuccessfully");
		driver.close();
		
	}
	
	@DataProvider
	public Object[][] getData() throws EncryptedDocumentException, IOException
	{
		return eUtil.readMultipleDataFromExcel("MultipleDataForOrg");
	}

}
