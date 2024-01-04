package manager;

import models.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HelperUser extends HelperBase{
    public HelperUser(WebDriver wd) {
        super(wd);
    }

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

    public void submitLogin(){
        //wd.findElement(By.xpath("//*[@type='submit']")).click();
        click(By.xpath("//*[@type='submit']"));
        //click(By.xpath("//*[@type='button']"));
    }


    public boolean isLogged() {

        return isElementPresent(By.xpath("//*[text()=' Logout ']"));

    }

    public void logout() {
        click(By.xpath("//*[text()=' Logout ']"));
    }


    public void clickOkButton() {
        click(By.xpath("//button[text()='Ok']"));
    }
}
