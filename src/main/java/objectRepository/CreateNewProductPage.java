package objectRepository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import genericUtilities.WebDriverUtility;

public class CreateNewProductPage extends WebDriverUtility{ //Declaration
	
	@FindBy(name="productname")
	private WebElement productsEdt;
	
	@FindBy(xpath = "//img[@alt='Select']")
	private WebElement selectVendorLookUpImg;
	
	@FindBy(name="glacct")
	private WebElement glAccountDropDown;
	
	@FindBy(name="search_text")
	private WebElement vendorSearchEdt;
	
	@FindBy(name="search")
	private WebElement searchBtn;
	
	@FindBy(xpath = "//input[@title='Save [Alt+S]']")
	private WebElement saveBtn;
	
	//initialization
	public CreateNewProductPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}

	//Utilization
	public WebElement getProductsEdt() {
		return productsEdt;
	}

	public WebElement getSelectVendorLookUpImg() {
		return selectVendorLookUpImg;
	}

	public WebElement getGlAccountDropDown() {
		return glAccountDropDown;
	}

	
	public WebElement getVendorSearchEdt() {
		return vendorSearchEdt;
	}

	public WebElement getSearchBtnBtn() {
		return searchBtn;
	}

	public WebElement getSaveBtn() {
		return saveBtn;
	}

	//Business Library
	/**
	 * This method will create new product
	 * @param PRODUCTNAME
	 */
	public void createNewProduct(String PRODUCTNAME)
	{
		productsEdt.sendKeys(PRODUCTNAME);
		saveBtn.click();
	}
	
	public void createNewProduct(WebDriver driver,String PRODUCTNAME,String VENDORNAME,String GLACCOUNT)
	{
		productsEdt.sendKeys(PRODUCTNAME);
		selectVendorLookUpImg.click();
		switchToWindow(driver, "Vendors");
		vendorSearchEdt.sendKeys(VENDORNAME);
		searchBtn.click();
		driver.findElement(By.xpath("//a[.='"+VENDORNAME+"']")).click();
		switchToWindow(driver, "Products");
		handleDropDown(glAccountDropDown, GLACCOUNT);
		saveBtn.click();
	}
	
}
