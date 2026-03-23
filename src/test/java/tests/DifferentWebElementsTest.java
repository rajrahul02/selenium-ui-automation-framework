package tests;

import java.util.Map;

import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import base.BaseTest;
import pages.DifferentWebElementsPage;
import utils.DriverFactory;
import utils.JsonUtil;
import utils.Log;

@Test(groups = { "regression" })
public class DifferentWebElementsTest extends BaseTest {

	private DifferentWebElementsPage automationPage;
	Map<String, String> data = JsonUtil.readJson("src/test/resources/testdata/differentWebElements.json", Map.class);
	private static final Logger log = Log.getLogger(LoginTest.class);

	@BeforeMethod(alwaysRun = true)
	public void pageSetup() {
		automationPage = new DifferentWebElementsPage(DriverFactory.getDriver());
		automationPage.openPage(data.get("url"));
	}

	@Test
	public void selectRadioButton() {
		String radioValue = data.get("radioValue");
		log.info("select radio button " + radioValue);
		automationPage.clickRadio(radioValue);
	}

	@Test
	public void setCountry() {
		automationPage.selectCountry(data.get("countryKeyword"), data.get("country"));
	}

	@Test
	public void addDropDownOption() {
		automationPage.selectDropDown(data.get("dropDownOption"));
	}
	
	@Test
	public void selectCheckBox() {
		automationPage.clickRadio(data.get("checkboxOPtion"));
	}
	
	@Test
	public void openNewWindow() {
		automationPage.openNewWindow();
	}
	
	@Test
	public void verifyAlert() {
		automationPage.selectAlert(data.get("alertTextMessage"));
	}

}
