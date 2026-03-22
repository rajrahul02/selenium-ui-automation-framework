package base;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import utils.DriverFactory;
import utils.EnvReader;

public class BaseTest {
	protected WebDriver driver;
	protected String baseUrl;
	protected String username;
	protected String password;

	@BeforeMethod(alwaysRun = true)
	public void setup() {
		DriverFactory.initDriver();
		driver = DriverFactory.getDriver();

		// Common config
		baseUrl = EnvReader.get("APP_BASE_URL");
		username = EnvReader.get("APP_USERNAME");
		password = EnvReader.get("APP_PASSWORD");
	}

	@AfterMethod(alwaysRun = true)
	public void teardown() {
		DriverFactory.quitDriver();
	}
}