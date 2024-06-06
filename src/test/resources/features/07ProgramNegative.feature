@tag07
 
Feature: To test the functionality of program controller Negative

  Background: Admin sets Authorization

  #Positive(POST request)
  @Test07
  Scenario: Check if Admin able to create a program with valid endpoint and request body with Authorization
    Given Admin creates POST Request for the LMS with request body
    When Admin sends HTTPS Request and  request Body with endpoint
    Then Admin receives 201 Created Status with response body

  #Negative(POST request with no auth)
  Scenario: Check if Admin able to create a program with valid endpoint and request body without Authorization
    Given Admin creates POST Request for the LMS with request body
    When Admin sends HTTPS Request without authorization and request Body with endpoint
    Then Admin receives 401 Unauthorized

  #Negative(POST request with invalid endpoint)
  Scenario: Check if Admin able to create a program with invalid endpoint
    Given Admin creates POST Request for the LMS with request body
    When Admin sends HTTPS Request with invalid endpoint and request Body with endpoint
    Then Admin receives 404 not found  Status with message and boolean success details

  #Negative(POST request with existing program name)
  Scenario: Check if Admin able to create a program with already existing program name
    Given Admin creates POST Request for the LMS with request body
    When Admin sends HTTPS Request with already existing program name and request Body with endpoint
    Then Admin receives 400 Bad Request Status with message and boolean success details

  #Negative(POST request with invalid method)
  Scenario: Check if Admin able to create a program with invalid method
    Given Admin creates POST Request for the LMS with request body
    When Admin sends HTTPS Request with invalid method and request Body with endpoint
    Then Admin receives 405 Method Not Allowed

  #Negative(POST request with invalid request body)
  Scenario: Check if Admin able to create a program with invalid request body
    Given Admin creates POST Request for the LMS with request body
    When Admin sends HTTPS Request with invalid request body and request Body with endpoint
    Then Admin receives 400 Bad Request Status

  #Negative(POST request with missing values)
  Scenario: Check if Admin able to create a program with missing values in the request body
    Given Admin creates POST Request for the LMS with request body
    When Admin sends HTTPS Request with missing values and request Body with endpoint
    Then Admin receives 400 Bad Request Status code

  #Negative(POST request with missing additional field)
  Scenario: Check if Admin able to create a program with missing additional field
    Given Admin creates POST Request for the LMS with request body
    When Admin sends HTTPS Request with missing additional field and request Body with endpoint
    Then Admin receives 400 Bad Request

  #Positive(GET request - All programs)
  Scenario: Check if Admin able to retrieve all programs with valid Endpoint
    Given Admin creates GET Request for the LMS API
    When Admin sends HTTPS Request with a endpoint to retrieve all programs
    Then Admin receives 200 OK Status with response body.

  #Negative(GET request - All programs - with invalid endpoint)
  Scenario: Check if Admin able to retrieve all programs with invalid Endpoint
    Given Admin creates GET Request for the LMS API
    When Admin sends HTTPS Request with invalid Endpoint
    Then Admin receives 404 not found  Status with error message

  #Negative(GET request - All programs - with invalid method)
  Scenario: Check if Admin able to retrieve all programs with invalid Method
    Given Admin creates GET Request for the LMS API
    When Admin sends HTTPS Request with invalid Method and endpoint
    Then Admin receives 405 Method Not Allowed status code

  #Negative(GET request - All programs - with no auth)
  Scenario: Check if Admin able to retrieve all programs without Authorization
    Given Admin creates GET Request for the LMS API
    When Admin sends HTTPS Request without Authorization with endpoint
    Then Admin receives 401 Status with response body as Unauthorized

  #Positive(GET request - Program ID)
  Scenario: Check if Admin able to retrieve a program with valid program ID
    Given Admin creates GET Request for the LMS API to retrieve a program by valid program ID
    When Admin sends HTTPS Request with endpoint and stores the response
    Then Admin receives 200 OK Status code with response body.

  #Negative(GET request - Program ID - with invalid program ID)
  Scenario: Check if Admin able to retrieve a program with invalid program ID
    Given Admin creates GET Request for the LMS API with invalid program ID
    When Admin sends HTTPS Request with invalid program ID and endpoint
    Then Admin receives 404 Not Found Status with the message and boolean success details

  #Negative(GET request - Program ID - with invalid baseURI)
  Scenario: Check if Admin able to retrieve a program with invalid baseURI
    Given Admin creates GET Request with invalid baseURI for the LMS API
    When Admin sends HTTPS Request with invalid baseURI endpoint
    Then Admin receives 404 Not Found Status code with message and boolean success details

  #Negative(GET request - Program ID - with no auth)
  Scenario: Check if Admin able to retrieve a program without Authorization
    Given Admin creates GET Request for the LMS API to retrieve a program without Authorization
    When Admin sends HTTPS Request to retrieve a program without Authorization with endpoint
    Then Admin receives 401 Unauthorized status code

  #Negative(GET request - Program ID - with invalid endpoint)
  Scenario: Check if Admin able to retrieve a program with invalid Endpoint
    Given Admin creates GET Request for the LMS API to retrieve a program with invalid endpoint
    When Admin sends HTTPS Request to retrieve a program with invalid Endpoint
    Then Admin receives 404 Not Found status code with message and boolean success details

  #Positive(GET request - All programs with users)
  Scenario: Check if Admin able to retrieve all programs with users and with valid Endpoint
    Given Admin creates GET Request for the LMS API retrieve all programs with users
    When Admin sends HTTPS Request with valid endpoint and stores the response
    Then Admin receives 200 OK Status with response body and asserts the response.

  #Negative(GET request - All programs with users - with invalid endpoint)
  Scenario: Check if Admin able to retrieve all programs with invalid Endpoint
    Given Admin creates GET request for the LMS API
    When Admin sends HTTPS Request with a invalid endpoint
    Then Admin receives 404 not found status with error message

  #Negative(GET request - All programs with users - with invalid method)
  Scenario: Check if Admin able to retrieve all programs with invalid Method
    Given Admin creates GET request for the LMS API
    When Admin sends HTTPS Request to retrieve all programs with invalid Method and endpoint
    Then Admin receives 405 Method Not Allowed Status Code

  #Negative(GET request - All programs with users - with no auth)
  Scenario: Check if Admin able to retrieve all programs without Authorization
    Given Admin creates GET request for the LMS API
    When Admin sends HTTPS Request with endpoint to retrieve all programs without Authorization
    Then Admin receives 401 status code with response body as Unauthorized

  #Positive(PUT request - Update program by Program ID)
  Scenario: Check if Admin able to update a program with valid programID endpoint and valid request body
    Given Admin creates PUT Request for the LMS API endpoint with request Body with mandatory, additional fields.
    When Admin sends HTTPS Request with valid endpoint and recieves the response
    Then Admin receives 200 OK Status code with updated value.

  #Negative(PUT request - Update program by Program ID - with invalid Program ID)
  Scenario: Check if Admin able to update a program with invalid programID endpoint  and valid request body
    Given Admin creates PUT Request for the LMS API endpoint with valid request Body with mandatory , additional  fields.
    When Admin sends HTTPS Request to update a program with invalid programID with invalid endpoint
    Then Admin receives 404 Not Found status with message and boolean success details

  #Negative(PUT request - Update program by Program ID - with invalid request body)
  Scenario: Check if Admin able to update a program with invalid request body
    Given Admin creates PUT Request for the LMS API  with invalid request body
    When Admin sends HTTPS Request to update a program with invalid request body and valid endpoint
    Then Admin receives 400 Bad Request status with message and boolean success details

  #Negative(PUT request - Update program by Program ID - without request body)
  Scenario: Check if Admin able to update a program without request body
    Given Admin creates PUT Request for the LMS API  with missing mandatory fields
    When Admin sends HTTPS Request with missing mandatory fields and valid endpoint
    Then Admin receives 400 Bad Request Status code with message

  #Negative(PUT request - Update program by Program ID - with invalid baseURI)
  Scenario: Check if Admin able to update a program with invalid baseURI
    Given Admin creates PUT Request for the LMS API endpoint with request Body with mandatory , additional  fields.
    When Admin sends HTTPS Request  with request Body with mandatory, additional  fields and valid endpoint
    Then Admin receives 404 Not Found with message and boolean success details

  #Negative(PUT request - Update program by Program ID - with invalid method)
  Scenario: Check if Admin able to update a program with invalid method
    Given Admin creates PUT Request for the LMS API endpoint with request Body
    When Admin sends HTTPS Request with request body and valid endpoint
    Then Admin receives 405 Method Not Allowed status

  #Negative(PUT request - Update program by Program ID - with no auth)
  Scenario: Check if Admin able to update a program without Authorization
    Given Admin creates PUT Request for the LMS API without Authorization and endpoint with request Body and mandatory, additional  fields.
    When Admin sends HTTPS Request with valid endpoint to update a program without Authorization
    Then Admin receives 401 Unauthorized status

  #Positive(PUT request - Update program by Program Name - Valid program Name and request body)
  Scenario: Check if Admin able to update a program with  Valid program Name and request body
    Given Admin creates PUT Request for the LMS API endpoint with valid request Body with all fields.
    When Admin sends HTTPS Request with valid endpoint and gets the response
    Then Admin receives 200 OK Status code with updated value in response body.

  #Positive(PUT request - Update program by Program Name - update a program status)
  Scenario: Check if Admin able to update a program status
    Given Admin creates PUT Request for the LMS API endpoint and Valid program Name and status
    When Admin sends HTTPS Request with Valid program Name, status and endpoint
    Then Admin receives 200 OK with updated value in response body.

  #Negative(PUT request - Update program by Program Name - with invalid Program Name)
  Scenario: Check if Admin able to update a program with invalid program Name and request body
    Given Admin creates PUT Request for the LMS API endpoint with valid request body with mandatory, additional fields.
    When Admin sends HTTPS request with invalid endpoint to update a program with invalid program Name
    Then Admin gets 404 Not Found Status with message and boolean success details

  #Negative(PUT request - Update program by Program Name - with missing programName and programStatus)
  Scenario: Check if Admin able to update a program using valid program name - missing mandatory fields in request body
    Given Admin creates PUT Request for the LMS API  with missing programName and programStatus
    When Admin sends HTTPS Request with  missing mandatory fields and valid endpoint
    Then Admin receives 400 Bad Request Status Code with message and boolean success details

  #Negative(PUT request - Update program by Program Name - with invalid values )
  Scenario: Check if Admin able to update a program with invalid values in request body
    Given Admin creates PUT Request for the LMS API endpoint and invalid program Name
    When Admin sends HTTPS Request and request Body with invalid values in mandatory fields. (program Description , program Name, program status)
    Then Admin receives 400 Bad Request with message and boolean success details

  #Negative(PUT request - Update program by Program Name - with invalid Program Description)
  Scenario: Check if Admin able to update a program with invalid program Description
    Given Admin creates PUT Request for the LMS API endpoint and invalid program Description
    When Admin sends HTTPS Request and request Body with invalid program Description in mandatory fields. (program Description , program Name, program status)
    Then Admin receives 400 Bad Request response with message and boolean success details

  #Negative(PUT request - Update program by Program Name - with no auth)
  Scenario: Check if Admin able to update a program without Authorization
    Given Admin creates PUT Request for the LMS API endpoint with request Body with all the fields.
    When Admin sends HTTPS Request with valid endpoint to update a program without authorization
    Then Admin receives 401 Unauthorized Status

  #Positive(DELETE request - delete by Program Name)
  Scenario: Check if Admin able to delete a program with valid programName
    Given Admin creates DELETE Request for the LMS API endpoint  and  valid programName
    When Admin sends HTTPS Request with endpoint to delete a program with program name
    Then Admin receives 200 Ok status with message

  #Negative(DELETE request - delete by Program Name - with invalid Program Name)
  Scenario: Check if Admin able to delete a program with valid LMS API, invalid programName
    Given Admin creates DELETE Request for the LMS API endpoint and invalid {programName}
    When Admin sends HTTPS Request with endpoint to delete a program with invalid programName
    Then Admin gets 404 Not Found status code with message and boolean success details

  #Negative(DELETE request - delete by Program Name - with no auth)
  Scenario: Check if Admin able to delete a program without Authorization
    Given Admin creates DELETE Request for the LMS API endpoint and valid programName
    When Admin sends HTTPS Request with endpoint to delete a program without Authorization
    Then Admin receives 401 unauthorized status code

  #Positive(Delete request - delete by Program ID)
  Scenario: Check if Admin able to delete a program with valid program ID
    Given Admin creates DELETE Request for the LMS API endpoint  and  valid program ID
    When Admin sends HTTPS Request with endpoint to delete a program with program ID
    Then Admin receives 200 Ok and validates the status

  #Negative(Delete request - delete by Program ID - with invalid Program ID)
  Scenario: Check if Admin able to delete a program with valid LMS API,invalid program ID
    Given Admin creates DELETE Request for the LMS API endpoint and invalid program ID
    When Admin sends HTTPS Request with endpoint to delete a program with invalid program ID
    Then Admin receives 404 Not Found Status Code as response with message and boolean success details

  #Negative(Delete request - delete by Program ID - with no auth)
  Scenario: Check if Admin able to delete a program without Authorization
    Given Admin creates DELETE Request for the LMS API with endpoint and valid programId
    When Admin sends HTTPS Request with endpoint to delete a program by programId without Authorization
    Then Admin gets 401 Unauthorized status code
    
   #POST REQUEST(for Batch)
  Scenario: Check if Admin able to create a program with valid endpoint and request body with Authorization to be used for batch
    Given Admin creates POST Request for the LMS with request body to be used for batch
    When Admin sends HTTPS Request and request Body with endpoint to be used for batch
    Then Admin receives 201 Created Status with response body to be used for batch
    
   #POST REQUEST(for Batch)
  Scenario: Check if Admin able to create a program with valid endpoint and request body with Authorization to be used for batch inactive programID
    Given Admin creates POST Request for the LMS with request body to be used for batch inactive programID
    When Admin sends HTTPS Request and request Body with endpoint to be used for batch inactive programID
    Then Admin receives 201 Created Status with response body to be used for batch inactive programID
  
  

