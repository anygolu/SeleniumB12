package Actions;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.BrowserUtils;

public class DragAndDrop {
    @Test
    public void dragAndDropPractice() {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://demos.telerik.com/kendo-ui/dragdrop/area");
        WebElement draggable = driver.findElement(By.xpath("//div[@id='draggable']"));
        WebElement orangeBox = driver.findElement(By.xpath("//div[@class='test2']"));
        WebElement cookies = driver.findElement(By.id("onetrust-accept-btn-handler"));
        cookies.click();
        String actualMessage = BrowserUtils.getText(orangeBox);
        String expectedMessage = "... Or here.";
        Assert.assertEquals(actualMessage, expectedMessage);

        Actions actions = new Actions(driver);
        actions.dragAndDrop(draggable, orangeBox).perform();
        String actualAfterDragAndDrop = BrowserUtils.getText(orangeBox);
        String expectedAfterDragAndDrop = "You did great!";
        Assert.assertEquals(actualAfterDragAndDrop, expectedAfterDragAndDrop);


    }


    @Test
    public void ClickAndHold() {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://demos.telerik.com/kendo-ui/dragdrop/area");
        WebElement draggable = driver.findElement(By.xpath("//div[@id='draggable']"));
        WebElement blueBox = driver.findElement(By.xpath("//div[@class='test1']"));
        WebElement cookies = driver.findElement(By.id("onetrust-accept-btn-handler"));
        cookies.click();
        String actualMessage = BrowserUtils.getText(blueBox);
        String expectedMessage = "Drag the small circle here ...";
        Assert.assertEquals(actualMessage, expectedMessage);
        Actions actions = new Actions(driver);
        blueBox = driver.findElement(By.xpath("//div[@class='test1']"));
        actions.clickAndHold(draggable).moveToElement(blueBox).release().perform();
        String actualAfterDragAndDrop = BrowserUtils.getText(blueBox);
        String expectedAfterDragAndDrop = "You did great!";
        Assert.assertEquals(actualAfterDragAndDrop, expectedAfterDragAndDrop);


    }

    /*
    1-Navigate to the website "https://demoqa.com/droppable"
    2-Validate the message inside of the Big box is "Drop here" -->first validation
    3-get the "Drop me" box and drop it into big box
    4-Validate the message turned to the "Dropped!"
    5-Validate the background color is steelBlue
       NOTE:To be able to validate this, you should getCSSValue(background-color)
            *TO be able to get the rgba value.Leave the expected part empty,
            and run the code it will show you the actual value. Just copy from it
            and put it on the expected.
     */
    @Test
    public void ClickAndHoldPractice() {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://demoqa.com/droppable");
        WebElement dropBox = driver.findElement(By.xpath("//div[@id-'simpleDropContainer']//div[@id='droppable']"));
        WebElement dragMe = driver.findElement(By.xpath("//div[@id='draggable']"));
        String actualMessage = BrowserUtils.getText(dropBox);
        String expectedMessage = "Drop here";
        Assert.assertEquals(actualMessage,expectedMessage);
        Actions actions = new Actions(driver);
        actions.dragAndDrop(dragMe,dropBox).perform();
        String actualMessageAfterDrop = BrowserUtils.getText(dropBox);
        String expectedMessageAfterDrop = "Dropped!";
        Assert.assertEquals(actualMessageAfterDrop,expectedMessageAfterDrop);
        String actualColor = dropBox.getCssValue("background-color");
        String expectedColor = "rgba(70, 130, 180, 1";
        Assert.assertEquals(actualColor,expectedColor);

    }


    @Test
    public void moveByOffSetPractice() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://the-internet.herokuapp.com/horizontal_slider");
        driver.manage().window().maximize();
        WebElement slider = driver.findElement(By.tagName("input"));
        Actions actions= new Actions(driver);
        actions.clickAndHold(slider).moveByOffset(30,0).perform();
        Thread.sleep(3000);
        actions.clickAndHold(slider).moveByOffset(10,0).perform();
        Thread.sleep(3000);
        actions.clickAndHold(slider).moveByOffset(-30,0).perform();


    }


    @Test
    public void moveOffByOffSetWithPOINTCLASS(){
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.techtorialacademy.com/contact-us");
        WebElement contactUs = driver.findElement(By.xpath("//div[@class='navigation hidden-xs']//a[.='Contact Us']"));
        Point coordinatesOfContactUs = contactUs.getLocation();
        int xCord = coordinatesOfContactUs.getX();
        int yCord=coordinatesOfContactUs.getY();
        System.out.println(xCord);
        System.out.println(yCord);
        Actions actions= new Actions(driver);
        actions.moveByOffset(xCord,yCord).click().perform();


    }
}