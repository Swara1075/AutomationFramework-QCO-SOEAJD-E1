package genericUtilities;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import io.github.bonigarcia.wdm.WebDriverManager;
import objectRepository.HomePage;
import objectRepository.LoginPage;
/**
 * This class consists of Basic Configuration annotations of testNG
 * @author swathi B
 *
 */
public class BaseClass {
	public PropertyFileUtility pUtil=new PropertyFileUtility();
	public ExcelFileUtility eUtil=new ExcelFileUtility();
	public WebDriverUtility wUtil =new WebDriverUtility();
	
	public WebDriver driver=null;
	public static WebDriver sDriver;
	
	@BeforeSuite(groups = {"SmokeSuite","RegressionSuite"})
	public void bsConfig()
	{
		System.out.println("=======Database Connection Successfull=======");
	}
	
	//@Parameters("browsers")
	//@BeforeTest
	@BeforeClass(alwaysRun = true)
	public void bcConfig(/*String BROWSER*/) throws IOException
	{
		String BROWSER = pUtil.readDataFromPropertyFile("browser");
		String URL = pUtil.readDataFromPropertyFile("url");
		if(BROWSER.equalsIgnoreCase("chrome"))
		{
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();
			System.out.println(BROWSER+"=======Launched Successfull========");
		}
		else if(BROWSER.equalsIgnoreCase("firefox"))
		{
			WebDriverManager.firefoxdriver().setup();
			driver=new FirefoxDriver();
			System.out.println(BROWSER+"=======Launched Successfull========");
		}
		else
		{
			System.out.println("==========Invalid Browser Name==========");
		}
		
		sDriver=driver;
		wUtil.maximizeWindow(driver);
		wUtil.waitForPageLoad(driver);
		driver.get(URL);
	}
	
	@BeforeMethod(groups = {"SmokeSuite","RegressionSuite"})
	public void bmConfig() throws IOException
	{
		String USERNAME = pUtil.readDataFromPropertyFile("username");
		String PASSWORD = pUtil.readDataFromPropertyFile("password");
		LoginPage lp=new LoginPage(driver);
		lp.loginToApp(USERNAME, PASSWORD);
		System.out.println("======Login Successfull==========");
	}
	
	@AfterMethod(groups = {"SmokeSuite","RegressionSuite"})
	public void amConfig()
	{
		HomePage hp=new HomePage(driver);
		hp.logoutOfApplication(driver);
		System.out.println("======Logout Successfull========");
	}
	
	//@AfterTest
	@AfterClass(groups = {"SmokeSuite","RegressionSuite"})
	public void acConfig()
	{
		driver.quit();
		System.out.println("========Browser Closed Successfull===========");
	}
	
	@AfterSuite(groups = {"SmokeSuite","RegressionSuite"})
	public void asConfig()
	{
		System.out.println("========Database Connection Closed==========");
	}

}
