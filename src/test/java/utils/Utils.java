package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Utils {

	String inpFile= "/home/ajit/eclipse_Space/Data_Driven/inputs/loginPass.xlsx" ;
	String outFile= "/home/ajit/eclipse_Space/Data_Driven/output/out.xlsx";
	
	XSSFWorkbook wb;
	XSSFSheet ws;
	
	public int rowCount() throws Throwable {
		FileInputStream filein= new FileInputStream(new File(inpFile));
		
		wb= new XSSFWorkbook(filein);
		ws= wb.getSheetAt(0);
		
		return ws.getLastRowNum() + 1;
		
	}
	
	
	public String getId(int row) {		
		 String id= ws.getRow(row).getCell(0).getStringCellValue();		
		 return id;
	}
	
	
	public String getPass(int row) {		
		String pass= ws.getRow(row).getCell(1).getStringCellValue();		
		return pass;
	}
	
	
	// set status in cell
	public void setData(int row, String status) throws Throwable {
		
		XSSFCellStyle style = null;
		
		if(status== "pass") {
			style= wb.createCellStyle();
			style.setFillBackgroundColor(IndexedColors.OLIVE_GREEN.getIndex());
			style.setFillPattern(FillPatternType.BIG_SPOTS);
		}
		else if(status== "fail") {
			style= wb.createCellStyle();
			style.setFillBackgroundColor(IndexedColors.RED.getIndex());
			style.setFillPattern(FillPatternType.BIG_SPOTS);
		}
		else if(status == "blocked") {
			style= wb.createCellStyle();
			style.setFillBackgroundColor(IndexedColors.BLUE_GREY.getIndex());
			style.setFillPattern(FillPatternType.BIG_SPOTS);
		}
		
		ws.getRow(row).createCell(2).setCellValue(status);
		ws.getRow(row).getCell(2).setCellStyle(style);
		
		FileOutputStream fo= new FileOutputStream(outFile);
		wb.write(fo);
	}
	
	
}












