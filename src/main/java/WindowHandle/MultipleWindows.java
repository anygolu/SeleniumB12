package WindowHandle;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
import utils.BrowserUtils;

import java.util.Set;

public class MultipleWindows {
    @Test
    public void MultipleWindowsPractice(){
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.navigate().to("https://the-internet.herokuapp.com/");
        driver.manage().window().maximize();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.open('http://www.techtorialacademy.com/')");
        js.executeScript("window.open('https://www.techtorialacademy.com/about-us')");
        js.executeScript("window.open('https://www.techtorialacademy.com/programs')");

        //How can I switch my window to the one I want to go with
        //previous implementation is comparing 2 windows, but now I have more
        //than 2 pages(tab)

        System.out.println(driver.getTitle());
        BrowserUtils.switchByTitle(driver,"About Us");
//        Set<String> allIds = driver.getWindowHandles();
//
//        for(String id:allIds){
//            driver.switchTo().window(id);
//            if(driver.getTitle().contains("About-Us")){
//                break;
//            }
//        }
        System.out.println(driver.getTitle());//about us-techtorial
    }
}
