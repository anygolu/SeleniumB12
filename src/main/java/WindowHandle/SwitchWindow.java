package WindowHandle;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.BrowserUtils;

import java.util.Set;
    public class SwitchWindow {
        @Test
        public void practice(){
            WebDriverManager.chromedriver().setup();
            WebDriver driver = new ChromeDriver();
            driver.navigate().to("https://the-internet.herokuapp.com/");
            WebElement multipleWindowsButton = driver.findElement(By.xpath("//a[contains(text(),'Multiple Windows')]"));
            multipleWindowsButton.click();
            WebElement clickHere = driver.findElement(By.xpath("//a[contains(text(),'Click Here')]"));
            clickHere.click();
            WebElement header = driver.findElement(By.tagName("h3"));
            System.out.println(header.getText());
            Assert.assertTrue(header.getText().equals("New Window"));
            //NOTE : this will fail because our driver is still in previous page . To be able to handle , We have to any Actions(manipulations)
            //on the new tab/window ,we have to switch our driver to the new page.
        }
        @Test
        public void switchingWindow() {
            WebDriverManager.chromedriver().setup();
            WebDriver driver = new ChromeDriver();
            driver.navigate().to("https://the-internet.herokuapp.com/");
            WebElement multipleWindowsButton = driver.findElement(By.xpath("//a[contains(text(),'Multiple Windows')]"));
            multipleWindowsButton.click();
            WebElement clickHere = driver.findElement(By.xpath("//a[contains(text(),'Click Here')]"));
            clickHere.click();
            String mainId = driver.getWindowHandle();//I am expecting one id
            BrowserUtils.switchByID(driver,mainId);
//            Set<String> allPageID = driver.getWindowHandles();
//            for (String id : allPageID){
//                System.out.println(id);//I am expecting 2 id(all id's) but one of them will be my main id
//                if(id.equals(mainId)==false){
//                    driver.switchTo().window(id);
//                }
//            }
            System.out.println(driver.getWindowHandle());
            WebElement header = driver.findElement(By.tagName("h3"));
            System.out.println(header.getText());
            Assert.assertTrue(header.getText().equals("New Window"));
            driver.close();
            driver.switchTo().window(mainId);
            WebElement poweredByText = driver.findElement(By.xpath("//div[contains(text(),'Powered by')]"));
            System.out.println(poweredByText.getText().trim());

        }
    }

