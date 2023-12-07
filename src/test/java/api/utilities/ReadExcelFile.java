package api.utilities;

import java.io.FileInputStream;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcelFile {

	public static FileInputStream inputSteam;
	public static XSSFWorkbook workbook;
	public static XSSFSheet excelSheet;
	public static XSSFRow row;
	public static XSSFCell cell;
	
	
	public static String getCellValue(String fileName,String sheetName,int rowNo,int cellNo) {
		
		try {
			
			inputSteam= new FileInputStream(fileName);
			workbook= new XSSFWorkbook(inputSteam);
			excelSheet=workbook.getSheet(sheetName);
			cell= workbook.getSheet(sheetName).getRow(rowNo).getCell(cellNo);
			
			workbook.close();
			
			
			return cell.getStringCellValue();
			
		}
		catch(Exception e) {
			return "";
		}
		
	}
	
	public static int getRowCount(String fileName,String sheetName) {
		
		try {
			inputSteam=new FileInputStream(fileName);
			// Create XSSFWorkbook class object for excel file manipulation
			
			workbook= new XSSFWorkbook(inputSteam);
			excelSheet=workbook.getSheet(sheetName);
			
			//get total number of columns
			int ttlRows=excelSheet.getLastRowNum()+1;
			workbook.close();
			return ttlRows;
			
		}
		catch(Exception e) {
			return 0;
		}
	}
	
	public static int getColCount(String fileName,String sheetName) {
		
		try {
			FileInputStream inputStream=new FileInputStream(fileName);
			// Create XSSFWorkbook class object for excel file manipulation
			
			XSSFWorkbook workbook= new XSSFWorkbook(inputStream);
			XSSFSheet excelSheet=workbook.getSheet(sheetName);
			
			//get total number of columns
			int ttlCells=excelSheet.getRow(0).getLastCellNum();
			workbook.close();
			return ttlCells;
			
		}
		catch(Exception e) {
			return 0;
		}
	}
}
