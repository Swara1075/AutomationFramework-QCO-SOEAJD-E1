package vtiger.CampaignTests;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import genericUtilities.BaseClass;
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

public class CreateCamapaignWithProductWithBaseClassTest extends BaseClass{
	
	@Test
	public void createCampaignWithProduct() throws EncryptedDocumentException, IOException
	{

		//create object of utilities
		ExcelFileUtility eUtil=new ExcelFileUtility();
		
		//Read data
		String CAMPAIGNNAME = eUtil.readDataFromExcel("Camapign", 1, 2);
		String TYPE = eUtil.readDataFromExcel("Camapign", 1, 3);
		String STATUS = eUtil.readDataFromExcel("Camapign", 1, 4);
		String PRODUCTNAME = eUtil.readDataFromExcel("Camapign", 1, 5);
		
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
		Assert.assertTrue(productHeader.contains(PRODUCTNAME));
//		if(productHeader.contains(PRODUCTNAME))
//		{
//			System.out.println(productHeader);
//			System.out.println("Product Created");
//		}
//		else
//		{
//			System.out.println("Fail");
//		}
		
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
		Assert.assertTrue(campaignHeader.contains(CAMPAIGNNAME));
//		if(campaignHeader.contains(CAMPAIGNNAME))
//		{
//			System.out.println(campaignHeader);
//			System.out.println("Campaign Created");
//			System.out.println("Test Script Passed");
//		}
//		else
//		{
//			System.out.println("Fail");
//		}
	}


}
