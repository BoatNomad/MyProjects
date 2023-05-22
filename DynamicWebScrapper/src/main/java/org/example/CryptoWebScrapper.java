package org.example;


import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

import data.CryptoData;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


/**
 * Dynamic data webscrapper
 */
public class CryptoWebScrapper {
    public static double price;
    public static String currency;
    public static String time;


    public static void main(String[] args) {
        date();
        fetchDataCurrency();
        fetchDataPrice();

        System.err.println(date() + " " + fetchDataCurrency() + ": " + fetchDataPrice());
        CryptoData.dbCollecting(price, currency, time);
    }

    public static String fetchDataCurrency() {
        WebDriverManager.chromedriver().setup();//Using the method setup from the WebDriver library

        ChromeOptions options = new ChromeOptions(); //creating new object options
        options.addArguments("--headless=new");// Without opening the browser
        options.setBinary("C:\\Program Files\\BraveSoftware\\Brave-Browser\\Application\\brave.exe"); //using method for options object

        WebDriver driver = new ChromeDriver(options); //creating new object driver
        driver.get("https://www.plus500.com/pl/Instruments/BTCUSD?id=134463&tags=g_sr%2B16450615089_cpi%2BPolandSearchDSA_cp%2B133461103385_agi%2BGeneral.DSA_agn%2B_ks%2Bdsa-19959388920_tid%2B_mt%2Bc_de%2Bg_nt%2B_ext%2B9067404_loc%2BUURL&%D7%900");

        WebElement element = driver.findElement(By.cssSelector("span.inst-name")); //creating new variable element1

        currency = element.getText();

        driver.quit();
        return currency;
    }

    public static double fetchDataPrice() {
        WebDriverManager.chromedriver().setup();//Using the method setup from the WebDriver library

        ChromeOptions options = new ChromeOptions(); //creating new object options
        options.addArguments("--headless=new");// Without opening the browser
        options.setBinary("C:\\Program Files\\BraveSoftware\\Brave-Browser\\Application\\brave.exe"); //using method for options object

        WebDriver driver = new ChromeDriver(options); //creating new object driver
        driver.get("https://www.plus500.com/pl/Instruments/BTCUSD?id=134463&tags=g_sr%2B16450615089_cpi%2BPolandSearchDSA_cp%2B133461103385_agi%2BGeneral.DSA_agn%2B_ks%2Bdsa-19959388920_tid%2B_mt%2Bc_de%2Bg_nt%2B_ext%2B9067404_loc%2BUURL&%D7%900");

        WebElement element = driver.findElement(By.cssSelector("span.rate")); //creating new variable element

        price = Double.parseDouble(element.getText());

        driver.quit();
        return price;
    }

    public static String date() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
        String date = now.format(dtf);
        time = date;
        return time;

    }

}
