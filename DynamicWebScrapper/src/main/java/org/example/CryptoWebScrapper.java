package org.example;


import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.util.Scanner;

import data.CryptoData;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;


/**
 * Static data webscrapper
 */
public class CryptoWebScrapper {
    public static double price;
    public static String currency;
    public static String time;
    static Scanner scanner = new Scanner(System.in);

    public static String url = "https://www.plus500.com/pl/Instruments/BTCUSD?id=134463&tags=g_sr%2B16450615089_cpi%2BPolandSearchDSA_cp%2B133461103385_agi%2BGeneral.DSA_agn%2B_ks%2Bdsa-19959388920_tid%2B_mt%2Bc_de%2Bg_nt%2B_ext%2B9067404_loc%2BUURL&%D7%900";


    public static void main(String[] args) {
        //mongod --dbpath C:\Users\pawel\Documents\Projekty_Java\Data
        WebDriver driver = browserSetup();
        date();
        fetchDataCurrency(driver);
        fetchDataPrice(driver);

        System.err.println(date() + " " + fetchDataCurrency(driver) + ": " + fetchDataPrice(driver));
        CryptoData.dbCollecting(price, currency, time);
    }


    public static WebDriver browserSetup() {
        System.out.println("Please select your browser by choosing the number: ");
        System.out.println("1.Edge");
        System.out.println("2.Chrome");
        System.out.println("3.Firefox");
        int select = scanner.nextInt();

        switch (select) {
            case 1:
                WebDriverManager.edgedriver().setup();
                EdgeOptions edgeOptions = new EdgeOptions();
                edgeOptions.addArguments("--headless=new");
                WebDriver edgeDriver = new EdgeDriver(edgeOptions);
                edgeDriver.get(url);
                return edgeDriver;
            case 2:
                WebDriverManager.chromedriver().setup();//Using the method setup from the WebDriver library for Chromium browsers
                ChromeOptions options = new ChromeOptions(); //creating new object options
                options.addArguments("--headless=new");// Without opening the browser
                options.setBinary("C:\\Program Files\\BraveSoftware\\Brave-Browser\\Application\\brave.exe"); //using method for options object
                WebDriver chromeDriver = new ChromeDriver(options); //creating new object driver
                chromeDriver.get(url);
                return chromeDriver;
            case 3:
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.addArguments("--headless=new");
                WebDriver firefoxDriver = new FirefoxDriver(firefoxOptions);
                firefoxDriver.get(url);
                return firefoxDriver;

        }
        return null;
    }

    public static String fetchDataCurrency(WebDriver driver) {
        WebElement element = driver.findElement(By.cssSelector("span.inst-name")); //creating new variable element1
        currency = element.getText();
        driver.quit();
        return currency;
    }

    public static double fetchDataPrice(WebDriver driver) {
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

    public static String getCurrency() {
        return currency;
    }

    public static double getPrice() {
        return price;
    }

    public static String getDate() {
        return time;
    }
}
