package vtiger.Practice;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import genericUtilities.IContantsUtility;

public class ExcelFileUtilityPractice {
	
	public String readDataFromExcel(String sheetName,int rowNum,int cellNum) throws EncryptedDocumentException, IOException
	{
		FileInputStream fise=new FileInputStream(IContantsUtility.excelFilePath);
		Workbook wb = WorkbookFactory.create(fise);
		Sheet sh = wb.getSheet(sheetName);
		Row rw = sh.getRow(rowNum);
		Cell cel = rw.getCell(cellNum);
		String value = cel.getStringCellValue();
		wb.close();
		return value;
	}
	
	public void writeDataIntoExcelSheet(String sheetName,int rowNum,int cellNum,String value) throws EncryptedDocumentException, IOException
	{
		FileInputStream fise=new FileInputStream(IContantsUtility.excelFilePath);
		Workbook wb = WorkbookFactory.create(fise);
		wb.createSheet(sheetName).createRow(rowNum).createCell(cellNum).setCellValue(value);
		FileOutputStream fos=new FileOutputStream(IContantsUtility.excelFilePath);
		wb.write(fos);
		wb.close();
	}
	
	public Object[][] readMultipleDataFromExcelSheet(String sheetName) throws EncryptedDocumentException, IOException
	{
		FileInputStream fise=new FileInputStream(IContantsUtility.excelFilePath);
		Workbook wb = WorkbookFactory.create(fise);
		Sheet sh = wb.getSheet(sheetName);
		int lr = sh.getLastRowNum();
		int lc = sh.getRow(0).getLastCellNum();
		
		Object[][] data=new Object[lr][lc];
		for(int i=0;i<lr;i++)
		{
			for(int j=0;j<lc;j++)
			{
				data[i][j] = sh.getRow(i+1).getCell(j).getStringCellValue();
			}
		}
		return data;
	}

}
