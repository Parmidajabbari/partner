package com.digiexpress.partner.weatherApp;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Assert;

public class WeatherAppSteps {
    public final static String apiKey = "3a39892dd00bf4bb9d692d62fd523cd9";

    private Response response;

    @Given("^the user has a valid API key$")
    public void theUserHasAValidApiKey() {
        RestAssured.baseURI = "https://api.openweathermap.org/data/2.5/weather";
        RestAssured.given().queryParam("appid", apiKey);
    }

    @When("^the user requests current weather data for \"([^\"]*)\"$")
    public void theUserRequestsCurrentWeatherDataFor(String location) {
        response = RestAssured.get("?q=" + location + "&appid=" + apiKey);
    }

    @Then("^the response status code should be (\\d+)$")
    public void theResponseStatusCodeShouldBe(int statusCode) {
        Assert.assertEquals(statusCode, response.getStatusCode());
    }

    @And("^the response body should contain the weather information for \"([^\"]*)\"$")
    public void theResponseBodyShouldContainTheWeatherInformationFor(String location) {
        Assert.assertTrue(response.getBody().asString().contains(location));
    }
}