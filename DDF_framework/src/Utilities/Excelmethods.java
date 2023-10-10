package Utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Excelmethods {
XSSFWorkbook wb;
//constructor for reading excelpath
public Excelmethods(String excelpath) throws Throwable
{
	FileInputStream fi=new FileInputStream(excelpath);
	wb=new XSSFWorkbook(fi);
}
//method for counting no.of rows
public int rowcount(String sheetname)
{
	return wb.getSheet(sheetname).getLastRowNum();
}
//method for reading celldata
public String getcelldata(String sheetname,int row,int column)
{
	String data="";
	if(wb.getSheet(sheetname).getRow(row).getCell(column).getCellType()==Cell.CELL_TYPE_NUMERIC)
	{
		int celldata=(int) wb.getSheet(sheetname).getRow(row).getCell(column).getNumericCellValue();
		data=String.valueOf(celldata);
	}
	else
	{
		data=wb.getSheet(sheetname).getRow(row).getCell(column).getStringCellValue();
	}
	return data;
}
//method for writing results
public void setcelldata(String sheetname,int row,int column,String status,String writeexcel) throws Throwable
{
	XSSFSheet ws=wb.getSheet(sheetname);
	XSSFRow sr=ws.getRow(row);
	XSSFCell rc=sr.createCell(column);
	rc.setCellValue(status);
	if(status.equalsIgnoreCase("Pass"))
	{
		XSSFCellStyle style=wb.createCellStyle();
		XSSFFont font=wb.createFont();
	    font.setColor(IndexedColors.GREEN.getIndex());
	    font.setBold(true);
	    style.setFont(font);
	    ws.getRow(row).getCell(column).setCellStyle(style);
	}
	else if (status.equalsIgnoreCase("Fail"))
	{
		XSSFCellStyle style=wb.createCellStyle();
		XSSFFont font=wb.createFont();
		font.setColor(IndexedColors.RED.getIndex());
		font.setBold(true);
		style.setFont(font);
		ws.getRow(row).getCell(column).setCellStyle(style);
	}
	else if (status.equalsIgnoreCase("Blocked"))
	{
		XSSFCellStyle style=wb.createCellStyle();
		XSSFFont font=wb.createFont();
		font.setColor(IndexedColors.BLUE.getIndex());
		font.setBold(true);
		style.setFont(font);
		ws.getRow(row).getCell(column).setCellStyle(style);
	}
	FileOutputStream fo=new FileOutputStream(writeexcel);
	wb.write(fo);
}
}
