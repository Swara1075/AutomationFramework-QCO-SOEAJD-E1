package genericUtilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 * This Class Consists of generic methods related to excel sheet
 * @author swathi B
 *
 */
public class ExcelFileUtility {
	/**
	 * This Method will read data from excel and return the value to caller
	 * @param SheetName
	 * @param RowNum
	 * @param CellNum
	 * @return
	 * @throws EncryptedDocumentException
	 * @throws IOException
	 */
	
public String readDataFromExcel(String sheetName,int rowNum,int cellNum) throws EncryptedDocumentException, IOException
{
	FileInputStream fise=new FileInputStream(IContantsUtility.excelFilePath);
	Workbook wb = WorkbookFactory.create(fise);
	Sheet sh = wb.getSheet(sheetName);
	Row rw = sh.getRow(rowNum);
	Cell ce = rw.getCell(cellNum);
	String value = ce.getStringCellValue();
	wb.close();
	return value;
}

/**
 * This method will write value in excel
 * @param sheetName
 * @param rowNum
 * @param cellNum
 * @param value
 * @throws EncryptedDocumentException
 * @throws IOException
 */

public void writeDataIntoExcel(String sheetName, int rowNum, int cellNum, String value) throws EncryptedDocumentException, IOException
{
	FileInputStream fise=new FileInputStream(IContantsUtility.excelFilePath);
	Workbook wb = WorkbookFactory.create(fise);
	wb.createSheet(sheetName).createRow(rowNum).createCell(cellNum).setCellValue(value);
	FileOutputStream fos=new FileOutputStream(IContantsUtility.excelFilePath);
	wb.write(fos);
	wb.close();
}



public Object[][] readMultipleDataFromExcel(String SheetName) throws EncryptedDocumentException, IOException
{
	FileInputStream fis=new FileInputStream(IContantsUtility.excelFilePath);
	Workbook wb = WorkbookFactory.create(fis);
	Sheet sh = wb.getSheet(SheetName);
	int lastRow = sh.getLastRowNum();
	int lastCellNum = sh.getRow(0).getLastCellNum();
	
	Object[][] data=new Object[lastRow][lastCellNum];
	for(int i = 0; i<lastRow; i++)
	{
		for(int j=0;j<lastCellNum;j++)
		{
			 data[i][j] = sh.getRow(i+1).getCell(j).getStringCellValue();
		}
	}
	
	
	return data;
	
}
}
