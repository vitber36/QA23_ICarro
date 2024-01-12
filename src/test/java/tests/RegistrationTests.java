package tests;

import models.User;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.Random;

public class RegistrationTests extends TestBase{
    @BeforeMethod
    public void preCondition(){
        if(app.getHelperUser().isLogged()){
            app.getHelperUser().logout();
        }
    }
    @Test
    public void registrationSuccess(){
        Random random=new Random();
        int i=random.nextInt(1000);
        System.out.println(System.currentTimeMillis());

        int z=(int)System.currentTimeMillis()/1000%3600;
        User user=new User().withFirstName("Vitaly").withLastName("Dolgopiat").withEmail("vitaly"+z+"@gmail.com").withPassword("1978Vit@lik");
        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        //app.getHelperUser().checkPolicy();
        app.getHelperUser().checkPolicyXY();
        app.getHelperUser().submit();

        Assert.assertEquals(app.getHelperUser().getMessage(),"You are logged in success");
    }

    @Test
    public void registrationUnsuccessEmptyFirstName(){
        Random random=new Random();
        int i= random.nextInt(1000);

        User user=new User().withFirstName("").withLastName("Dolgopiat").withEmail("vitaly"+i+"@gmail.com").withPassword("1978Vit@lik");
        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicy();

        Assert.assertEquals(app.getHelperUser().getMessageEmptyFirstName(),"Name is required");
    }

    @Test
    public void registrationUnsuccessEmptyLastName(){
        Random random=new Random();
        int i= random.nextInt(1000);

        User user=new User().withFirstName("Vitaly").withLastName("").withEmail("vitaly"+i+"@gmail.com").withPassword("1978Vit@lik");
        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicy();

        Assert.assertEquals(app.getHelperUser().getMessageEmptyLastName(),"Last name is required");
    }

    @Test
    public void registrationUnsuccessWrongEmail(){
         User user=new User().withFirstName("Vitaly").withLastName("Dolgopiat").withEmail("vitalygmail.com").withPassword("1978Vit@lik");
        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicy();

        Assert.assertEquals(app.getHelperUser().getMessageEmailWithouShtrudel(),"Wrong email format");
    }

    @Test
    public void registrationUnsuccessWrongPassword(){
        Random random=new Random();
        int i= random.nextInt(1000);

        User user=new User().withFirstName("Vitaly").withLastName("Dolgopiat").withEmail("vitaly"+i+"@gmail.com").withPassword("Vit@lik");
        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicyXY();

        Assert.assertEquals(app.getHelperUser().getMessageWrongPasswordShort(),"Password must contain minimum 8 symbols");
    }

    @AfterMethod
    public void postCondition(){
        app.getHelperUser().clickOkButton();
    }
}
