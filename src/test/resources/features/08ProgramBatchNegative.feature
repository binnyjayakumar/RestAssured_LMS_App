@tag08
@programBatchWithAuthNegative
Feature: Program Batch module  
  Background: 
  Given Admin set Authorization
  
  #Negative - GET REQUEST (All Batches)
  @Test08
	Scenario: Check if admin able to retrieve all batches with invalid Endpoint
		Given Admin creates GET Request of program batch of all batches
		When Admin sends HTTPS GET Request with invalid endpoint to retrieve all batches
		Then Admin receives 404 status with error message Not Found. 
	
  #Negative - GET REQUEST (By Batch ID)
	Scenario: Check if admin able to retrieve a batch with invalid endpoint
		Given Admin creates GET Request with valid Batch ID
		When Admin sends HTTPS Request with invalid endpoint to retrieve batch by batchId
		Then Admin receives 404 status with error message Not Found. 
   
 	#Negative - GET REQUEST (By Batch Name)
	Scenario: Check if admin able to retrieve a batch with invalid endpoint
		Given Admin creates GET Request with valid batch name
		When Admin sends HTTPS Request with invalid endpoint to retrieve batch by batchName
		Then Admin receives 404 status with error message Not Found. 
		
	#Negative - GET REQUEST (By Program Id)
	Scenario: Check if admin able to retrieve a batch with invalid endpoint
		Given Admin creates GET Request with valid Program Id
		When Admin sends HTTPS Request with invalid endpoint to retrieve batch by programid
		Then Admin receives 404 status with error message Not Found. 
		
	#Negative - PUT REQUEST (Update Batch by batchId)
	Scenario: Check if admin able to update a Batch with invalid endpoint
		Given Admin creates PUT Request with valid BatchId and Data
		When Admin sends PUT HTTPS Request  with invalid endpoint
		Then Admin receives 404 status with error message Not Found. 
		
	#Negative - DELETE REQUEST (Delete Batch by batchId)
	Scenario:Check if admin able to delete a Batch with invalid endpoint
		Given Admin creates DELETE Request with valid BatchId
		When Admin sends HTTPS Request  with invalid endpoint for delete batch 
	  Then Admin receives 404 status with error message Not Found. 	
	                                  	

	
	#Negative - POST REQUEST (valid endpoint and existing batch name)
	Scenario: Check if admin able to create a Batch with valid endpoint and request body (existing value in Batch Name)
		Given Admin creates POST Request  with existing value in request body 
		When Admin sends POST HTTPS Request with endpoint  
	  Then Admin receives 400 Bad Request Status with message and boolean success details. 	

	#Negative - POST REQUEST (missing mandatory feild)
	Scenario: Check if admin able to create a Batch missing mandatory fields in request body
		Given Admin creates POST Request  with invalid data missing mandatory feild in request body 
		When Admin sends POST HTTPS Request with endpoint  
	  Then Admin receives 400 Bad Request Status with message and boolean success details. 	
	
	#Negative - POST REQUEST (invalid data)
	Scenario: Check if admin able to create a batch with invalid data in request body
		Given Admin creates POST Request  with invalid data in request body 
		When Admin sends POST HTTPS Request with endpoint  
	  Then Admin receives 400 Bad Request Status with message and boolean success details. 
	  
		
	#Negative - POST REQUEST (inactive program id)
	Scenario: Check if admin able to create a batch  with inactive program ID
		Given Admin creates POST Request with inactive program id 
		When Admin sends POST HTTPS Request with endpoint 
	  Then Admin receives 400 Bad Request Status with message and boolean success details. 
	  
	#Negative - GET REQUEST (invalid batch id)
	Scenario: Check if admin able to retrieve a batch with invalid BATCH ID
		Given Admin creates GET Request with invalid Batch ID 
		When Admin sends GET HTTPS Request with endpoint with invalid batch id
	  Then Admin receives 404 Not Found Status with message and boolean success details

	#Negative - GET REQUEST (invalid batch name)
	Scenario: Check if admin able to retrieve a batch with invalid BATCH Name
		Given Admin creates GET Request with invalid Batch Name 
		When Admin sends GET HTTPS Request with endpoint with invalid batch name
	  Then Admin receives 404 Not Found Status with message and boolean success details
		
	#Negative - GET REQUEST (invalid program id)
	Scenario: Check if admin able to retrieve a batch with invalid Program Id
		Given Admin creates GET Request with invalid Program Id 
		When Admin sends GET HTTPS Request with endpoint with invalid program id
	  Then Admin receives 404 Not Found Status with message and boolean success details
	  
	#Negative - PUT REQUEST (Put update Batch by batchId invalid batchId and valid data)
	Scenario: Check if admin able to update a Batch with invalid batchID and mandatory fields in request body
		Given Admin creates PUT Request with invalid BatchId and valid Data
		When Admin sends PUT HTTPS Request endpoint with invalid BatchId and valid Data in request body
	  Then Admin receives 404 Not Found Status with message and boolean success details	  	
	 
	#Negative - PUT REQUEST (Put update Batch by batchId invalid data)
	Scenario: Check if admin able to update a Batch with invalid batchID and missing mandatory fields request body
		Given Admin creates PUT Request with invalid data
		When Admin sends PUT HTTPS Request  with endpoint with invalid data
	  Then Admin receives 400 Bad Request Status with message and boolean success details.	
	   	 	
#	#Negative - DELETE REQUEST (Delete Batch by batchId)
#	Scenario: Check if admin able to delete a Batch with invalid Batch ID
#		Given Admin creates DELETE Request with invalid BatchId
#		When Admin sends DELETE HTTPS Request with endpoint 
#	  Then Admin receives 404 Not Found Status with message and boolean success details
#	  	
	
	  
	 
	  
	
