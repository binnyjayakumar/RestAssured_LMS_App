@tag09

Feature: Negative scenario for User Module
Background: 
Given Admin sets Authorization token

  @User_GetallRoles_Noauth
  @Test09
  Scenario: Check if admin is able to retreive all the available roles without Authorization
    Given Admin creates GET Request without Authorization
    When Admin sends HTTPS Request with GET All Roles endpoint
    Then Admin receives status 401 with Unauthorized message

  @User_GetallRoles_Invalidendpoint
  Scenario: Check if admin is able to retreive all the available roles with invalid End point
    Given Admin creates GET Request
    When Admin sends HTTPS Request with the invalid endpoint
    Then Admin receives status 404 with Not Found error message

  @User_GetallUser_NoAuth
  Scenario: Check if admin able to retrieve all Admin without Authorization
    Given Admin creates GET Request without Authorization
    When Admin sends HTTPS Request with valid endpoint
    Then Admin receives status 401 with Unauthorized message

  @User_GetallUser_Invalid_endpoint
  Scenario: Check if admin able to retrieve all Admin with invalid endpoint
    Given Admin creates GET Request
    When Admin sends HTTPS Request with invalid endpoint
    Then Admin receives status 404 with Not Found error message

  @User_GetUser_ValidId_Invalidendpoint
  Scenario: Check if admin able to retrieve a Admin with valid Admin ID and invalid endpoint
    Given Admin creates GET Request with valid AdminId
    When Admin sends HTTPS Request with invalid endpointId
    Then Admin receives status 404 with Not Found error message

  @User_GetUser_ValidId_Noauth
  Scenario: Check if admin able to retrieve a Admin with valid Admin ID with No authorization
    Given Admin creates GET Request with valid AdminId and with No Auth
    When Admin sends HTTPS Request with a valid endpoint
    Then Admin receives status 401 with Unauthorized message

  @User_GetUser_InvalidId
  Scenario: Check if Admin able to retrieve a Admin with invalid Admin ID
    Given Admin creates GET Request with invalid AdminId
    When Admin sends HTTPS Request with a valid endpoint
    Then Admin receives status 404 with Not Found error message

  @User_GetallUserswithRoles_Noauth
  Scenario: Check if admin able to retrieve all Admins with roles with No authorization
    Given Admin creates GET Request without Authorization
    When Admin sends HTTPS Request with endpoint as Alluserswithroles
    Then Admin receives status 401 with Unauthorized message

  @User_GetallUserswithRoles_Invalidendpoint
  Scenario: Check if admin able to retrieve all Admins with roles with invalid endpoint
    Given Admin creates GET Request
    When Admin sends HTTPS Request with invalid endpoint
    Then Admin receives status 404 with Not Found error message

  @User_CreateUser_Invaliddata
  Scenario: Check if admin is able to create a Admin with valid endpoint and invalid values in request body
    Given Admin creates POST request with all mandatory fields and additional fields
    When Admin sends HTTPS Request with endpoint
    Then Admin receives 400 Bad Request Status with message and boolean success details

  @User_CreateUser_Missingfields
  Scenario: Check if Admin able to create a Admin missing mandatory fields in request body
    Given Admin creates POST request with missing mandatory fields in request body
    When Admin sends HTTPS Request with endpoint
    Then Admin receives 400 Bad Request Status with error message

  @User_CreateUser_Noauth
  Scenario: Check if admin able to create a new Admin with request body without authorization
    Given Admin creates POST request with all mandatory fields and additional fields with No Auth
    When Admin sends HTTPS Request with endpoint
    Then Admin receives status 401 with Unauthorized message
    
  # ( get count of active and inactive)
 @Test01
 Scenario: Check if admin is able to get count of active and inactive Admins with invalid endpoint  
  Given  Admin creates GET Request  
 When Admin sends HTTPS Request with endpoint with invalid endpoint   
 Then Admin receives status 404 with Not Found error message 
     #(get count of active and inactive)
 @Test02 
 Scenario: Check if admin is able to get count of active and inactive Admins by invalid role ID   
 Given Admin creates GET Request with active and inactive Admins Negative endpoint
 When Admin sends GET HTTPS Request  endpoint with	invalid role ID
 Then  Admin receives status 401 with Unauthorized message

  #(get count of active and inactive)  
 @Test03 
 Scenario: Check if admin is able to get count of active and inactive Admins with no authorization  
 Given Admin creates no authorization GET Request   
 When Admin sends a HTTPS Request with endpoint no authorization  
 Then Admin receives status 401 with Unauthorized message
 
 #Get All Active Users
 @Test03 
 Scenario:  Check if admin able to retrieve all active Admins with invalid endpoint   
 Given  Admin creates GET Request   
 When Admin sends HTTPS Request with all active Admins with invalid endpoint  
 Then Admin receives status 404 with Not Found error message 
  #new Get All Active Users
 @Test03 
 Scenario: Check if admin able to retrieve all active Admins with no authorization  
  Given Admin creates no authorization GET Request
  When Admin sends HTTPS Request with endpoint with no auth 
  Then Admin receives status 401 with Unauthorized
 
 #Gets User by Program BatchesId 
  @Test04 
 Scenario: Check if admin is able to get the Admins by program batches for invalid batch ID 
 Given Admin creates GET Request  with invalid batchId
 When Admin sends GET Request  with invalid batchId by Program Batches endpoint
 Then Admin receives status 404 with Not Found error message 
 
 #Gets Users By ProgramId
