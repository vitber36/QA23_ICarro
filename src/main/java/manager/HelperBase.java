package manager;

import com.google.common.io.Files;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class HelperBase {
    Logger logger= LoggerFactory.getLogger(HelperBase.class);
    WebDriver wd;

    public HelperBase(WebDriver wd) {
        this.wd = wd;
    }

    public void type(By locator, String text){
        WebElement element=wd.findElement(locator);
        element.click();
        element.clear();
        clearNew(element);
        if(text!=null) {
            element.sendKeys(text);
        }

    }

    public void submit(){
        //wd.findElement(By.xpath("//*[@type='submit']")).click();
        click(By.xpath("//*[@type='submit']"));
        //click(By.xpath("//*[@type='button']"));
    }

    public void click(By locator){
        WebElement element=wd.findElement(locator);
        element.click();
    }
    public boolean isElementPresent(By locator){
        List<WebElement> list=wd.findElements(locator);
        return list.size()>0;
    }

    public void clearNew(WebElement element) {
        element.sendKeys("");
        element.sendKeys(Keys.BACK_SPACE);
    }

    public String getMessage() {
        //WebElement element = wd.findElement(By.cssSelector(".dialog-container>h2"));
       // String text = element.getText();
        //return text;
        //pause(2000);
        return wd.findElement(By.cssSelector(".dialog-container>h2")).getText();

    }

    public void pause(int time){
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isAlertPresent(String message) {
        Alert alert = new WebDriverWait(wd,10)
                .until(ExpectedConditions.alertIsPresent());
        if (alert!=null&&alert.getText().contains(message)){
            //click ok-->
            //pause(5000);
            alert.accept();
            //click cancel--> alert.dismiss();
            //type into alert--> alert.sendKeys("hello");
            return true;
        }
        return false;
    }
    public void getScreen(String link) {
        TakesScreenshot takesScreenshot= (TakesScreenshot) wd;
        File tmp=takesScreenshot.getScreenshotAs(OutputType.FILE);
        try {
            Files.copy(tmp,new File(link));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
