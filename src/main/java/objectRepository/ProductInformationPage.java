package objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductInformationPage { //Declaration
	
	@FindBy(xpath = "//span[@class='lvtHeaderText']")
	private WebElement productHeaderText;
	
	//initialization
	public ProductInformationPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}

	//Utilization
	public WebElement getProductHeaderText() {
		return productHeaderText;
	}

	//Buisness Library
	/**
	 * This method will return product header
	 * @return
	 */
	public String getProductHeader()
	{
		return productHeaderText.getText();
	}
	
}
