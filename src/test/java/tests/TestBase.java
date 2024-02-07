package tests;

import manager.ApplicationManager;
import org.openqa.selenium.remote.BrowserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class TestBase {
Logger logger= LoggerFactory.getLogger(TestBase.class);
    static ApplicationManager app=new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));

    @BeforeSuite(alwaysRun = true)

    public static void setApp() {
        app.inIt();

    }
    @AfterSuite(alwaysRun = true)

    public static void tearDown() {
       // app.stop();

    }
    @AfterMethod(alwaysRun = true)
    public void end(){
        logger.info("======================================");
    }
}
