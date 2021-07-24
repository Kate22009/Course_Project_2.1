package Tests;

import BasePart.DriverSingleton;
import Processes.Filters;
import Processes.GiftOrdering;
import Processes.PickBusiness;
import Processes.SignUp;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Main {

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
        test = extent.createTest("MyFirstTest", "Sample description");
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
            openURL();
        }
     /*opens URL depending on url chosen in XML file*/
     public static void openURL(){
         try {
             driver.get(DriverSingleton.getData("URL"));
         } catch (Exception e) {
             e.printStackTrace();
         }
     }

        /* First test A. Intro & Registration screen*/
        @Test
        public void test_01 () {
            try {
                SignUp signup1 = new SignUp();
                signup1.openPage();
                signup1.signup();

                /*Assertion of all text fields in the registration window after entering the constant values*/
                Assert.assertEquals(driver.findElement(new By.ByCssSelector("input[placeholder='שם פרטי']")).getAttribute("value"), SignUp.EXPNAME);
                System.out.println(driver.findElement(new By.ByCssSelector("input[placeholder='שם פרטי']")).getAttribute("value"));
                Assert.assertEquals(driver.findElement(new By.ByCssSelector("input[type=email]")).getAttribute("value"), SignUp.EXPMAIL);
                System.out.println(driver.findElement(new By.ByCssSelector("input[type=email]")).getAttribute("value"));
                Assert.assertEquals(driver.findElement(new By.ByCssSelector("input[type=password]")).getAttribute("value"), SignUp.EXPPASS);
                System.out.println(driver.findElement(new By.ByCssSelector("input[type=password]")).getAttribute("value"));
                Assert.assertEquals(driver.findElement(new By.ByCssSelector("input[placeholder='אימות סיסמה']")).getAttribute("value"), SignUp.EXCPASS);
                System.out.println(driver.findElement(new By.ByCssSelector("input[placeholder='אימות סיסמה']")).getAttribute("value"));

                signup1.login();
                test.pass("details", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot(timeNow)).build());
            } catch (Exception e) {
                e.printStackTrace();
                test.log(com.aventstack.extentreports.Status.FAIL, "Registration failed" + e.getMessage());
            }
        }

        /* Second test  B. Home Screen*/
        @Test
        public void test_02 () {
            try {
                Filters filter1 = new Filters();
                filter1.filter();

                test.pass("details", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot(timeNow)).build());
            } catch (Exception e) {
                e.printStackTrace();
                test.log(com.aventstack.extentreports.Status.FAIL, "Filtering functionality failed" + e.getMessage());
            }
        }

        /* Third test C. Pick business*/
        @Test
        public void test_03 () {

            try {
                String URL = driver.getCurrentUrl();
                Assert.assertEquals(URL, "https://buyme.co.il/search?budget=3&category=204&region=11");

                PickBusiness business = new PickBusiness();
                business.pickBusiness();

                test.pass("details", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot(timeNow)).build());
            } catch (Exception e) {
                e.printStackTrace();
                test.log(com.aventstack.extentreports.Status.FAIL, "Gift card choice failed" + e.getMessage());
            }

        }
        /* Forth test D. Sender & Receiver information screen*/
        @Test
        public void test_04 () {
            try {

                GiftOrdering order1 = new GiftOrdering();
                order1.ordering();
                test.pass("details", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot(timeNow)).build());
            } catch (Exception e) {
                e.printStackTrace();
                test.log(com.aventstack.extentreports.Status.FAIL, "Order option test failed" + e.getMessage());
            }
        }

        @AfterTest
        public static void tearDown () {
            driver.quit();
            extent.flush();
        }
        /* method that takes a screenshot of the whole page*/
        static String takeScreenShot (String ImagesPath){
            TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
            File screenShotFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
            File destinationFile = new File(ImagesPath + ".png");
            try {
                FileUtils.copyFile(screenShotFile, destinationFile);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
            return ImagesPath + ".png";
        }

    }