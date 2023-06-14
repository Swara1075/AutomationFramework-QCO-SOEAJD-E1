package vtiger.Practice;

import java.io.FileInputStream;
import java.io.IOException;
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

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateContactWithDataDriven {

	public static void main(String[] args) throws IOException {
		
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
		Row rw = wb.getSheet("Contact").getRow(1);
		String LASTNAME = rw.getCell(2).getStringCellValue();
		
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
		
		//Login to application with valid credentials
		
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();
		
		//Navigate to Contacts link
		
        driver.findElement(By.linkText("Contacts")).click();
        
        //Click on Create contact look Up Image

        driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
        
        //Create Contact with Mandatory fields

        driver.findElement(By.name("lastname")).sendKeys(LASTNAME);
        
        //Save
        
        driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
        
        //Verify
        
        String contactHeader = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
        if(contactHeader.contains(LASTNAME))
        {
        	System.out.println(contactHeader);
        	System.out.println("Test Script Passed");
        }
        else
        {
        	System.out.println("Tset Script Failed");
        }
        
        //Logout
        WebElement adImg = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
        Actions act=new Actions(driver);
        act.moveToElement(adImg).perform();
        driver.findElement(By.linkText("Sign Out")).click();
        driver.close();
        
		
	}

}
