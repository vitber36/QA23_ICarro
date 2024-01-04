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
        app.getHelperUser().submitLogin();


        Assert.assertTrue(app.getHelperUser().isLogged());

    }


    @Test
    public void loginSuccess() {
        User user = new User().withEmail("vitber06@mail.ru").withPassword("1978Vit@lik");
//        user.setEmail("margo@gmail.com");
//        user.setPassword("Mmar123456$");
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm(user);
        app.getHelperUser().submitLogin();
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
        app.getHelperUser().submitLogin();
        //Assert if element with text "Logged in success" is present
        Assert.assertEquals(app.getHelperUser().getMessage(), "Logged in success");
        //app.getHelperUser().clickOKButton();


    }

    @AfterMethod
    public void postCondition(){
        app.getHelperUser().clickOkButton();
    }
}
