Feature: Get Current Weather Data
  As a user of Open Weather Map API
  I want to retrieve current weather data for a specific location
  So that I can display the weather information on my application

  Scenario: Get current weather data for a valid location
    Given the user has a valid API key
    When the user requests current weather data for "London"
    Then the response status code should be 200
    And the response body should contain the weather information for "London"

