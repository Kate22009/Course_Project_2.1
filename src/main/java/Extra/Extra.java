package Extra;

import BasePart.BasePage;
import BasePart.DriverSingleton;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Extra extends BasePage {

    private static WebDriver driver;
    /* create ExtentReports and attach reporter(s)*/
    private static ExtentReports extent;
    /* creates a toggle for the given test, adds all log events under it*/
    private static ExtentTest test;
    /*create names with current time for screenshots*/
    String timeNow = String.valueOf(System.currentTimeMillis());

    @Parameters("BrowserType")
    @BeforeClass
    public static void runOnceBeforeClass() throws Exception {

        /*Reporter functionality*/
        String cwd = System.getProperty("user.dir");
        ExtentSparkReporter htmlReporter = new ExtentSparkReporter(cwd + "\\extent.html");
        /*attach reporter*/
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        /*name your test and add description*/
        test = extent.createTest("MyExtraTests", "Sample description");
        /*log results*/
        test.log(com.aventstack.extentreports.Status.INFO, "@Before class");

        try {
            /*create WebElement driver using class BasePart.BasePage.DriverSingleton*/
            driver = DriverSingleton.getDriverInstance();
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);

            test.log(com.aventstack.extentreports.Status.PASS, "Driver established successfully");
        } catch (Exception e) {
            e.printStackTrace();
            test.log(com.aventstack.extentreports.Status.FAIL, "Driver connection failed! " + e.getMessage());
            throw new Exception("Driver failed");
        }
        driver.get(DriverSingleton.getData("URL"));
    }


    @Test
    public void test_extra_1() {
        openError();
        String expMessage = "כל המתנות מחכות לך! אבל קודם צריך מייל וסיסמה";
        Assert.assertEquals(driver.findElement(By.className("parsley-required")).getText(), expMessage);
        closeWindow();
    }
    /*Home screen - Assert error message*/

    private void openError() {
        clickElement(By.className("seperator-link"));
        clickElement(By.cssSelector("button[type=submit]"));

    }

    private void closeWindow() {
        clickElement(By.id("times"));
    }

    /*Choose gift screen*/
    @Test
    public void test_extra_2() throws Exception {
        WebElement element = driver.findElement(By.className("footer-bottom"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        Thread.sleep(3000);
        File screenShotFile = element.getScreenshotAs(OutputType.FILE); // take the screenshot
        try {
            FileUtils.copyFile(screenShotFile, new File("element-screenshot.png")); // save screenshot to disk
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*Sender & Receiver information screen -  Print the text color of the step name ( (למי לשלוח*/
    @Test
    public void test_extra_3() {
        driver.navigate().to("https://buyme.co.il/package/5641345/5642532");
        WebElement textElement = driver.findElement(new By.ByXPath("//div[contains(@class,'step active')]//div[@class='label bottom-xs']"));
        System.out.println(textElement.getCssValue("color"));
    }


    @AfterTest
    public static void tearDown () {
        driver.quit();
        extent.flush();
    }
}