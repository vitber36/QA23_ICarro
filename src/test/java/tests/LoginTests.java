package tests;

import manager.DataProviderUser;
import models.Car;
import models.User;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class
LoginTests extends TestBase{

    @BeforeMethod
    public void preCondition(){
        if(app.getHelperUser().isLogged()){
            app.getHelperUser().logout();
        }
    }

    @Test
    public void loginSuccess1(){
        logger.info("Start test with name 'login success1'");
        logger.info("Test data--> email:'vitber06@mail.ru' & password: '1978Vit@lik'");

        User user=new User().withEmail("vitber06@mail.ru").withPassword("1978Vit@lik");
        //user.setEmail("vitber06@mail.ru");
        //user.setPassword("1978Vit@lik");
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm(user);
        app.getHelperUser().submit();

        Assert.assertTrue(app.getHelperUser().isLogged());

        logger.info("Assert check that button 'logout' is present");
    }


    @Test(dataProvider="loginDate",dataProviderClass = DataProviderUser.class)
    public void loginSuccess(String email,String password) {
        logger.info("Start test with name 'login success'");
        logger.info("Test data--> email: "+email+" & password: "+password);

        User user = new User().withEmail("vitber06@mail.ru").withPassword("1978Vit@lik");
//        user.setEmail("vitber06@mail.ru");
//        user.setPassword("1978Vit@lik");
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm(email,password);
        app.getHelperUser().submit();
        //Assert if element with text "Logged in success" is present
        Assert.assertEquals(app.getHelperUser().getMessage(), "Logged in success");
        //app.getHelperUser().clickOKButton();

        logger.info("Assert message 'Logged in success' is present");
    }

    @Test(dataProvider="loginDateFile",dataProviderClass = DataProviderUser.class)
    public void loginSuccessFile(User user) {
        logger.info("Start test with name 'login success'");
        logger.info("Test data--> "+user.toString());

        //User user = new User().withEmail("vitber06@mail.ru").withPassword("1978Vit@lik");
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm(user);
        app.getHelperUser().submit();

        Assert.assertEquals(app.getHelperUser().getMessage(), "Logged in success");

        logger.info("Assert message 'Logged in success' is present");
    }



    @Test(dataProvider = "loginModels",dataProviderClass = DataProviderUser.class)
    public void loginSuccessModel(User user) {
        logger.info("Start test with name 'login success model'");
        logger.info("Test data--> "+user.toString());

        //User user = new User().withEmail("vitber06@mail.ru").withPassword("1978Vit@lik");
//        user.setEmail("vitber06@mail.ru");
//        user.setPassword("1978Vit@lik");
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm(user);
        app.getHelperUser().submit();
        //Assert if element with text "Logged in success" is present
        Assert.assertEquals(app.getHelperUser().getMessage(), "Logged in success");
        //app.getHelperUser().clickOKButton();

        logger.info("Assert message 'Logged in success' is present");
    }

   @Test
    public void loginWrongEmailWithoutShtrudel(){
       logger.info("Start test with name 'login wrong email without shtrulel'");
       logger.info("Test data--> email:'vitber06mail.ru' & password: '1978Vit@lik'");

        User user=new User().withEmail("vitber06mail.ru").withPassword("1978Vit@lik");
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm(user);
        app.getHelperUser().submit();
        Assert.assertEquals(app.getHelperUser().getErrorText(),"It'snot look like email");
        Assert.assertTrue(app.getHelperUser().isYallaButtonNotActive());

       logger.info("Assert message 'It's not look like email' is present");
    }

    @Test

    public void loginWrongEmailWithoutPoint(){
        User user=new User().withEmail("vitber06@mailru").withPassword("1978Vit@lik");
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm(user);
        app.getHelperUser().submit();
        Assert.assertFalse(app.getHelperUser().isLogged());

    }

    @Test

    public void loginWrongPasswordShort(){
        logger.info("Start test with name 'login wrong password short'");
        logger.info("Test data--> email:'vitber06@mail.ru' & password: '1978Vit@'");

        User user=new User().withEmail("vitber06@mail.ru").withPassword("8Vit@");
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm(user);
        app.getHelperUser().submit();
        Assert.assertEquals(app.getHelperUser().getMessage(),"\"Login or Password incorrect\"");

        logger.info("Assert message 'Login or Password incorrect' is present");
    }

    @Test

    public void loginWrongPasswordWithoutDigits(){
        User user=new User().withEmail("vitber06@mail.ru").withPassword("qqqqVit@lik");
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm(user);
        app.getHelperUser().submit();
        Assert.assertEquals(app.getHelperUser().getMessage(),"\"Login or Password incorrect\"");

    }

    @Test

    public void loginWrongPasswordWithoutSpecialSymbol(){
        User user=new User().withEmail("vitber06@mail.ru").withPassword("1978Vitalik");
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm(user);
        app.getHelperUser().submit();
        Assert.assertFalse(app.getHelperUser().isLogged());

    }
    @Test
    public void loginUnregisteredUser(){
        logger.info("Start test with name 'login unregistered user'");
        logger.info("Test data--> email:'vvitber06@mail.ru' & password: '11978Vit@lik'");

        User user=new User().withEmail("vvitber06@mail.ru").withPassword("11978Vitalik");
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm(user);
        app.getHelperUser().submit();
        Assert.assertEquals(app.getHelperUser().getMessage(),"\"Login or Password incorrect\"");

        logger.info("Assert message 'Login or Password incorrect' is present");
    }
    @AfterMethod
    public void postCondition(){
        app.getHelperUser().clickOkButton();
    }
}
