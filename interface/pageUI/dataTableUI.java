package pageUI;

public class dataTableUI {
	public static final String PAGE_NUMBER = "//li[@class='qgrd-pagination-page']/a[text()='%s']";
	public static final String ADD_NEW_RECORD_BUTTON = "//button[@class='qgrd-add-row-btn']";
	public static final String INPUT_TEXTBOX = "//input[@name='%s']";
	public static final String SUBMIT_BUTTON = "//input[@type='submit']";
	public static final String CELL_TEXT = "//div[text()='%s']/ancestor::thead/following-sibling::tbody//td[@data-key='country' and text()='%s']";
	public static final String DELETE_RECORD_BUTTON = "//td[text()='%s']/preceding-sibling::td/button[@class='qgrd-remove-row-btn']";
	public static final String COLUMN_INDEX = "//th[text()='%s']/preceding-sibling::th";
	public static final String CELL_ROW_COLUMN = "//tr[%s]/td[%s]/input";
	
}
