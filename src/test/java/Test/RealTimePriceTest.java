package Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class RealTimePriceTest {

    WebDriver driver;

    @BeforeClass
    public void setup() {
        driver = DriverFactory.getDriver();
        driver.get("https://www.tradingview.com/chart/");
    }

    @Test
    public void validateLivePrice() throws InterruptedException {
        By priceTicker = By.cssSelector("YOUR_PRICE_TICKER_SELECTOR"); // replace

        String priceA = driver.findElement(priceTicker).getText();
        Thread.sleep(5000);
        String priceB = driver.findElement(priceTicker).getText();

        Assert.assertNotEquals(priceA, priceB, "Price did not change, data is not live!");
        Assert.assertTrue(priceA.matches("\\d{1,4}\\.\\d{2}"), "Price format invalid");
        Assert.assertTrue(priceB.matches("\\d{1,4}\\.\\d{2}"), "Price format invalid");
    }

    @AfterClass
    public void teardown() {
        DriverFactory.quitDriver();
    }
}
