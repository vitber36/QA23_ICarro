package manager;

import models.Car;
import org.testng.annotations.DataProvider;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DataProviderCar {
    @DataProvider
    public Iterator<Object[]> example(){
        List<Object[]> list=new ArrayList<>();
        return list.iterator();
    }

    @DataProvider
    public Iterator<Object[]>carSuccess(){
        List<Object[]>list=new ArrayList<>();
        list.add(new Object[]{
                Car.builder()
                .location("Tel Aviv, Israel")
                .manufacture("Mazda")
                .model("M3")
                .year("2012")
                .fuel("Gas")
                .seats(4)
                .carClass("C")
                .carRegNumber("234-001")
                .price(50)
                .about("Very nice car")
                .build()
        });
        list.add(new Object[]{
                Car.builder()
                .location("Tel Aviv, Israel")
                .manufacture("Maz")
                .model("SD")
                .year("2022")
                .fuel("Gas")
                .seats(5)
                .carClass("D")
                .carRegNumber("001-232")
                .price(90)
                .build()
        });
        return list.iterator();
    }


}
