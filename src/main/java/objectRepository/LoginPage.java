package objectRepository;
/**
 * 
 * @author swathi B
 *
 */

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage { //Rule 1: Create Class
	
	//Rule 2:Identify WebElement using annotations
	@FindBy(name="user_name")
	private WebElement usernameEdt;
	
	@FindBy(name="user_password")
	private WebElement passwordEdt;
	
	@FindAll({@FindBy(id="submitButton"),@FindBy(xpath="//input[@id='submitButton']")})
	private WebElement loginBtn;
	
	//Rule 3: Create Constructor to initialize
	
	public LoginPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
		
	}

	//Rule 4:Provide getters to access

	public WebElement getUsernameEdt() {
		return usernameEdt;
	}

	public WebElement getPasswordEdt() {
		return passwordEdt;
	}

	public WebElement getLoginBtn() {
		return loginBtn;
	}
	
	//Business Libraries-Project specific generic utilities
	/**
	 * This method will login to application
	 * @param USERNAME
	 * @param PASSWORD
	 */
   public void loginToApp(String USERNAME,String PASSWORD)
    {
	usernameEdt.sendKeys(USERNAME);
	passwordEdt.sendKeys(PASSWORD);
	loginBtn.click();
    }
	
}
	
			
	
	


