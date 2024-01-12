package manager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class ApplicationManager {

    WebDriver wd;
    HelperUser helperUser;
    HelperCar helperCar;

    public void inIt() {
        wd=new ChromeDriver();
        wd.manage().window().maximize();
        wd.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        wd.navigate().to("https://ilcarro.web.app/search");
        helperUser=new HelperUser(wd);
        helperCar=new HelperCar(wd);


    }

    public HelperCar getHelperCar() {
        return helperCar;
    }

    public HelperUser getHelperUser() {
        return helperUser;
    }

    public void stop() {
        wd.quit();

    }


}
