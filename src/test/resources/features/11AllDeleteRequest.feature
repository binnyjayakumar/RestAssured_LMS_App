 @tag11
 Feature: Delete Request for all modules
 Background: 
  Given Admin sets Authorization bearer

   #DELETE REQUEST(DELETE REQUEST(Delete UserController  program/batch assigned to a Admin))
  @Test11
    Scenario: Check if admin is able to delete the program batch for a Admin	
    Given Admin creates DELETE Request to delete Admin assigned to program/batch by AdminId	
    When Admin sends HTTPS Request to delete Admin assigned to program/batch by AdminId		
    Then Admin receives 200 OK
    
     #DELETE REQUEST(Delete User)
    Scenario: Check if Admin able to delete a Admin with valid Admin Id
    Given Admin creates DELETE Request to delete Admin details	
    When Admin sends HTTPS request with valid Admin Id
	  Then Admin receives 200 Ok 
	  
	    #Positive - DELETE REQUEST (Delete Batch by batchId)
	 Scenario: Check if admin able to delete a Batch with valid Batch ID
		Given Admin creates DELETE Request with invalid BatchId
		When Admin sends HTTPS Request  with endpoint for delete batch 
	  Then Admin receives 200 Ok status with message for delete batch.
	
	  
    #DELETE REQUEST(Delete Program by Program Name)
  Scenario: Check if Admin able to delete a program with valid programName
    Given Admin creates DELETE Request for the LMS API endpoint  and  valid programName
    When Admin sends HTTPS Request with endpoint to delete a program with program name
    Then Admin receives 200 Ok status with message 

 # DELETE REQUEST(Delete Program by Program ID)
  Scenario: Check if Admin able to delete a program with valid program ID
    Given Admin creates DELETE Request for the LMS API endpoint  and  valid program ID
    When Admin sends HTTPS Request with endpoint to delete a program with program ID
    Then Admin receives 200 Ok and validates the status
    
  