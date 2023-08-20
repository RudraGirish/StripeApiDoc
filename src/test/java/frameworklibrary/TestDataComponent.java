package frameworklibrary;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;




public class TestDataComponent {
	
	String className;
	String methodName;
	HSSFWorkbook wb = null;
	
	TestDataComponent(String className, String methodName) {
		this.className = className;
		this.methodName =methodName;
	}
	
	public ArrayList<Map<String,String>> getData() throws IOException {
		
		String filePath = System.getProperty("user.dir") + "//TestData//" +  className + "." + "xls";
		FileInputStream fis = new FileInputStream(filePath);
		wb = new HSSFWorkbook(fis);
		HSSFSheet sheet = wb.getSheet(className);
		String tcName = "";
		ArrayList<Map<String,String>> data = new ArrayList<Map<String,String>>();
		
		Map<String,String> map = new HashMap<String,String>();
		
		int totalRow = sheet.getLastRowNum();
		int totalCol = sheet.getRow(0).getLastCellNum();
		
		for(int i=1;i<=totalRow;i++) {
			tcName = sheet.getRow(i).getCell(0).getStringCellValue().trim();
			
			if (tcName.equals(methodName)) {
				for(int j=1;j<totalCol;j++) {
					String keyName = sheet.getRow(0).getCell(j).getStringCellValue();
					String keyVal =  sheet.getRow(i).getCell(j).getStringCellValue();
					map.put(keyName,keyVal);
				}
				data.add(map);
			}
		}
		
		return data;
	}
}
