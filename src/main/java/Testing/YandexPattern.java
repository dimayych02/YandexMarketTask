package Testing;

import org.testng.IRetryAnalyzer;

import org.testng.ITestResult;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;



public class YandexPattern implements IRetryAnalyzer {

    Yandex task = new Yandex();
    private int count =0;
    private int maxcount =4;

    public final static String GooglePath = "https://www.google.com/";
    public final static String YandexMarket ="https://market.yandex.ru/";
    public final static String Find = "//button[@id=\"catalogPopupButton\"]";
    public final static String Search ="//input[@class=\"gLFyf\"]";
    public final static String Search1 ="//h3[@class=\"LC20lb MBeuO DKV0Md\"]";
    public final static String laptop ="//a[@href=\"/catalog--noutbuki/67959/list?promo-type=market&hid=91013\"]";
    public final static String priceRange = "//div[@data-auto='filter-range-glprice']//following-sibling::input[1]";
    public final static String Brand = "//label[@data-auto=\"filter-list-item-152981\"]";



    public final static String list ="//div[@data-test-id=\"virtuoso-item-list\"]//a[@title]";
    public final static String validPrice = "//div[@data-test-id='virtuoso-item-list']//article//a[@title]//following::span[@data-autotest-currency][1]";

    public final static String  links = "//div[@data-test-id=\"virtuoso-item-list\"]//h3//a[@href]";
    public final static String addingGood ="//div[@class=\"DhXul\"]//button";


        @Test
        public void test1() throws InterruptedException {
        task.settingDriver(GooglePath);

        }

        @Test
        public void test2() throws InterruptedException {
            task.Laptops(Search,YandexMarket,Search1,Find);

        }

        @Test
        public void test3 () throws InterruptedException {
            task.filter(laptop,Brand,priceRange);

        }
    @Test(dataProvider ="dataTest")
    public void test4(String minV,String maxV) throws InterruptedException {
        task.TestData(minV,maxV);
    }


        @Test(retryAnalyzer = YandexPattern.class, dataProvider ="dataTest")
        public void test5(String v1,String v2) throws InterruptedException{
            task.ValidElements(list,validPrice,v1,v2);

        }

        @Test(retryAnalyzer = YandexPattern.class)

        public void test6() throws InterruptedException{
        task.Payment(links);
        }
        @Test(retryAnalyzer = YandexPattern.class )
        public void test7() throws InterruptedException{
            task.AddGood();
        }

        @Test(retryAnalyzer = YandexPattern.class)

        public void test8()  throws InterruptedException{
        task.SelectElement(addingGood);

        }

        @Test
        public void test9() throws InterruptedException{
            task.quitDriver();

        }



        @Override
        public boolean retry(ITestResult result) {
        if (!result.isSuccess()) {
            if (count < maxcount) {
                count++;
                return true;
                }
            }
            return false;
        }
    @DataProvider(name="dataTest")
    public Object[][] DataAndPrice(){
        Object[][] GetData={{"2500","3000"},{"3500","5000"}};
        return GetData;
    }
}
