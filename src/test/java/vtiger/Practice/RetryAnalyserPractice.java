package vtiger.Practice;

import org.testng.Assert;
import org.testng.annotations.Test;

public class RetryAnalyserPractice {
	
	@Test(retryAnalyzer=genericUtilities.RetryAnalyzerImplementation.class)
	public void retryAnalyser()
	{
		Assert.fail();
		System.out.println("pass");
	}

}