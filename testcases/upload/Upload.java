package upload;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.AbstractTest;
import commons.PageGeneratorManager;
import pageObject.UploadFileObject;

public class Upload extends AbstractTest{
WebDriver driver;
UploadFileObject uploadPage;

	@Parameters({"browser", "url"})
	@BeforeClass
	public void beforeClass(String browserName, String urlValue) {
		driver = opendriver(browserName, urlValue);
		uploadPage = PageGeneratorManager.getUploadPage(driver);
	}

	@Test
	public void f() {
		uploadPage.sendKeysToAddFiles();
		
	}

	@AfterClass
	public void afterClass() {
	}

}
