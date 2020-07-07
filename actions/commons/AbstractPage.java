package commons;

import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


public abstract class AbstractPage {
	private Select select;
	private JavascriptExecutor js;
	private WebDriverWait explicitWait;
	private WebElement element;
	private List<WebElement> elements;
	private Actions action;

	public void openUrl(WebDriver driver, String urlValue) {
		driver.get(urlValue);
	}

	public String getPageTitle(WebDriver driver) {
		return driver.getTitle();
	}

	public String getCurrentUrl(WebDriver driver) {
		return driver.getCurrentUrl();
	}

	public void back(WebDriver driver) {
		driver.navigate().back();
	}

	public void refresh(WebDriver driver) {
		driver.navigate().refresh();
	}

	public void forward(WebDriver driver) {
		driver.navigate().forward();

	}

	public void acceptAlert(WebDriver driver) {
		driver.switchTo().alert().accept();
	}

	public void cancelAler(WebDriver driver) {
		driver.switchTo().alert().dismiss();
	}

	public void sendkeyToAlear(WebDriver driver, String value) {
		driver.switchTo().alert().sendKeys(value);
	}

	public String getTextInAlert(WebDriver driver) {
		return driver.switchTo().alert().getText();
	}

	public void waitAlertPresent(WebDriver driver) {
		WebDriverWait explicitWait;
		explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
		explicitWait.until(ExpectedConditions.alertIsPresent());
	}

	public void switchWindowByID(WebDriver driver, String parentID) {
		Set<String> allWindowIDs = driver.getWindowHandles();
		for (String runWindows : allWindowIDs) {
			if (!runWindows.equals(parentID)) {
				driver.switchTo().window(runWindows);
			}
		}
	}

	public void switchWindowByTitle(WebDriver driver, String targetTitle) {
		Set<String> allWindowIDs = driver.getWindowHandles();
		for (String runWindows : allWindowIDs) {
			driver.switchTo().window(runWindows);
			String currentWindowTitle = driver.getTitle();
			if (currentWindowTitle.equals(targetTitle)) {
				break;
			}
		}
	}

	public boolean areAllWindowsCloseWithoutParent(WebDriver driver, String parentWindow) {

		Set<String> allWindowIDs = driver.getWindowHandles();
		for (String runWindows : allWindowIDs) {
			if (!runWindows.equals(parentWindow)) {
				driver.switchTo().window(runWindows);
				driver.close();
			}
		}
		driver.switchTo().window(parentWindow);
		if (driver.getWindowHandles().size() == 1) {
			return true;
		}
		return false;
	}

	public By byXpath(String locator) {
		return By.xpath(locator);
	}

	public WebElement findElementByXpath(WebDriver driver, String locator) {
		return driver.findElement(byXpath(locator));
	}
	public WebElement findElementByXpath(WebDriver driver, String locator, String...values) {
		return driver.findElement(byXpath(castToObject(locator, values)));
	}

	public String castToObject(String locator, String... values) {
		return String.format(locator, (Object[]) values);
	}

	public List<WebElement> findElementsByXpath(WebDriver driver, String locator) {
		return driver.findElements(byXpath(locator));
	}
	public List<WebElement> findElementsByXpath(WebDriver driver, String locator, String...values) {
		return driver.findElements(byXpath(castToObject(locator, values)));
	}

