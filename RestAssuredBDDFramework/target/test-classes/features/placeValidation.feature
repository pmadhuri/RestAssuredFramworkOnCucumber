Feature: Validating place API
@AddPlace
Scenario Outline:: verify if place is being successfully added using AddPlaceAPI

Given add place payload with Payload "<name>" "<language>" "<address>"
When user calla "addPlaceAPI" with "post" http request
Then the api call is success with status code 200
And "status" code in response body is "OK"
And "scope" code in response body is "APP"
And Verify place_ID created maps to "<name>" using "getPlaceAPI"



Examples: 
                | name    |  language |   address             |
                | AHouse  |  English  |   world cross cernter1 |
              #  | BHouse  |  Spanish  |   world cross cernter2 |
              #2nd
  @DeletePlace 
   Scenario: Verify if Delete place functionality is working
   Given DeletePlace Payload
   When user calla "deletePlaceAPI" with "POST" http request
   Then the api call is success with status code 200
   And "status" code in response body is "OK"