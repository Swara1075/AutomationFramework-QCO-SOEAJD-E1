package genericUtilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * This Class Consists of generic methods related to property file
 * @author swathi B
 *
 */

public class PropertyFileUtility {
	/**
	 * This method will read data from property file and return value to caller
	 * @param key
	 * @return
	 * @throws IOException
	 */
	
	public String readDataFromPropertyFile(String key) throws IOException
	{
		FileInputStream fisp=new FileInputStream(IContantsUtility.propertyFiePath);
		Properties pObj=new Properties();
		pObj.load(fisp);
		 String value = pObj.getProperty(key);
		 return value;
		
	}
	
	

}
