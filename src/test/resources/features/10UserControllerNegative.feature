@tag10

Feature: Negative scenario for User Controller
Background: 
  Given Admin sets Authorization bearer token

   #GET REQUEST(get all Admins with program/batch)
 @Test10
    Scenario: Check if admin is able to retreive all Admins with assigned program batches with No Authorization	
    Given Admin creates GET Request for all Admins No Authorization
    When Admin sends HTTPS Request to retreive all Admins with assigned program batches
    Then Admin receives status 401 with a Unauthorized message
    
    #GET REQUEST(get  program/batch details for Admin)
   Scenario:Check if admin is able to retreive assigned program batches for invalid AdminId	
    Given Admin creates GET Request to retrieve Admin assigned to Program Batch by invalid AdminID	
    When Admin sends HTTPS Request to retreive assigned program batches for invalid AdminId	
    Then Admin receives status "404" with Not Found error message
    
   Scenario:Check if admin is able to retreive assigned program batches for valid AdminId with No authorization	
	 Given Admin creates GET Request for all Admins No Authorization
	When Admin sends HTTPS Request to retreive assigned program batches for valid AdminId with No authorization
	Then Admin receives status 401 with Unauthorized message
	
	#@deleteuserMap
  Scenario: Check if admin is able to delete the program batch for invalid Admin
    Given Admin creates DELETE Request to delete Admin assigned to program/batch by invalid AdminId
    When Admin sends HTTPS Request 
    Then Admin receives 404
    
  @tag2
  Scenario: Check if admin is able to delete the program batch for valid Admin and No Authorization
    Given Admin creates DELETE Request to delete Admin assigned to program/batch by valid AdminId
    When Admin sends program batch HTTPS Request 
    Then Admin receives status 401 with Unauthorized message
	
	
	