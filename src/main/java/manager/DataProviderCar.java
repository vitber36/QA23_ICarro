package manager;

import models.Car;
import org.testng.annotations.DataProvider;

import java.io.*;
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
                .carRegNumber("234-003")
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
                .carRegNumber("003-232")
                .price(90)
                .build()
        });
        return list.iterator();
    }

    @DataProvider
    public Iterator<Object[]> carSuccessFile() throws IOException {
        List<Object[]> list=new ArrayList<>();
        BufferedReader reader=new BufferedReader(new FileReader(new File("src/test/resources/carData")));
        String line=reader.readLine();
        while (line!=null){
            String[]all=line.split(",");
            list.add(new Object[]{
                    Car.builder()
                            .location(all[0])
                            .manufacture(all[1])
                            .model(all[2])
                            .year(all[3])
                            .fuel(all[4])
                            .seats(Integer.parseInt(all[5]))
                            .carClass(all[6])
                            .carRegNumber(all[7])
                            .price(Double.parseDouble(all[8]))
                            .about(all[9])
                            .build()
            });
            line=reader.readLine();

        }
        return list.iterator();
    }
}
