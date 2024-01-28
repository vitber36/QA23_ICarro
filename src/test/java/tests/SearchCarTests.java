package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class SearchCarTests extends TestBase{

    @Test
    public void searchCurrentMonthSuccess(){
        app.getHelperCar().searchCurrentMonth("Tel Aviv, Israel","1/27/2024","1/30/2024");
        app.getHelperCar().submit();

        Assert.assertTrue(app.getHelperCar().isListOfCarAppeared());
    }
    @Test
    public void searchCurrentYearSuccess(){
        app.getHelperCar().searchCurrentYear("Tel Aviv, Israel","4/28/2024","7/15/2024");
        app.getHelperCar().submit();

        Assert.assertTrue(app.getHelperCar().isListOfCarAppeared());
    }

    public void searchAnyPeriodSuccess(){
        //app.getHelperCar().searchAnyPeriod("Tel Aviv, Israel","6/11/2024","1/15/2025");
        //app.getHelperCar().submit();

        //Assert.assertTrue(app.getHelperCar().isListOfCarAppeared());
    }
}