import org.openqa.selenium.By;
import org.testng.Assert;


public class SignUp extends BasePage {


    public static final String EXPNAME = "John";
    public static final String EXPMAIL = "vorovayak21@gmail.com";
    public static final String EXPPASS = "123456Aa";
    public static final String EXCPASS = "123456Aa";

    public void openPage() { openSignupPage(); }

    public void signup() throws Exception { enterCredentials(); }

    public  void  login(){ pressLogin(); }


    private void openSignupPage() {
        clickElement(By.className("seperator-link"));
        clickElement(By.className("theme"));

    }

    private void enterCredentials() throws Exception {
        sendKeysToElement(By.cssSelector("input[type=text]"), EXPNAME);
        sendKeysToElement(By.cssSelector("input[type=email]"), EXPMAIL);
        sendKeysToElement(By.cssSelector("input[type=password]"), EXPPASS);
        sendKeysToElement(By.cssSelector("input[placeholder='אימות סיסמה']"), EXCPASS);

    }



    private void pressLogin(){
        clickElement(By.cssSelector("button[type=submit]"));
    }

}
