package vtiger.Practice;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;

import genericUtilities.ExcelFileUtility;

public class ExcelFileUtilityPrcatice {

	public static void main(String[] args) throws EncryptedDocumentException, IOException {

		ExcelFileUtility eUtil=new ExcelFileUtility();
		String value = eUtil.readDataFromExcel("Organization", 1, 2);
		System.out.println(value);
		
		eUtil.writeDataIntoExcel("Trial", 3, 4, "SpiderMan");
		System.out.println("Data Written");
	}

}
