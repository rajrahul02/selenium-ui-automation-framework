package base;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import utils.DriverFactory;
import utils.EnvReader;

public class BaseTest {
	protected WebDriver driver;
	

	@BeforeMethod(alwaysRun = true)
	public void setup() {
		DriverFactory.initDriver();
		driver = DriverFactory.getDriver();
	}

	@AfterMethod(alwaysRun = true)
	public void teardown() {
		DriverFactory.quitDriver();
	}
}