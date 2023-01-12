package web;

import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import util.Crawler;
import util.KeyWordTree;

import java.util.ArrayList;
import java.util.HashMap;

public class NaverDataLab {
    public static String XPATH_SET_DEVICE_ALL = "//*[@id=\'18_device_0']";
    public static String XPATH_SET_AGE_ALL = "//*[@id=\"20_age_0\"]";
    public static String XPATH_SET_GENDER_ALL = "//*[@id=\'19_gender_0']";
    public static String XPATH_SET_TIME_RANGE_YEAR = "//*[@id=\"content\"]/div[2]/div/div[1]/div/div/div[2]/div[1]/span/label[3]";
    public static String XPATH_SEARCH_ICON = "//*[@id=\"content\"]/div[2]/div/div[1]/div/a/span";

    public static String XPATH_KEYWORD_LIST_PRE = "//*[@id=\"content\"]/div[2]/div/div[2]/div[2]/div/div/div[1]/ul/li[";
    public static String XPATH_KEYWORD_LIST_POST = "]/a";
    public static String XPATH_KEYWORD_NEXT_BUTTON = "//*[@id=\"content\"]/div[2]/div/div[2]/div[2]/div/div/div[2]/div/a[2]";
    public static String XPATH_CATEGORY_BUTTON_PRE = "//*[@id=\"content\"]/div[2]/div/div[1]/div/div/div[1]/div/div[";
    public static String XPATH_CATEGORY_BUTTON_POST = "]/span";
    public static String XPATH_CATEGORY_LIST_PRE = "]/ul/li[";
    public static String XPATH_CATEGORY_LIST_POST = "]/a";

    Logger logger;
    Crawler crawler;
    public NaverDataLab(){
        crawler = new Crawler();
    }
    public void crawlDataLabKeyWord(){
        ChromeDriver driver = crawler.openWebPage("https://datalab.naver.com/shoppingInsight/sCategory.naver");
        this.defaultSearchSetup(driver);
        this.printCategoryNameList(driver);
    }

    private void defaultSearchSetup(ChromeDriver driver){
        crawler.click(driver, XPATH_SET_DEVICE_ALL);
        crawler.click(driver, XPATH_SET_AGE_ALL);
        crawler.click(driver, XPATH_SET_GENDER_ALL);
        crawler.click(driver, XPATH_SET_TIME_RANGE_YEAR);
    }

//    private KeyWordTree makeCategoryTree(){
//        KeyWordTree root = new KeyWordTree("root");
//
//    }
//
//

    private void printCategoryNameList(ChromeDriver driver){
        ArrayList<String> rootList = getCategoryNameList(driver, 1);
        for(String name : rootList){
            System.out.println(name);
        }

    }


    private ArrayList<String> getCategoryNameList(ChromeDriver driver, int categoryIndex){
        ArrayList<String> nameList = new ArrayList<>();
        String Button = XPATH_CATEGORY_BUTTON_PRE + categoryIndex + XPATH_CATEGORY_BUTTON_POST;
        crawler.click(driver, Button);
        String name = "";
        int listIndex = 1;
        String listXpath = XPATH_CATEGORY_BUTTON_PRE + categoryIndex + XPATH_CATEGORY_LIST_PRE + listIndex + XPATH_KEYWORD_LIST_POST;
        name = crawler.getXpathText(listXpath, driver);
        while(name != null){
            nameList.add(name);
            listIndex++;
            listXpath = XPATH_CATEGORY_BUTTON_PRE + categoryIndex + XPATH_CATEGORY_LIST_PRE + listIndex + XPATH_KEYWORD_LIST_POST;
            name = crawler.getXpathText(listXpath, driver);
        }

        return nameList;
    }


    private HashMap<Integer, String> getTop500KeyWord(ChromeDriver driver){
        HashMap<Integer, String> keyWordMap = new HashMap<>();
        for(int tabCount = 0 ; tabCount < 25 ; tabCount++) {
            for (int rowNum = 1; rowNum < 21; rowNum++) {
                String listXpath = XPATH_KEYWORD_LIST_PRE+rowNum+XPATH_KEYWORD_LIST_POST;
                String keyWord = crawler.getXpathText(listXpath, driver);
                String[] splitKeyWord = keyWord.split("\n");
                Integer rank = new Integer(splitKeyWord[0]);
                keyWordMap.put(rank, splitKeyWord[1]);
            }
        }
        return keyWordMap;
    }




}
