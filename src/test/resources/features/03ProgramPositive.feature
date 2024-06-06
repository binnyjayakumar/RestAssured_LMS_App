@tag03
Feature: program controller Positive
 Background: 
  Given Admin sets Authorization
 #Positive
 @Test03

 Scenario: Check if Admin able to create a program with valid endpoint and request body with Authorization
   Given Admin creates POST Request for the LMS with request body
   When Admin sends HTTPS Request and  request Body with endpoint
   Then Admin receives 201 Created Status with response body
   
  # GET REQUEST(All Programs)
  Scenario: Check if Admin able to retrieve all programs with valid Endpoint
    Given Admin creates GET Request for the LMS API
    When Admin sends HTTPS GET Request with endpoint to get all programs
    Then Admin receives 200 OK Status with response body.

	#GET REQUEST(Get program by Program ID)
  Scenario: Check if Admin able to retrieve a program with valid program ID
    Given Admin creates GET Request for the LMS API to retrieve a program by valid program ID
    When Admin sends HTTPS Request with endpoint and stores the response
    Then Admin receives 200 OK Status code with response body.

 # GET REQUEST(All Programs with Users)
  Scenario: Check if Admin able to retrieve all programs with users and with valid Endpoint
    Given Admin creates GET Request for the LMS API retrieve all programs with users
    When Admin sends HTTPS Request with valid endpoint and stores the response
    Then Admin receives 200 OK Status with response body and asserts the response.

 # PUT REQUEST(Update Program by Program Name)
  Scenario: Check if Admin able to update a program with  Valid program Name and request body
    Given Admin creates PUT Request for the LMS API endpoint with valid request Body with all fields.
    When Admin sends HTTPS Request with valid endpoint and gets the response
    Then Admin receives 200 OK Status code with updated value in response body.

  #PUT REQUEST(Update Program by Program ID)
  Scenario: Check if Admin able to update a program with valid programID endpoint and valid request body
    Given Admin creates PUT Request for the LMS API endpoint with request Body with mandatory, additional fields.
    When Admin sends HTTPS Request with valid endpoint and recieves the response
    Then Admin receives 200 OK Status code with updated value in response body.

 

   