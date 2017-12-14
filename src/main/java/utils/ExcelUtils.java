package utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.BeforeSuite;

public class ExcelUtils {

	public static XSSFWorkbook workBook = null;
	public static XSSFSheet sheet = null;
	public static XSSFRow row = null;
	public static XSSFCell cell = null;
	public static FileInputStream fileInputStream = null;
	public static FileOutputStream fileOutputStream = null;
	public static Properties properties;
	
	static String filepath ;

	public static String read(int rowNo, int columnNo, String sheetName)
	{
		String content = null;
		try
		{
			filepath = ExcelUtils.configuration("testDatapath");
			fileInputStream = new FileInputStream(filepath);
			workBook = new XSSFWorkbook(fileInputStream);
			sheet = workBook.getSheet(sheetName);
			DataFormatter dataFormatter = new DataFormatter();
			content =dataFormatter.formatCellValue(sheet.getRow(rowNo).getCell(columnNo));
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return content;
	}
	public static void write(int rowNo,int columnNo, String sheetName, String value)
	{
		try
		{
			filepath = ExcelUtils.configuration("testDatapath");
			fileInputStream = new FileInputStream(filepath);
			workBook = new XSSFWorkbook(fileInputStream);
			sheet = workBook.getSheet(sheetName);
			row = sheet.getRow(rowNo);
			cell = row.getCell(columnNo,row.RETURN_BLANK_AS_NULL);
			if(cell==null)
			{
				cell = row.createCell(columnNo);
				System.out.println("message"+value);
				cell.setCellValue(value);
			}
			else
			{
				cell.setCellValue(value);
			}
			fileOutputStream = new FileOutputStream(filepath);
			workBook.write(fileOutputStream);
			fileOutputStream.flush();
			fileOutputStream.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static String configuration(String key) throws IOException
	{
		fileInputStream = new FileInputStream((System.getProperty("user.dir")+"//src//main//resources//config.properties"));
		properties = new Properties();
		properties.load(fileInputStream);
		String value = properties.getProperty(key, "Key not found");
		value.trim();
		return value;
	}
}
