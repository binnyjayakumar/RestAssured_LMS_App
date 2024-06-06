@tag1
Feature: User Login Negative request 


@Test01
 Scenario: Check if Admin able to generate token with invalid endpoint
    Given Add UserLogin Payload
    When Admin calls Post Https method  with invalid endpoint
    Then Admin receives 401 unauthorized
    
    Scenario:Check if Admin able to generate token with invalid credentials
    Given Admin creates request with invalid credentials
    When Admin calls Post Https method  with valid endpoint
    Then Admin receives 400 Bad request
   

   