package vtiger.Practice;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Set;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.opentelemetry.exporter.logging.SystemOutLogRecordExporter;

public class EndToEndIntegrationByDataDrivenTesting {

	public static void main(String[] args) throws IOException {

		WebDriver driver = null;
		 
		//Required data
		FileInputStream fisp=new FileInputStream(".\\src\\test\\resources\\CommonData.properties");
		Properties p = new Properties();
		p.load(fisp);
		String BROWSER = p.getProperty("browser");
		String URL = p.getProperty("url");
		String USERNAME = p.getProperty("username");
		String PASSWORD = p.getProperty("password");
		
		FileInputStream fise=new FileInputStream(".\\src\\test\\resources\\TestData.xlsx");
		Workbook wb = WorkbookFactory.create(fise);
		String LASTNAME = wb.getSheet("Contact").getRow(4).getCell(2).getStringCellValue();
		
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
			System.out.println("Invalid Browser name");
		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get(URL);
		
		//Login to application with valid credentials

		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();
		
		//Navigate to Contacts link

		driver.findElement(By.linkText("Contacts")).click();
		
		//Click on Create contact look up image

		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
		
		//Create contact with mandatory fields

		driver.findElement(By.name("lastname")).sendKeys(LASTNAME);
		
		//Select the Organization from organization look up image

		driver.findElement(By.xpath("//img[contains(@onclick,'Accounts&action') and @src='themes/softed/images/select.gif']")).click();
		Set<String> allWindows = driver.getWindowHandles();
		for(String childWindow:allWindows)
		{
			driver.switchTo().window(childWindow);
		}
		driver.findElement(By.linkText("Qspiders")).click();
		Set<String> allWindows1 = driver.getWindowHandles();
		for(String parenWindow:allWindows1)
		{
			driver.switchTo().window(parenWindow);
		}
		
		//Save
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		
		//Verify
		String contactHeader = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if(contactHeader.contains(LASTNAME))
		{
			System.out.println(contactHeader);
			System.out.println("Test Script Pass");
		}
		else
		{
			System.out.println("Test Script Failed");
		}
		
		//Logout
		WebElement adImg = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		Actions act=new Actions(driver);
		act.moveToElement(adImg).perform();
		driver.findElement(By.xpath("//a[@class='drop_down_usersettings' and @href='index.php?module=Users&action=Logout']")).click();
		driver.close();
	}

}
