package tests;

import java.util.Map;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import base.BaseTest;
import pages.LoginPage;
import utils.DriverFactory;
import utils.JsonUtil;
import org.apache.logging.log4j.Logger;
import utils.Log;

@Test(groups = {"regression"})
public class LoginTest extends BaseTest {
	private LoginPage loginPage;
	private static final Logger log = Log.getLogger(LoginTest.class);
	
	 @BeforeMethod(alwaysRun = true)
	    public void pageSetup() {
	        loginPage = new LoginPage(DriverFactory.getDriver()); // only page init
	    }

	@Test(groups = {"validlogin"})
	public void loginTest() {	
		log.info("Starting login test with " +username+ " and "+password);
		loginPage.open(baseUrl);
		loginPage.login(username, password);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void inValidLoginTest() {
		log.info("Starting invalid login test");
		loginPage.open(baseUrl);
		Map<String, String> data = JsonUtil.readJson("src/test/resources/testdata/login.json", Map.class);
		loginPage.login(data.get("username"), data.get("password"));
		 Assert.assertTrue(loginPage.isErrorMessageVisible());
	}
}