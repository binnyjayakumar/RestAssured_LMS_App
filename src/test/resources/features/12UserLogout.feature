 #@logout
 @tag12
 Feature: User Logout

  #@logout1
  @Test12
  Scenario: Check if Admin able to logout with valid endpoint
    Given Admin sets authorization to bearer Token with token
    When Admin calls Get Https method with valid endpoint
    Then Admin receives 200 ok  and response with "Logout successful"
    
  
