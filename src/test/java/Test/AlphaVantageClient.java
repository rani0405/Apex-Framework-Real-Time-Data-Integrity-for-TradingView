package Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.JSONObject;

public class AlphaVantageClient {

    private static final String BASE_URL = "https://www.alphavantage.co/query";
    private static final String API_KEY = "https://www.alphavantage.co/"; // Replace with your key

    public static JSONObject getDailyOHLC(String symbol, String date) {
        Response response = RestAssured.given()
                .queryParam("function", "TIME_SERIES_DAILY")
                .queryParam("symbol", symbol)
                .queryParam("apikey", API_KEY)
                .get(BASE_URL);

        JSONObject json = new JSONObject(response.getBody().asString());
        JSONObject dailyData = json.getJSONObject("Time Series (Daily)");
        return dailyData.getJSONObject(date); // returns OHLC for the date
    }
}

