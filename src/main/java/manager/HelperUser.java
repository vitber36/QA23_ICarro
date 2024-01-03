package manager;

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
        click(By.xpath("//*[@href='/login?url=%2Fsearch']"));
    }

    public void fillLoginForm(String email,String password){
      //  WebElement emailInput= wd.findElement(By.xpath("//*[@id='email']"));
        //emailInput.click();
       // emailInput.clear();
       // emailInput.sendKeys(email);
        type(By.xpath("//*[@id='email']"),email);

       // WebElement passwordInput=wd.findElement(By.xpath("//*[@id='password']"));
        //passwordInput.click();
       // passwordInput.clear();
       // passwordInput.sendKeys(password);
        type(By.xpath("//*[@id='password']"),password);
    }

    public void submitLogin(){
        click(By.xpath("//*[@type='submit']"));
        click(By.xpath("//*[@type='button']"));
    }


    public boolean isLogged() {

        return isElementPresent(By.xpath("//*[@class='message']"));

    }

    public void logout() {
        click(By.xpath("//*[@href='/logout?url=%2Fsearch"));
    }
}
