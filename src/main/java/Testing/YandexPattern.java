package Testing;

import org.testng.annotations.Test;

public class YandexPattern {

    public final static String GooglePath = "https://www.google.com/";
    public final static String YandexMarket ="https://market.yandex.ru/";
    public final static String Find = "//button[@id=\"catalogPopupButton\"]";
    public final static String Search ="//input[@class=\"gLFyf gsfi\"]";
    public final static String Search1 ="//h3[@class=\"LC20lb MBeuO DKV0Md\"]";
    public final static String laptop ="//a[@href=\"/catalog--noutbuki/67959/list?promo-type=market&hid=91013\"]";
    public final static String priceRange = "//div[@data-auto='filter-range-glprice']//following-sibling::input[1]";
    public final static String Brand = "//label[@data-auto=\"filter-list-item-152981\"]";
    public final static String minvalue = "25000";
    public final static String maxvalue ="30000";
    public final static String list ="//div[@data-test-id=\"virtuoso-item-list\"]//a[@title]";
    public final static String validPrice = "//div[@data-test-id='virtuoso-item-list']//article//a[@title]//following::span[@data-autotest-currency][1]";


    @Test
    public void Test() throws InterruptedException {
        Yandex task = new Yandex();
        task.settingDriver(GooglePath);
        task.Laptops(Search,YandexMarket,Search1,Find);
        task.filter(laptop,Brand,priceRange,minvalue,maxvalue);
        task.ValidElements(list,validPrice,minvalue,maxvalue);
        task.quitDriver();
    }
}
