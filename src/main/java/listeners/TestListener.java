package listeners;

import org.testng.*;
import utils.ScreenshotUtil;
import io.qameta.allure.Allure;

import java.io.ByteArrayInputStream;

public class TestListener implements ITestListener {

    public void onTestFailure(ITestResult result) {
        byte[] screenshot = ScreenshotUtil.capture();
        Allure.addAttachment("Failure Screenshot",
                new ByteArrayInputStream(screenshot));
        Allure.addAttachment("Error",
                result.getThrowable().getMessage());
    }
}