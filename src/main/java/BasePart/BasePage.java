package BasePart;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class BasePage {
    /* method that clicks on an element*/
    public void clickElement(By locator) {
        getWebElement(locator).click();
    }

    /*method that sends text to an element*/
    public void sendKeysToElement(By locator, String text) {
        getWebElement(locator).sendKeys(text);
    }

    /*method that creates a new instance of webdriver*/
    private static WebElement getWebElement(By locator) {
        return DriverSingleton.getDriverInstance().findElement(locator);
    }

    /*method that clears text from field*/
    public void clearText(By locator) {
        getWebElement(locator).clear();
    }


}
