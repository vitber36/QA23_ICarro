package tests;

import models.User;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.Random;

public class RegistrationTests extends TestBase{
    @BeforeMethod(alwaysRun = true)
    public void preCondition(){
        if(app.getHelperUser().isLogged()){
            app.getHelperUser().logout();
        }
    }
    @Test(groups = {"smoke","regress","retest"})
    public void registrationSuccess(){
        logger.info("Start success registration test");
        logger.info("test data---> first name-'Vitaly', last name-'Dolgopiat', email-'vitaly+z+@gmail.com', password-'1978Vit@lik'");

        Random random=new Random();
        int i=random.nextInt(1000);
        System.out.println(System.currentTimeMillis());

        //int z=(int)System.currentTimeMillis()/1000%3600;
        User user=new User().withFirstName("Vitaly").withLastName("Dolgopiat").withEmail("vitaly"+i+"@gmail.com").withPassword("1978Vit@lik");
        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        //app.getHelperUser().checkPolicy();
        app.getHelperUser().checkPolicyXY();
        app.getHelperUser().submit();

        Assert.assertEquals(app.getHelperUser().getMessage(),"You are logged in success");

        logger.info("assert get message 'You are logged in success'");
    }

    @Test
    public void registrationUnsuccessEmptyFirstName(){
        logger.info("Start unsuccess registration test with empty first name");
        logger.info("test data---> first name-'', last name-'Dolgopiat', email-'vitaly+i+@gmail.com', password-'1978Vit@lik'");

        Random random=new Random();
        int i= random.nextInt(1000);

        User user=new User().withFirstName("").withLastName("Dolgopiat").withEmail("vitaly"+i+"@gmail.com").withPassword("1978Vit@lik");
        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicy();
        app.getHelperUser().submit();

        //Assert.assertEquals(app.getHelperUser().getMessageEmptyFirstName(),"Name is required");
        Assert.assertEquals(app.getHelperUser().getErrorText(),"Name is required");
        Assert.assertTrue(app.getHelperUser().isYallaButtonNotActive());

        logger.info("assert get message 'Name is required'");
    }

    @Test
    public void registrationUnsuccessEmptyLastName(){
        logger.info("Start unsuccess registration test with empty last name");
        logger.info("test data---> first name-'Vitaly', last name-'', email-'vitaly+i+@gmail.com', password-'1978Vit@lik'");

        Random random=new Random();
        int i= random.nextInt(1000);

        User user=new User().withFirstName("Vitaly").withLastName("").withEmail("vitaly"+i+"@gmail.com").withPassword("1978Vit@lik");
        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicy();
        app.getHelperUser().submit();

        Assert.assertEquals(app.getHelperUser().getMessageEmptyLastName(),"Last name is required");

        logger.info("assert get message 'Last name is required'");
    }

    @Test
    public void registrationUnsuccessWrongEmail(){
        logger.info("Start unsuccess registration test with wrong email");
        logger.info("test data---> first name-'Vitaly', last name-'Dolgopiat', email-'vitalygmail.com', password-'1978Vit@lik'");

         User user=new User().withFirstName("Vitaly").withLastName("Dolgopiat").withEmail("vitalygmail.com").withPassword("1978Vit@lik");
        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicy();
        app.getHelperUser().submit();

        Assert.assertEquals(app.getHelperUser().getMessageEmailWithouShtrudel(),"Wrong email format");

        logger.info("assert get message 'Wrong email format'");
    }

    @Test
    public void registrationUnsuccessWrongPassword(){
        logger.info("Start unsuccess registration test with wrong password");
        logger.info("test data---> first name-'Vitaly', last name-'Dolgopiat', email-'vitaly+i+@gmail.com', password-'Vit@lik'");

        Random random=new Random();
        int i= random.nextInt(1000);

        User user=new User().withFirstName("Vitaly").withLastName("Dolgopiat").withEmail("vitaly"+i+"@gmail.com").withPassword("Vit@lik");
        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicyXY();
        app.getHelperUser().submit();

        Assert.assertEquals(app.getHelperUser().getMessageWrongPasswordShort(),"Password must contain minimum 8 symbols");

        logger.info("assert get message contains 'Password must contain minimum 8 symbols'");
    }

    @AfterMethod(alwaysRun = true)
    public void postCondition(){
        app.getHelperUser().clickOkButton();
    }
}
