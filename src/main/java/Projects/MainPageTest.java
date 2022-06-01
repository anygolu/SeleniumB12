package Projects;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.*;

public class MainPageTest {
    @Test
    public void indexTest() {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://automationpractice.com/index.php");

        WebElement cartText =
                driver.findElement(By.tagName("b"));

        WebElement emptyTextElement=
                driver.findElement(By.className("ajax_cart_no_product"));

        String actualCartText =
                cartText.getText().trim() + emptyTextElement.getText().trim();

        assertTrue(cartText.isDisplayed() && emptyTextElement.isDisplayed());
        String expectedCartText = "Cart(empty)";
        assertEquals(actualCartText, expectedCartText);

        List<WebElement> priceElements =
                driver.findElements(By.xpath("//ul[@id='homefeatured']//div[@class='right-block']//span[@class='price product-price']"));

        List<WebElement> nameElements =
                driver.findElements(By.xpath("//ul[@id='homefeatured']//a[@class=\"product-name\"]"));

        double minPrice = new Double(priceElements.get(0).getText().trim().substring(1));
        int index = 0;
        for (int i =0;i<priceElements.size();i++)
        {
            String priceText = priceElements.get(i).getText().trim().substring(1);

            double value =new Double(priceText);
            if (minPrice>value){
                minPrice=value;
                index = i;
            }
            // I know smallest price and I know it's nam
        }
        System.out.println(nameElements.get(index).getText()+" $"+minPrice);

        //In the beginning one value as minimum
            //so if the value that I have currently in the iteration is smaller than
            //the min value in the beginning we should assign min value with

        List<WebElement> priceDiscounts =
                driver.findElements(By.xpath(
                        "//ul[@id='homefeatured']//div[@class='right-block']//a[contains(text(),'\"+nameElements.get(index).getText().trim()+\"')]/parent::h5/following-sibling::div[@class=\\\"content_price\\\"]/span"));
        double originalPrice = new Double(priceDiscounts.get(1).getText().trim().substring(1));
        double percentage = new Double(priceDiscounts.get(2).getText().trim().substring(1,priceDiscounts.get(2).getText().trim().length()-1));
        //% = 20
        // percentage is 20
        double toBeDiscounted = originalPrice*percentage/100;
        double expectedMin = originalPrice-toBeDiscounted;
        assertEquals(minPrice,expectedMin,"Percentage calculation is not right.");
        System.out.println("Expected price is "+expectedMin+" and actual displayed price is "+minPrice);
        System.out.println("Percentage of sale is "+ percentage);
        System.out.println("Original price of the product is "+ originalPrice);

    }
        }



