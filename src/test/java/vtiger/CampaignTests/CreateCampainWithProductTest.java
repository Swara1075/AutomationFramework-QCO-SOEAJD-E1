package vtiger.CampaignTests;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import genericUtilities.ExcelFileUtility;
import genericUtilities.JavaUtility;
import genericUtilities.PropertyFileUtility;
import genericUtilities.WebDriverUtility;
import io.github.bonigarcia.wdm.WebDriverManager;
import objectRepository.CamapignInformationPage;
import objectRepository.CampaignsPage;
import objectRepository.CreateNewCampaignPage;
import objectRepository.CreateNewProductPage;
import objectRepository.HomePage;
import objectRepository.LoginPage;
import objectRepository.ProductInformationPage;
import objectRepository.ProductsPage;

public class CreateCampainWithProductTest {

	@Test 
		public void createCampainWithProduct() throws IOException
		{

		WebDriver driver=null;
		
		//create object of utilities
		JavaUtility jUtil=new JavaUtility();
		PropertyFileUtility pUtil=new PropertyFileUtility();
		ExcelFileUtility eUtil=new ExcelFileUtility();
		WebDriverUtility wUtil=new WebDriverUtility();
		
		//Read data
		String BROWSER = pUtil.readDataFromPropertyFile("browser");
		String URL = pUtil.readDataFromPropertyFile("url");
		String USERNAME = pUtil.readDataFromPropertyFile("username");
		String PASSWORD = pUtil.readDataFromPropertyFile("password");
		
		String CAMPAIGNNAME = eUtil.readDataFromExcel("Camapign", 1, 2);
		String TYPE = eUtil.readDataFromExcel("Camapign", 1, 3);
		String STATUS = eUtil.readDataFromExcel("Camapign", 1, 4);
		String PRODUCTNAME = eUtil.readDataFromExcel("Camapign", 1, 5);
		
		
		//Launch browser
		if(BROWSER.contains("chrome"))
		{
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();
			System.out.println("Chrome Launched");
		}
		else if(BROWSER.contains("firefox"))
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
		
		//login 
		LoginPage lp=new LoginPage(driver);
		lp.loginToApp(USERNAME, PASSWORD);
		
		//click on products
		HomePage hp=new HomePage(driver);
		hp.clickOnProductLink();
		
		//click on create new products look up image
		ProductsPage pp=new ProductsPage(driver);
		pp.clickOnProductsLookUpImg();
		
		//create new product
		CreateNewProductPage cnpp=new CreateNewProductPage(driver);
		cnpp.createNewProduct(PRODUCTNAME);
		
		//Verify Product
		ProductInformationPage pip=new ProductInformationPage(driver);
		String productHeader = pip.getProductHeader();
		if(productHeader.contains(PRODUCTNAME))
		{
			System.out.println(productHeader);
			System.out.println("Product Created");
		}
		else
		{
			System.out.println("Fail");
		}
		
		//Click on campaign
		hp.clickOnCampaignsLink(driver);
		
		//click on create new campaign look up image
		CampaignsPage cp=new CampaignsPage(driver);
		cp.clickOnCampaignLookUpImage();
		
		//create new Campaign
		CreateNewCampaignPage cncp=new CreateNewCampaignPage(driver);
		cncp.createNewCamapign(driver, CAMPAIGNNAME, PRODUCTNAME, TYPE, STATUS);
		
		//verify Campaign
		CamapignInformationPage cip=new CamapignInformationPage(driver);
		String campaignHeader = cip.getCampaignHeader();
		if(campaignHeader.contains(CAMPAIGNNAME))
		{
			System.out.println(campaignHeader);
			System.out.println("Campaign Created");
			System.out.println("Test Script Passed");
		}
		else
		{
			System.out.println("Fail");
		}
		
		hp.logoutOfApplication(driver);
		System.out.println("Logout Successfully");
		driver.close();
		
		
	}

}
