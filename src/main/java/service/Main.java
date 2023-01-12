package service;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import util.Crawler;
import web.NaverDataLab;

import static java.lang.Thread.sleep;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        //Crawler cr = new Crawler();
        //cr.test();
        NaverDataLab nlab = new NaverDataLab();
        nlab.crawlDataLabKeyWord();
    }



}