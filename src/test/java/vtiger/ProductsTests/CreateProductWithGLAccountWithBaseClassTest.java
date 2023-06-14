package vtiger.ProductsTests;

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
import objectRepository.CreateNewProductPage;
import objectRepository.CreateNewVendorPage;
import objectRepository.HomePage;
import objectRepository.LoginPage;
import objectRepository.ProductInformationPage;
import objectRepository.ProductsPage;
import objectRepository.VendorInformationPage;
import objectRepository.VendorsPage;

public class CreateProductWithGLAccountWithBaseClassTest extends BaseClass {
	
	@Test
	public void createProduct() throws EncryptedDocumentException, IOException
	{
                String PRODUCTNAME = eUtil.readDataFromExcel("Product", 4, 2);
				String VENDORNAME = eUtil.readDataFromExcel("Product", 4, 3);
				String GLACCOUNT = eUtil.readDataFromExcel("Product", 4, 4);
				
				
				//Click on vendors
				HomePage hp=new HomePage(driver);
				hp.clickonVendorsLink(driver);
				
				//click on vendors look up image
				VendorsPage vp=new VendorsPage(driver);
				vp.clickOnVendorsLookUpImg();
				
				//create new vendor
				CreateNewVendorPage cnvp=new CreateNewVendorPage(driver);
				cnvp.createNewVendor(VENDORNAME);
				
				//Verify vendor
				VendorInformationPage vip=new VendorInformationPage(driver);
				String vendorHeader = vip.getVendorHeader();
				if(vendorHeader.contains(VENDORNAME))
				{
					System.out.println(vendorHeader);
					System.out.println("Vendor Created");
				}
				else
				{
					System.out.println("Fail");
				}
				
				//click on products
				hp.clickOnProductLink();
				
				//click on products look up image
				ProductsPage pp=new ProductsPage(driver);
				pp.clickOnProductsLookUpImg();
				
				//create new product
				CreateNewProductPage cnpp=new CreateNewProductPage(driver);
				cnpp.createNewProduct(driver, PRODUCTNAME, VENDORNAME, GLACCOUNT);
				
				//Verify
				ProductInformationPage pip=new ProductInformationPage(driver);
				String productHeader = pip.getProductHeader();
				if(productHeader.contains(PRODUCTNAME))
				{
					System.out.println(productHeader);
					System.out.println("Test Script Pass");
				}
				else
				{
					System.out.println("Test Script Failed");
				}	
	}

}
