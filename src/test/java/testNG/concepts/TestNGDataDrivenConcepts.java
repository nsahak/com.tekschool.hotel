package testNG.concepts;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Ignore;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TestNGDataDrivenConcepts {
	// In TestNG we can do DataDriven Testing in two ways.
	// 1. Parameters
	// 2. Data Provider
	// Using Parameters we can pass values from TestNG.xml file
	// Using Data Provider we can pass values from Excel using Apache POI lib
	
	@Test
	@Ignore
	@Parameters({ "valueA", "valueB" })
	public void parametersExample(int a, int b) {
		System.out.println("this is value of  " + a);
		System.out.println("this is value of  " + b);
	}

	// Data Providers
	@Test(dataProvider = "getData")
	public void dataProviderExample(String firstName, String lastName, String email, String password) {
		
		System.out.println(firstName);
		System.out.println(lastName);
		System.out.println(email);
		System.out.println(password);
	}

	@DataProvider()
	public Object[][] getData() {
		Object data[][] = getExcelData();
		return data;
	}

	public static Object[][] getExcelData() {
		Workbook book;
		Sheet sheet;
		String excelPath = ".\\Data.xlsx";
		String sheetName = "info";
		try {
			FileInputStream file = new FileInputStream(excelPath);
			book = WorkbookFactory.create(file);
			sheet = book.getSheet(sheetName);
			// This line of code will read all values in excel till last row and last column
			Object data[][] = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
			for (int i = 0; i < sheet.getLastRowNum(); i++) {
				for (int j = 0; j < sheet.getRow(0).getLastCellNum(); j++) {
					data[i][j] = sheet.getRow(i + 1).getCell(j).toString();
				}
			}
			return data;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}