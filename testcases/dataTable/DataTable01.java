package dataTable;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.AbstractTest;
import commons.PageGeneratorManager;
import pageObject.DataTableObject;
import pageUI.dataTableUI;

public class DataTable01 extends AbstractTest{
WebDriver driver;
DataTableObject dataTablePage;
	@Parameters({"browser", "url"})
	@BeforeClass
	public void BeforeClass(String browserName, String urlValue) {
		driver = opendriver(browserName, urlValue);
		dataTablePage = new DataTableObject(driver);

	}

	//@Test
	public void DataTable_01_submitRecord() {
		dataTablePage.clickToNewRecordButton();
		dataTablePage.sendKeyToTextbox("females", "Hanh");
		dataTablePage.sendKeyToTextbox("country", "VietNam");
		dataTablePage.sendKeyToTextbox("age", "30");
		dataTablePage.sendKeyToTextbox("males", "Hai");
		dataTablePage.sendKeyToTextbox("year", "2000");
		dataTablePage.sendKeyToTextbox("total", "9999");
		dataTablePage.clickToSubmitButton();
		Assert.assertTrue(dataTablePage.inputNewTextDisplay());
	}
	
	//@Test
	public void DataTable_02_deleteRecord() {
		dataTablePage.clickToPageNumber("1");
		dataTablePage.clickToDeleteRecord("Aruba");
		Assert.assertFalse(dataTablePage.isRecordDeleted("Country", "Aruba"));
		
	}
	@Test
	public void DataTable_03() {
		dataTablePage.openUrl(driver, "https://www.jqueryscript.net/demo/jQuery-Dynamic-Data-Grid-Plugin-appendGrid");
		dataTablePage.sendkeysToTextbox("Company", "2", "gameloft");
	}
	
	@AfterClass
	public void AfterClass() {
		driver.quit();
	}
}
