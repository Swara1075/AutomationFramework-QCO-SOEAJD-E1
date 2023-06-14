package objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class VendorsPage { //Declaration
	
	@FindBy(xpath = "//img[@alt='Create Vendor...']")
	private WebElement vendorsLookUpImg;
	
	//initialization
	public VendorsPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}

	//Utilization
	public WebElement getVendorsLookUpImg() {
		return vendorsLookUpImg;
	}

	//Business Library
	/**
	 * This method will click on look up image
	 */
	public void clickOnVendorsLookUpImg()
	{
		vendorsLookUpImg.click();
	}
}
