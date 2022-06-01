package Iframe;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.BrowserUtils;

/*TASK: NOTE: You might need Action class,GetWindowHandles, and Iframe knowledge for this task
 1-Navigate to the website"https://skpatro.github.io/demo/iframes/"
 2-Click Pavilion and click Selenium-java and validate the header "Selenium-Java Tutorial – Basic to Advance"
 3-Go back to mainPage and click category1 and validate the header "Category Archives: SeleniumTesting"
 4-Go back to mainPage and click Category3 and validate the header "Category Archives: SoftwareTesting"
 and url "https://qavalidation.com/category/softwaretesting/"
 5-Close All Tabs
 */

public class IframeTask {
    @Test
    public void IframeTask() {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get("https://skpatro.github.io/demo/iframes/");
        driver.manage().window().maximize();
        WebElement Pavilion = driver.findElement(By.linkText("Pavilion"));
        Pavilion.click();
        BrowserUtils.switchByTitle(driver,"Home = qavalidation");
        WebElement Selenium = driver.findElement(By.xpath("//ul[@id='primary-menu']//span[.='Selenium']//span"));
        Actions actions = new Actions(driver);
        actions.moveToElement(Selenium).perform();
        WebElement selenium_java = driver.findElement(By.xpath("//ul[@id='primary-menu']//span[.='Selenium-Java']//span"));
        actions.click(selenium_java).perform();
        WebElement header = driver.findElement(By.tagName("h1"));
        String actualHeader = BrowserUtils.getText(header);
        String expectedHeader = "Selenium-Java Tutorial - Basic to Advance";
        Assert.assertEquals(actualHeader,expectedHeader);
        BrowserUtils.switchByTitle(driver,"iframes");
        driver.switchTo().frame("Frame1");
        WebElement category1 = driver.findElement(By.linkText("Category1"));
        category1.click();
        BrowserUtils.switchByTitle(driver,"SeleniumTesting Archives");
        WebElement header1 = driver.findElement(By.tagName("h1"));
        Assert.assertTrue(BrowserUtils.getText(header1).equals("Category Archives: SeleniumTesting"));
        BrowserUtils.switchByTitle(driver,"iframes");
        driver.switchTo().frame("Frame2");
        WebElement category3 = driver.findElement(By.linkText("Category3"));
        category3.click();
        BrowserUtils.switchByTitle(driver,"SoftwareTesting Archives");
        WebElement header2 = driver.findElement(By.tagName("h1"));
        Assert.assertTrue(BrowserUtils.getText(header2).equals("Category Archives: SoftwareTesting"));
        Assert.assertTrue(driver.getCurrentUrl().equals("https://qavalidation.com/category/softwaretesting/"));
        driver.quit();
    }

}