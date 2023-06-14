package objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import genericUtilities.WebDriverUtility;

public class HomePage extends WebDriverUtility{// Rule 1: Create Class
	
	//Rule 2:Identify elements with annotation
	
	@FindBy(linkText = "Organizations")
	private WebElement organizationsLink;
	
	@FindBy(linkText = "Contacts")
	private WebElement contactsLink;
	
	@FindBy(linkText = "Opportunities")
	private WebElement opportunitiesLink;
	
	@FindBy(linkText = "Products")
	private WebElement productsLink;
	
	@FindBy(xpath = "//img[@src='themes/softed/images/menuDnArrow.gif']")
	private WebElement moreLink;
	
	@FindBy(linkText = "Vendors")
	private WebElement vendorsLink;
	
	@FindBy(linkText = "Campaigns")
	private WebElement campaignsLink;
	
	@FindBy(xpath = "//img[@src='themes/softed/images/user.PNG']")
	private WebElement administratorImg;
	
	@FindBy(linkText = "Sign Out")
	private WebElement signOutLink;
	
	//Rule 3: Create Constructor to initialize
	 
	public HomePage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}

	//Rule 4:Generate getters to access
	
	public WebElement getOrganizationLink() {
		return organizationsLink;
	}

	public WebElement getContactLink() {
		return contactsLink;
	}

	public WebElement getAdministratorImg() {
		return administratorImg;
	}

	public WebElement getProductsLink() {
		return productsLink;
	}

	public WebElement getOpportunitiesLink() {
		return opportunitiesLink;
	}
	
	public WebElement getMoreLink() {
		return moreLink;
	}

	public WebElement getvendorsLink() {
		return vendorsLink;
	}
	
	public WebElement getCampaignsLink() {
		return campaignsLink;
	}

	public WebElement getSignOutLink() {
		return signOutLink;
	}
	
	//Business Libraries project specific generic utilities
	/**
	 * This method will click on organization link
	 */
	public void clickOnOrganizationLink()
	{
		organizationsLink.click();
		
	}
	
	/**
	 * This method will click on contacts link
	 */
	public void clickOnContactsLink()
	{
		contactsLink.click();
	}
	
	/**
	 * This method will click on Opportunities Link
	 */
	public void clickOnOppertunitiesLink()
	{
		opportunitiesLink.click();
	}
	
	/**
	 * This method is used to click on products
	 */
	public void clickOnProductLink()
	{
		productsLink.click();
	}
	
	/**
	 * This method will click on vendors link
	 */
	public void clickonVendorsLink(WebDriver driver)
	{
		mouseHoverAction(driver, moreLink);
		vendorsLink.click();
		
	}
	/**
	 * This method is used to create campaign
	 */
	public void clickOnCampaignsLink(WebDriver driver)
	{
		mouseHoverAction(driver, moreLink);
		campaignsLink.click();
	}
	/**
	 * This method will logout of application
	 * @param driver
	 */
	public void logoutOfApplication(WebDriver driver)
	{
		mouseHoverAction(driver, administratorImg);
		signOutLink.click();
	}

	
}
