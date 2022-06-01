package Projects;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class MainPageTest2 {
    /*
    Navigate to
"http://automationpractice.com/index.php"
Get all the product prices
Find expensive product and get the name of product
Click MORE button
Validate product price is matching after More button
Get the product name
Validate product name is matching after clicking the
MORE button
Change the color of product
Validate the url ending with selected color name
Steps
     */

    @Test
    public void indexText(){
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://automationpractice.com/index.php");


    }
}
