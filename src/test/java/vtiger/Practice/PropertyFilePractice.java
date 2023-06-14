package vtiger.Practice;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyFilePractice {

	public static void main(String[] args) throws IOException {

		//Step 1: Open the file in java readable format
		FileInputStream fis=new FileInputStream(".\\src\\test\\resources\\CommonData.properties");
		
		//Step 2:Create object of properties class from java.util
		Properties pObj=new Properties();
		
		//Step 3:Load the file into properties
		pObj.load(fis);
		
		//Step 4:give the key and read the value
		String value = pObj.getProperty("browser");
		System.out.println(value);
	}

}
