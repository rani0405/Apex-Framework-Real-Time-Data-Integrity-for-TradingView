package Test;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class HistoricalDataTest {

    WebDriver driver;
    ExtentReports report;
    ExtentTest test;

    @BeforeClass
    public void setup() {
        driver = DriverFactory.getDriver();
        report = ExtentReportManager.getReports();
        test = report.createTest("Historical OHLC Data Validation");
        driver.get("https://www.tradingview.com/chart/");
    }

    @Test
    public void verifyOHLCValues() throws InterruptedException {
        String symbol = "AAPL";
        String date = "2025-09-24"; // Replace with yesterday’s date
        JSONObject apiData = AlphaVantageClient.getDailyOHLC(symbol, date);

        test.info("API Data: " + apiData.toString());

        // Hover on chart candlestick (simplified example, update selector)
        Actions actions = new Actions(driver);
        By candle = By.cssSelector("YOUR_CANDLE_SELECTOR"); // Replace with actual selector
        actions.moveToElement(driver.findElement(candle)).perform();
        Thread.sleep(2000);

        String uiData = driver.findElement(By.cssSelector("YOUR_UI_DATA_SELECTOR")).getText();
        test.info("UI Data: " + uiData);

        Assert.assertEquals(uiData, apiData.toString(), "OHLC values do not match!");
    }

    @AfterClass
    public void teardown() {
        DriverFactory.quitDriver();
        report.flush();
    }
}
