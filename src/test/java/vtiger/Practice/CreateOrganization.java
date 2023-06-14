package vtiger.Practice;

import java.time.Duration;
import java.util.Random;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateOrganization {

	public static void main(String[] args) {

		        Random ran=new Random();
		        int r=ran.nextInt(1000);
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
				
				//Step3:Navigate to Organizations link 
                driver.findElement(By.linkText("Organizations")).click();
                
                //Step4:Click on Create Organization look Up Image 
                driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();
                
                //Step5:Create Organization with Mandatory fields 
                driver.findElement(By.name("accountname")).sendKeys("Landmark"+r);
                
                //Step6:Save
        		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
        		
        		//Step7:Verify
        		String orgHeader = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
        		if(orgHeader.contains("Landmark"))
        		{
        			System.out.println(orgHeader);
        			System.out.println("Test Script Passes");
        		}
        		else
        		{
        			System.out.println("Failed");
        		}

        		//Step8:Logout
                WebElement adImg = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
                Actions act=new Actions(driver);
                act.moveToElement(adImg).perform();
                driver.findElement(By.linkText("Sign Out")).click();
                System.out.println("Logout Successfully");
                driver.close();
				
	}

}
