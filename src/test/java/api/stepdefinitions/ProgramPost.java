package api.stepdefinitions;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import api.pojo.CreateProgramPojo;
import api.resourses.CreateProgramData;
import api.resourses.UpdateProgramByProgramId;
import api.resourses.UpdateProgramByProgramName;
import api.utils.IdHolder;
import api.utils.RestUtils;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class ProgramPost extends RestUtils {
	
	
	CreateProgramData cpd = new CreateProgramData();
	CreateProgramPojo cp = new CreateProgramPojo();
	UpdateProgramByProgramId upp = new UpdateProgramByProgramId();
	UpdateProgramByProgramName uppn = new UpdateProgramByProgramName();

	RequestSpecification request;
	ResponseSpecification responseSpec;
	Response response;
	Logger logger = LogManager.getLogger("ProgramPost.java");
   UserLogin User=new  UserLogin();
     
      
     @Given("Admin sets Authorization")
     public void admin_sets_authorization() throws FileNotFoundException, IOException {
    	 User.add_user_login_payload();
    	 User.admin_calls_post_https_method_with_valid_endpoint();
    	
     }
	//<---------------Post Request--------------->
	@Given("Admin creates POST Request for the LMS with request body")
	public void admin_creates_post_request_for_the_lms_with_request_body() throws FileNotFoundException, IOException 
	{   

		request=given().spec(requestSpecification());
		 logger.info("=========== POST request =====================  ");
	}


	@When("Admin sends HTTPS Request and  request Body with endpoint")
	public void admin_sends_https_request_and_request_body_with_endpoint() throws IOException
	{
		cp = cpd.dataBuild();
		response = request.body(cp).header("Authorization", "Bearer " +IdHolder.token)
				.when().post(routes.getString("Post_Createprogram")).then().log().all().extract().response();

		int i = Integer.parseInt(UserKeyJson(response,"programId"));
		System.out.println(i);
		IdHolder.programId = i;

		IdHolder.programName = UserKeyJson(response,"programName");
		
		logger.info("===========  POST request with all mandatory fields =====================  ");	
	}

	@Then("Admin receives {int} Created Status with response body")
	public void admin_receives_created_status_with_response_body(int statuscode) 
	{
		assertEquals(statuscode, response.getStatusCode());

		response
		.then()
		.assertThat().body(matchesJsonSchemaInClasspath("Schema/ProgramModule/CreateProgram.json"));

		assertEquals(UserKeyJson(response,"programName"),cp.getProgramName());
	}

	@When("Admin sends HTTPS Request without authorization and request Body with endpoint")
	public void admin_sends_https_request_without_authorization_and_request_body_with_endpoint() throws IOException 
	{
		cp = cpd.dataBuild1();
		response = request.body(cp)
				.when().post(routes.getString("Post_Createprogram")).then().log().all().extract().response();
		
		logger.info("===========  POST request with no auth =====================  ");	
	}

	@Then("Admin receives {int} Unauthorized")
	public void admin_receives_unauthorized(int statuscode) 
	{
		assertEquals(statuscode, response.getStatusCode());
	}

	@When("Admin sends HTTPS Request with invalid endpoint and request Body with endpoint")
	public void admin_sends_https_request_with_invalid_endpoint_and_request_body_with_endpoint() throws IOException
	{
		cp = cpd.dataBuild2();
		response = request.body(cp).header("Authorization", "Bearer " +IdHolder.token)
				.when().post(routes.getString("Post_Createprogram_invalid_endpoint")).then().log().all().extract().response();
		
		logger.info("===========  POST request with invalid endpoint =====================  ");	
	}

	@Then("Admin receives {int} not found  Status with message and boolean success details")
	public void admin_receives_not_found_status_with_message_and_boolean_success_details(int statuscode)
	{
		assertEquals(statuscode, response.getStatusCode());
	}

	@When("Admin sends HTTPS Request with already existing program name and request Body with endpoint")
	public void admin_sends_https_request_with_already_existing_program_name_and_request_body_with_endpoint() throws IOException 
	{
		cp = cpd.dataBuild3();
		response = request.body(cp).header("Authorization", "Bearer " +IdHolder.token)
				.when().post(routes.getString("Post_Createprogram")).then().log().all().extract().response();
		
		logger.info("===========  POST request with existing program name =====================  ");	
	}

	@Then("Admin receives {int} Bad Request Status with message and boolean success details")
	public void admin_receives_bad_request_status_with_message_and_boolean_success_details(int statuscode) 
	{
		assertEquals(statuscode, response.getStatusCode());
	}

	@When("Admin sends HTTPS Request with invalid method and request Body with endpoint")
	public void admin_sends_https_request_with_invalid_method_and_request_body_with_endpoint() throws IOException 
	{
		cp = cpd.dataBuild4();
		response = request.body(cp).header("Authorization", "Bearer " +IdHolder.token)
				.when().get(routes.getString("Post_Createprogram")).then().log().all().extract().response();
		
		logger.info("===========  POST request with invalid method =====================  ");	
	}

	@Then("Admin receives {int} Method Not Allowed")
	public void admin_receives_method_not_allowed(int statuscode) 
	{
		assertEquals(statuscode, response.getStatusCode());
	}

	@When("Admin sends HTTPS Request with invalid request body and request Body with endpoint")
	public void admin_sends_https_request_with_invalid_request_body_and_request_body_with_endpoint() throws IOException 
	{
		cp = cpd.dataBuild5();
		response = request.body(cp).header("Authorization", "Bearer " +IdHolder.token)
				.when().post(routes.getString("Post_Createprogram")).then().log().all().extract().response();
		
		logger.info("===========  POST request with invalid request body =====================  ");	
	}

	@Then("Admin receives {int} Bad Request Status")
	public void admin_receives_bad_request_status(int statuscode) 
	{
		assertEquals(statuscode, response.getStatusCode());
	}

	@When("Admin sends HTTPS Request with missing values and request Body with endpoint")
	public void admin_sends_https_request_with_missing_values_and_request_body_with_endpoint() throws IOException 
	{
		cp = cpd.dataBuild6();
		response = request.body(cp).header("Authorization", "Bearer " +IdHolder.token)
				.when().post(routes.getString("Post_Createprogram")).then().log().all().extract().response();
		
		logger.info("===========  POST request with missing values in request body =====================  ");	
	}

	@Then("Admin receives {int} Bad Request Status code")
	public void admin_receives_bad_request_status_code(int statuscode) 
	{
		assertEquals(statuscode, response.getStatusCode());
	}

	@When("Admin sends HTTPS Request with missing additional field and request Body with endpoint")
	public void admin_sends_https_request_with_missing_additional_field_and_request_body_with_endpoint() throws IOException 
	{
		cp = cpd.dataBuild7();
		response = request.body(cp).header("Authorization", "Bearer " +IdHolder.token)
				.when().post(routes.getString("Post_Createprogram")).then().log().all().extract().response();
		
		logger.info("===========  POST request with missing additional field =====================  ");	
	}

	@Then("Admin receives {int} Bad Request")
	public void admin_receives_bad_request(int statuscode) 
	{
		assertEquals(statuscode, response.getStatusCode());
	}
	
	//<---------------Get Request(All programs)--------------->
	
	@Given("Admin creates GET Request for the LMS API")
	public void admin_creates_get_request_for_the_lms_api() throws FileNotFoundException 
	{
		request=given().spec(requestSpecification());
		
		logger.info("=========== GET request(All programs) =====================  ");
	}
	
	@When("Admin sends HTTPS Request with a endpoint to retrieve all programs")
	public void admin_sends_https_get_request_with_a_endpoint_to_get_all_programs() {
	   
		response = request.header("Authorization", "Bearer " +IdHolder.token)
				.when().get(routes.getString("Get_AllPrograms")).then().log().all().extract().response();
		
		logger.info("=========== GET request with endpoint =====================  ");
	}


//	@When("Admin sends HTTPS Request with endpoint to retrieve all programs")
//	public void admin_sends_https_request_with_endpoint() 
//	{
//		response = request.header("Authorization", "Bearer " +IdHolder.token)
//				.when().get(routes.getString("Get_AllPrograms")).then().log().all().extract().response();
//		
//		logger.info("=========== GET request with endpoint =====================  ");
//	}

	@Then("Admin receives {int} OK Status with response body.")
	public void admin_receives_ok_status_with_response_body(int statuscode) 
	{
		assertEquals(statuscode, response.getStatusCode());
	}
	
	@When("Admin sends HTTPS Request with invalid Endpoint")
	public void admin_sends_https_request_with_invalid_endpoint() 
	{
		response = request.header("Authorization", "Bearer " +IdHolder.token)
				.when().get(routes.getString("Get_AllPrograms_invalid_endpoint")).then().log().all().extract().response();
		
		logger.info("=========== GET request with invalid endpoint =====================  ");
	}

	@Then("Admin receives {int} not found  Status with error message")
	public void admin_receives_not_found_status_with_error_message(int statuscode) 
	{
		assertEquals(statuscode, response.getStatusCode());
	}
	
	@When("Admin sends HTTPS Request with invalid Method and endpoint")
	public void admin_sends_https_request_with_invalid_method_and_endpoint() 
	{
		response = request.header("Authorization", "Bearer " +IdHolder.token)
				.when().post(routes.getString("Get_AllPrograms")).then().log().all().extract().response();
		
		logger.info("=========== GET request with invalid method =====================  ");
	}

	@Then("Admin receives {int} Method Not Allowed status code")
	public void admin_receives_method_not_allowed_status_code(int statuscode) 
	{
		assertEquals(statuscode, response.getStatusCode());
	}
	
	@When("Admin sends HTTPS Request without Authorization with endpoint")
	public void admin_sends_https_request_without_authorization_with_endpoint() 
	{
		response = request
				.when().get(routes.getString("Get_AllPrograms")).then().log().all().extract().response();
		
		logger.info("=========== GET request with no auth =====================  ");
	}

	@Then("Admin receives {int} Status with response body as Unauthorized")
	public void admin_receives_status_with_response_body_as_unauthorized(int statuscode) 
	{
		assertEquals(statuscode, response.getStatusCode());
	}
	
	//<----------- Get Request(Program ID) --------------->
	
	@Given("Admin creates GET Request for the LMS API to retrieve a program by valid program ID")
	public void admin_creates_get_request_for_the_lms_api_to_retrieve_a_program_by_valid_program_id() throws FileNotFoundException 
	{
		request=given().spec(requestSpecification());
		
		logger.info("=========== GET request(Program ID) =====================  ");
	}

	@When("Admin sends HTTPS Request with endpoint and stores the response")
	public void admin_sends_https_request_with_endpoint_and_stores_the_response()
	{
		response = request.header("Authorization", "Bearer " +IdHolder.token)
				.when().get(routes.getString("Get_ProgramByProgramId")+IdHolder.programId).then().log().all().extract().response();

	}

	@Then("Admin receives {int} OK Status code with response body.")
	public void admin_receives_ok_status_code_with_response_body(int statuscode) 
	{
		assertEquals(statuscode, response.getStatusCode());
		
		response
		.then()
	    .assertThat().body(matchesJsonSchemaInClasspath("Schema/ProgramModule/CreateProgram.json"));
	}
	
	@Given("Admin creates GET Request for the LMS API with invalid program ID")
	public void admin_creates_get_request_for_the_lms_api_with_invalid_program_id() throws FileNotFoundException 
	{
		request=given().spec(requestSpecification());
	}
	@When("Admin sends HTTPS Request with invalid program ID and endpoint")
	public void admin_sends_https_request_with_invalid_program_id_and_endpoint() 
	{
		response = request.header("Authorization", "Bearer " +IdHolder.token)
				.when().get(routes.getString("Get_ProgramByProgramId_invalid_programid")).then().log().all().extract().response();
		
		logger.info("=========== GET request - Program ID - with invalid program ID =====================  ");
	}

//	@Then("Admin receives {int} Not Found Status with message and boolean success details")
//	public void admin_receives_not_found_with_message_and_boolean_success_details(int statuscode) 
//	{
//		assertEquals(statuscode, response.getStatusCode());
//	}
	
	@Given("Admin creates GET Request with invalid baseURI for the LMS API")
	public void admin_creates_get_request_with_invalid_base_uri_for_the_lms_api() 
	{
		request=given().baseUri(routes.getString("invalid_baseUrl"));
	}

	@When("Admin sends HTTPS Request with invalid baseURI endpoint")
	public void admin_sends_https_request_with_invalid_base_uri_endpoint()
	{
		response = request.header("Authorization", "Bearer " +IdHolder.token)
				.when().get(routes.getString("Get_ProgramByProgramId")).then().log().all().extract().response();
		
		logger.info("=========== GET request - Program ID - with invalid BaseURI =====================  ");
	}

	@Then("Admin receives {int} Not Found Status code with message and boolean success details")
	public void admin_receives_not_found_status_code_with_message_and_boolean_success_details(int statuscode) 
	{
		assertEquals(statuscode, response.getStatusCode());
	}
	
	@Given("Admin creates GET Request for the LMS API to retrieve a program without Authorization")
	public void admin_creates_get_request_for_the_LMS_API_to_retrieve_a_program_without_Authorization() throws FileNotFoundException 
	{
		request=given().spec(requestSpecification());
	}
	
	@When("Admin sends HTTPS Request to retrieve a program without Authorization with endpoint")
	public void admin_sends_https_request_to_retrieve_a_program_without_authorization_with_endpoint() 
	{
		response = request
				.when().get(routes.getString("Get_ProgramByProgramId")).then().log().all().extract().response();
	
		logger.info("=========== GET request - Program ID - with no auth =====================  ");
	}

	@Then("Admin receives {int} Unauthorized status code")
	public void admin_receives_unauthorized_status_code(int statuscode) 
	{
		assertEquals(statuscode, response.getStatusCode());
	}
	
	@Given("Admin creates GET Request for the LMS API to retrieve a program with invalid endpoint")
	public void admin_creates_get_request_for_the_LMS_API_to_retrieve_a_program_with_invalid_endpoint() throws FileNotFoundException 
	{
		request=given().spec(requestSpecification());
	}
	
	@When("Admin sends HTTPS Request to retrieve a program with invalid Endpoint")
	public void admin_sends_https_request_to_retrieve_a_program_with_invalid_endpoint() 
	{
		response = request.header("Authorization", "Bearer " +IdHolder.token)
				.when().get(routes.getString("Get_ProgramByProgramId_invalid_endpoint")).then().log().all().extract().response();
	
		logger.info("=========== GET request - Program ID - with invalid endpoint =====================  ");
	}

	@Then("Admin receives {int} Not Found status code with message and boolean success details")
	public void admin_receives_Not_found_status_code_with_message_and_boolean_success_details(int statuscode)
	{
		assertEquals(statuscode, response.getStatusCode());
	}
	
	//<----------- Get Request(All Programs with users) --------------->
	
	@Given("Admin creates GET Request for the LMS API retrieve all programs with users")
	public void admin_creates_get_request_for_the_lms_api_retrieve_all_programs_with_users() throws FileNotFoundException
	{
		request=given().spec(requestSpecification());
	}

	@When("Admin sends HTTPS Request with valid endpoint and stores the response")
	public void admin_sends_https_request_with_valid_endpoint_and_stores_the_response() 
	{
		response = request.header("Authorization", "Bearer " +IdHolder.token)
				.when().get(routes.getString("Get_AllProgramsWithUsers")).then().log().all().extract().response();
		
		logger.info("=========== GET request - All programs with users =====================  ");
	}

	@Then("Admin receives {int} OK Status with response body and asserts the response.")
	public void admin_receives_ok_status_with_response_body_and_asserts_the_response(int statuscode) 
	{
		assertEquals(statuscode, response.getStatusCode());
	}
	
	@Given("Admin creates GET request for the LMS API")
	public void admin_creates_get_request_for_the_LMS_api() throws FileNotFoundException 
	{
		request=given().spec(requestSpecification());
	}

	
	@When("Admin sends HTTPS Request with a invalid endpoint")
	public void admin_sends_https_request_with_a_invalidEndpoint() 
	{
		response = request.header("Authorization", "Bearer " +IdHolder.token)
				.when().get(routes.getString("Get_AllProgramsWithUsers_invalid_endpoint")).then().log().all().extract().response();

		logger.info("=========== GET request - All programs with users - with invalid endpoint =====================  ");
	}

	@Then("Admin receives {int} not found status with error message")
	public void admin_receives_not_found_status_with_errorMessage(int statuscode) 
	{
		assertEquals(statuscode, response.getStatusCode());
	}
	
	@When("Admin sends HTTPS Request to retrieve all programs with invalid Method and endpoint")
	public void admin_sends_https_request_to_retrieve_all_programs_with_invalid_method_and_endpoint() 
	{
		response = request.header("Authorization", "Bearer " +IdHolder.token)
				.when().post(routes.getString("Get_AllProgramsWithUsers")).then().log().all().extract().response();
		
		logger.info("=========== GET request - All programs with users - with invalid method =====================  ");
	}

	@Then("Admin receives {int} Method Not Allowed Status Code")
	public void admin_receives_method_not_allowed_statusCode(int statuscode)
	{
		assertEquals(statuscode, response.getStatusCode());
	}

	@When("Admin sends HTTPS Request with endpoint to retrieve all programs without Authorization")
	public void admin_sends_https_request_with_endpoint_to_retrieve_all_programs_without_authorization() 
	{
		response = request
				.when().get(routes.getString("Get_AllProgramsWithUsers")).then().log().all().extract().response();
		
		logger.info("=========== GET request - All programs with users - with no auth =====================  ");
	}

	@Then("Admin receives {int} status code with response body as Unauthorized")
	public void admin_receives_status_code_with_response_body_as_unauthorized(int statuscode) 
	{
		assertEquals(statuscode, response.getStatusCode());
	}
	
	//<--------------- PUT request(Update program by program ID) --------------->
	
	@Given("Admin creates PUT Request for the LMS API endpoint with request Body with mandatory, additional fields.")
	public void admin_creates_put_request_for_the_lms_api_endpoint_with_request_body_with_mandatory_additional_fields() throws FileNotFoundException 
	{
		request=given().spec(requestSpecification());
	}

	@When("Admin sends HTTPS Request with valid endpoint and recieves the response")
	public void admin_sends_https_request_with_valid_endpoint_and_recieves_the_response() throws IOException
	{
		response = request.body(upp.dataBuild()).header("Authorization", "Bearer " +IdHolder.token)
				.when().put(routes.getString("Update_ProgramByProgramId")+IdHolder.programId).then().log().all().extract().response();

		IdHolder.programName = UserKeyJson(response,"programName"); 
		
		logger.info("=========== PUT request - Update by program ID - valid endpoint =====================  ");
	}

	@Then("Admin receives {int} OK Status code with updated value.")
	public void admin_receives_ok_status_with_updated_value_in_response_body(int statuscode) 
	{
		assertEquals(statuscode, response.getStatusCode());
		
		response
		.then()
	    .assertThat().body(matchesJsonSchemaInClasspath("Schemas/CreateProgram.json"));
		
	}
	
	@Given("Admin creates PUT Request for the LMS API endpoint with valid request Body with mandatory , additional  fields.")
	public void admin_creates_put_request_for_the_lms_api_endpoint_with_valid_request_body_with_mandatory_additional_fields() throws FileNotFoundException 
	{
		request=given().spec(requestSpecification());
	}

	@When("Admin sends HTTPS Request to update a program with invalid programID with invalid endpoint")
	public void admin_sends_https_request_to_update_a_program_with_invalid_program_id_with_invalid_endpoint() throws IOException 
	{
		response = request.body(upp.dataBuild1()).header("Authorization", "Bearer " +IdHolder.token)
				.when().put(routes.getString("Update_ProgramByProgramId_invalid_programid")).then().log().all().extract().response();

		logger.info("=========== PUT request - Update by program ID - invalid programID =====================  ");
	}

	@Then("Admin receives {int} Not Found status with message and boolean success details")
	public void admin_receives_not_found_with_message(int statuscode) 
	{
		assertEquals(statuscode, response.getStatusCode());
	}
	
	@Given("Admin creates PUT Request for the LMS API  with invalid request body")
	public void admin_creates_put_request_for_the_lms_api_with_invalid_request_body() throws FileNotFoundException 
	{
		request=given().spec(requestSpecification());
	}

	@When("Admin sends HTTPS Request to update a program with invalid request body and valid endpoint")
	public void admin_sends_https_request_to_update_a_program_with_invalid_request_body_and_valid_endpoint() throws IOException 
	{
		response = request.body(upp.dataBuild2()).header("Authorization", "Bearer " +IdHolder.token)
				.when().put(routes.getString("Update_ProgramByProgramId")+IdHolder.programId).then().log().all().extract().response();

		logger.info("=========== PUT request - Update by program ID - invalid request body =====================  ");
	}

	@Then("Admin receives {int} Bad Request status with message and boolean success details")
	public void admin_receives_bad_request_with_message_and_boolean_success_details(int statuscode) 
	{
		assertEquals(statuscode, response.getStatusCode());
	}
	
	@Given("Admin creates PUT Request for the LMS API  with missing mandatory fields")
	public void admin_creates_put_request_for_the_lms_api_with_missing_mandatory_fields() throws FileNotFoundException 
	{
		request=given().spec(requestSpecification());
	}

	@When("Admin sends HTTPS Request with missing mandatory fields and valid endpoint")
	public void admin_sends_https_request_with_missing_mandatory_fields_and_valid_endpoint() throws IOException 
	{
		response = request.body(upp.dataBuild3()).header("Authorization", "Bearer " +IdHolder.token)
				.when().put(routes.getString("Update_ProgramByProgramId")+IdHolder.programId).then().log().all().extract().response();

		logger.info("=========== PUT request - Update by program ID - with missing mandatory fields =====================  ");
	}
	
	@Then("Admin receives {int} Bad Request Status code with message")
	public void admin_receives_bad_request_status_code_with_message(int statuscode) 
	{
		assertEquals(statuscode, response.getStatusCode());
	}
	
	@Given("Admin creates PUT Request for the LMS API endpoint with request Body with mandatory , additional  fields.")
	public void admin_creates_put_request_for_the_lms_api_endpoint_with_request_body_with_mandatory_and_additional_fields() 
	{
		request=given().baseUri(routes.getString("invalid_baseUrl"));
	}

	@When("Admin sends HTTPS Request  with request Body with mandatory, additional  fields and valid endpoint")
	public void admin_sends_https_request_with_request_body_with_mandatory_additional_fields_and_valid_endpoint() throws IOException 
	{
		response = request.body(upp.dataBuild4()).header("Authorization", "Bearer " +IdHolder.token)
				.when().put(routes.getString("Update_ProgramByProgramId")+IdHolder.programId).then().log().all().extract().response();

		logger.info("=========== PUT request - Update by program ID - with invalid BaseURI =====================  ");
	}
	
	@Then("Admin receives {int} Not Found with message and boolean success details")
	public void admin_receives_not_found_with_message_and_boolean_successDetails(int statuscode) 
	{
		assertEquals(statuscode, response.getStatusCode());
	}

	
	@Given("Admin creates PUT Request for the LMS API endpoint with request Body")
	public void admin_creates_put_request_for_the_lms_api_endpoint_with_request_body() throws FileNotFoundException 
	{
		request=given().spec(requestSpecification());
	}

	@When("Admin sends HTTPS Request with request body and valid endpoint")
	public void admin_sends_https_request_with_request_body_and_valid_endpoint() throws IOException 
	{
		response = request.body(upp.dataBuild5()).header("Authorization", "Bearer " +IdHolder.token)
				.when().get(routes.getString("Update_ProgramByProgramId")+IdHolder.programId).then().log().all().extract().response();

		logger.info("=========== PUT request - Update by program ID - with invalid method =====================  ");
	}

	@Then("Admin receives {int} Method Not Allowed status")
	public void admin_receives_method_not_allowed_status(int statuscode) 
	{
		assertEquals(statuscode, response.getStatusCode());
	}
	
	@Given("Admin creates PUT Request for the LMS API without Authorization and endpoint with request Body and mandatory, additional  fields.")
	public void admin_creates_put_request_for_the_lms_api_without_authorization_and_endpoint_with_request_body_and_mandatory_additional_fields() throws FileNotFoundException 
	{
		request=given().spec(requestSpecification());
	}
	
	@When("Admin sends HTTPS Request with valid endpoint to update a program without Authorization")
	public void admin_sends_https_request_with_valid_endpoint_to_update_a_program_without_authorization() throws IOException 
	{
		response = request.body(upp.dataBuild6())
				.when().put(routes.getString("Update_ProgramByProgramId")+IdHolder.programId).then().log().all().extract().response();

		logger.info("=========== PUT request - Update by program ID - with no auth =====================  ");
	}

	@Then("Admin receives {int} Unauthorized status")
	public void admin_receives_unauthorized_status(int statuscode) 
	{
		assertEquals(statuscode, response.getStatusCode());
	}
	
	//<--------------- PUT Request(Update by Program Name) --------------->
	
	@Given("Admin creates PUT Request for the LMS API endpoint with valid request Body with all fields.")
	public void admin_creates_put_request_for_the_lms_api_endpoint_with_valid_request_body_with_all_fields() throws FileNotFoundException 
	{
		request=given().spec(requestSpecification());
	}

	@When("Admin sends HTTPS Request with valid endpoint and gets the response")
	public void admin_sends_https_request_with_valid_endpoint_and_gets_the_response() throws IOException
	{
		response = request.body(uppn.dataBuild()).header("Authorization", "Bearer " +IdHolder.token)
				.when().put(routes.getString("Update_ProgramByProgramName")+IdHolder.programName).then().log().all().extract().response();
		
		IdHolder.programName = UserKeyJson(response,"programName"); 
		
		logger.info("=========== PUT request - Update by Program Name =====================  ");
	}

	@Then("Admin receives {int} OK Status code with updated value in response body.")
	public void admin_receives_ok_status_code_with_updated_value_in_response_body(int statuscode) 
	{
		assertEquals(statuscode, response.getStatusCode());
		
		response
		.then()
	    .assertThat().body(matchesJsonSchemaInClasspath("Schema/ProgramModule/CreateProgram.json"));
	}
	
	@Given("Admin creates PUT Request for the LMS API endpoint and Valid program Name and status")
	public void admin_creates_put_request_for_the_lms_api_endpoint_and_valid_program_name_and_status() throws FileNotFoundException
	{
		request=given().spec(requestSpecification());
	}

	@When("Admin sends HTTPS Request with Valid program Name, status and endpoint")
	public void admin_sends_https_request_with_valid_program_name_status_and_endpoint() throws IOException 
	{
		response = request.body(uppn.dataBuild1()).header("Authorization", "Bearer " +IdHolder.token)
				.when().put(routes.getString("Update_ProgramByProgramName")+IdHolder.programName).then().log().all().extract().response();
		
		IdHolder.programName = UserKeyJson(response,"programName"); 
		
		logger.info("=========== PUT request - Update by Program Name - update a Program Status  =====================  ");
	}

	@Then("Admin receives {int} OK with updated value in response body.")
	public void admin_receives_ok_with_updated_value_in_response_body(int statuscode)
	{
		assertEquals(statuscode, response.getStatusCode());
	}
	
	@Given("Admin creates PUT Request for the LMS API endpoint with valid request body with mandatory, additional fields.")
	public void admin_creates_put_request_for_the_lmsAPI_endpoint_with_valid_request_body_with_mandatory_additional_fields() throws FileNotFoundException 
	{
		request=given().spec(requestSpecification());
	}

	@When("Admin sends HTTPS request with invalid endpoint to update a program with invalid program Name")
	public void admin_sends_https_request_with_invalid_endpoint_to_update_a_program_with_invalid_program_name() throws IOException 
	{
		response = request.body(uppn.dataBuild2()).header("Authorization", "Bearer " +IdHolder.token)
				.when().put(routes.getString("Update_ProgramByProgramName_invalid_programName")).then().log().all().extract().response();

		logger.info("=========== PUT request - Update by Program Name - with invalid Program Name =====================  ");
	}

	@Then("Admin gets {int} Not Found Status with message and boolean success details")
	public void admin_gets_not_found_status_with_message_and_boolean_success_details(int statuscode) 
	{
		assertEquals(statuscode, response.getStatusCode());
	}
	
	@Given("Admin creates PUT Request for the LMS API  with missing programName and programStatus")
	public void admin_creates_put_request_for_the_lms_api_with_missing_program_name_and_program_status() throws FileNotFoundException 
	{
		request=given().spec(requestSpecification());
	}

	@When("Admin sends HTTPS Request with  missing mandatory fields and valid endpoint")
	public void admin_sends_https_request_with_missing_mandatory_fields_and_validEndpoint() throws IOException 
	{
		response = request.body(uppn.dataBuild3()).header("Authorization", "Bearer " +IdHolder.token)
				.when().put(routes.getString("Update_ProgramByProgramName")+IdHolder.programName).then().log().all().extract().response();

		logger.info("=========== PUT request - Update by Program Name - with missing mandatory fields =====================  ");
	}

	@Then("Admin receives {int} Bad Request Status Code with message and boolean success details")
	public void admin_receives_bad_request_status_code_with_message_and_boolean_success_details(int statuscode) 
	{
		assertEquals(statuscode, response.getStatusCode());
	}
	
	@Given("Admin creates PUT Request for the LMS API endpoint and invalid program Name")
	public void admin_creates_put_request_for_the_lms_api_endpoint_and_invalid_program_name() throws FileNotFoundException
	{
		request=given().spec(requestSpecification());
	}

	@When("Admin sends HTTPS Request and request Body with invalid values in mandatory fields. \\(program Description , program Name, program status)")
	public void admin_sends_https_request_and_request_body_with_invalid_values_in_mandatory_fields_program_description_program_name_program_status() throws IOException 
	{
		response = request.body(uppn.dataBuild4()).header("Authorization", "Bearer " +IdHolder.token)
				.when().put(routes.getString("Update_ProgramByProgramName")+IdHolder.programName).then().log().all().extract().response();

		logger.info("=========== PUT request - Update by Program Name - with invalid values in mandatory fields =====================  ");
	}

	@Then("Admin receives {int} Bad Request with message and boolean success details")
	public void admin_receives_bad_request_with_message(int statuscode) 
	{
		assertEquals(statuscode, response.getStatusCode());
	}
	
	@Given("Admin creates PUT Request for the LMS API endpoint and invalid program Description")
	public void admin_creates_put_request_for_the_lms_api_endpoint_and_invalid_program_description() throws FileNotFoundException 
	{
		request=given().spec(requestSpecification());
	}

	@When("Admin sends HTTPS Request and request Body with invalid program Description in mandatory fields. \\(program Description , program Name, program status)")
	public void admin_sends_https_request_and_request_body_with_invalid_program_description_in_mandatory_fields_program_description_program_name_program_status() throws IOException 
	{
		response = request.body(uppn.dataBuild5()).header("Authorization", "Bearer " +IdHolder.token)
				.when().put(routes.getString("Update_ProgramByProgramName")+IdHolder.programName).then().log().all().extract().response();

		logger.info("=========== PUT request - Update by Program Name - with invalid Program Description =====================  ");
	}

	@Then("Admin receives {int} Bad Request response with message and boolean success details")
	public void admin_receives_bad_request_response_with_message_and_boolean_success_details(int statuscode) 
	{
		assertEquals(statuscode, response.getStatusCode());
	}
	
	@Given("Admin creates PUT Request for the LMS API endpoint with request Body with all the fields.")
	public void admin_creates_put_request_for_the_lms_api_endpoint_with_request_body_with_all_the_fields() throws FileNotFoundException 
	{
		request=given().spec(requestSpecification());
	}

	@When("Admin sends HTTPS Request with valid endpoint to update a program without authorization")
	public void admin_sends_https_request_with_validEndpoint_to_update_a_program_without_authorization() throws IOException 
	{
		response = request.body(uppn.dataBuild6())
				.when().put(routes.getString("Update_ProgramByProgramName")+IdHolder.programName).then().log().all().extract().response();
		
		logger.info("=========== PUT request - Update by Program Name - with no auth =====================  ");
	}

	@Then("Admin receives {int} Unauthorized Status")
	public void admin_receives_unauthorizedstatus(int statuscode)
	{
		assertEquals(statuscode, response.getStatusCode());
	}
	
	//<---------------DELETE request - by Program Name --------------->
	
	@Given("Admin creates DELETE Request for the LMS API endpoint  and  valid programName")
	public void admin_creates_delete_request_for_the_lms_api_endpoint_and_valid_program_name() throws FileNotFoundException 
	{
		request=given().spec(requestSpecification());
	}

	@When("Admin sends HTTPS Request with endpoint to delete a program with program name")
	public void admin_sends_https_request_with_endpoint_to_delete_a_program_with_program_name() 
	{
		response = request.header("Authorization", "Bearer " +IdHolder.token)
				.when().delete(routes.getString("Delete_ProgramByProgramName")+IdHolder.programName).then().log().all().extract().response();
		
		logger.info("=========== DELETE request - by Program Name =====================  ");
	}

	@Then("Admin receives {int} Ok status with message")
	public void admin_receives_ok_status_with_message(int statuscode) 
	{
		assertEquals(statuscode, response.getStatusCode());
	}
	
	@Given("Admin creates DELETE Request for the LMS API endpoint and invalid \\{programName}")
	public void admin_creates_delete_request_for_the_lms_api_endpoint_and_invalid() throws FileNotFoundException 
	{
		request=given().spec(requestSpecification());
	}

	@When("Admin sends HTTPS Request with endpoint to delete a program with invalid programName")
	public void admin_sends_https_request_with_endpoint_to_delete_a_program_with_invalid_program_name() 
	{
		response = request.header("Authorization", "Bearer " +IdHolder.token)
				.when().delete(routes.getString("Delete_ProgramByProgramName_invalid_programName")).then().log().all().extract().response();
		
		logger.info("=========== DELETE request - by Program Name - with invalid Program Name =====================  ");
	}

	@Then("Admin gets {int} Not Found status code with message and boolean success details")
	public void admin_gets_not_found_status_code_with_message_and_boolean_success_details(int statuscode) 
	{
		assertEquals(statuscode, response.getStatusCode());
	}
	
	@Given("Admin creates DELETE Request for the LMS API endpoint and valid programName")
	public void admin_creates_delete_request_for_the_lms_api_endpoint_and_program_name() throws FileNotFoundException
	{
		request=given().spec(requestSpecification());
	}

	@When("Admin sends HTTPS Request with endpoint to delete a program without Authorization")
	public void admin_sends_https_request_with_endpoint_to_delete_a_program_without_authorization()
	{
		response = request
				.when().delete(routes.getString("Delete_ProgramByProgramName")+IdHolder.programName).then().log().all().extract().response();
		
		logger.info("=========== DELETE request - by Program Name - with no auth =====================  ");
	}

	@Then("Admin receives {int} unauthorized status code")
	public void admin_receives_unauthorized_statusCode(int statuscode) 
	{
		assertEquals(statuscode, response.getStatusCode());
	}

	//<--------------- DELETE request - by Program ID --------------->
	
	@Given("Admin creates DELETE Request for the LMS API endpoint  and  valid program ID")
	public void admin_creates_delete_request_for_the_lms_api_endpoint_and_valid_program_id() throws FileNotFoundException 
	{
		request=given().spec(requestSpecification());
	}

	@When("Admin sends HTTPS Request with endpoint to delete a program with program ID")
	public void admin_sends_https_request_with_endpoint_to_delete_a_program_with_program_id() 
	{
		response = request.header("Authorization", "Bearer " +IdHolder.token)
				.when().delete(routes.getString("Delete_ProgramByProgramId")+IdHolder.programId).then().log().all().extract().response();
		
		logger.info("=========== DELETE request - by Program ID =====================  ");
	}

	@Then("Admin receives {int} Ok and validates the status")
	public void admin_receives_ok_and_validates_the_status(int statuscode) 
	{
		assertEquals(statuscode, response.getStatusCode());
	}
	
	@Given("Admin creates DELETE Request for the LMS API endpoint and invalid program ID")
	public void admin_creates_delete_request_for_the_lms_api_endpoint_and_invalid_program_id() throws FileNotFoundException
	{
		request=given().spec(requestSpecification());
	}

	@When("Admin sends HTTPS Request with endpoint to delete a program with invalid program ID")
	public void admin_sends_https_request_with_endpoint_to_delete_a_program_with_invalid_program_id() 
	{
		response = request.header("Authorization", "Bearer " +IdHolder.token)
				.when().delete(routes.getString("Delete_ProgramByProgramId_invalid_programId")).then().log().all().extract().response();
		
		logger.info("=========== DELETE request - by Program ID - with invalid Program ID =====================  ");
	}

	@Then("Admin receives {int} Not Found Status Code as response with message and boolean success details")
	public void admin_receives_not_found_status_code_as_response_with_message_and_boolean_success_details(int statuscode) 
	{
		assertEquals(statuscode, response.getStatusCode());
	}
	
	@Given("Admin creates DELETE Request for the LMS API with endpoint and valid programId")
	public void admin_creates_delete_request_for_the_lms_api_with_endpoint_and_valid_program_id() throws FileNotFoundException 
	{
		request=given().spec(requestSpecification());
	}

	@When("Admin sends HTTPS Request with endpoint to delete a program by programId without Authorization")
	public void admin_sends_https_request_with_endpoint_to_delete_a_program_by_program_id_without_authorization() 
	{
		response = request
				.when().delete(routes.getString("Delete_ProgramByProgramId")+IdHolder.programId).then().log().all().extract().response();
		
		logger.info("=========== DELETE request - by Program ID - with no auth =====================  ");
	}

	@Then("Admin gets {int} Unauthorized status code")
	public void admin_gets_unauthorized_status_code(int statuscode) 
	{
		assertEquals(statuscode, response.getStatusCode());
	}
	//<--------------- POST request(for batch) --------------->
	@Given("Admin creates POST Request for the LMS with request body to be used for batch")
	public void admin_creates_post_request_for_the_lms_with_request_body_active_batch() throws FileNotFoundException, IOException 
	{   

		 request=given().spec(requestSpecification());
		 logger.info("=========== POST request =====================  ");
	}


	@When("Admin sends HTTPS Request and request Body with endpoint to be used for batch")
	public void admin_sends_https_request_and_request_body_with_endpoint_active_batch() throws IOException
	{
		cp = cpd.dataBuild8_active();
		response = request.body(cp).header("Authorization", "Bearer " +IdHolder.token)
				.when().post(routes.getString("Post_Createprogram")).then().log().all().extract().response();

		int i = Integer.parseInt(UserKeyJson(response,"programId"));
		System.out.println(i);
		IdHolder.programId = i;

		IdHolder.programName = UserKeyJson(response,"programName");
		
		logger.info("===========  POST request with all mandatory fields for batch =====================  ");	
	}

	@Then("Admin receives {int} Created Status with response body to be used for batch")
	public void admin_receives_created_status_with_response_body_active_batch(int statuscode) 
	{
		assertEquals(statuscode, response.getStatusCode());

		response
		.then()
		.assertThat().body(matchesJsonSchemaInClasspath("Schema/ProgramModule/CreateProgram.json"));

		assertEquals(UserKeyJson(response,"programName"),cp.getProgramName());
	}
	
	@Given("Admin creates POST Request for the LMS with request body to be used for batch inactive programID")
	public void admin_creates_post_request_for_the_lms_with_request_body_active_batch_inactive_programID() throws FileNotFoundException, IOException 
	{   

		 request=given().spec(requestSpecification());
		 logger.info("=========== POST request =====================  ");
	}


	@When("Admin sends HTTPS Request and request Body with endpoint to be used for batch inactive programID")
	public void admin_sends_https_request_and_request_body_with_endpoint_active_batch_inactive_programID() throws IOException
	{
		cp = cpd.dataBuild9_inactive();
		response = request.body(cp).header("Authorization", "Bearer " +IdHolder.token)
				.when().post(routes.getString("Post_Createprogram")).then().log().all().extract().response();

		int i = Integer.parseInt(UserKeyJson(response,"programId"));
		System.out.println(i);
		IdHolder.programId_inactive = i;

		IdHolder.programName_inactive = UserKeyJson(response,"programName");
		
		logger.info("===========  POST request with all mandatory fields for batch inactive programID =====================  ");	
	}

	@Then("Admin receives {int} Created Status with response body to be used for batch inactive programID")
	public void admin_receives_created_status_with_response_body_active_batch_inactive_programID(int statuscode) 
	{
		assertEquals(statuscode, response.getStatusCode());

		response
		.then()
		.assertThat().body(matchesJsonSchemaInClasspath("Schema/ProgramModule/CreateProgram.json"));

		assertEquals(UserKeyJson(response,"programName"),cp.getProgramName());
	}

	@Then("Admin receives {int} Not Found Status with the message and boolean success details")
	public void admin_receives_not_found_status_with_the_message_and_boolean_success_details(int actualStatusCode) {
		assertEquals(response.getStatusCode(), actualStatusCode);
		logger.info("====Then with status code " + actualStatusCode);
	}
 	
}


