package framework.YourStore.PageFactory;

import java.io.FileInputStream;
import java.io.IOException;

import java.nio.file.Paths;
import java.util.Base64;
import java.util.Properties;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType.LaunchOptions;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class PlaywrightPagefactory {

	Playwright playwright;
	Browser browser;
	BrowserContext browserContext;
	Page page;
	Properties property;


	private static ThreadLocal<Playwright> threadLocalPlaywright = new ThreadLocal<Playwright>();
	private static ThreadLocal<Browser> threadLocalBrowser = new ThreadLocal<Browser>();
	private static ThreadLocal<BrowserContext> threadLocalBrowserContext = new ThreadLocal<BrowserContext>();
	private static ThreadLocal<Page> threadLocalPage = new ThreadLocal<Page>();
	private static ThreadLocal<Properties> threadLocalProperty = new ThreadLocal<Properties>();

	public static Playwright getThreadLocalPlaywright() {
		return threadLocalPlaywright.get();
	}

	public static Browser getThreadLocalBrowser() {
		return threadLocalBrowser.get();
	}

	public static BrowserContext getThreadLocalBrowserContext() {
		return threadLocalBrowserContext.get();
	}

	public static Page getThreadLocalPage() {
		return threadLocalPage.get();
	}

	public static Properties getThreadLocalProperty() {
		return threadLocalProperty.get();
	}

	public Page launchBrowser(Properties property) {
	    String browserName = property.getProperty("browser");
	    String url = property.getProperty("url");
	    playwright = Playwright.create();

	    switch (browserName.toLowerCase()) {
	        case "chromium":
	            browser = playwright.chromium()
	                    .launch(new LaunchOptions().setHeadless(Boolean.parseBoolean(property.getProperty("headless"))));
	            break;
	        case "chrome":
	            browser = playwright.chromium()
	                    .launch(new LaunchOptions().setHeadless(Boolean.parseBoolean(property.getProperty("headless")))
	                            .setChannel(browserName.trim()));
	            break;
	        case "firefox":
	            browser = playwright.firefox()
	                    .launch(new LaunchOptions().setHeadless(Boolean.parseBoolean(property.getProperty("headless"))));
	            break;
	        case "safari":
	            browser = playwright.webkit()
	                    .launch(new LaunchOptions().setHeadless(Boolean.parseBoolean(property.getProperty("headless"))));
	            break;
	        default:
	            System.out.println("Invalid browser name. Please provide a valid browser.");
	            break;
	    }

	    // Set the initialized browser to the ThreadLocal variable
	    threadLocalBrowser.set(browser);

	    // Initialize BrowserContext and Page and set them to their respective ThreadLocal variables
	    threadLocalBrowserContext.set(browser.newContext());
	    threadLocalPage.set(getThreadLocalBrowserContext().newPage());
	    
	    // Navigate to the specified URL
	    getThreadLocalPage().navigate(url);
	    
	    return getThreadLocalPage();
	}
	public Properties readPropertyFile() throws IOException {
		FileInputStream file = new FileInputStream(".\\src\\main\\resources\\config\\config,properties");
		property = new Properties();
		property.load(file);
		return property;


	}

	public Properties readLocatorPropertyFile() throws IOException {

		FileInputStream file = new FileInputStream(".\\src\\main\\resources\\config\\locator_config,properties");
		property = new Properties();
		property.load(file);
		threadLocalProperty.set(property);
		return getThreadLocalProperty();

	}

	public String readExcelData(String columnName) throws IOException {
		String columnValue = null;
		try (FileInputStream excelFile = new FileInputStream(".\\src\\main\\resources\\testdata\\InputTestData.xlsx");
				Workbook workbook = new XSSFWorkbook(excelFile)) {
			Sheet sheet = workbook.getSheetAt(0);
			int lastRowIndex = sheet.getLastRowNum();
			for (int i = 1; i <= lastRowIndex; i++) {
				Row row = sheet.getRow(i);
				if (row != null) {
					Cell cell0 = row.getCell(0);
					if (cell0 != null) {
						String cellValue0;
						// Check the cell type and handle accordingly
						switch (cell0.getCellType()) {
						case STRING:
							cellValue0 = cell0.getStringCellValue();
							break;
						case NUMERIC:
							cellValue0 = String.valueOf(cell0.getNumericCellValue());
							break;
						default:
							cellValue0 = "";
							break;
						}
						if (columnName.trim().equals(cellValue0.trim())) {
							Cell cell1 = row.getCell(1);
							if (cell1 != null) {
								switch (cell1.getCellType()) {
								case STRING:
									columnValue = cell1.getStringCellValue();
									break;
								case NUMERIC:
									columnValue = String.valueOf(cell1.getNumericCellValue());
									break;
								default:
									columnValue = "";
									break;
								}
							}
							break;
						}
					}
				}
			}
		}
		return columnValue;
	}

	public static String takeScreenshot() {
		String path = System.getProperty("user.dir") + "/screenshot/" + System.currentTimeMillis() + ".png";

		byte[] buffer = getThreadLocalPage().screenshot(new Page.ScreenshotOptions().setPath(Paths.get(path)).setFullPage(true));
		String base64Path = Base64.getEncoder().encodeToString(buffer);

		return base64Path;
	}


}
