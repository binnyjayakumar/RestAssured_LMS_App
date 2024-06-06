@tag02
Feature: User Login Post request 

#Positive
@Test02
  Scenario: Check if Admin able to generate token with valid credential
    Given Add UserLogin Payload
    When Admin calls Post Https method  with valid endpoint
    Then Admin receives 201 created with auto generated token
   
 
    
   