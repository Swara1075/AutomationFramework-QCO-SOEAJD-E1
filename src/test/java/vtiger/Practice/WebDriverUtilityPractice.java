package vtiger.Practice;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.io.Files;



/**
 * This class consists of methods specific to webDriver actions
 * @author swathi B
 *
 */
public class WebDriverUtilityPractice {
	
	public void maximizeWindow(WebDriver driver)
	{
		driver.manage().window().maximize();
	}

	public void minimizeWindow(WebDriver driver)
	{
		driver.manage().window().minimize();
	}
	
	public void waitForPageLoad(WebDriver driver)
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	}
	
	public void waitForElementTobeClickable(WebDriver driver,WebElement element)
	{
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	
	public void waitForElementToBeVisible(WebDriver driver,WebElement element)
	{
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	public void handleDropDown(WebElement element,int index)
	{
		Select sel=new Select(element);
		sel.selectByIndex(index);
	}
	
	public void handleDropDown(WebElement element,String text)
	{
		Select sel=new Select(element);
		sel.selectByVisibleText(text);
		
	}
	
	public void handleDropDown(String value,WebElement element)
	{
		Select sel=new Select(element);
		sel.selectByValue(value);
	}
	
	public void mouseHoverAction(WebDriver driver,WebElement element)
	{
		Actions act=new Actions(driver);
		act.moveToElement(element).perform();
	}
	
	public void rightClickAction(WebDriver driver)
	{
		Actions act=new Actions(driver);
		act.contextClick().perform();
	}
	
	public void rigthClickAction(WebDriver driver,WebElement element)
	{
		Actions act=new Actions(driver);
		act.contextClick(element).perform();
	}
	
	public void doubleClickAction(WebDriver driver)
	{
		Actions act=new Actions(driver);
		act.doubleClick().perform();
	}
	
	public void doubleClickAction(WebDriver driver,WebElement element)
	{
		Actions act=new Actions(driver);
		act.doubleClick(element).perform();
	}
	
	public void  dragAndDropAction(WebDriver driver,WebElement source,WebElement target)
	{
		Actions act=new Actions(driver);
		act.dragAndDrop(source, target).perform();
	}
	
	public void dragAndDropAction(WebDriver driver,WebElement source,int x,int y)
	{
		Actions act=new Actions(driver);
		act.dragAndDropBy(source, x, y).perform();
		
	}
	
	public void switchToFrame(WebDriver driver,int index)
	{
		driver.switchTo().frame(index);
	}
	
	public void switchToFrame(WebDriver driver,String nameOrId)
	{
		driver.switchTo().frame(nameOrId);
	}
	
	public void switchToFrame(WebDriver driver,WebElement element)
	{
		driver.switchTo().frame(element);
	}
	
	public void switchToParentFrame(WebDriver driver)
	{
		driver.switchTo().parentFrame();
	}
	
	public void switchToDefaultFrame(WebDriver driver)
	{
		driver.switchTo().defaultContent();
	}
	
	
	public void acceptAlert(WebDriver driver)
	{
		driver.switchTo().alert().accept();
	}
	
	public void dismissAlert(WebDriver driver)
	{
		driver.switchTo().alert().dismiss();
	}
	
	public String getTextAlert(WebDriver driver)
	{
		return driver.switchTo().alert().getText();
	}
	
	//Take Screenshot
	public String takeScreenShot(WebDriver driver,String screenShotName) throws IOException
	{
		TakesScreenshot ts=(TakesScreenshot) driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		File dst=new File(".\\ScreenShots\\"+screenShotName+".png");
		Files.copy(src, dst);
		
		return dst.getAbsolutePath(); //extent report
	}
	
	//switch window
	public void switchToWindow(WebDriver driver,String partialWindowTitle)
	{
		//step 1 capture all window ID
        Set<String> winIDs = driver.getWindowHandles();
        
        //navigate to each window
        for(String winID:winIDs)
        {
        	//step 3:get title of each window
        	String actualTitle = driver.switchTo().window(winID).getTitle();
        
	
        //compare the title
        if(actualTitle.contains(partialWindowTitle))
        {
        	break;
        }
	
        }
	
	}
	 	
	
	
	
	
	
	
	
}
