package util;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;

import static java.lang.Thread.sleep;
import org.slf4j.Logger;

public class Crawler {
    Logger logger;
    public Crawler(){
    }
    public ChromeDriver openWebPage(String url) {
        WebDriverManager.chromedriver().setup();
        ChromeDriver driver = new ChromeDriver();
        try {
            driver.get("https://datalab.naver.com/shoppingInsight/sCategory.naver");
        }catch (Exception ex){
            ex.printStackTrace();
            logger.error("fail to open web page [{}] mesage [{}]", url, ex.getMessage());
        }
        return driver;
    }

    public String getXpathText(String xpath, ChromeDriver driver){
        String text = null;

        try{
            WebElement element = driver.findElement(By.xpath(xpath));
            text = element.getText();
        }catch (Exception ex){
            //ex.printStackTrace();
            //logger.error("fail to get text from xpath[{}]", xpath);
            text = null;
        }
        return text;
    }

    public boolean click(ChromeDriver driver, String xpath){
        try{
            driver.findElement(By.xpath(xpath)).click();
        }catch (Exception ex){
            //ex.printStackTrace();
            logger.error("click fail. xpath[{}]", xpath);
            return false;
        }
        return true;
    }

    public void test() throws InterruptedException {

        ChromeDriver driver = openWebPage("https://datalab.naver.com/shoppingInsight/sCategory.naver");

        //search
        driver.findElement(By.xpath("//*[@id=\'19_gender_0']")).click(); //all gender
        driver.findElement(By.xpath("//*[@id=\'18_device_0']")).click(); //all device
        driver.findElement(By.xpath("//*[@id=\"20_age_0\"]")).click(); //all age
        driver.findElement(By.xpath("//*[@id=\"content\"]/div[2]/div/div[1]/div/div/div[2]/div[1]/span/label[3]")).click(); //one year

        String xpath;
        String selectedItemText;

        xpath = "//*[@id=\"content\"]/div[2]/div/div[1]/div/div/div[1]/div/div[1]/span";  //분야 클릭 - 1단계
        selectedItemText = this.getXpathText(xpath, driver);
        System.out.println("분야 1 : " + selectedItemText);
        driver.findElement(By.xpath(xpath)).click();

        xpath = "//*[@id=\"content\"]/div[2]/div/div[1]/div/div/div[1]/div/div[1]/ul/li[8]/a"; //분야 선택 - 1번째
        //*[@id="content"]/div[2]/div/div[1]/div/div/div[1]/div/div[1]/ul/li[8]/a
        //*[@id="content"]/div[2]/div/div[1]/div/div/div[1]/div/div[1]/ul/li[1]/a
        selectedItemText = this.getXpathText(xpath, driver);
        System.out.println("선택 : " + selectedItemText);
        driver.findElement(By.xpath(xpath)).click();



        xpath = "//*[@id=\"content\"]/div[2]/div/div[1]/div/div/div[1]/div/div[2]/span"; //분야 선택 - 2번째
        selectedItemText = this.getXpathText(xpath, driver);
        System.out.println("분야 2 : " + selectedItemText);
        driver.findElement(By.xpath(xpath)).click();

        xpath = "//*[@id=\"content\"]/div[2]/div/div[1]/div/div/div[1]/div/div[2]/ul/li[2]/a"; //분야 선택 - 1번째
        selectedItemText = this.getXpathText(xpath, driver);
        System.out.println("선택 : " + selectedItemText);
        driver.findElement(By.xpath(xpath)).click();


        xpath = "//*[@id=\"content\"]/div[2]/div/div[1]/div/div/div[1]/div/div[3]/span"; //분야 선택 - 3번째
        selectedItemText = this.getXpathText(xpath, driver);
        System.out.println("분야 3 : " + selectedItemText);
        driver.findElement(By.xpath(xpath)).click();

        xpath = "//*[@id=\"content\"]/div[2]/div/div[1]/div/div/div[1]/div/div[3]/ul/li[10]/a"; //분야 선택 - 1번째
        selectedItemText = this.getXpathText(xpath, driver);
        System.out.println("선택 : " + selectedItemText);
        driver.findElement(By.xpath(xpath)).click();



        xpath = "//*[@id=\"content\"]/div[2]/div/div[1]/div/a/span"; //조회하기
        driver.findElement(By.xpath(xpath)).click();
        sleep(1000);


        for(int tabCount = 0 ; tabCount < 25 ; tabCount++){
            for(int rowNum = 1 ; rowNum < 21 ; rowNum++ ){
                String listXpath = "//*[@id=\"content\"]/div[2]/div/div[2]/div[2]/div/div/div[1]/ul/li["+rowNum+"]/a";
                String keyword = this.getXpathText(listXpath, driver);
                keyword = keyword.replace("\n", "\t");
                System.out.println(keyword);
            }
            xpath = "//*[@id=\"content\"]/div[2]/div/div[2]/div[2]/div/div/div[2]/div/a[2]"; //next button
            driver.findElement(By.xpath(xpath)).click();
            //sleep(1000);
        }


        driver.close();
    }
}
