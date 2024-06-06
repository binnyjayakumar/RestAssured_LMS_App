 @tag06
 Feature: User Controller Positive
 Background: 
  Given Admin sets Authorization bearer token
  
   #Get Assigned Program/Batch of a User By User Id
   @Test06
     Scenario: admin is able to retreive assigned program batches for valid AdminId
     Given Admin creates a GET Request to retrieve Admin assigned to Program or Batch by AdminId
     When Admin sends HTTPS Request to retreive assigned program batches for valid AdminId
    Then Admin receives a 200 OK response

  #Get Assigned Program/Batch(es) of All Users
   Scenario: admin is able to retreive all Admins with assigned program batches
    Given Admin creates a GET Request to retrieve all Admins assigned to programs or batches
    When Admin sends HTTPS Request
    Then Admin receives a 200 OK response
    
   
