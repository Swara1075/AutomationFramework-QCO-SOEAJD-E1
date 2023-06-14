package vtiger.Practice;

import java.util.Date;
import java.util.Random;

/**
 * This method consists of generic methods related to java
 * @author swathi B
 *
 */
public class JavaUtilityPractices {
	
	public int getRandomNumber()
	{ 
		Random r=new Random();
		return r.nextInt(1000);
	}
	
	public String getSystemDate()
	{
		Date d=new Date();
		return d.toString();
		
	}

	public String getSystemDateInFormat()
	{
		Date d=new Date();
		String[] dArray = d.toString().split(" ");
		String date = dArray[2];
		String month = dArray[1];
		String year = dArray[5];
		String time = dArray[3].replace(":", "-");
		return date+" "+month+" "+year+" "+time;
				
	
	
	
	
	
	}
}
