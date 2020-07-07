package commons;

import org.openqa.selenium.WebDriver;

import pageObject.DataTableObject;
import pageObject.UploadFileObject;

public class PageGeneratorManager {
	public static DataTableObject getDataTablePage(WebDriver driver) {
		return new DataTableObject(driver);
	}

	public static UploadFileObject getUploadPage(WebDriver driver) {
		return new UploadFileObject(driver);
	}
}
