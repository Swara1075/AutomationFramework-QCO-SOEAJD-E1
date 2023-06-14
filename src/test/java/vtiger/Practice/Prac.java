package vtiger.Practice;


import org.openqa.selenium.Dimension;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Prac {

	public static void main(String[] args) throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
		
		driver.manage().window().maximize();
		Thread.sleep(5000);
		
		driver.get("https://www.google.co.in/");
		//driver.switchTo().activeElement().sendKeys("java",Keys.ENTER);
		
		/*Dimension d = new Dimension(200,300);
		driver.manage().window().setSize(d);
		
		Point p = new Point(200,300);
		driver.manage().window().setPosition(p);
		*/
		
		System.out.println(driver.getCurrentUrl());
		System.out.println(driver.getPageSource());
		
		driver.close();
	}
}
