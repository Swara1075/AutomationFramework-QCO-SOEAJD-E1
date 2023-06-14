package objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductsPage {//Declaration
	
	@FindBy(xpath = "//img[@alt='Create Product...']")
	private WebElement productsLookUpImg;
	
	//initialization
	public ProductsPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}

	//Utilization
	public WebElement getProductsLookUpImg() {
		return productsLookUpImg;
	}
	
	//Business Library
	/**
	 * This method will click on products look up image
	 */
	public void clickOnProductsLookUpImg()
	{
		productsLookUpImg.click();
	}

}
