package vtiger.Practice;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataProviderInterviewQuestion {
	
	@Test(dataProvider = "apparel")
	public void apparelDetails(String brand,int price,int size)
	{
		System.out.println(brand+"<->"+price+"<->"+size);
	}

	@DataProvider(name="apparel")
	public Object[][] getData()
	{
		Object[][] data=new Object[5][3];
		
		data[0][0] = "Nike";
		data[0][1] = 1000;
		data[0][2] = 32;
		
		data[1][0] = "Adidas";
		data[1][1] = 2000;
		data[1][2] = 34;
		
		data[2][0] = "Burberry";
		data[2][1] = 3000;
		data[2][2] = 36;
		
		data[3][0] = "Levis";
		data[3][1] = 1000;
		data[3][2] = 32;
		
		data[4][0] = "Zara";
		data[4][1] = 1000;
		data[4][2] = 32;
		return data;
		
	}
}
