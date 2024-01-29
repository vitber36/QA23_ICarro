package manager;

import models.User;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

import javax.swing.*;

public class HelperUser extends HelperBase{
    public HelperUser(WebDriver wd) {
        super(wd);
    }

    //--------------login---------------
    public void openLoginForm(){
       // WebElement loginTab=wd.findElement(By.xpath("//*[@href='/login?url=%2Fsearch']"));
        //loginTab.click();
        click(By.xpath("//a[text()=' Log in ']"));
    }

    public void fillLoginForm(User user){
      //  WebElement emailInput= wd.findElement(By.xpath("//*[@id='email']"));
        //emailInput.click();
       // emailInput.clear();
       // emailInput.sendKeys(email);
        type(By.xpath("//*[@id='email']"), user.getEmail());

       // WebElement passwordInput=wd.findElement(By.xpath("//*[@id='password']"));
        //passwordInput.click();
       // passwordInput.clear();
       // passwordInput.sendKeys(password);
        type(By.xpath("//*[@id='password']"), user.getPassword());
    }




    public boolean isLogged() {

        return isElementPresent(By.xpath("//*[text()=' Logout ']"));

    }

    public void logout() {
        click(By.xpath("//*[text()=' Logout ']"));
    }


    public void clickOkButton() {
        if(isElementPresent(By.xpath("//button[text()='Ok']"))){
        click(By.xpath("//button[text()='Ok']"));
        }
    }

    public String getErrorText(){
        return wd.findElement(By.cssSelector("div.error")).getText();
    }


//---------------registration------------------
    public void openRegistrationForm() {
        click(By.xpath("//a[text()=' Sign up ']"));
    }

    public void fillRegistrationForm(User user) {
        type(By.id("name"), user.getFirstName());
        type(By.id("lastName"), user.getLastName());
        type(By.id("email"), user.getEmail());
        type(By.id("password"), user.getPassword());
    }


    public void checkPolicy() {
        if (!wd.findElement(By.id("terms-of-use")).isSelected()) {
            //click(By.id("terms-of-use"));
            //32click(By.cssSelector("label[for='terms-of-use']"));

            //var 2
            JavascriptExecutor js = (JavascriptExecutor) wd;
            js.executeScript("document.querySelector('#terms-of-use').click()");
        }
    }

    public void checkPolicyXY(){
        if(!wd.findElement(By.id("terms-of-use")).isSelected()) {
            WebElement label = wd.findElement(By.cssSelector("label[for='terms-of-use']"));

            Rectangle rect = label.getRect();
            int w = rect.getWidth();
            int xOffSet = -w / 2;
            Actions actions = new Actions(wd);
            actions.moveToElement(label, xOffSet, 0).click().release().perform();

        }
    }

    public void login(User user) {
        openLoginForm();
        fillLoginForm(user);
        submit();
        clickOkButton();
    }
    public String getMessageEmptyFirstName() {
        return wd.findElement(By.xpath("//*[text()=' Name is required ']")).getText();
    }

    public String getMessageEmptyLastName() {
        return wd.findElement(By.xpath("//*[text()=' Last name is required ']")).getText();
    }

    public String getMessageEmailWithouShtrudel() {
        return wd.findElement(By.xpath("//*[text()='Wrong email format']")).getText();
    }

    public String getMessageWrongPasswordShort() {
        return wd.findElement(By.xpath("//*[contains(text(),'minimum 8 symbols')]")).getText();
    }
}
