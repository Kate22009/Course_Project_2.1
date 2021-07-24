package Processes;

import BasePart.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.WebElement;

public class GiftOrdering extends BasePage {

    public void ordering() {
        pressToSmbElseOption();
        enterReceiverName();
        pickEvent();
        enterBlessing();
        uploadPicture();
        clickButton();
    }


    /* Press radio button*/
    private void pressToSmbElseOption() {
        clickElement(new By.ByClassName("button-forSomeone"));
    }

    /*Enter receiver name*/
    private void enterReceiverName() {
        sendKeysToElement(new By.ByCssSelector("input[type=text]"), "Kate");
    }


    /*Pick an event (wedding /birthday)*/
    private void pickEvent() throws ElementClickInterceptedException {
        clickElement(new By.ByClassName("selected-name"));
        clickElement(new By.ByXPath("//span[contains(text(),'מתנות סוף שנה')]"));
    }
        /*Enter a blessing*/

    private void enterBlessing() {
            clearText(new By.ByCssSelector("textarea"));
            sendKeysToElement(new By.ByCssSelector("textarea"), "Happy birthday");
        }
        /*Upload a picture*/
    private void uploadPicture () {

        sendKeysToElement (new By.ByXPath("//input[@name='logo']"), "E:\\QA experts\\Automation\\pic1.jpeg");}

        /*Click on Continue button*/
        private void clickButton () {
            clickElement(new By.ByCssSelector("button[gtm='המשך']"));
        }
    }
