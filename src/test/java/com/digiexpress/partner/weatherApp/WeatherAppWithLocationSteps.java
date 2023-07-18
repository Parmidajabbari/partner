package com.digiexpress.partner.weatherApp;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Assert;

public class WeatherAppWithLocationSteps {
    public final static String apiKey = "3a39892dd00bf4bb9d692d62fd523cd9";

    private Response response;

    @Given("^the user has a valid API key2$")
    public void theUserHasAValidApiKey2() {
        RestAssured.baseURI = "https://api.openweathermap.org/data/2.5/weather";
        RestAssured.given().queryParam("appid", apiKey);
    }

    @When("^the user requests current weather data for latitude \"([^\"]*)\" and longitude \"([^\"]*)\"$")
    public void theUserRequestsCurrentWeatherDataFor(String lat, String lon) {
        response = RestAssured.get("?lat=" + lat + "&lon=" + lon + "&appid=" + apiKey);
    }

    @Then("^the response status code should be2 (\\d+)$")
    public void theResponseStatusCodeShouldBe2(int statusCode) {
        Assert.assertEquals(statusCode, response.getStatusCode());
    }

    @And("^the response body should contain the weather information for the given coordinates$")
    public void theResponseBodyShouldContainTheWeatherInformationFor() {
        Assert.assertNotNull(response.getBody().jsonPath().getString("weather.description"));
        Assert.assertNotNull(response.getBody().jsonPath().getString("main.temp"));
    }
}