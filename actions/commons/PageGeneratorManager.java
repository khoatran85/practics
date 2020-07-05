package commons;

import org.openqa.selenium.WebDriver;

import pageObject.DataTableObject;

public class PageGeneratorManager {
	public static DataTableObject getDataTablePage (WebDriver driver) {
		return new DataTableObject(driver);
	}
}
