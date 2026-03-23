package pages;

import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import utils.Log;
import utils.WaitUtil;

public class DifferentWebElementsPage {

	private WebDriver driver;
	private Logger log = Log.getLogger(DifferentWebElementsPage.class);
	private String radioButton = "//input[@value='%s']";
	private By countryDropDown = By.xpath("//input[@id='autocomplete']");
	private String selectCountry = "//*[@class='ui-menu-item-wrapper'][text()='%s']";
	private By selectDropDownElement = By.id("dropdown-class-example");
	private By checkbox = By.xpath("//input[@type='checkbox']");
	private By openWindow = By.id("openwindow");
	private By newWindowElement = By.xpath("//*[@data-testid='parkwebLayout']");
	private By alertButton = By.id("alertbtn");
	private By confirmButton = By.id("confirmbtn");

	public DifferentWebElementsPage(WebDriver driver) {
		this.driver = driver;
	}

	public void openPage(String url) {
		driver.get(url);
	}

	public void clickRadio(String value) {
		driver.findElement(By.xpath(String.format(radioButton, value))).click();
	}

	public void selectCountry(String keyword, String country) {
		WaitUtil.waitForElementVisible(driver, countryDropDown).sendKeys(keyword);
		WaitUtil.waitForElementVisible(driver, (By.xpath(String.format(selectCountry, country))));
		driver.findElement(By.xpath(String.format(selectCountry, country))).click();
	}

	public void selectDropDown(String option) {
		WebElement dropDown = driver.findElement(selectDropDownElement);
		WaitUtil.waitForElementClickable(driver, selectDropDownElement);
		Select select = new Select(dropDown);
		select.selectByValue(option);
	}

	public void setCheckbox(String option) {
		List<WebElement> checkboxes = driver.findElements(checkbox);
		for (WebElement checkbox : checkboxes) {
			String checkboxValue = checkbox.getAttribute("value");
			if (checkboxValue.equals(option)) {
				if (!checkbox.isSelected()) {
					checkbox.click();
				}
			}
		}
	}

	public void openNewWindow() {
		String parentWindow = driver.getWindowHandle();
		driver.findElement(openWindow).click();
		Set<String> allwindows = driver.getWindowHandles();
		for (String window : allwindows) {
			if (!window.equals(parentWindow)) {
				driver.switchTo().window(window);
				break;
			}
		}
		WaitUtil.waitForElementVisible(driver, newWindowElement);
		Assert.assertTrue(driver.findElement(newWindowElement).isDisplayed());
		driver.close();
		driver.switchTo().window(parentWindow);
		Assert.assertTrue(driver.findElement(openWindow).isDisplayed());
	}
	
	public void selectAlert(String alertTextMessage) {
		WaitUtil.waitForElementClickable(driver, alertButton);
		driver.findElement(alertButton).click();
		Alert alert = driver.switchTo().alert();
		String alertText = alert.getText();
		alertText.equals(alertTextMessage);
		alert.accept();
		WaitUtil.waitForElementClickable(driver, confirmButton);
		driver.findElement(confirmButton).click();
		alert.accept();
	}

}
