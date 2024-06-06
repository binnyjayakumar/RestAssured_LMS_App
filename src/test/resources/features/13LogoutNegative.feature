#@logout
 @tag13
 Feature: User Logout Negative

    
 # @logout2
  @Test13
  Scenario: Check if Admin able to logout with invalid endpoint
    Given Admin sets authorization to bearer Token with token
    When Admin calls Get Https method with invalid endpoint
    Then Admin receives 404 Not found
    
  #@logout3
  Scenario: Check if Admin able to logout with valid endpoint with NoAuth
    Given Admin sets No authorization 
    When Admin calls Get Https method with valid endpoint
    Then Admin receives status 401 with unauthorized error message	Negative