Feature: Get current weather data by latitude and longitude

  Scenario: Get current weather data for a location by latitude and longitude
    Given the user has a valid API key2
    When the user requests current weather data for latitude "37.7749" and longitude "-122.4194"
    Then the response status code should be2 200
    And the response body should contain the weather information for the given coordinates