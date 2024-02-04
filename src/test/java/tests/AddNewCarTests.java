package tests;


import manager.DataProviderCar;
import models.*;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Random;

public class AddNewCarTests extends TestBase{
    @BeforeClass
    public void precondition(){
        if(!app.getHelperUser().isLogged()){
            app.getHelperUser().login(new User().withEmail("vitber06@mail.ru").withPassword("1978Vit@lik"));
        }
    }

    @Test(dataProvider = "carSuccess",dataProviderClass = DataProviderCar.class)
    public void addNewCarSuccessAll(Car car){
        logger.info("test add new car success all started");
        logger.info("test data: "+car.toString());

          int i=new Random().nextInt(1000)+1000;
//        Car car=Car.builder()
//                .location("Tel Aviv, Israel")
//                .manufacture("Mazda")
//                .model("M3")
//                .year("2012")
//                .fuel("Gas")
//                .seats(4)
//                .carClass("C")
//                .carRegNumber("234-232"+i)
//                .price(50)
//                .about("Very nice car")
//                .build();
        app.getHelperCar().openCarForm();
        app.getHelperCar().fillCarForm(car);
        app.getHelperCar().getScreen("src/test/screenshots/screen"+i+".png");
        app.getHelperCar().submit();

        Assert.assertTrue(app.getHelperCar().getMessage().contains("added successful"));
        Assert.assertEquals(app.getHelperCar().getMessage(),
                car.getManufacture()+" "+car.getModel()+" added successful");

        logger.info("assert check message 'added successful'");

    }

    @Test(dataProvider = "carSuccessFile",dataProviderClass = DataProviderCar.class)
    public void addNewCarSuccessAllFile(Car car){
        logger.info("test add new car success all started");
        logger.info("test data: "+car.toString());

        int i=new Random().nextInt(1000)+1000;
        app.getHelperCar().openCarForm();
        app.getHelperCar().fillCarForm(car);
        app.getHelperCar().getScreen("src/test/screenshots/screen"+i+".png");
        app.getHelperCar().submit();

        Assert.assertTrue(app.getHelperCar().getMessage().contains("added successful"));
        Assert.assertEquals(app.getHelperCar().getMessage(),
                car.getManufacture()+" "+car.getModel()+" added successful");

        logger.info("assert check message 'added successful'");

    }

    @Test
    public void addNewCarSuccess(){
        logger.info("test add new car success started");
        logger.info("test data: location-'Tel Aviv, Israel', manufacture-'Kia', model-'Sportage', year-'2020'," +
                " fuel-'Gas',seats-'5', car class-'C', car number-'432-232 +i',price-'50'");

        int i=new Random().nextInt(1000)+1000;
        Car car=Car.builder()
                .location("Tel Aviv, Israel")
                .manufacture("Kia")
                .model("Sportage")
                .year("2020")
                .fuel("Gas")
                .seats(5)
                .carClass("C")
                .carRegNumber("432-232"+i)
                .price(50)
                .build();
        app.getHelperCar().openCarForm();
        app.getHelperCar().fillCarForm(car);
        app.getHelperCar().attachPhoto("D:\\QA_23\\QA23_ICarro\\02-bugatti-cd-nardo-testing.jpg");
        app.getHelperCar().submit();

        Assert.assertTrue(app.getHelperCar().getMessage().contains("added successful"));
        Assert.assertEquals(app.getHelperCar().getMessage(),
                car.getManufacture()+" "+car.getModel()+" added successful");

        logger.info("assert check message 'added successful'");
    }

    @Test
    public void addNewCarWrongManufacture(){
        logger.info("test add new car wrong manufacture (empty) started");
        logger.info("test data: location-'Tel Aviv, Israel', manufacture-'', model-'Sportage', year-'2020'," +
                " fuel-'Gas',seats-'5', car class-'C', car number-'432-232 +i',price-'50'");

        Car car=Car.builder()
                .location("Tel Aviv, Israel")
                .manufacture("")
                .model("Sportage")
                .year("2020")
                .fuel("Gas")
                .seats(5)
                .carClass("C")
                .carRegNumber("432-232")
                .price(50)
                .build();
        app.getHelperCar().openCarForm();
        app.getHelperCar().fillCarForm(car);
        app.getHelperCar().submit();

        Assert.assertTrue(app.getHelperCar().isElementPresent(By.xpath("//*[contains(text(),'Make is required')]")));

        logger.info("assert check message 'Make is required'");
    }