@Test06
  Scenario:Check if admin is able to get the Admins for invalid program Id  
  Given Admin creates GET Request with invalid program Id 
  When Admin sends GET Request  with invalid program Id by Program  endpoint
  Then Admin receives status 404 with Not Found error message
    #Gets Users by invalid role ID  
@Test07
 Scenario: Check if admin is able to retreive Admins by invalid role ID 
  Given Admin creates GET Request for GET with invalid role ID  
  When Admin sends GET HTTPS Request  endpoint with	invalid role ID
  Then Admin receives status 404 with Not Found error message
  
  @Get_Admin_with_filters_401
  Scenario:  Check if admin is able to retrieve all Admins with filters with no authorization
    Given Admin creates GET Request with filters with no authorization
    When Admin sends HTTPS Request with filters with no authorization with endpoint
    Then Admin receives status 401 with Unauthorized message
 
 @Get_Admin_with_filters_404
  Scenario:  Check if admin is able to retrieve all Admins with filters with invalid endpoint
    Given Admin creates GET Request with filters with invalid endpoint
    When Admin sends HTTPS Request with filters with invalid endpoint
    Then Admin receives status 404 with Not Found error message
  
@PUT_Admin_info_401
    Scenario:  Check if admin is able to update Admin details with No authorization
    Given Admin creates PUT Request with valid data in request body with No Authorization
    When Admin sends HTTPS request with endpoiint with No Authorization
    Then Admin receives status 401 with Unauthorized message
    
    @Put_byAdmin_LoginStatus_400
  Scenario: Check if admin is able to update the Admin login status by Admin ID with invalid request body
    Given Admin creates PUT Request with Admin login status by Admin ID with invalid request body
    When Admin sends HTTPS Request for update User with Admin login status by Admin ID with invalid request body
    Then Admin receives status "400" Bad Request   
    
@Put_byAdmin_LoginStatus_404
  Scenario: Check if admin is able to update the Admin login status by Admin ID with invalid AdminId
    Given Admin creates PUT Request with Admin login status by Admin ID with invalid AdminId
    When Admin sends HTTPS Request for update User with Admin login status by Admin ID with invalid AdminId
    Then Admin receives status "404" with Not Found error message
    
@Put_byAdmin_LoginStatus_401
  Scenario: Check if admin is able to update the Admin login status by Admin ID with NoAuth
    Given Admin creates PUT Request with Admin login status by Admin ID with NoAuth
    When Admin sends HTTPS Request for update User with Admin login status by Admin ID with NoAuth
    Then Admin receives status "401" with Unauthorized message
    
    @Del_User_404
  Scenario: Check if admin is able to delete Admin with invalid Admin ID
    Given Admin creates DELETE Request with Admin with invalid Admin ID
    When Admin sends HTTPS Request for delete User with invalid Admin ID
    Then Admin receives status "404" with Not Found error message
    
@Del_User_401
  Scenario: Check if admin is able to delete the Admin with NoAuth
    Given Admin creates DELETE Request with Admin with NoAuth
    When Admin sends HTTPS Request for delete User with NoAuth
    Then Admin receives status "401" with Unauthorized message
    
@Del_User_404
   Scenario: Check if admin is able to delete the Admin with invalid endpoint
    Given Admin creates DELETE Request with Admin with invalid endpoint
    When Admin sends HTTPS Request for delete User with invalid endpoint
    Then Admin receives status "404" with Not Found error message
    
    @Del_User_405
   Scenario: Check if admin is able to delete the Admin with invalid endpoint
    Given Admin creates DELETE Request with Admin with invalid endpoint
    When Admin sends HTTPS DELETE Request for delete User with invalid endpoint
    Then Admin receives status "405" with Method Not Allowed message
