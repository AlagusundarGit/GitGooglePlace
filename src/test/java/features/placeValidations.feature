Feature: Validatig Place API's

  # Scenario: Verify if place is being Successfully added using AddPlaceAPI
  #  Given Add Place Payload
  #  When user calls "AddPlaceAPI" with "post" http request
  #  Then The API call is success with status code 200
  #  And "status" in response body is "OK"
  #  And "scope" in response body is "APP"
  @AddPlace @Regression
  Scenario Outline: : Verify if place is being Successfully added using AddPlaceAPI
    Given Add Place Payload with "<name>" "<language>" "<address>"
    When user calls "AddPlaceAPI" with "post" http request
    Then The API call is success with status code 200
    And "status" in response body is "OK"
    And "scope" in response body is "APP"
    And verify place_Id created maps to "<name>" using "getPlaceAPI"

    Examples: 
      | name | language | address          |
      | Test | English  | Fire Street road |
  # | Sundar | AU-EN    | Rich Street Road |
  
  @DeletePlace @Regression
  Scenario: Verify if Delete place fuctionality is working
    Given Delete Place Payload
    When user calls "deletePlaceAPI" with "POST" http request
    Then The API call is success with status code 200
    And "status" in response body is "OK"
