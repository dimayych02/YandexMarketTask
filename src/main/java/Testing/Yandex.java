package Testing;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import java.time.Duration;
import java.util.Collections;
import java.util.List;
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

                WebElement Brand = dr.findElement(By.xpath(BrandLenovo));
                Brand.click();

                List<WebElement> pricesRange = dr.findElements(By.xpath(range));
                pricesRange.get(0).sendKeys(min);
                pricesRange.get(1).sendKeys(max);
                Thread.sleep(4000);

        }

    }
        public void ValidElements(String listOfElements,String valPrice,String minvalue,String maxvalue){
            JavascriptExecutor js = (JavascriptExecutor) dr;
            js.executeScript("window.scrollBy(0,13000)");

            List <WebElement> LenovoLaptops = dr.findElements(By.xpath(listOfElements)).stream().collect(Collectors.toList());
            List <WebElement> validPrice = dr.findElements(By.xpath(valPrice)).stream().collect(Collectors.toList());
            for(WebElement element:LenovoLaptops){
            String result = element.getAttribute("title");

            Assert.assertTrue(result.toLowerCase().contains("lenovo"));
            }
            for(WebElement price:validPrice){
            String result1 =  price.getText().replaceAll("\\s+","").replaceAll("₽","");
            Assert.assertTrue(Integer.parseInt(result1)>Integer.parseInt(minvalue)&&Integer.parseInt(result1)<Integer.parseInt(maxvalue));

        }

    }
    public void quitDriver(){
        dr.quit();
    }
}
