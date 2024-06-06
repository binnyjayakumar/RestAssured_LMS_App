package api.stepdefinitions;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import api.pojo.CreateProgramPojo;
import api.resourses.CreateProgramData;
import api.utils.IdHolder;
import api.utils.RestUtils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class AllDeleteRequest extends RestUtils {
	
	 CreateProgramData cpd = new CreateProgramData();
     CreateProgramPojo cp = new CreateProgramPojo();
     RequestSpecification request;
     ResponseSpecification responseSpec;
     Response response;
     UserLogin User=new  UserLogin();
     Logger logger = LogManager.getLogger("DeleteRequest.java");
     
     @Given("Admin sets Authorization bearer")
     public void admin_sets_authorization() throws FileNotFoundException, IOException {
    	 User.add_user_login_payload();
    	 User.admin_calls_post_https_method_with_valid_endpoint();
    	
     }
     
  // ("=========== DELETE REQUEST(All Programs/Batches assigned to the User By UserId) =====================  ") 
     
     @Given("Admin creates DELETE Request to delete Admin assigned to program\\/batch by AdminId")
     public void admin_creates_delete_request_to_delete_admin_assigned_to_program_batch_by_admin_id() throws FileNotFoundException {
    	 request=given().spec(requestSpecification()).header("Authorization","Bearer "+IdHolder.token).pathParam("userId",IdHolder.userId2 );
     }

     @When("Admin sends HTTPS Request to delete Admin assigned to program\\/batch by AdminId")
     public void admin_sends_https_request_to_delete_admin_assigned_to_program_batch_by_admin_id() throws FileNotFoundException {
    	
    	 response = request
 				.when().get(routes.getString("delete_AllPrograms/BatchesByUserId"))
 				.then()
 				.spec(resSpecBuilder()).statusCode(200).log().all().extract().response();
     }
     
     // ("===========End of DELETE REQUEST(All Programs/Batches assigned to the User By UserId) =====================  ")   
     
  // ("======================================= DELETE REQUEST(DeleteUser) =================================  ")   
     @Given("Admin creates DELETE Request to delete Admin details")
     public void admin_creates_delete_request_to_delete_admin_details() throws FileNotFoundException {
    	 request=given().spec(requestSpecification()).header("Authorization","Bearer "+IdHolder.token).pathParam("userId",IdHolder.userId2 );
     }

     @When("Admin sends HTTPS request with valid Admin Id")
     public void admin_sends_https_request_with_valid_admin_id() {
    	 response = request
  				.when().delete(routes.getString("delete_DeleteUser"))
  				.then()
  				.spec(resSpecBuilder()).statusCode(200).log().all().extract().response();
     }

     @Then("Admin receives {int} Ok")
     public void admin_receives_ok(Integer int1) {
    	  
    	 assertEquals(response.getStatusCode(),200);
     }

  // ("====================================End of DELETE REQUEST(DeleteUser) ========================================  ")   
     
//   //(========================================= DELETE Batch by valid BatchId=============================================)
//		@Given("Admin creates DELETE Request with valid BatchId")
//		public void admin_creates_delete_request_with_valid_batch_id() throws FileNotFoundException {
//			request = given().spec(requestSpecification());
//		}
//
//		@When("Admin sends HTTPS Request  with endpoint for delete batch")
//		public void delete_batch_by_batchID() {
//			String endpoint = routes.getString("Delete_Batch");
//			response = request.header("Authorization", "Bearer " +IdHolder.token).pathParam("id",IdHolder.batchId)
//					.delete(endpoint).then().log().all().extract().response();
//		}
//
////		@Then("Admin receives {int} Ok status with message for delete batch.")
////		public void admin_receives_ok_status_with_message_for_delete_batch(int actualStatusCode) {
////			assertEquals(response.getStatusCode(), actualStatusCode);
////		}
//  
     
     
//    // ("======================== DELETE REQUEST(by program Name) ========================================================  ")
//     
//	@Given("Admin creates DELETE Request for the LMS API endpoint  and  valid programName")
// 	public void admin_creates_delete_request_for_the_lms_api_endpoint_and_valid_program_name() throws FileNotFoundException 
// 	{
// 		request=given().spec(requestSpecification());
// 		
// 		 logger.info("=========== DELETE REQUEST(by program Name) =====================  ");
// 	}
//
// 	@When("Admin sends HTTPS Request with endpoint to delete a program with program name")
// 	public void admin_sends_https_request_with_endpoint_to_delete_a_program_with_program_name() 
// 	{
// 		response = request.header("Authorization", "Bearer " +IdHolder.token)
// 				.when().delete(routes.getString("Delete_ProgramByProgramName")+IdHolder.programName).then().log().all().extract().response();
//
// 		 logger.info("=========== DELETE request to delete a program by program name=====================  ");
// 	
// 	}
//
// 	@Then("Admin receives {int} Ok status with message")
// 	public void admin_receives_ok_status_with_message(int statuscode) 
// 	{
// 		assertEquals(statuscode, response.getStatusCode());
// 	}
// 	
// 	@Given("Admin creates DELETE Request for the LMS API endpoint  and  valid program ID")
// 	public void admin_creates_delete_request_for_the_lms_api_endpoint_and_valid_program_id() throws FileNotFoundException 
// 	{
// 		request=given().spec(requestSpecification());
// 		
// 		 logger.info("=========== DELETE REQUEST(by program ID) =====================  ");
// 	}
//
// 	@When("Admin sends HTTPS Request with endpoint to delete a program with program ID")
// 	public void admin_sends_https_request_with_endpoint_to_delete_a_program_with_program_id() 
// 	{
// 		response = request.header("Authorization", "Bearer " +IdHolder.token)
// 				.when().delete(routes.getString("Delete_ProgramByProgramId")+IdHolder.programId).then().log().all().extract().response();
// 		
// 		 logger.info("=========== DELETE request to delete program by program ID =====================  ");
//
// 	}
//
// 	@Then("Admin receives {int} Ok and validates the status")
// 	public void admin_receives_ok_and_validates_the_status(int statuscode) 
// 	{
// 		assertEquals(statuscode, response.getStatusCode());
// 	}
//   // ("=========== End of DELETE REQUEST(by program Name) =====================  ")

}