	public void sleepInSeconds(long timeOut) {
		try {
			Thread.sleep(timeOut * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void clickToElement(WebDriver driver, String locator) {
		findElementByXpath(driver, locator).click();

	}

	public void clickToElement(WebDriver driver, String locator, String... values) {
		findElementByXpath(driver, castToObject(locator, values)).click();

	}

	public void sendkeyToElement(WebDriver driver, String locator, String value) {
		element = findElementByXpath(driver, locator);
		element.clear();
		element.sendKeys(value);
	}
	public void sendkeyToElement(WebDriver driver, String locator, String value, String...values) {
		element = findElementByXpath(driver, castToObject(locator, values));
		element.clear();
		element.sendKeys(value);
	}

	public String getElementText(WebDriver driver, String locator) {
		return findElementByXpath(driver, locator).getText().trim();
	}

	public String getElementAttribute(WebDriver driver, String locator, String AttributeName) {
		return findElementByXpath(driver, locator).getAttribute(AttributeName);
	}

	public void selectValueInDropdown(WebDriver driver, String locator, String value) {
		select = new Select(findElementByXpath(driver, locator));
		select.selectByVisibleText(value);
	}

	public String getSelectedIteminDropdown(WebDriver driver, String locator) {
		select = new Select(findElementByXpath(driver, locator));
		return select.getFirstSelectedOption().getText();
	}

	public void selectItemInCustomDropdown(WebDriver driver, String parentLocator, String allItemsLocator,
			String targetValue) {
		js = (JavascriptExecutor) driver;
		element = findElementByXpath(driver, parentLocator);
		js.executeScript("arguments[0].click();", element);
		sleepInSeconds(1);
		explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(byXpath(allItemsLocator)));
		elements = findElementsByXpath(driver, allItemsLocator);
		for (WebElement item : elements) {
			if (item.getText().equals(targetValue)) {
				if (item.isDisplayed()) {
					js.executeScript("arguments[0].click();", item);
				} else {
					js.executeScript("arguments[0].scrollIntoView(true);", item);
					sleepInSeconds(1);
					js.executeScript("arguments[0].click();", item);
				}
				sleepInSeconds(1);
				break;
			}
		}
	}

	public int countElementNumber(WebDriver driver, String locator) {
		return findElementsByXpath(driver, locator).size();
	}
	public int countElementNumber(WebDriver driver, String locator, String...values) {
		return findElementsByXpath(driver, castToObject(locator, values)).size();
	}

	public void checkToCheckbox(WebDriver driver, String locator) {
		element = findElementByXpath(driver, locator);
		if (!element.isSelected()) {
			element.click();
		}
	}

	public void uncheckToCheckbox(WebDriver driver, String locator) {
		element = findElementByXpath(driver, locator);
		if (element.isSelected()) {
			element.click();
		}
	}

	public boolean isElementDisplay(WebDriver driver, String locator) {
		return findElementByXpath(driver, locator).isDisplayed();

	}

	public boolean isElementDisplay(WebDriver driver, String locator, String... values) {
		return findElementByXpath(driver, castToObject(locator, values)).isDisplayed();

	}

	public boolean isElementEnable(WebDriver driver, String locator) {
		return findElementByXpath(driver, locator).isEnabled();

	}

	public boolean isElementselected(WebDriver driver, String locator) {
		return findElementByXpath(driver, locator).isSelected();

	}

	public void switchToFrameorIframe(WebDriver driver, String locator) {
		driver.switchTo().frame(findElementByXpath(driver, locator));
	}

	public void switchToDefault(WebDriver driver) {
		driver.switchTo().defaultContent();
	}

	public void hoverMouseToElement(WebDriver driver, String locator) {
		action = new Actions(driver);
		action.moveToElement(findElementByXpath(driver, locator)).perform();
	}

	public void doubleClickToElement(WebDriver driver, String locator) {
		action = new Actions(driver);
		action.doubleClick(findElementByXpath(driver, locator)).perform();
	}

	public void rightClickToElement(WebDriver driver, String locator) {
		action = new Actions(driver);
		action.contextClick(findElementByXpath(driver, locator)).perform();
	}

	public void DragAndDrop(WebDriver driver, String sourceLocator, String targetLocator) {
		action = new Actions(driver);
		action.dragAndDrop(findElementByXpath(driver, sourceLocator), findElementByXpath(driver, targetLocator))
				.perform();
	}

	public void sendKeyboardToElement(WebDriver driver, String locator, Keys key) {
		action = new Actions(driver);
		action.sendKeys(findElementByXpath(driver, locator), key).perform();
	}

	public void upload1FileBySenkey(WebDriver driver, String locator, String imagepath) {
		findElementByXpath(driver, locator).sendKeys(imagepath);

	}

	public void upload3FilesBySenkey(WebDriver driver, String locator, String imagepath1, String imagepath2,
			String imagepath3) {
		findElementByXpath(driver, locator).sendKeys(imagepath1 + "\n" + imagepath2 + "\n" + imagepath3);
	}

	public Object executeForBrowser(WebDriver driver, String javaSript) {
		js = (JavascriptExecutor) driver;
		return js.executeScript(javaSript);
	}

	public boolean verifyTextInInnerText(WebDriver driver, String expectedText) {
		js = (JavascriptExecutor) driver;
		String textActual = (String) js
				.executeScript("return document.documentElement.innerText.match('" + expectedText + "')[0]");
		return textActual.equals(expectedText);
	}

	public void scrollToBottomPage(WebDriver driver) {
		js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	public void highlightElement(WebDriver driver, String locator) {
		js = (JavascriptExecutor) driver;
		element = findElementByXpath(driver, locator);
		String originalStyle = element.getAttribute("style");
		js.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style",
				"border: 5px solid red; border-style: dashed;");
		sleepInSeconds(1);
		js.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", originalStyle);

	}

	public void clickToElementByJS(WebDriver driver, String locator) {
		js = (JavascriptExecutor) driver;
		element = findElementByXpath(driver, locator);
		js.executeScript("arguments[0].click();", element);
	}

	public void scrollToElement(WebDriver driver, String locator) {
		js = (JavascriptExecutor) driver;
		element = findElementByXpath(driver, locator);
		js.executeScript("arguments[0].scrollIntoView(true);", element);
	}

	public void removeAttributeInDOM(WebDriver driver, String locator, String attributeRemove) {
		js = (JavascriptExecutor) driver;
		element = findElementByXpath(driver, locator);
		js.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", element);
	}

	public boolean isImageLoaded(WebDriver driver, String locator) {
		js = (JavascriptExecutor) driver;
		element = findElementByXpath(driver, locator);
		boolean status = (boolean) js.executeScript(
				"return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0",
				element);
		if (status) {
			return true;
		}
		return false;
	}

	public void waitForElementVisible(WebDriver driver, String locator) {
		explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(byXpath(locator)));
	}

	public void waitForElementVisible(WebDriver driver, String locator, String... values) {
		explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(byXpath(castToObject(locator, values))));
	}

	public void waitForElementInvisible(WebDriver driver, String locator) {
		explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(byXpath(locator)));
	}

	public void waitForElementInvisible(WebDriver driver, String locator, String... values) {
		explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(byXpath(castToObject(locator, values))));
	}

	public void waitForElementClickable(WebDriver driver, String locator) {
		explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
		explicitWait.until(ExpectedConditions.elementToBeClickable(byXpath(locator)));
	}
	public void waitForElementClickable(WebDriver driver, String locator, String...values) {
		explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
		explicitWait.until(ExpectedConditions.elementToBeClickable(byXpath(castToObject(locator, values))));
	}

}