    @Test
    public void addNewCarWrongCarModel(){
        logger.info("test add new car wrong car model (empty) started");
        logger.info("test data: location-'Tel Aviv, Israel', manufacture-'Kia', model-'', year-'2020'," +
                " fuel-'Gas',seats-'5', car class-'C', car number-'432-232 +i',price-'50'");

        Car car=Car.builder()
                .location("Tel Aviv, Israel")
                .manufacture("Kia")
                .model("")
                .year("2020")
                .fuel("Gas")
                .seats(5)
                .carClass("C")
                .carRegNumber("432-232")
                .price(50)
                .build();
        app.getHelperCar().openCarForm();
        app.getHelperCar().fillCarForm(car);
        app.getHelperCar().submit();

        Assert.assertTrue(app.getHelperCar().isElementPresent(By.xpath("//*[contains(text(),'Model is required')]")));

        logger.info("assert check message 'Model is required'");
    }

    @Test
    public void addNewCarWrongYear(){
        logger.info("test add new car wrong year (empty) started");
        logger.info("test data: location-'Tel Aviv, Israel', manufacture-'Kia', model-'Sportage', year-''," +
                " fuel-'Gas',seats-'5', car class-'C', car number-'432-232 +i',price-'50'");

        Car car=Car.builder()
                .location("Tel Aviv, Israel")
                .manufacture("Kia")
                .model("Sportage")
                .year("")
                .fuel("Gas")
                .seats(5)
                .carClass("C")
                .carRegNumber("432-232")
                .price(50)
                .build();
        app.getHelperCar().openCarForm();
        app.getHelperCar().fillCarForm(car);
        app.getHelperCar().submit();

        Assert.assertTrue(app.getHelperCar().isElementPresent(By.xpath("//*[contains(text(),'Year required')]")));

        logger.info("assert check message 'Year is required'");
    }
    @Test
    public void addNewCarWrongLocation(){

        Car car=Car.builder()
                .location("")
                .manufacture("Kia")
                .model("Sportage")
                .year("2020")
                .fuel("Gas")
                .seats(5)
                .carClass("C")
                .carRegNumber("432-232")
                .price(50)
                .build();
        logger.info("test add new car wrong location (empty) started");
        logger.info("test data:"+car.toString());

        app.getHelperCar().openCarForm();
        app.getHelperCar().fillCarEmptyLocations(car);
        app.getHelperCar().submit();

        Assert.assertEquals(app.getHelperUser().getErrorText(), "Wrong address");
        Assert.assertTrue(app.getHelperUser().isYallaButtonNotActive());

        logger.info("assert check message 'Wrong address'");
    }

    @Test
    public void addNewCarEmptyFuel() {

        Car car = Car.builder()
                .location("Tel Aviv, Israel")
                .manufacture("KIA")
                .model("Sportage")
                .year("2020")
                .fuel("")
                .seats(4)
                .carClass("C")
                .carRegNumber("986-326-")
                .price(50)
                .build();
        logger.info("Test start with test data --->" + car.toString());
        app.getHelperCar().openCarForm();
        app.getHelperCar().fillCarFormEmptyFuel(car);
        //app.getHelperCar().attachPhoto("D:\\QA_23\\QA23_IlCarro\\02-bugatti-cd-nardo-testing.jpg");
        app.getHelperCar().submit();
        Assert.assertEquals(app.getHelperUser().getErrorText(), "Fuel is required");
        Assert.assertTrue(app.getHelperUser().isYallaButtonNotActive());


    }

    @AfterMethod
    public void postCondition() {
        if(app.getHelperCar().isButtonReturnToHomePresent()) {
            app.getHelperCar().returnToHome();
        }else
            app.getHelperCar().navigateByLogo();
    }

}
