package genericUtilities;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

/**
 * This class provides implementation for ITestListener Interface
 * @author swathi B
 *
 */
public class ListenersImplementation implements ITestListener {
	
	ExtentReports report;
	ExtentTest test;

	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		String methodName = result.getMethod().getMethodName();
		System.out.println(methodName+"-----Test Execution Started-----");
		
		test=report.createTest(methodName);
		test.log(Status.INFO, methodName+ "->Started");	
	}

	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		String methodName = result.getMethod().getMethodName();
		System.out.println(methodName+"-----Test Execution Successfull-----");
		
		test.log(Status.PASS, methodName+ "->Pass");
	}

	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		String methodName = result.getMethod().getMethodName();
		System.out.println(methodName+"-----Test Execution Failed-----");
		System.out.println(result.getThrowable());
		
		test.log(Status.FAIL, methodName+ "->Fail");
		test.log(Status.WARNING, result.getThrowable());
		
		
//		//Take ScreenShot
//		String screenShotName=methodName+"-"+new JavaUtility().getSystemDateInFormat();
//		WebDriverUtility wUtil=new WebDriverUtility();
//		try {
//			String path = wUtil.takeScreenShot(BaseClass.sDriver, screenShotName);
//			test.addScreenCaptureFromPath(path);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		}

	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		String methodName = result.getMethod().getMethodName();
		System.out.println(methodName+"-----Test Execution Skipped-----");
		System.out.println(result.getThrowable());
		
		test.log(Status.SKIP, methodName+ "->Skip");
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
	}

	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub
	}

	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		System.out.println("------Execution Started-----");
		ExtentSparkReporter html=new ExtentSparkReporter(".\\ExtentReports\\Report - "+new JavaUtility().getSystemDateInFormat()+".html");
		html.config().setDocumentTitle("vTiger Execution Report");
		html.config().setReportName("Execution Report BuildV2.3.4");
		html.config().setTheme(Theme.DARK);
		
		report=new ExtentReports();
		report.attachReporter(html);
		report.setSystemInfo("Base Browser", "chrome");
		report.setSystemInfo("Base URL", "http://localhost:8888/");
		report.setSystemInfo("Base Platform", "Windows-Family");
		report.setSystemInfo("Reporter", "Swathi");
		}

	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		System.out.println("-----Execution Finished-----");
		report.flush(); //To Generate Extent Report
	}
	
	

}
