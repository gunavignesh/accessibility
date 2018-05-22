package library;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class DataProviderBean {

	public static WebDriver driver;
	public String Browsr = null;
	Properties properties;

	/*********************************************************/

	// properties file reading

	public void readPropertiesFile(String ElementName) throws Exception {
		try {
			FileInputStream Locator = new FileInputStream(ElementName);
			properties = new Properties();
			properties.load(Locator);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public String getData(String ElementName) throws Exception {
		// Read value using the logical name as Key
		String data = properties.getProperty(ElementName);
		return data;
	}

	public By getLocator(String ElementName) throws Exception {
		// Read value using the logical name as Key
		String locator = properties.getProperty(ElementName);
		// Split the value which contains locator type and locator value
		String locatorType = locator.split(":")[0];
		String locatorValue = locator.split(":")[1];
		// Return a instance of By class based on type of locator
		if (locatorType.toLowerCase().equals("id"))
			return By.id(locatorValue);
		else if (locatorType.toLowerCase().equals("name"))
			return By.name(locatorValue);
		else if ((locatorType.toLowerCase().equals("classname")) || (locatorType.toLowerCase().equals("class")))
			return By.className(locatorValue);
		else if ((locatorType.toLowerCase().equals("tagname")) || (locatorType.toLowerCase().equals("tag")))
			return By.className(locatorValue);
		else if ((locatorType.toLowerCase().equals("linktext")) || (locatorType.toLowerCase().equals("link")))
			return By.linkText(locatorValue);
		else if (locatorType.toLowerCase().equals("partiallinktext"))
			return By.partialLinkText(locatorValue);
		else if ((locatorType.toLowerCase().equals("cssselector")) || (locatorType.toLowerCase().equals("css")))
			return By.cssSelector(locatorValue);
		else if (locatorType.toLowerCase().equals("xpath"))
			return By.xpath(locatorValue);
		else
			throw new Exception("Locator type '" + locatorType + "' not defined!!");
	}

	/*********************************************************/

	// Method for Selecting Desired Browser

	public void SelectBrowser(String BROWSER) throws Exception {

		System.out.println("Browser: " + BROWSER);
		if (BROWSER.equals("FF")) {
			Browsr = "Firefox_";
			System.out.println("Firefox is selected");
			System.setProperty("webdriver.gecko.driver", "E:\\Selenium\\Lib\\geckodriver.exe");
			driver = new FirefoxDriver();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.manage().window().maximize();
			// File profileDirectory = new
			// File("C:\\Users\\SRiNIVASALU\\AppData\\Roaming\\Mozilla\\Firefox\\Profiles\\67uahvun.Automation");
			// FirefoxProfile profile = new FirefoxProfile(profileDirectory);
			// driver = new FirefoxDriver(profile);

		} else if (BROWSER.equals("IE")) {
			Browsr = "IExplore_";
			System.out.println("IE is selected");
			driver = new InternetExplorerDriver();
			driver.manage().window().maximize();
			Thread.sleep(2000);

		} else if (BROWSER.equals("Chrome")) {
			Browsr = "GChrome_";
			System.out.println("Google chrome is selected");
			System.setProperty("webdriver.chrome.driver", "E:\\Selenium\\Lib\\chromedriver.exe");
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			Thread.sleep(2000);
		} else {
			System.out.println("Browser not found");
		}
	}
	// End of Method for Selecting Desired Browser
	
	/*********************************************************/

	// Write Excel File
	public void writeExcel(String filePath, String fileName, String sheetName, String[] dataToWrite)
			throws IOException {
		// Create an object of File class to open xlsx file
		File file = new File(filePath + "\\" + fileName);
		// Create an object of FileInputStream class to read excel file
		FileInputStream inputStream = new FileInputStream(file);
		Workbook Workbook = null;
		// Find the file extension by splitting file name in substring and getting only
		// extension name
		String fileExtensionName = fileName.substring(fileName.indexOf("."));
		// Check condition if the file is xlsx file
		if (fileExtensionName.equals(".xlsx")) {
			// If it is xlsx file then create object of XSSFWorkbook class
			Workbook = new XSSFWorkbook(inputStream);
		}
		// Check condition if the file is xls file
		else if (fileExtensionName.equals(".xls")) {
			// If it is xls file then create object of XSSFWorkbook class
			Workbook = new HSSFWorkbook(inputStream);
		}
		// Read excel sheet by sheet name
		Sheet sheet = Workbook.getSheet(sheetName);
		// Get the current count of rows in excel file
		int rowCount = sheet.getLastRowNum() - sheet.getFirstRowNum();
		// Get the first row from the sheet
		Row row = sheet.getRow(0);
		// Create a new row and append it at last of sheet
		Row newRow = sheet.createRow(rowCount + 1);
		// Create a loop over the cell of newly created Row
		for (int j = 0; j < row.getLastCellNum(); j++) {
			// Fill data in row
			Cell cell = newRow.createCell(j);
			cell.setCellValue(dataToWrite[j]);
		}
		// Close input stream
		inputStream.close();
		// Create an object of FileOutputStream class to create write data in excel file
		FileOutputStream outputStream = new FileOutputStream(file);
		// write data in the excel file
		Workbook.write(outputStream);
		// close output stream
		outputStream.close();
	}

	/*********************************************************/
	// Compare Excel file

	public String[] compareExcel(String cText, int cLoc, String filePath, String fileName, String sheetName)
			throws IOException {
		// Create an object of File class to open xlsx file
		File file = new File(filePath + "\\" + fileName);
		// String cTextret[]=null;
		DataFormatter formatter = new DataFormatter();
		// Create an object of FileInputStream class to read excel file
		FileInputStream inputStream = new FileInputStream(file);
		Workbook Workbook = null;
		// Find the file extension by splitting file name in substring and getting only
		// extension name
		String fileExtensionName = fileName.substring(fileName.indexOf("."));
		// Check condition if the file is xlsx file
		if (fileExtensionName.equals(".xlsx")) {
			// If it is xlsx file then create object of XSSFWorkbook class
			Workbook = new XSSFWorkbook(inputStream);
		}
		// Check condition if the file is xls file
		else if (fileExtensionName.equals(".xls")) {
			// If it is xls file then create object of XSSFWorkbook class
			Workbook = new HSSFWorkbook(inputStream);
		}
		// Read sheet inside the workbook by its name
		Sheet mySheet = Workbook.getSheet(sheetName);
		// Find number of rows in excel file
		int rowCount = mySheet.getLastRowNum() - mySheet.getFirstRowNum();

		String cTextret[] = new String[mySheet.getRow(0).getLastCellNum()];

		// Create a loop over all the rows of excel file to read it
		for (int i = 0; i < rowCount + 1; i++) {
			Row row = mySheet.getRow(i);
			int colCount = row.getLastCellNum();
			
			if (row.getCell(cLoc).getStringCellValue().trim().equals(cText.trim())) {
				for (int j = 0; j < colCount; j++) {
					cTextret[j] = formatter.formatCellValue(row.getCell(j));
				}
				break;
			}
		}
		return cTextret;
	}

	/*********************************************************/

}