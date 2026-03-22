package utils;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

public class WaitUtil {

	private static final int TIMEOUT = Integer.parseInt(EnvReader.get("TIMEOUT"));

    public static WebElement waitForElementVisible(WebDriver driver, By locator) {
        WebDriverWait wait = new WebDriverWait(
        		driver,
                Duration.ofSeconds(TIMEOUT)
        );
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static WebElement waitForElementClickable(WebDriver driver, By locator) {
        WebDriverWait wait = new WebDriverWait(
        		driver,
                Duration.ofSeconds(TIMEOUT)
        );
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public static boolean waitForUrlContains(WebDriver driver, String fraction) {
        WebDriverWait wait = new WebDriverWait(
        		driver,
                Duration.ofSeconds(TIMEOUT)
        );
        return wait.until(ExpectedConditions.urlContains(fraction));
    }

    public static void waitForPageLoad(WebDriver driver) {
        new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT))
                .until(driver1 -> ((JavascriptExecutor) driver)
                        .executeScript("return document.readyState")
                        .equals("complete"));
    }
}