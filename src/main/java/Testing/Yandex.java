


import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import java.time.Duration;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Yandex {
    public static WebDriver dr;

    public void settingDriver(String google) {


        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
        options.addArguments("--disable-useAutomationExtension");
        options.addArguments("--disable-blink-features=AutomationControlled");
        WebDriverManager.chromedriver().setup();

        dr = new ChromeDriver(options);

        dr.manage().window().maximize();
        dr.manage().timeouts().implicitlyWait(Duration.ofSeconds(6));
        dr.get(google);

    }


    public void Laptops(String searching,String aHref,String S,String finding) throws InterruptedException {
        try {
            WebElement elo = dr.findElement(By.xpath(searching));
            elo.click();
            elo.sendKeys(aHref, Keys.ENTER);
            Thread.sleep(2000);
            WebElement eloo = dr.findElement(By.xpath(S));
            eloo.click();
            Thread.sleep(2000);
            WebElement el1 = dr.findElement(By.xpath(finding));
            el1.click();
            Thread.sleep(3000);

        }
        catch (Exception e) {

            WebElement bot = dr.findElement(By.xpath("//input[@type=\"submit\"]"));
            bot.click();
            Thread.sleep(13000);
            WebElement el1 = dr.findElement(By.xpath(finding));
            el1.click();
            Thread.sleep(3000);
        }



    }
    public void filter(String laptops,String BrandLenovo,
                       String range,String min,String max) throws InterruptedException {
        try {
            WebElement el2 = dr.findElement(By.xpath(laptops));
            el2.click();

            WebElement Brand = dr.findElement(By.xpath(BrandLenovo));
            Brand.click();

            List<WebElement> pricesRange = dr.findElements(By.xpath(range));
            pricesRange.get(0).sendKeys(min);
            pricesRange.get(1).sendKeys(max);
            Thread.sleep(4000);

        }
        catch (Exception e){

            WebElement bot = dr.findElement(By.xpath("//input[@type=\"submit\"]"));
            bot.click();
            Thread.sleep(13000);
            WebElement Brand = dr.findElement(By.xpath(BrandLenovo));
            Brand.click();

            List<WebElement> pricesRange = dr.findElements(By.xpath(range));
            pricesRange.get(0).sendKeys(min);
            pricesRange.get(1).sendKeys(max);
            Thread.sleep(4000);

        }



    }
    public void ValidElements(String listOfElements,String valPrice,String minvalue,String maxvalue) throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) dr;

        js.executeScript("window.scrollBy(0,13100)");

        dr.manage().timeouts().implicitlyWait(Duration.ofSeconds(6));
        List <WebElement> LenovoLaptops = dr.findElements(By.xpath(listOfElements)).stream().collect(Collectors.toList());
        List <WebElement> validPrice = dr.findElements(By.xpath(valPrice)).stream().collect(Collectors.toList());
        System.out.println(LenovoLaptops.size());
        System.out.println(validPrice.size());
        for(WebElement element:LenovoLaptops){

            String result = element.getAttribute("title");
            System.out.println(result);

            Assert.assertTrue(result.toLowerCase().contains("lenovo"));

            Assert.assertTrue(result.length()>0);


        }
        for(WebElement price:validPrice){
            String result1 =  price.getText().replaceAll("\\s+","").replaceAll("₽","");
            System.out.println(result1);


            Assert.assertTrue(Integer.parseInt(result1)>=Integer.parseInt(minvalue)&&Integer.parseInt(result1)<=Integer.parseInt(maxvalue));

             Assert.assertTrue(result1.length()>0);
        }


    }
    public void Payment(String linksOfElements){
    JavascriptExecutor js = (JavascriptExecutor) dr;
    js.executeScript("window.scrollBy(0,-12830)");
    List <WebElement> LenovoLaptop = dr.findElements(By.xpath(linksOfElements));

    WebElement OurLaptop =LenovoLaptop.get(0);
    OurLaptop.click();
    }



    public void AddGood() throws InterruptedException {
        try{
            String window1 = dr.getWindowHandle();

            Set<String> currentWindows = dr.getWindowHandles();
            String window2 = null;

            for(String window :currentWindows){
                if(!window.equals(window1)){
                    window2 = window;
                    break;
                }
            }
            dr.switchTo().window(window2);
            dr.getCurrentUrl();
        }
        catch (Exception e){
            WebElement bot = dr.findElement(By.xpath("//input[@type=\"submit\"]"));
            bot.click();
            Thread.sleep(13000);

            String window1 = dr.getWindowHandle();

            Set<String> currentWindows = dr.getWindowHandles();
            String window2 = null;

            for(String window :currentWindows){
                if(!window.equals(window1)){
                    window2 = window;
                    break;
                }
            }
            dr.switchTo().window(window2);
            dr.getCurrentUrl();

        }



    }
    public void SelectElement(String Good) throws InterruptedException{
        try {

            List <WebElement> buying = dr.findElements(By.xpath(Good));

            WebElement buy = buying.get(0);
            buy.click();
        }


        catch (Exception e){

            WebElement bot = dr.findElement(By.xpath("//input[@type=\"submit\"]"));
            bot.click();
            Thread.sleep(13000);

            List <WebElement> buying = dr.findElements(By.xpath(Good));

            WebElement buy = buying.get(0);
            buy.click();
        }
                }


    public void quitDriver(){

        dr.quit();
    }
}
