package Processes;

import BasePart.BasePage;
import BasePart.DriverSingleton;
import org.openqa.selenium.By;

public class Filters extends BasePage {


    public void filter (){
        filterAmount();
        filterRegion();
        filterCategory();
        buttonSearch();
    }
/* chooses "200-299" in the Amount drop list*/
    private void filterAmount(){
        clickElement(new By.ByClassName("chosen-single"));

        System.out.println(DriverSingleton.getDriverInstance().findElements(new By.ByClassName("chosen-single")).size());
        clickElement(new By.ByCssSelector("div[class='chosen-container chosen-container-single form-control dib search-chosen ember-view ember-chosenselect form-control chosen-rtl chosen-rtl chosen-container-single-nosearch chosen-with-drop chosen-container-active'] ul[class='chosen-results']"));
    }
    /*chooses the 'מרכז' option in the region field*/
    private void filterRegion(){

        clickElement(new By.ByXPath("//div[@class='ember-view header-search-bar home']//div[2]//a[1]"));
        clickElement(new By.ByXPath("//li[contains(text(),'מרכז')]"));
    }
    /*choose 'מתנות עד הבית' option in tne category field*/
    private void filterCategory(){
        clickElement(new By.ByXPath("/html/body/div[1]/div/header/div[3]/div/form/div[3]/a/span"));
        clickElement(new By.ByXPath("/html/body/div[1]/div/header/div[3]/div/form/div[3]/div/ul/li[2]"));
    }

    /* presses the Search button*/
    private void buttonSearch (){
        clickElement(new By.ByClassName("search"));
    }
}
