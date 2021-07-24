package Processes;

import BasePart.BasePage;
import BasePart.DriverSingleton;
import org.openqa.selenium.By;

public class PickBusiness extends BasePage {
    public void pickBusiness () {
    pickGiftCard();
    enterPrice();
    }

/* clicks on a voucher*/
private void pickGiftCard (){
        clickElement(new By.ByClassName("bm-product-card-link"));
    System.out.println(DriverSingleton.getDriverInstance().findElements(new By.ByClassName("bm-product-card-link")).size());
    }
/*enters an amount of the voucher*/
    private void enterPrice () {
    sendKeysToElement(new By.ByCssSelector("input[placeholder='הכנס סכום']"),"142");
    clickElement(new By.ByCssSelector("button[gtm='בחירה']"));
    }

}
