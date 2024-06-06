package api.stepdefinitions;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import api.resourses.UserModuledata;
import api.utils.IdHolder;
import api.utils.RestUtils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class UserController extends RestUtils {
	
	 Logger logger = LogManager.getLogger("UserController.java");
	 UserLogin User=new  UserLogin();
		
		UserModuledata UserModuledata= new UserModuledata(); 
		RequestSpecification request;
		ResponseSpecification responseSpec;
		Response response;
		String asString;
		public static String token;
		
		 @Given("Admin sets Authorization bearer token")
	     public void admin_sets_authorization() throws FileNotFoundException, IOException {
	    	 User.add_user_login_payload();
	    	 User.admin_calls_post_https_method_with_valid_endpoint();
	    	
	     }
	
	@Given("Admin creates a GET Request to retrieve all Admins assigned to programs or batches")
	public void admin_creates_a_get_request_to_retrieve_all_admins_assigned_to_programs_or_batches() throws FileNotFoundException {
		request=given().spec(requestSpecification()).header("Authorization","Bearer "+IdHolder.token);
	}

	@When("Admin sends HTTPS Request")
	public void admin_sends_https_request() {
		response = request
				.when().get(routes.getString("Get_AssignedProgram/Batch(es)ofAllUsers"))
				.then()
				.spec(resSpecBuilder()).statusCode(200).log().all().extract().response();
		logger.info("=========== GET Request to retrieve all Admins assigned to programs or batches=====================  ");
	    
	}

	@Then("Admin receives a {int} OK response")
	public void admin_receives_a_ok_response(Integer int1) {
		assertEquals(response.getStatusCode(),200);
	}

	@Given("Admin creates a GET Request to retrieve Admin assigned to Program or Batch by AdminId")
	public void admin_creates_a_get_request_to_retrieve_admin_assigned_to_program_or_batch_by_admin_id() throws FileNotFoundException {
		
		request=given().spec(requestSpecification()).header("Authorization","Bearer "+IdHolder.token).pathParam("userId",IdHolder.userId1 );
	}
	//U1262
	@When("Admin sends HTTPS Request to retreive assigned program batches for valid AdminId")
	public void admin_sends_https_request_to_retreive_assigned_program_batches_for_valid_admin_id() {
		response = request
				.when().get(routes.getString("Get_AssignedProgram/BatchofaUserByUserId"))
				.then()
				.spec(resSpecBuilder()).statusCode(200).log().all().extract().response();
		logger.info("=========== GET Request to retrieve Admin assigned to Program or Batch by AdminId=====================  ");
	}
	
	//("=========== GET Request Negative Scenario=====================  ");
	
	@Given("Admin creates GET Request for all Admins No Authorization")
	public void admin_creates_get_request_for_all_admins_no_authorization() throws FileNotFoundException {
		request=given().spec(requestSpecification());
	}

	@When("Admin sends HTTPS Request to retreive all Admins with assigned program batches")
	public void admin_sends_https_request_to_retreive_all_admins_with_assigned_program_batches() {
	    
		response = request
				.when().get(routes.getString("Get_AssignedProgram/Batch(es)ofAllUsers"))
				.then()
				.spec(resSpecBuilder()).statusCode(200).log().all().extract().response();
		logger.info("=========== get Request to retrieve all Admins No Authorization=====================  ");
	}

	@Then("Admin receives status {int} with a Unauthorized message")
	public void admin_receives_status_with_unauthorized_message(Integer int1) {
	
		assertEquals(response.getStatusCode(),401);
	}

	@Given("Admin creates GET Request to retrieve Admin assigned to Program Batch by invalid AdminID")
	public void admin_creates_get_request_to_retrieve_admin_assigned_to_program_batch_by_invalid_admin_id() throws FileNotFoundException {
		
		request=given().spec(requestSpecification()).header("Authorization","Bearer "+IdHolder.token).pathParam("userId",IdHolder.inactive_id );
	}

	@When("Admin sends HTTPS Request to retreive assigned program batches for invalid AdminId")
	public void admin_sends_https_request_to_retreive_assigned_program_batches_for_invalid_admin_id() {
		response = request
				.when().get(routes.getString("Get_AssignedProgram/BatchofaUserByUserId"))
				.then()
				.spec(resSpecBuilder()).statusCode(200).log().all().extract().response();
		logger.info("=========== Get HTTPS Request to retreive assigned program batches for invalid AdminId=====================  ");
	
	}

//	@Then("Admin receives status {string} with Not Found error message")
//	public void admin_receives_status_with_not_found_error_message(String string) {
//		assertEquals(response.getStatusCode(),404);
//	}

	@When("Admin sends HTTPS Request to retreive assigned program batches for valid AdminId with No authorization")
	public void admin_sends_https_request_to_retreive_assigned_program_batches_for_valid_admin_id_with_no_authorization() {
	    
		response = request
				.when().get(routes.getString("Get_AssignedProgram/BatchofaUserByUserId"))
				.then()
				.spec(resSpecBuilder()).statusCode(200).log().all().extract().response();
		logger.info("=========== GET Request to retrieve AdminId with No authorization=====================  ");
	}
	
	//Delete Usermap----------------------------------------------------------------------------------
			@Given("Admin creates DELETE Request to delete Admin assigned to program\\/batch by invalid AdminId")
			public void admin_creates_delete_request_to_delete_admin_assigned_to_program_batch_by_invalid_admin_id() throws FileNotFoundException {
				request=given().spec(requestSpecification()).header("Authorization","Bearer "+IdHolder.token).pathParam("userId",IdHolder.invalidId);
			}

			@When("Admin sends program batch HTTPS Request ")
			public void admin_sends_program_batch_https_request() {
				response = request.when()
						.delete(routes.getString("delete_AllPrograms/BatchesByUserId"))
						.then().log().all().extract().response();
			}

			@Then("Admin receives {int}")
			public void admin_receives(Integer int1) {
				assertEquals(404,response.getStatusCode());
				assertEquals(response.header("Content-Type"),"application/json");
			}

			

			@Given("Admin creates DELETE Request to delete Admin assigned to program\\/batch by valid AdminId")
			public void admin_creates_delete_request_to_delete_admin_assigned_to_program_batch_by_valid_admin_id() throws FileNotFoundException {
				request=given().spec(requestSpecification())
							.pathParam("userId",IdHolder.userId1);

			}


	
}
