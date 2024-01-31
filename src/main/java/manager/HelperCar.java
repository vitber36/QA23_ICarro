package manager;

import models.Car;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class HelperCar extends HelperBase {
    public HelperCar(WebDriver wd) {
        super(wd);
    }

    public void openCarForm() {
        pause(500);
        click(By.xpath("//a[text()=' Let the car work ']"));
    }

    public void fillCarForm(Car car) {
        typeLocation(car.getLocation());
        type(By.id("make"), car.getManufacture());
        type(By.id("model"), car.getModel());
        type(By.id("year"), car.getYear());
        select(By.id("fuel"), car.getFuel());
        type(By.id("seats"), String.valueOf(car.getSeats()));
        type(By.id("class"), car.getCarClass());
        type(By.id("serialNumber"), car.getCarRegNumber());
        type(By.id("price"), car.getPrice() + "");
        type(By.id("about"), car.getAbout());
    }

    private void select(By locator, String option) {
        Select select = new Select(wd.findElement(locator));
        select.selectByValue(option);
    }

    private void typeLocation(String location) {
        type(By.id("pickUpPlace"), location);
        click(By.cssSelector("div.pac-item"));
    }

    public void returnToHome() {
        click(By.xpath("//button[text()='Search cars']"));
    }

    public void attachPhoto(String link) {
        wd.findElement(By.id("photos")).sendKeys(link);
    }

    public void searchCurrentMonth(String city, String dateFrom, String dateTo) {
        typeCity(city);
        clearTextBox(By.id("dates"));
        click(By.id("dates"));

        String[] from = dateFrom.split("/");
        String locatorFrom = "//div[text()=' " + from[1] + " ']";
        click(By.xpath(locatorFrom));

        String[] to = dateTo.split("/");
        String locatorTo = "//div[text()=' " + to[1] + " ']";
        click(By.xpath(locatorTo));
    }

    private void typeCity(String city) {
        clearTextBox(By.id("city"));
        type(By.id("city"), city);
        click(By.cssSelector("div.pac-item"));
    }

    public boolean isListOfCarAppeared() {
        return isElementPresent(By.cssSelector("a.car-container"));
    }

    public void searchCurrentYear(String city, String dateFrom, String dateTo) {
        typeCity(city);
        clearTextBox(By.id("dates"));
        click(By.id("dates"));

        LocalDate now = LocalDate.now();
        int month = now.getMonthValue();
        //int year=now.getYear();
        //int day=now.getDayOfMonth();

        LocalDate from = LocalDate.parse(dateFrom, DateTimeFormatter.ofPattern("M/d/yyyy"));
        LocalDate to = LocalDate.parse(dateTo, DateTimeFormatter.ofPattern("M/d/yyyy"));

        int diffMonthFrom = from.getMonthValue() - month;
        if (diffMonthFrom > 0) {
            clickNextMonthButtonFrom(diffMonthFrom);
        }
        click(By.xpath("//div[text()=' " + from.getDayOfMonth() + " ']"));

        int diffMonthTo = to.getMonthValue() - from.getMonthValue();
        if (diffMonthTo > 0) {
            clickNextMonthButtonTo(diffMonthTo);
        }
        //click(By.xpath("//div[text()=' " + to.getDayOfMonth() + " ']"));
    String locator=String.format("//div[text()=' %s ']",to.getDayOfMonth());
        click(By.xpath(locator));
    }

    private void clickNextMonthButtonFrom(int diffMonthFrom) {
        for (int i = 0; i < diffMonthFrom; i++) {
            click(By.cssSelector("button[aria-label='Next month']"));
        }
    }

    private void clickNextMonthButtonTo(int diffMonthTo) {
        for (int i = 0; i < diffMonthTo; i++) {
            click(By.cssSelector("button[aria-label='Next month']"));
        }
    }

    public void searchAnyPeriod(String city, String dateFrom, String dateTo) {
        typeCity(city);
        clearTextBox(By.id("dates"));
        click(By.id("dates"));

        LocalDate now = LocalDate.now();
        LocalDate from = LocalDate.parse(dateFrom, DateTimeFormatter.ofPattern("M/d/yyyy"));
        LocalDate to = LocalDate.parse(dateTo, DateTimeFormatter.ofPattern("M/d/yyyy"));

        int diffYear;
        int diffMonth;

        diffYear = from.getYear() - now.getYear();
        if (diffYear == 0) {
            diffMonth = from.getMonthValue() - now.getMonthValue();
        } else {
            diffMonth = 12 - now.getMonthValue() + from.getMonthValue();
        }
        clickNextMonthButtonFrom(diffMonth);
        String locator=String.format("//div[text()=' %s ']",from.getDayOfMonth());
        click(By.xpath(locator));

        //***to****
        diffYear=to.getYear()-from.getYear();
        if(diffYear==0){
            diffMonth=to.getMonthValue()-from.getMonthValue();
        }else{
            diffMonth=12-from.getDayOfMonth()+to.getMonthValue();
        }
        clickNextMonthButtonTo(diffMonth);
        locator=String.format("//div[text()=' %s ']",to.getDayOfMonth());
        click(By.xpath(locator));
    }

    public void navigateByLogo() {
        click(By.cssSelector("a.logo"));
    }

    public void wrongDate(String city, String dateFrom, String dateTo) {
        typeCity(city);
        click(By.id("dates"));
        click(By.xpath("/html/body/div[2]/div[1]"));
        type(By.id("dates"),dateFrom+" - "+dateTo);
        click(By.xpath("/html/body/div[2]/div[1]"));
    }

    public String wrongDataMessage() {
        return wd.findElement(By.xpath("/html/body/app-root/app-navigator/app-search/div/div/form/div[2]/div/div[1]")).getText();
    }
}
