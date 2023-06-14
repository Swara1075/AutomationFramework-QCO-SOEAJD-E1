package vtiger.Practice;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.opentelemetry.exporter.logging.SystemOutLogRecordExporter;

public class CreateOrganizationWithChemicalsByDataDriven {

	public static void main(String[] args) throws IOException, InterruptedException {

		WebDriver driver=null;
		Random r=new Random();
		int random = r.nextInt(1000);
		
		//Read all required data
		FileInputStream fisp=new FileInputStream(".\\src\\test\\resources\\CommonData.properties");
		Properties p=new Properties();
		p.load(fisp);
		String BROWSER = p.getProperty("browser");
		String URL = p.getProperty("url");
		String USERNAME = p.getProperty("username");
		String PASSWORD = p.getProperty("password");
		
		FileInputStream fise=new FileInputStream(".\\src\\test\\resources\\TestData.xlsx");
		Workbook wb = WorkbookFactory.create(fise);
		Row rw = wb.getSheet("Organization").getRow(4);
		String ORGNAME = rw.getCell(2).getStringCellValue();
		String INDUSTRY = rw.getCell(3).getStringCellValue();
		
		//Launch Browser

		if(BROWSER.equalsIgnoreCase("chrome"))
		{
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();
			System.out.println("Browser Launched");
		}
		else if(BROWSER.equalsIgnoreCase("firefox"))
		{
			WebDriverManager.firefoxdriver().setup();
			driver=new FirefoxDriver();
			System.out.println("Browser Launched");
		}
		else
		{
			System.out.println("Invalid Browser Name");
		}
		
		driver.get(URL);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		
		//Login to application with valid credentials

		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();
		
		//Navigate to Organizations link
		
		driver.findElement(By.linkText("Organizations")).click();
		
		//Click on Create Organization look Up Image

		driver.findElement(By.xpath("//img[@alt='Create Organization...']")).click();
		
		//Create Organization with Mandatory fields

		driver.findElement(By.name("accountname")).sendKeys(ORGNAME+random);
		WebElement industryDropDown = driver.findElement(By.name("industry"));
		Select sel=new Select(industryDropDown);
		sel.selectByValue(INDUSTRY);
		
		//Save
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		
		//Verify
		
		String orgHeader = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if(orgHeader.contains(ORGNAME))
		{
			System.out.println(ORGNAME);
			System.out.println("Test Script pass");
		}
		else
		{
			System.out.println("Test Script Failed");
		}
		
		//Logout
		Thread.sleep(5000);
		WebElement adImg = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		Actions act=new Actions(driver);
		act.moveToElement(adImg);
		driver.findElement(By.linkText("Sign Out")).click();
        driver.close();

	}

}
