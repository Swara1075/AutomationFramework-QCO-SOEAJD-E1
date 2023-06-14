package objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateNewVendorPage { //Declaration
	
	@FindBy(name="vendorname")
	private WebElement vendorEdt;
	
	@FindBy(xpath = "//input[@title='Save [Alt+S]']")
	private WebElement saveBtn;
	
	//initialization

	public CreateNewVendorPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}

	//Utilization

	public WebElement getVendorEdt() {
		return vendorEdt;
	}

	public WebElement getSaveBtn() {
		return saveBtn;
	}
	
	//Business Library
	/**
	 * This method will create vendor
	 * @param VENDORNAME
	 */
	public void createNewVendor(String VENDORNAME)
	{
		vendorEdt.sendKeys(VENDORNAME);
		saveBtn.click();
	}
}
