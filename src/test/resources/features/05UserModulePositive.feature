@tag05
Feature: User Module Positive request 
Background: 
Given Admin sets Authorization token


#PostRequest_Positive
@Test05
  Scenario: Check if admin is able to create a new Admin with valid endpoint and request body with mandatory fields 
    Given Admin creates POST request with all mandatory fields 
    When Admin sends HTTPS Request with endpoint
   Then Admin receives 201 Created Status with response body. 
   
   
  # @User_Post_02  
  Scenario: Check if admin able to create a new Admin with valid endpoint and request body with mandatory and additional fields
    Given Admin creates POST request with all mandatory fields and an additional fields
    When  Admin sends POST HTTPS Request with mandatory and additional fields
    Then  Admin receives 201 Created Status
    
    # @User_Get_all_roles
  Scenario: Check if admin is able to retreive all the available  roles(all roles)
     Given Admin creates GET Request
     When  Admin sends HTTPS Request with GET All Roles endpoint
     Then  Admin receives 200 OK
     
 #@User_Get_all_users
   Scenario: Check if admin able to retrieve all Admin with valid endpoint(all users)
     Given Admin creates GET Request
     When  Admin sends HTTPS Request with valid endpoint
     Then  Admin receives 200 OK 
     
   #@User_Get_User_byUserId      
   Scenario: Check if admin able to retrieve a Admin with valid Admin ID
     Given Admin creates GET Request with valid AdminId
     When  Admin sends HTTPS Request with a valid endpoint
     Then  Admin receives 200 OK 
     
   # @User_Get_alluserswithroles                                                                     
   Scenario: Check if admin able to retrieve all Admins with roles
     Given  Admin creates GET Request 
     When   Admin sends HTTPS Request with an endpoint
     Then   Admin receives 200 OK 
    
   
  #GetRequest_Positive(Gets count of active and inactive users)
   Scenario: Check if admin is able to get count of active and inactive Admins 
    Given  Admin creates GET Request with active and inactive Admins endpoint
    When Admin sends Get count of active and inactive users  HTTPS Request with endpoint
    Then Admin receives 200 OK
  
   
#GetRequest_Positive(All Active Users )
  Scenario: Check if admin able to retrieve all active Admins
    Given  Admin creates GET Request 
    When Admin sends Get All Active Users HTTPS Request with endpoint
    Then Admin receives 200 OK
    
   
# UpdateRequest_Positive(Assign Update User Role Program Batch Status)
    Scenario: Check if admin is able to assign Admin to with program/batch by Admin Id	
	Given Admin creates PUT Request with valid data in request body 
		When Admin sends PUT HTTPS Request with User Role Program Batch Status endpoint	
		Then  Admin receives 200 OK     
   
#GetRequest_Positive(Get User by Program Batches)
  Scenario: Check if admin is able to get the Admins by program batches for valid batch ID
    Given  Admin creates GET Request with valid batch Id
    When Admin sends Get User by Program Batches HTTPS Request with endpoint
    Then Admin receives 200 OK  
    
   
#GetRequest_Positive(Gets Users for Program)
  Scenario: Check if admin is able to get the Admins for valid program Id
    Given  Admin creates GET Request with valid program Id
    When Admin sends Gets Users for Program HTTPS Request with endpoint
    Then  Admin receives 200 OK
    
    
# GetRequest_Positive(Gets Users by roleId)
  Scenario: Check if admin is able to retreive Admins by valid role ID
    Given  Admin creates GET Request with valid role ID 
    When Admin sends Gets Users by roleId HTTPS Request with endpoint
    Then  Admin receives 200 OK
    
    
  # User_Get_V2Filter
  Scenario: Check if admin is able to retrieve all Admins with filters
    Given Admin creates GET Request with filters
    When Admin sends HTTPS Request with filters endpoint
    Then Admin receives "200" OK

 
 #User_Put_byUserId
  Scenario: Check if admin is able to update Admin details with Admin id and mandatory fields
    Given Admin creates PUT Request with valid data in request body (values only in mandatory fields) 
    When Admin sends HTTPS request for update by userId with endpoiint
    Then Admin receives "200" OK
    
 
 #User_Put_byRoleStatus
  Scenario: Check if admin is able to update role status of a Admin with valid Admin id
    Given Admin creates PUT Request with valid user data in request body 
    When Admin sends HTTPS Request for update by roleStatus with endpoint
    Then Admin receives "200" OK
    
   #@User_Put_byRoleId
  Scenario: Check if admin is able to update User by roleId with valid Admin id
    Given Admin creates PUT Request with valid user data to with roleID 
    When Admin sends HTTPS Request for update User by roleID 
    Then Admin receives "200" OK
    
  #@User_Put_byAdmin_LoginStatus
  Scenario: Check if admin is able to update the Admin login status by Admin ID
    Given Admin creates PUT Request with Admin login status by Admin ID 
    When Admin sends HTTPS Request for update User with Admin login status by Admin ID
    Then Admin receives "200" OK
                                   