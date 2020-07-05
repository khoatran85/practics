package pageObject;


import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import commons.AbstractPage;
import pageUI.dataTableUI;

public class DataTableObject extends AbstractPage{
WebDriver driver;
	public DataTableObject (WebDriver mapdriver) {
		driver = mapdriver;	
	}
	public void clickToNewRecordButton() {
		waitForElementClickable(driver, dataTableUI.ADD_NEW_RECORD_BUTTON);
		clickToElement(driver, dataTableUI.ADD_NEW_RECORD_BUTTON);
	}
	public void sendKeyToTextbox(String values, String value) {
		sendkeyToElement(driver, dataTableUI.INPUT_TEXTBOX, value, values);
	}
	public void clickToSubmitButton() {
		waitForElementClickable(driver, dataTableUI.SUBMIT_BUTTON);
		clickToElement(driver, dataTableUI.SUBMIT_BUTTON);
	}
	public boolean inputNewTextDisplay() {
		clickToElement(driver, dataTableUI.PAGE_NUMBER, "24");
		sleepInSeconds(4);
		waitForElementVisible(driver, dataTableUI.CELL_TEXT, "Country", "VietNam");
		return isElementDisplay(driver, dataTableUI.CELL_TEXT, "Country", "VietNam");
	}
	public void clickToPageNumber(String values) {
		clickToElement(driver, dataTableUI.PAGE_NUMBER, values);
		sleepInSeconds(3);
		
	}
	public void clickToDeleteRecord(String values) {
		waitForElementClickable(driver, dataTableUI.DELETE_RECORD_BUTTON, values);
		clickToElement(driver, dataTableUI.DELETE_RECORD_BUTTON, values);
	}
	public boolean isRecordDeleted(String...values) {
		waitForElementInvisible(driver, dataTableUI.CELL_TEXT, values);
		return isElementDisplay(driver, dataTableUI.CELL_TEXT, values);
	}
	
	

}
