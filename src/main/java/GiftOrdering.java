import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.WebElement;

public class GiftOrdering extends BasePage {

    public void ordering() throws Exception {
        pressToSmbElseOption();
        enterReceiverName();
        pickEvent();
        enterBlessing();
        uploadPicture();
        clickButton();
    }


    /* Press radio
button*/
    private void pressToSmbElseOption() {
        clickElement(new By.ByClassName("button-forSomeone"));
    }

    /*Enter receiver name*/
    private void enterReceiverName() throws Exception {
        sendKeysToElement(new By.ByCssSelector("input[type=text]"), "Kate");
    }


    /*Pick an event (wedding /birthday)*/
    private void pickEvent() throws ElementClickInterceptedException {
        clickElement(new By.ByClassName("selected-name"));
        clickElement(new By.ByXPath("//span[contains(text(),'מתנות סוף שנה')]"));
    }
        /*Enter a blessing*/

    private void enterBlessing() throws Exception  {
            clearText(new By.ByCssSelector("textarea"));
            sendKeysToElement(new By.ByCssSelector("textarea"), "Happy birthday");
        }
        /*Upload a picture*/
    private void uploadPicture () {
//        clickElement(new By.ByCssSelector("label[class='media-circle-btn ember-view bm-media-upload'] div[class='bm-caption-2']"));
        sendKeysToElement (new By.ByXPath("//input[@name='logo']"), "E:\\QA experts\\Automation\\pic1.jpeg");}
//    media-circle-btn ember-view bm-media-upload
        /*Click on Continue button*/
        private void clickButton () {
            clickElement(new By.ByCssSelector("button[gtm='המשך']"));
        }
    }
