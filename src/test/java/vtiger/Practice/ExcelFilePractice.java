package vtiger.Practice;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;

import genericUtilities.ExcelFileUtility;

public class ExcelFilePractice {

	public static void main(String[] args) throws EncryptedDocumentException, IOException {


		ExcelFileUtility eUtil=new ExcelFileUtility();
		String value = eUtil.readDataFromExcel("Organization", 1, 2);
		System.out.println(value);
	

}
}