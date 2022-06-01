package JavaScriptExecutor;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.BrowserUtils;

public class JavaScriptMethods {

    //Use JavaScript Title and click and normal methods are not working
    //this should be your last step to get title or click the element

    @Test
    public void titleMEthod() {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("    http://www.techtorialacademy.com/");
        System.out.println(driver.getTitle()+"Driver");
        JavascriptExecutor js= (JavascriptExecutor) driver;
        String title = js.executeScript("return document.title").toString();//it gets the title
        System.out.println(title+" JavaScriptExecutor");
    }

    @Test
    public void clickMethodJS(){
        //If the javascript fails, it will throw the "JavascriptException".
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://www.techtorialacademy.com/");
        WebElement studentLogin = driver.findElement(By.xpath("//div[@class='navigation hidden-xs']//a[.='Student login']"));
        JavascriptExecutor js= (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click()",studentLogin);
    }

    //*****
    //Scroll up and down the page?
    @Test
    public void ScrollIntoViewMethod() {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://www.techtorialacademy.com/");
        WebElement applyNow = driver.findElement(By.xpath("//div[@class='eduaply-btn']"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        //js.executeScript("argument[0].click()",applyNow);
        WebElement copyRight = driver.findElement(By.className("copyrightSec"));
        //System.out.println(copyRight.getText());
        js.executeScript("arguments[0].scrollIntoView(true)", copyRight);
    }


    @Test
    public void scrollIntoViewPractice(){
        /*
        1. Navigate into website techtorial
        2. Click browse course with JS
        3. GwtTitle with JS and validate it
        4. Scroll down until information bottom of the page

        BONUS:
        5. Scroll down "Get started" from SDET Course(*) and click with JS
        Reward --> Chocolate on Saturday (if the xpatch is good) --> not index or copyxpatch
         */
        WebDriverManager.chromedriver().setup();
        WebDriver driver=new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://www.techtorialacademy.com/");
        WebElement browseCourse=driver.findElement(By.linkText("Browse Course"));
        JavascriptExecutor js= (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click()",browseCourse);
        String actualTitle= js.executeScript("return document.title").toString();
        String expectedTitle="Programs - Techtorial";
        Assert.assertEquals(actualTitle,expectedTitle);
        WebElement information=driver.findElement(By.xpath("//h2[.='information']"));
        js.executeScript("arguments[0].scrollIntoView(true)",information);
        WebElement getStarted=driver.findElement(By.xpath("//h4[.='SDET Course']//..//a[.='Get Started']"));
        js.executeScript("arguments[0].click()",getStarted);
    }


    @Test
    public void practiceWithBrowserUtils(){
        WebDriverManager.chromedriver().setup();
        WebDriver driver=new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://www.techtorialacademy.com/");
        WebElement browseCourse=driver.findElement(By.linkText("Browse Course"));
        BrowserUtils.clickWithJS(driver,browseCourse);
        String actualTitle= BrowserUtils.getTitleWithJS(driver);
        String expectedTitle="Programs - Techtorial";
        Assert.assertEquals(actualTitle,expectedTitle);
        WebElement information=driver.findElement(By.xpath("//h2[.='information']"));
        BrowserUtils.scrollWithJS(driver,information);
        WebElement getStarted=driver.findElement(By.xpath("//h4[.='SDET Course']//..//a[.='Get Started']"));
        BrowserUtils.clickWithJS(driver,getStarted);
    }

}
