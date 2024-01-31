package manager;

import models.User;
import org.testng.annotations.DataProvider;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DataProviderUser {

    @DataProvider
    public Iterator<Object[]>example(){
        List<Object[]>list=new ArrayList<>();
        return list.iterator();
    }

    @DataProvider
    public Iterator<Object[]> loginDate(){
        List<Object[]> list=new ArrayList<>();
        list.add(new Object[]{"vitber06@mail.ru","1978Vit@lik"});
        list.add(new Object[]{"vitber36@gmail.com","1978Vit@lik"});
        return list.iterator();
    }

    @DataProvider
    public Iterator<Object[]>loginModels(){
        List<Object[]>list=new ArrayList<>();
        list.add(new Object[]{new User().withEmail("vitber06@mail.ru").withPassword("1978Vit@lik")});
        list.add(new Object[]{new User().withEmail("vitber36@gmail.com").withPassword("1978Vit@lik")});
        return list.iterator();
    }
}
