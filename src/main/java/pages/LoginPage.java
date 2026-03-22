package pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import utils.Log;
import utils.WaitUtil;

public class LoginPage {

	private WebDriver driver;
	private Logger log = Log.getLogger(LoginPage.class);
	private By username = By.id("user-name");
	private By password = By.id("password");
	private By loginButton = By.id("login-button");
	private By errorMsg = By.xpath("//*[@class='error-message-container error']");

	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}

	public void open(String url) {
		log.info("Opening URL: " + url);
		driver.get(url);
		 WaitUtil.waitForElementVisible(driver, username);
	}

	public void login(String user, String pass) {
		log.info("Entering username: " + user);
		WaitUtil.waitForElementVisible(driver, username).sendKeys(user);
		log.info("Entering password");
		WaitUtil.waitForElementVisible(driver, password).sendKeys(pass);
		log.info("Clicking login button");
		 WaitUtil.waitForElementClickable(driver, loginButton).click();
	}

	public boolean isErrorMessageVisible() {
		return  WaitUtil.waitForElementVisible(driver, errorMsg).isDisplayed();
	}
}