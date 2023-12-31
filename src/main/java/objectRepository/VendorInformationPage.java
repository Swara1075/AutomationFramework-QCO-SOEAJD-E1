package objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class VendorInformationPage { //Declaration
	
	@FindBy(xpath = "//span[@class='lvtHeaderText']")
	private WebElement vendorHeaderText;
	
	//initialization
	public VendorInformationPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}

	//Utilization
	public WebElement getVendorHeaderText() {
		return vendorHeaderText;
	}

	//Business Library
	public String getVendorHeader()
	{
		return vendorHeaderText.getText();
	}
	
}
