package vtiger.Practice;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateContact {

	public static void main(String[] args) {

		
		//Step1:Launch Browser
		WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get("http://localhost:8888/");
		
		//Step2:Login to application with valid credentials
        driver.findElement(By.name("user_name")).sendKeys("admin");
		driver.findElement(By.name("user_password")).sendKeys("admin");
		driver.findElement(By.id("submitButton")).click();
		
		//Step3:Navigate to Contacts link
		driver.findElement(By.xpath("//a[text()='Contacts']")).click();
		
		//Step4:Click on Create contact look Up Image
        driver.findElement(By.xpath("//img[@alt='Create Contact...']")).click();
		
		//Step5:Create Contact with Mandatory fields
        driver.findElement(By.name("lastname")).sendKeys("Swathi");
		
		//Step6:Save
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		
		//Step7:Verify
		String contactHeader = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if(contactHeader.contains("Swathi"))
		{
			System.out.println(contactHeader);
			System.out.println("Test Script Passed");
		}
		else
		{
			System.out.println("Failed");
		}
		
		//Step8:logout of Application
        WebElement adImg = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
        Actions act=new Actions(driver);
        act.moveToElement(adImg).perform();
        driver.findElement(By.linkText("Sign Out")).click();
        System.out.println("Logout Successfully");
        driver.close();
		
		

		

	}

}
