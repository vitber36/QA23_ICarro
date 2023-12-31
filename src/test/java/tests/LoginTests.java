package tests;

import models.User;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

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
        User user=new User().withEmail("vitber06@mail.ru").withPassword("1978Vit@lik");
        //user.setEmail("vitber06@mail.ru");
        //user.setPassword("1978Vit@lik");

        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm(user);
        app.getHelperUser().submit();


        Assert.assertTrue(app.getHelperUser().isLogged());

    }


    @Test
    public void loginSuccess() {
        User user = new User().withEmail("vitber06@mail.ru").withPassword("1978Vit@lik");
//        user.setEmail("vitber06@mail.ru");
//        user.setPassword("1978Vit@lik");
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm(user);
        app.getHelperUser().submit();
        //Assert if element with text "Logged in success" is present
        Assert.assertEquals(app.getHelperUser().getMessage(), "Logged in success");
        //app.getHelperUser().clickOKButton();


    }

    @Test
    public void loginSuccessModel() {
        User user = new User().withEmail("vitber06@mail.ru").withPassword("1978Vit@lik");
//        user.setEmail("vitber06@mail.ru");
//        user.setPassword("1978Vit@lik");
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm(user);
        app.getHelperUser().submit();
        //Assert if element with text "Logged in success" is present
        Assert.assertEquals(app.getHelperUser().getMessage(), "Logged in success");
        //app.getHelperUser().clickOKButton();


    }

   @Test
    public void loginWrongEmailWithoutShtrudel(){
        User user=new User().withEmail("vitber06mail.ru").withPassword("1978Vit@lik");
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm(user);
        app.getHelperUser().submit();
        Assert.assertEquals(app.getHelperUser().getErrorText(),"It'snot look like email");
        Assert.assertTrue(app.getHelperUser().isYallaButtonNotActive());
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
        User user=new User().withEmail("vitber06@mail.ru").withPassword("8Vit@");
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm(user);
        app.getHelperUser().submit();
        Assert.assertEquals(app.getHelperUser().getMessage(),"\"Login or Password incorrect\"");

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
        User user=new User().withEmail("vvitber06@mail.ru").withPassword("11978Vitalik");
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm(user);
        app.getHelperUser().submit();
        Assert.assertEquals(app.getHelperUser().getMessage(),"\"Login or Password incorrect\"");


    }
    @AfterMethod
    public void postCondition(){
        app.getHelperUser().clickOkButton();
    }
}
