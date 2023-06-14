package genericUtilities;

/**
 * This class will provide implementation for IRetryAnalyser
 */
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzerImplementation implements IRetryAnalyzer{

	int count=0;
	int retryCount=4; //manually executed
	public boolean retry(ITestResult result) {

		while(count<retryCount) //0<1/1<4/2<4/3<4/4<4
		{
		count++; //1/2/3/4
		return true;
		}
		return false;
	

}}
