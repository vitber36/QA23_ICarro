package tests;

import models.*;
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

    @Test
    public void addNewCarSuccess(){
        int i=new Random().nextInt(1000)+1000;
        Car car=Car.builder()
                .location("Tel Aviv, Israel")
                .manufacture("Mazda")
                .model("M3")
                .year("2012")
                .fuel("gas")
                .seats(4)
                .carClass("C")
                .carRegNumber("234-232"+i)
                .price(50)
                .about("Very nice car")
                .build();
        app.getHelperCar().openCarForm();
        app.getHelperCar().fillCarForm(car);
        //app.getHelperCar().submitCarForm();

    }
}
