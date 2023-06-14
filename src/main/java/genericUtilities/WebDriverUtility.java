package genericUtilities;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.JavascriptExecutor;
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
 * This class consists of methods related to webDriver
 * @author swathi B
 *
 */
public class WebDriverUtility {
	
	/**
	 * This method will maximize the window
	 * @param driver
	 */
	public void maximizeWindow(WebDriver driver)
	{
		driver.manage().window().maximize();
	}
	
	/**
	 * This method will minimize the window
	 * @param driver
	 */
	public void minimizeWindow(WebDriver driver)
	{
		driver.manage().window().minimize();
	}
	
	/**
	 * This method will wait for page to load for 20 seconds
	 * @param driver
	 */
	public void waitForPageLoad(WebDriver driver)
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	}
	
	/**
	 * This methos will wait for 20 seconds for a element to be visible
	 * @param driver
	 * @param element
	 */
	public void waitForElementTobevisible(WebDriver driver,WebElement element)
	{
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	/**
	 * This method will wait for 20 seconds for a element to be clickable
	 * @param driver
	 * @param element
	 */
	public void waitForElementToBeClickable(WebDriver driver,WebElement element)
	{
	     WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	     wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	
	/**
	 * This method is used to handle dropdown by index
	 * @param element
	 * @param index
	 */
	public void handleDropDown(WebElement element,int index)
	{
		Select sel = new Select(element);
		sel.selectByIndex(index);
	}
	
	/**
	 * This method is used to handle dropdown by value
	 * @param element
	 * @param value
	 */
	public void handleDropDown(WebElement element,String value)
	{
		Select sel = new Select(element);
		sel.selectByValue(value);
	}
	
	/**
	 * This method is used to handle drop down by visible text
	 * @param text
	 * @param element
	 */
	public void handleDropDown(String text,WebElement element)
	{
		Select sel = new Select(element);
		sel.selectByVisibleText(text);
	}
	
	/**
	 * This method will perform mouse hover action
	 * @param driver
	 * @param element
	 */
	public void mouseHoverAction(WebDriver driver,WebElement element)
	{
	    Actions act = new Actions(driver);
	    act.moveToElement(element).perform();
	}
	
	/**
	 * This method will right click any where in web page
	 * @param driver
	 */
	public void rightClickAction(WebDriver driver)
	{
		Actions act = new Actions(driver);
		act.contextClick().perform();
	}
	
	/**
	 * This method will right click on particular element
	 * @param driver
	 * @param element
	 */
	public void rightClickAction(WebDriver driver,WebElement element)
	{
		Actions act = new Actions(driver);
		act.contextClick(element).perform();
	}
	
	/**
	 * This method will double click any where on web page
	 * @param driver
	 */
	public void doubleClickAction(WebDriver driver)
	{
		Actions act = new Actions(driver);
		act.doubleClick().perform();
	}
	
	/**
	 * This method will double click on particular element
	 * @param driver
	 * @param element
	 */
	public void doubleClickAction(WebDriver driver,WebElement element)
	{
		Actions act = new Actions(driver);
		act.doubleClick(element).perform();
	}
	
	/**
	 * This method will drag and drop from source element to target element
	 * @param driver
	 * @param sourceElement
	 * @param targetElement
	 */
	public void dragAndDropAction(WebDriver driver,WebElement sourceElement,WebElement targetElement)
	{
		Actions act = new Actions(driver);
		act.dragAndDrop(sourceElement, targetElement).perform();
	}
	
	/**
	 * This method drag and drop source element to target based on offsets
	 * @param driver
	 * @param source
	 * @param x
	 * @param y
	 */
	public void dragAndDropAction(WebDriver driver,WebElement source,int x,int y)
	{
		Actions act = new Actions(driver);
		act.dragAndDropBy(source, x, y).perform();
	}
	
	/**
	 * This method will handle frame by index
	 * @param driver
	 * @param index
	 */
	public void switchToFrame(WebDriver driver,int index)
	{
		driver.switchTo().frame(index);
	}

	/**
	 * This method will handle frame by name or ID
	 * @param driver
	 * @param nameOrId
	 */
	public void switchToFrame(WebDriver driver,String nameOrId)
	{
		driver.switchTo().frame(nameOrId);
	}
	
	/**
	 * This method will handle frame by web element
	 * @param driver
	 * @param element
	 */
	public void switchToFrame(WebDriver driver,WebElement element)
	{
		driver.switchTo().frame(element);
	}
	
	/**
	 * This method will switch the control from child frame to immediate parent frame
	 * @param driver
	 */
	public void switchToParentFrame(WebDriver driver)
	{
		driver.switchTo().parentFrame();
	}
	
	/**
	 * This method will switch the control from child frame to default parent frame
	 * @param driver
	 */
	public void switchToDefaultFrame(WebDriver driver)
	{
		driver.switchTo().defaultContent();
	}
	
	/**
	 * This method will accept the alert pop-up
	 * @param driver
	 */
	public void acceptAlert(WebDriver driver)
	{
		driver.switchTo().alert().accept();
	}
	
	/**
	 * This method will dismiss the alert pop-up
	 * @param driver
	 */
	public void dismissAlert(WebDriver driver)
	{
		driver.switchTo().alert().dismiss();
	}
	
	/**
	 * This method will capture the alert text and return it to user
	 * @param driver
	 * @return
	 */
	public String getAlertText(WebDriver driver)
	{
		return driver.switchTo().alert().getText();
	}
	
	/**
	 * This method will take screen shot and return the absolute path
	 * @param driver
	 * @param screenShotName
	 * @return
	 * @throws IOException
	 */
	public String takeScreenShot(WebDriver driver,String screenShotName) throws IOException
	{
		TakesScreenshot ts=(TakesScreenshot) driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		File dst = new File(".\\ScreenShots\\"+screenShotName+".png");
		                                     //ScreenShot1.png
		Files.copy(src, dst);
		
		return dst.getAbsolutePath(); //--used for Extent Reports
		//D://MavenProjects/AutomationFrameWork/Screenshots/Screen.png
	} 
	
	/**
	 * This method will switch the windows based on window title
	 * @param driver
	 * @param partialWindowTitle
	 */
	public void switchToWindow(WebDriver driver,String partialWindowTitle)
	{
		//Step 1: Capture all the window IDs
		Set<String> winIDs = driver.getWindowHandles();
		
		//Step 2: Navigate to each window
		for(String winID:winIDs)
		{
			//Step 3: Capture the title of each window
			String actualTitle = driver.switchTo().window(winID).getTitle();
			
			//Step 4: Compare the title
			if(actualTitle.contains(partialWindowTitle))
			{
				break;
			}
		}
	}
}
