package BasePart;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class DriverSingleton {
    private static WebDriver driver;

    /* method that gets the key we are looking
  for in the XML file as a String and return the value stored in the
  corresponding key.*/
    public static String getData(String keyName) throws Exception {
        File fXmlFile = new File("C:\\Users\\Kate\\IdeaProjects\\Course_Project_2-master\\src\\main\\resources\\Data.xml");
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(fXmlFile);
        doc.getDocumentElement().normalize();
        return doc.getElementsByTagName(keyName).item(0).getTextContent();
    }

    public static WebDriver getDriverInstance() {
        /*XML data for cross-browser testing*/
        String type = null;
        try {
            type = getData("browserType");
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (driver == null && type.equals("Chrome")) {
            System.setProperty("webdriver.chrome.driver", "E:\\QA experts\\Programs\\chromedriver.exe");
            driver = new ChromeDriver();

        } else if (driver == null && type.equals("FF")) {
            System.setProperty("webdriver.gecko.driver", "E:\\QA experts\\Programs\\geckodriver.exe");
            driver = new FirefoxDriver();

        }

        return driver;
    }

}
