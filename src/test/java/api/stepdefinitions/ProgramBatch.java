package api.stepdefinitions;

/*import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.json.JSONObject;

import api.resourses.CreateBatchData;
import api.utils.IdHolder;
import api.utils.RestUtils;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;*/

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.json.JSONObject;

import api.resourses.CreateBatchData;

import api.utils.RestUtils;
import api.utils.IdHolder;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ProgramBatch extends RestUtils {
	
	/*CreateBatchData cbd = new CreateBatchData();
	AllDeleteRequest adr=new AllDeleteRequest();
	 UserLogin User=new  UserLogin();
	RequestSpecification request;
	ResponseSpecification responseSpec;
	Response response;
	JSONObject requestBody;
	
	 @Given("Admin set Authorization")
     public void admin_sets_authorization() throws FileNotFoundException, IOException {
    	 User.add_user_login_payload();
    	 User.admin_calls_post_https_method_with_valid_endpoint();
    	
     }
	
	@Given("Admin creates POST Request  with valid data in request body")
	public void admin_creates_post_request_with_valid_data_in_request_body() throws FileNotFoundException, IOException {
		// Build the request body including the programId attribute
		requestBody = new JSONObject(cbd.dataBuild());
        requestBody.put("programId", IdHolder.programId);
        // Set the request body as a string and create the request
        request = given().spec(requestSpecification()).body(requestBody.toString());
	}

	@When("Admin sends POST HTTPS Request with endpoint of program batch")
	public void admin_sends_https_request_with_endpoint() {
	    response = request.header("Authorization", "Bearer " + IdHolder.token).when()
				.post(routes.getString("Post_CreateBatch")).then()
				.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("Schema/ProgramBatchModule/CreateBatch.json"))
				.log().all().extract().response();
		int i = Integer.parseInt(UserKeyJson(response, "batchId"));
		IdHolder.batchId = i;
		IdHolder.batchName = UserKeyJson(response, "batchName");
	}
	
	@Then("Admin receives {int} Created Status with response body of Post Batch request")
	public void admin_receives_created_status_with_response_body_of_post_batch_request(Integer int1) {
		assertEquals(response.getStatusCode(), 201);
	}
	

		// Get ALL BATCHES
		@Given("Admin creates GET Request of program batch of all batches")
		public void admin_creates_get_request_of_program_batch_of_all_batches() throws FileNotFoundException {
			request = given().spec(requestSpecification());
		}

		@When("Admin sends HTTPS Request with endpoint of program batch of all batches")
		public void admin_sends_https_request_with_endpoint_of_program_batch_of_all_batches() {
			response = request.header("Authorization", "Bearer " +IdHolder.token).when()
					.get(routes.getString("Get_AllBatch")).then().log().all().extract().response();
		}

		@Then("Admin receives {int} OK Status with response body of program batch of all batches.")
		public void admin_receives_ok_status_with_response_body_of_program_batch_of_all_batches(int int1) {
			assertEquals(response.getStatusCode(), 200);
		}

		// Get ALL BATCHES - search string
		@Given("Admin creates GET Request with search string of program batch")
		public void admin_creates_get_request_with_search_string_of_program_batch() throws FileNotFoundException {
			request = given().spec(requestSpecification());
		}

		@When("Admin sends HTTPS Request with endpoint of program batch of search string")
		public void admin_sends_https_request_with_endpoint_of_program_batch() {
			String batchName =IdHolder.batchName;
			String endpoint = routes.getString("Get_AllBatch") + "?batchName=" + batchName;
			response = request.header("Authorization", "Bearer " +IdHolder.token).when().get(endpoint).then().log()
					.all().extract().response();
		}

		@Then("Admin receives {int} Ok status with response body of program batch of search string")
		public void admin_receives_ok_status_with_response_body_of_program_batch_of_search_string(int actualStatusCode) {
			assertEquals(response.getStatusCode(), actualStatusCode);
		}

		// Get BATCH BY VALID Batch ID
		@Given("Admin creates GET Request with valid Batch ID")
		public void admin_creates_get_request_with_valid_batch_id() throws FileNotFoundException {
			request = given().spec(requestSpecification());
		}

		@When("Admin sends HTTPS Request with endpoint of program batch with batch id")
		public void get_batch_by_batchID() {
			String endpoint = routes.getString("Get_BatchByBatchId");
			response = request.header("Authorization", "Bearer " +IdHolder.token)
					.pathParam("batchId",IdHolder.batchId).get(endpoint).then().log().all().extract().response();
		}

		@Then("Admin receives {int} Ok status with response body of program batch of batch id")
		public void admin_receives_ok_status_with_response_body_of_program_batch_of_batch_id(int actualStatusCode) {
			assertEquals(response.getStatusCode(), actualStatusCode);
		}

		// Get BATCH BY VALID Batch ID after Deleting batch
		@Given("Admin creates GET Request with valid Batch ID after deleting batch")
		public void admin_creates_get_request_with_valid_batch_id_after_deleting_batch() throws FileNotFoundException {
			request = given().spec(requestSpecification());
			adr.delete_batch_by_batchID();
			request = given().spec(requestSpecification());
		}

		@When("Admin sends HTTPS Request with endpoint of program batch with batch id after deleting batch")
		public void admin_sends_https_request_with_endpoint_of_program_batch_with_batch_id_after_deleting_batch() {
			get_batch_by_batchID();
		}

		@Then("Admin receives {int} OK Status with batchStatus field {string} in the response body.")
		public void admin_receives_ok_status_with_response_body_of_program_batch_of_batch_id_after_deleting_batch(
				int actualStatusCode, String str) {
			assertEquals(response.getStatusCode(), actualStatusCode);
		}

		// Get BATCH BY VALID Batch Name
		@Given("Admin creates GET Request with valid batch name")
		public void admin_creates_get_request_with_valid_batch_name() throws FileNotFoundException {
			request = given().spec(requestSpecification());
		}

		@When("Admin sends HTTPS Request with endpoint with batch name")
		public void admin_sends_https_request_with_endpoint_with_batch_name() {
			String endpoint = routes.getString("Get_BatchByBatchName");
			response = request.header("Authorization", "Bearer " +IdHolder.token)
					.pathParam("batchName",IdHolder.batchName).get(endpoint).then().log().all().extract().response();
		}

		@Then("Admin receives {int} OK Status with response body of batch name.")
		public void admin_receives_ok_status_with_response_body_of_batch_name(int actualStatusCode) {
			assertEquals(response.getStatusCode(), actualStatusCode);
		}

		// Get BATCH BY VALID Batch Name after Deleting batch
		@Given("Admin creates GET Request with batch Name after deleting batch")
		public void admin_creates_get_request_with_batch_name_after_deleting_batch() throws FileNotFoundException {
			request = given().spec(requestSpecification());
			adr.delete_batch_by_batchID();
			request = given().spec(requestSpecification());
		}

		@When("Admin sends HTTPS Request with endpoint of program batch with batch name after deleting batch")
		public void admin_sends_https_request_with_endpoint_of_program_batch_with_batch_name_after_deleting_batch() {
			get_batch_by_batchID();
		}

		@Then("Admin receives {int} OK Status with  batchStatus field {string} in the response body using batchName.")
		public void admin_receives_ok_status_with_batch_status_field_in_the_response_body_using_batch_name(
				int actualStatusCode, String string) {
			assertEquals(response.getStatusCode(), actualStatusCode);
		}

		// Get BATCH BY VALID Program Id
		@Given("Admin creates GET Request with valid Program Id")
		public void admin_creates_get_request_with_valid_program_id() throws FileNotFoundException {
			request = given().spec(requestSpecification());
		}

		@When("Admin sends HTTPS Request with endpoint with valid program id")
		public void admin_sends_https_request_with_endpoint_with_valid_program_id() {
			String endpoint = routes.getString("Get_BatchByProgramId");
			response = request.header("Authorization", "Bearer " +IdHolder.token)
					.pathParam("programId", IdHolder.programId).get(endpoint).then().log().all().extract()
					.response();
		}

		@Then("Admin receives {int} OK Status with response body with valid program id.")
		public void admin_receives_ok_status_with_response_body_with_valid_program_id(int actualStatusCode) {
			assertEquals(response.getStatusCode(), actualStatusCode);
		}

		// PUT Request BY VALID Batch Id and Data
		@Given("Admin creates PUT Request with valid BatchId and Data")
		public void admin_creates_put_request_with_valid_batch_id_and_data() throws FileNotFoundException, IOException {
			// Build the request body including the programId attribute
			requestBody = new JSONObject(cbd.put_dataBuild());
			requestBody.put("programId", IdHolder.programId);
			// Set the request body as a string and create the request
			request = given().spec(requestSpecification()).body(requestBody.toString());
		}

		@When("Admin sends HTTPS Request with endpoint with PUT in request body")
		public void admin_sends_https_request_with_endpoint_with_put_in_request_body() {
			response = request.header("Authorization", "Bearer " +IdHolder.token)
					.pathParam("batchId",IdHolder.batchId).when().put(routes.getString("Put_Batch")).then().log().all()
					.extract().response();
			int i = Integer.parseInt(UserKeyJson(response, "batchId"));
			IdHolder.batchId = i;
			IdHolder.batchName = UserKeyJson(response, "batchName");
		}

		@Then("Admin receives {int} OK Status with updated value in response body with PUT.")
		public void admin_receives_ok_status_with_updated_value_in_response_body_with_put(int actualStatusCode) {
			assertEquals(response.getStatusCode(), actualStatusCode);
		}*/

	CreateBatchData cbd = new CreateBatchData();
	RequestSpecification request;
	ResponseSpecification responseSpec;
	Response response;
	JSONObject requestBody;
	UserLogin User=new  UserLogin();
	//private static Logger logger = LogManager.getLogger(ProgramBatch_SD.class);

	Logger logger = LogManager.getLogger("ProgramBatch_SD.java");
	
	@Given("Admin set Authorization")
    public void admin_sets_authorization() throws FileNotFoundException, IOException {
   	 User.add_user_login_payload();
   	 User.admin_calls_post_https_method_with_valid_endpoint();
   	
    }
	// NEGATIVE CASES - NO Auth
	@Given("Admin sets no Authorization for program batch")
	public void admin_sets_no_authorization() {
		IdHolder.token = "";
		logger.info("====NEGATIVE  - No Auth - Admin sets no Authorization, SET BACKGROUND===");
	}

	// NEGATIVE CASES - POST request
	@When("Admin sends POST HTTPS Request with endpoint of program batch without Authorization")
	public void admin_sends_post_https_request_with_endpoint_without_auth() {
		response = request.when().post(routes.getString("Post_CreateBatch")).then().log().all().extract().response();
		logger.info(
				"====NEGATIVE CASES - POST request - Admin sends POST HTTPS Request with endpoint of program batch without Authorization===");
	}

	// NEGATIVE CASES - NO Auth Get All Batches
	@Given("Admin creates GET Request to retrieve all batches")
	public void admin_creates_get_request_to_retrieve_all_batches() throws FileNotFoundException {
		request = given().spec(requestSpecification());
		logger.info("====NEGATIVE CASES - No Auth -  Get All Batches===");
	}

	@When("Admin sends HTTPS Request with endpoint to retrieve all batches")
	public void admin_sends_https_request_with_endpoint_to_retrieve_all_batches() {
		response = request.when().get(routes.getString("Get_AllBatch")).then().log().all().extract().response();
		logger.info(
				"====NEGATIVE CASES - No Auth Get All Batches -  Admin sends HTTPS Request with endpoint to retrieve all batches===");
	}

	@Then("Admin receives {int} status with error message Unauthorized.")
	public void admin_receives_status_with_error_message_unauthorized(int actualStatusCode) {
		assertEquals(response.getStatusCode(), actualStatusCode);
		logger.info("====NEGATIVE CASES - No Auth Get All Batches -  Admin receives " + actualStatusCode
				+ " status with error message Unauthorized===");
	}

	// Negative Case - Get Request by Batch Name
	@Given("Admin creates GET Request with batch Name")
	public void admin_creates_get_request_with_batch_name() throws FileNotFoundException {
		request = given().spec(requestSpecification());
		logger.info("====Negative Case - Get Request by Batch Name===");
	}

	@When("Admin sends HTTPS Request with endpoint to retrieve batch by batch name")
	public void admin_sends_https_request_with_endpoint_to_retrieve_batch_by_batch_name() {
		String endpoint = routes.getString("Get_BatchByBatchName");
		response = request.pathParam("batchName", "").get(endpoint).then().log().all().extract().response();
		logger.info(
				"====Negative Case - Get Request by Batch Name Admin sends HTTPS Request with endpoint to retrieve batch by batch name===");
	}

	// Negative Case - Get Request by Program Id
	@Given("Admin creates GET Request with program id")
	public void admin_creates_get_request_with_program_id() throws FileNotFoundException {
		request = given().spec(requestSpecification());
		logger.info(
				"====Negative Case - Get Request by Program Id Admin sends HTTPS Request with endpoint to retrieve batch by batch name===");
	}

	@When("Admin sends HTTPS Request with endpoint with program id")
	public void admin_sends_https_request_with_endpoint_with_program_id() {
		String endpoint = routes.getString("Get_BatchByProgramId");
		response = request.pathParam("programId", IdHolder.programId).get(endpoint).then().log().all().extract()
				.response();
		logger.info(
				"====Negative Case - Get Request by Program Id Admin sends HTTPS Request with endpoint with program id===");
	}

	// Negative Case - Put Request with valid Batch id and Data
	@Given("Admin creates PUT Request with valid BatchId and Data for put request")
	public void admin_creates_put_request_with_endpoint_with_valid_batch_id_and_data_for_put_request()
			throws IOException {
		// First login and create new batch and store the batch id and try to update
		// that batch details without no auth
		UserLogin user = new UserLogin();
		user.add_user_login_payload();
		user.admin_calls_post_https_method_with_valid_endpoint();
		// Build the request body including the programId attribute
		requestBody = new JSONObject(cbd.noauth_dataBuild());
		requestBody.put("programId", IdHolder.programId);
		// Set the request body as a string and create the request
		request = given().spec(requestSpecification()).body(requestBody.toString());
		response = request.header("Authorization", "Bearer " + IdHolder.token).when()
				.post(routes.getString("Post_CreateBatch")).then().log().all().extract().response();
		int i = Integer.parseInt(UserKeyJson(response, "batchId"));
		IdHolder.batchId = i;
		IdHolder.batchName = UserKeyJson(response, "batchName");
		IdHolder.token = "";
		// Build the request body including the programId attribute
		requestBody = new JSONObject(cbd.noauth_updatedataBuild());
		requestBody.put("programId", IdHolder.programId);
		// Set the request body as a string and create the request
		request = given().spec(requestSpecification()).body(requestBody.toString());
		logger.info(
				"====Negative Case - Put Request with valid Batch id and Data Creates PUT Request with valid BatchId and Data for put request===");
	}

	@When("Admin sends HTTPS Request  with endpoint with valid BatchId and Data for put request")
	public void admin_sends_https_request_with_endpoint_with_valid_batch_id_and_data() {
		response = request.header("Authorization", "Bearer " + IdHolder.token).when()
				.pathParam("batchId", IdHolder.batchId).when().put(routes.getString("Put_Batch")).then().log().all()
				.extract().response();
		logger.info(
				"====Negative Case - Put Request with valid Batch id and Data, Admin sends HTTPS Request with endpoint with valid BatchId and Data for put request===");
	}

	@When("Admin sends HTTPS Request with endpoint for delete request")
	public void no_auth_delete_batch_by_batchID() {
		String endpoint = routes.getString("Delete_Batch");
		response = request.pathParam("id", IdHolder.batchId).delete(endpoint).then().log().all().extract()
				.response();
		logger.info(
				"====Negative Case - Put Request with valid Batch id and Data, Admin sends HTTPS Request with endpoint for delete request===");
	}

	// Set Background
	@Given("Admin sets Authorization to Bearer Token for program batch.")
	public void admin_sets_authorization_to_bearer_token() throws FileNotFoundException, IOException {
		UserLogin user = new UserLogin();
		user.add_user_login_payload();
		user.admin_calls_post_https_method_with_valid_endpoint();
		logger.info("====Set Background - Admin sets Authorization to Bearer Token===");
	}

	// Negative Case - Get All Batches
	@When("Admin sends HTTPS GET Request with invalid endpoint to retrieve all batches")
	public void admin_sends_https_get_request_with_invalid_endpoint_to_retrieve_all_batches() {
		response = request.header("Authorization", "Bearer " + IdHolder.token).when()
				.get(routes.getString("Get_AllBatch_Invalid")).then().log().all().extract().response();

		logger.info(
				"====Negative Case - Get All Batches - Admin sends HTTPS GET Request with invalid endpoint to retrieve all batches===");
	}

	@Then("Admin receives {int} status with error message Not Found.")
	public void admin_receives_status_with_error_message_not_found(int actualStatusCode) {
		assertEquals(response.getStatusCode(), actualStatusCode);
		logger.info("====Negative Case - Get All Batches - Admin receives " + actualStatusCode
				+ " status with error message Not Found.===");
	}

	// Negative Case - Get batch by batch id invalid endpoint
	@When("Admin sends HTTPS Request with invalid endpoint to retrieve batch by batchId")
	public void admin_sends_https_request_with_invalid_endpoint_to_retrieve_batch_by_batchId() {
		response = request.header("Authorization", "Bearer " + IdHolder.token).pathParam("batchId", "")
				.get(routes.getString("Get_BatchByBatchId_Invalid")).then().log().all().extract().response();
		logger.info(
				"====Negative Case - Negative Case - Get batch by batch id invalid endpoint - Admin sends HTTPS Request with invalid endpoint to retrieve batch by batchId===");
	}

	// Negative Case - Get batch by batch name invalid endpoint
	@When("Admin sends HTTPS Request with invalid endpoint to retrieve batch by batchName")
	public void admin_sends_https_request_with_invalid_endpoint_to_retrieve_batch_by_batchName() {
		response = request.header("Authorization", "Bearer " + IdHolder.token).pathParam("batchName", "")
				.get(routes.getString("Get_BatchByBatchName_Invalid")).then().log().all().extract().response();
		logger.info(
				"====Negative Case - Negative Case - Get batch by batch name invalid endpoint - Admin sends HTTPS Request with invalid endpoint to retrieve batch by batchName===");
	}

	// Negative Case - Get batch by program id invalid endpoint
	@When("Admin sends HTTPS Request with invalid endpoint to retrieve batch by programid")
	public void admin_sends_https_request_with_invalid_endpoint_to_retrieve_batch_by_programid() {
		String endpoint = routes.getString("Get_BatchByProgramId_Invalid");
		response = request.header("Authorization", "Bearer " + IdHolder.token).pathParam("programId", "")
				.get(endpoint).then().log().all().extract().response();
		logger.info(
				"====Negative Case - Negative Case -  Get batch by program id invalid endpoint - Admin sends HTTPS Request with invalid endpoint to retrieve batch by programid");
	}

	// Negative Case - Put request update batch by batch id invalid endpoint
	@When("Admin sends PUT HTTPS Request  with invalid endpoint")
	public void admin_sends_put_https_request_with_invalid_endpoint() {
		response = request.header("Authorization", "Bearer " + IdHolder.token)
				.pathParam("batchId", IdHolder.batchId).when().put(routes.getString("Put_Batch_Invalid")).then()
				.log().all().extract().response();
		try {
			int i = Integer.parseInt(UserKeyJson(response, "batchId"));
			IdHolder.batchId = i;
			IdHolder.batchName = UserKeyJson(response, "batchName");
		} catch (Exception e) {
			System.out.println("PUT negative auth: " + e);
		}
		logger.info(
				"====Negative Case - Negative Case - Put request update batch by batch id invalid endpoint - Admin sends PUT HTTPS Request  with invalid endpoint");
	}

	// Negative Case - Put request update batch by batch id invalid endpoint
	@When("Admin sends HTTPS Request  with invalid endpoint for delete batch")
	public void admin_sends_https_request_with_invalid_endpoint_for_delete_batch() {
		response = request.header("Authorization", "Bearer " + IdHolder.token).pathParam("id", IdHolder.batchId)
				.when().delete(routes.getString("Delete_Batch_Invalid")).then().log().all().extract().response();
		try {
			int i = Integer.parseInt(UserKeyJson(response, "batchId"));
			IdHolder.batchId = i;
			IdHolder.batchName = UserKeyJson(response, "batchName");
		} catch (Exception e) {
			System.out.println("PUT negative auth: " + e);
		}
		logger.info(
				"====Negative Case - Put request update batch by batch id invalid endpoint - Admin sends HTTPS Request  with invalid endpoint for delete batch");
	}

	// NEGATIVE CASE - POST - valid endpoint and existing batch name
	@Given("Admin creates POST Request  with existing value in request body")
	public void admin_creates_post_request_with_existing_value_in_request_body() throws IOException {
		// Build the request body including the programId attribute
		requestBody = new JSONObject(cbd.valid_endpoint_existingBatch_dataBuild());
		requestBody.put("programId", IdHolder.programId);
		// Set the request body as a string and create the request
		request = given().spec(requestSpecification()).body(requestBody.toString());
		logger.info("====Negative Case - POST method - Admin creates POST Request  with valid data in request body");
	}

	@When("Admin sends POST HTTPS Request with endpoint")
	public void post_valid_with_existing_batchName() {
		logger.info("====Negative Case - POST method - Admin sends POST HTTPS Request with endpoint of program batch");

		response = request.header("Authorization", "Bearer " + IdHolder.token).when()
				.post(routes.getString("Post_CreateBatch")).then().log().all().extract().response();
		try {
			int i = Integer.parseInt(UserKeyJson(response, "batchId"));
			IdHolder.batchId = i;
			IdHolder.batchName = UserKeyJson(response, "batchName");
		} catch (Exception e) {
			logger.info("No value to save" + e.getLocalizedMessage());
		}
		logger.info("Done post_valid_with_existing_batchName");
	}

	@Then("Admin receives {int} Bad Request Status with message and boolean success details.")
	public void admin_receives_bad_request_status_with_message_and_boolean_success_details(int actualStatusCode) {
		assertEquals(response.getStatusCode(), actualStatusCode);
		logger.info("====Negative Case - POST method - Admin receives " + actualStatusCode + "Admin receives "
				+ actualStatusCode + " Bad Request Status with message and boolean success details.");
	}
	
	// invalid data - invalid data missing mandatory field name
	@Given("Admin creates POST Request  with invalid data missing mandatory feild in request body")
	public void admin_creates_post_request_with_invalid_value_missing_mandatory_field_in_request_body() throws IOException {
		// Build the request body including the programId attribute
		requestBody = new JSONObject(cbd.valid_endpoint_missing_mandatory_dataBuild());
		requestBody.put("programId", IdHolder.programId);
		// Set the request body as a string and create the request
		request = given().spec(requestSpecification()).body(requestBody.toString());
		logger.info("====Negative Case - POST method - Admin creates POST Request  with invalid data missing mandatory feild in request body");
	}
	
	// invalid data - invalid data for description
	@Given("Admin creates POST Request  with invalid data in request body")
	public void admin_creates_post_request_with_invalid_data_in_request_body() throws IOException {
		// Build the request body including the programId attribute
		requestBody = new JSONObject(cbd.valid_endpoint_invalid_data_dataBuild());
		requestBody.put("programId", IdHolder.programId);
		// Set the request body as a string and create the request
		request = given().spec(requestSpecification()).body(requestBody.toString());
		logger.info("====Negative Case - POST method - Admin creates POST Request  with invalid data in request body");
	}
	// NEGATIVE CASE - POST REQUEST - inactive program id	
	@Given("Admin creates POST Request with inactive program id")
	public void admin_creates_post_request_with_invalid_programID() throws IOException {
		// Build the request body including the programId attribute
		requestBody = new JSONObject(cbd.valid_endpoint_invalid_data_dataBuild());
		requestBody.put("programId", IdHolder.programId_inactive);
		// Set the request body as a string and create the request
		request = given().spec(requestSpecification()).body(requestBody.toString());
		logger.info("====Negative Case - POST method - Admin creates POST Request  with invalid data in request body");
	}
	

	// invalid batchId
	@Given("Admin creates GET Request with invalid Batch ID")
	public void admin_creates_get_request_with_invalid_batch_id() throws FileNotFoundException {
		request = given().spec(requestSpecification());
		logger.info("====Negative Case - Get BATCH BY INVALID Batch ID - Given");
	}

	@When("Admin sends GET HTTPS Request with endpoint with invalid batch id")
	public void admin_sends_get_https_request_with_endpoint_with_invalid_batch_id() {
		String endpoint = routes.getString("Get_BatchByBatchId");
		response = request.header("Authorization", "Bearer " + IdHolder.token)
				.pathParam("batchId", "123").get(endpoint).then().log().all().extract().response();
		logger.info("====Negative Case - Get BATCH BY INVALID Batch ID - When");
	}
	
	@Then("Admin receives {int} Not Found Status with message and boolean success details")
	public void admin_receives_not_found_status_with_message_and_boolean_success_details(int actualStatusCode) {
		assertEquals(response.getStatusCode(), actualStatusCode);
		logger.info("====Then with status code " + actualStatusCode);
	}
	
	// invalid batch name
	@Given("Admin creates GET Request with invalid Batch Name")
	public void admin_creates_get_request_with_invalid_batch_name() throws FileNotFoundException {
		request = given().spec(requestSpecification());
		logger.info("====Negative Case - Get BATCH BY INVALID Batch Name - Given");
	}

	@When("Admin sends GET HTTPS Request with endpoint with invalid batch name")
	public void admin_sends_get_https_request_with_endpoint_invalid_batch_name() {
		String endpoint = routes.getString("Get_BatchByBatchName");
		response = request.header("Authorization", "Bearer " + IdHolder.token)
				.pathParam("batchName", "qwerty@").get(endpoint).then().log().all().extract().response();
		logger.info("====Negative Case - Get BATCH BY INVALID Batch Name - When");
	}
	
	@Given("Admin creates GET Request with invalid Program Id")
	public void admin_creates_get_request_with_invalid_program_id() throws FileNotFoundException {
		request = given().spec(requestSpecification());
		logger.info("====Negative Case - Get BATCH BY INVALID Progam id - Given");
	}

	@When("Admin sends GET HTTPS Request with endpoint with invalid program id")
	public void admin_sends_get_https_request_with_endpoint_with_invalid_program_id() {
		String endpoint = routes.getString("Get_BatchByProgramId");
		response = request.header("Authorization", "Bearer " + IdHolder.token)
				.pathParam("programId", "123").get(endpoint).then().log().all().extract()
				.response();
		logger.info("====Negative Case - Get BATCH BY INVALID Program Id- When");
	}
	
	// Negative Case PUT Request BY INVALID Batch Id and Data
	@Given("Admin creates PUT Request with invalid BatchId and valid Data")
	public void put_request_invalid_batchID_data_given() throws FileNotFoundException, IOException {
		// Build the request body including the programId attribute
		requestBody = new JSONObject(cbd.put_dataBuild());
		requestBody.put("programId", IdHolder.programId);
		// Set the request body as a string and create the request
		request = given().spec(requestSpecification()).body(requestBody.toString());
		logger.info("====Negative Case - PUT BATCH BY INVALID Batch Id and Data - Given");
	}

	@When("Admin sends PUT HTTPS Request endpoint with invalid BatchId and valid Data in request body")
	public void put_request_invalid_batchID_data_when() {
		response = request.header("Authorization", "Bearer " + IdHolder.token)
				.pathParam("batchId", "123").when().put(routes.getString("Put_Batch")).then().log().all()
				.extract().response();
		try {
		int i = Integer.parseInt(UserKeyJson(response, "batchId"));
		IdHolder.batchId = i;
		IdHolder.batchName = UserKeyJson(response, "batchName");
		}catch(Exception e) {
			logger.info("====Negative Case - Exception occured - PUT BATCH BY INVALID Batch Id and Data - When");
		}
		logger.info("====Negative Case - PUT BATCH BY INVALID Batch Id and Data - When");
	}
	// Negative Case PUT Request BY VALID Batch Id and missing mandatory fields
	@Given("Admin creates PUT Request with valid batch Id and missing mandatory fields")
	public void admin_creates_put_request_with_valid_batch_id_and_missing_mandatory_fields() throws IOException {
		// Build the request body including the programId attribute
		requestBody = new JSONObject(cbd.valid_endpoint_missing_mandatory_dataBuild());
		requestBody.put("programId", IdHolder.programId);
		// Set the request body as a string and create the request
		request = given().spec(requestSpecification()).body(requestBody.toString());
		logger.info("====Negative Case - PUT BATCH BY valid Batch Id and missing mandatory fields - Given");
			
	}

	@When("Admin sends PUT HTTPS Request endpoint with valid batchId and missing mandatory fields in request body")
	public void admin_sends_put_https_request_endpoint_with_valid_batch_id_and_missing_mandatory_fields_in_request_body() {
		response = request.header("Authorization", "Bearer " + IdHolder.token)
				.pathParam("batchId", IdHolder.batchId).when().put(routes.getString("Put_Batch")).then().log().all()
				.extract().response();
		try {
		int i = Integer.parseInt(UserKeyJson(response, "batchId"));
		IdHolder.batchId = i;
		IdHolder.batchName = UserKeyJson(response, "batchName");
		}catch(Exception e) {
			logger.info("====Negative Case - Exception occured - PUT BATCH BY INVALID Batch Id and missing mandatory fields - When");
		}
		logger.info("====Negative Case - PUT BATCH BY valid Batch Id and missing mandatory fields - When");
	}
	
	// Negative Case PUT Request BY invalid data 
	@Given("Admin creates PUT Request with invalid data")
	public void admin_creates_put_request_with_invalid_data() throws IOException {
		// Build the request body including the programId attribute
				requestBody = new JSONObject(cbd.valid_endpoint_invalid_data_put_dataBuild());
				requestBody.put("programId", IdHolder.programId);
				// Set the request body as a string and create the request
				request = given().spec(requestSpecification()).body(requestBody.toString());
				logger.info("====Negative Case - PUT Request BY invalid data  - Given");
					
			}

	@When("Admin sends PUT HTTPS Request  with endpoint with invalid data")
	public void admin_sends_put_https_request_with_endpoint_with_invalid_data() {
		response = request.header("Authorization", "Bearer " + IdHolder.token)
				.pathParam("batchId", IdHolder.batchId).when().put(routes.getString("Put_Batch")).then().log().all()
				.extract().response();
		try {
		int i = Integer.parseInt(UserKeyJson(response, "batchId"));
		IdHolder.batchId = i;
		IdHolder.batchName = UserKeyJson(response, "batchName");
		}catch(Exception e) {
			logger.info("====Negative Case - Exception occured - PUT BATCH BY INVALID Batch Id and missing mandatory fields - When");
		}
		logger.info("====Negative Case - PUT BATCH BY valid Batch Id and missing mandatory fields - When");
	
	}

	
	// DELETE request - invalid batch id
	@Given("Admin creates DELETE Request with invalid BatchId")
	public void admin_creates_delete_request_with_invalid_batch_id() throws FileNotFoundException {
		request = given().spec(requestSpecification());
		logger.info("====NEGATIVE Case - DELETE Batch by invalid batch id - Given");
	}

	@When("Admin sends DELETE HTTPS Request with endpoint")
	public void admin_sends_delete_https_request_with_endpoint() {
		String endpoint = routes.getString("Delete_Batch");
		response = request.header("Authorization", "Bearer " + IdHolder.token).pathParam("id", "123")
				.delete(endpoint).then().log().all().extract().response();
		logger.info("====Positive Case - DELETE Batch by invalid BatchId - When");
	}

	// POSITIVE CASES
	// POST Method
	@Given("Admin creates POST Request  with valid data in request body")
	public void admin_creates_post_request_with_valid_data_in_request_body() throws FileNotFoundException, IOException {
		// Build the request body including the programId attribute
		requestBody = new JSONObject(cbd.dataBuild());
		requestBody.put("programId", IdHolder.programId);
		// Set the request body as a string and create the request
		request = given().spec(requestSpecification()).body(requestBody.toString());
		logger.info("====Positive Case - POST method - Admin creates POST Request  with valid data in request body");
	}

	@When("Admin sends POST HTTPS Request with endpoint of program batch")
	public void admin_sends_https_request_with_endpoint() {
		response = request.header("Authorization", "Bearer " + IdHolder.token).when()
				.post(routes.getString("Post_CreateBatch")).then().log().all().extract().response();
		int i = Integer.parseInt(UserKeyJson(response, "batchId"));
		IdHolder.batchId = i;
		IdHolder.batchName = UserKeyJson(response, "batchName");
		logger.info("====Positive Case - POST method - Admin sends POST HTTPS Request with endpoint of program batch");
	}

	@Then("Admin receives {int} Created Status with response body of Post Batch request")
	public void admin_receives_created_status_with_response_body_of_post_batch_request(int actualStatusCode) {
		assertEquals(response.getStatusCode(), actualStatusCode);
		logger.info("====Positive Case - POST method - Admin receives " + actualStatusCode
				+ "Created Status with response body of Post Batch request");
	}

	@Then("Validate the Schema after post batch request")
	public void validate_the_schema_after_post_batch_request() {
		// schema Validation
		response.then().assertThat().body(matchesJsonSchemaInClasspath("Schema/ProgramBatchModule/CreateBatch.json"));
		logger.info("====Positive Case - POST method - Schema validation");
	}

	@Then("Validate the header after post batch request")
	public void validate_the_header_after_post_batch_request() {
		assert response.getHeader("Content-Type") != null
				&& response.getHeader("Content-Type").contains("application/json");
		logger.info("====Positive Case - POST method - Header validation");
	}

	// Get ALL BATCHES
	@Given("Admin creates GET Request of program batch of all batches")
	public void get_all_batches_pre() throws FileNotFoundException {
		request = given().spec(requestSpecification());
		logger.info("====Positive Case - Get ALL BATCHES - Get");
	}

	@When("Admin sends HTTPS Request with endpoint of program batch of all batches")
	public void get_all_batches_when() {
		response = request.header("Authorization", "Bearer " + IdHolder.token).when()
				.get(routes.getString("Get_AllBatch")).then().log().all().extract().response();
		logger.info("====Positive Case - Get ALL BATCHES - When");
	}

	@Then("Admin receives {int} OK Status with response body of program batch of all batches.")
	public void admin_receives_ok_status_with_response_body_of_program_batch_of_all_batches(int actualStatusCode) {
		assertEquals(response.getStatusCode(), actualStatusCode);
		logger.info("====Positive Case - Get ALL BATCHES - Then " + actualStatusCode);
	}

	// Get ALL BATCHES - search string
	@Given("Admin creates GET Request with search string of program batch")
	public void admin_creates_get_request_with_search_string_of_program_batch() throws FileNotFoundException {
		request = given().spec(requestSpecification());
		logger.info("====Positive Case - Get ALL BATCHES search string - Given");
	}

	@When("Admin sends HTTPS Request with endpoint of program batch of search string")
	public void admin_sends_https_request_with_endpoint_of_program_batch() {
		String batchName = IdHolder.batchName;
		String endpoint = routes.getString("Get_AllBatch") + "?batchName=" + batchName;
		response = request.header("Authorization", "Bearer " + IdHolder.token).when().get(endpoint).then().log()
				.all().extract().response();
		logger.info("====Positive Case - Get ALL BATCHES search string - When");
	}

	@Then("Admin receives {int} Ok status with response body of program batch of search string")
	public void admin_receives_ok_status_with_response_body_of_program_batch_of_search_string(int actualStatusCode) {
		assertEquals(response.getStatusCode(), actualStatusCode);
		logger.info("====Positive Case - Get ALL BATCHES search string - Then " + actualStatusCode);
	}

	// Get BATCH BY VALID Batch ID
	@Given("Admin creates GET Request with valid Batch ID")
	public void admin_creates_get_request_with_valid_batch_id() throws FileNotFoundException {
		request = given().spec(requestSpecification());
		logger.info("====Positive Case - Get BATCH BY VALID Batch ID - Given");
	}

	@When("Admin sends HTTPS Request with endpoint of program batch with batch id")
	public void get_batch_by_batchID() {
		String endpoint = routes.getString("Get_BatchByBatchId");
		response = request.header("Authorization", "Bearer " + IdHolder.token)
				.pathParam("batchId", IdHolder.batchId).get(endpoint).then().log().all().extract().response();
		logger.info("====Positive Case - Get BATCH BY VALID Batch ID - When");
	}

	@Then("Admin receives {int} Ok status with response body of program batch of batch id")
	public void admin_receives_ok_status_with_response_body_of_program_batch_of_batch_id(int actualStatusCode) {
		assertEquals(response.getStatusCode(), actualStatusCode);
		logger.info("====Positive Case - Get BATCH BY VALID Batch ID - Then " + actualStatusCode);
	}

	// Get BATCH BY VALID Batch ID after Deleting batch
	@Given("Admin creates GET Request with valid Batch ID after deleting batch")
	public void admin_creates_get_request_with_valid_batch_id_after_deleting_batch() throws FileNotFoundException {
		request = given().spec(requestSpecification());
		delete_batch_by_batchID();
		request = given().spec(requestSpecification());
		logger.info("====Positive Case - Get BATCH BY VALID Batch ID after Deleting batch- Given");
	}

	@When("Admin sends HTTPS Request with endpoint of program batch with batch id after deleting batch")
	public void admin_sends_https_request_with_endpoint_of_program_batch_with_batch_id_after_deleting_batch() {
		get_batch_by_batchID();
		logger.info("====Positive Case - Get BATCH BY VALID Batch ID after Deleting batch- When");
	}

	@Then("Admin receives {int} OK Status with batchStatus field {string} in the response body.")
	public void admin_receives_ok_status_with_response_body_of_program_batch_of_batch_id_after_deleting_batch(
			int actualStatusCode, String str) {
		assertEquals(response.getStatusCode(), actualStatusCode);
		logger.info("====Positive Case - Get BATCH BY VALID Batch ID after Deleting batch- Then " + actualStatusCode);
	}

	// Get BATCH BY VALID Batch Name
	@Given("Admin creates GET Request with valid batch name")
	public void admin_creates_get_request_with_valid_batch_name() throws FileNotFoundException {
		request = given().spec(requestSpecification());
		logger.info("====Positive Case - Get BATCH BY VALID Batch Name - Given");
	}

	@When("Admin sends HTTPS Request with endpoint with batch name")
	public void get_batch_by_batch_name() {
		String endpoint = routes.getString("Get_BatchByBatchName");
		response = request.header("Authorization", "Bearer " + IdHolder.token)
				.pathParam("batchName", IdHolder.batchName).get(endpoint).then().log().all().extract().response();
		logger.info("====Positive Case - Get BATCH BY VALID Batch Name - When ");
	}

	@Then("Admin receives {int} OK Status with response body of batch name.")
	public void admin_receives_ok_status_with_response_body_of_batch_name(int actualStatusCode) {
		assertEquals(response.getStatusCode(), actualStatusCode);
		logger.info("====Positive Case - Get BATCH BY VALID Batch Name - Then " + actualStatusCode);
	}

	// Get BATCH BY VALID Batch Name after Deleting batch
	@Given("Admin creates GET Request with batch Name after deleting batch")
	public void admin_creates_get_request_with_batch_name_after_deleting_batch() throws FileNotFoundException {
		request = given().spec(requestSpecification());
		delete_batch_by_batchID();
		request = given().spec(requestSpecification());
		logger.info("====Positive Case - Get BATCH BY VALID Batch Name after deleting batch- Given");
	}

	@When("Admin sends HTTPS Request with endpoint of program batch with batch name after deleting batch")
	public void admin_sends_https_request_with_endpoint_of_program_batch_with_batch_name_after_deleting_batch() {
		get_batch_by_batchID();
		logger.info("====Positive Case - Get BATCH BY VALID Batch Name after deleting batch- When");
	}

	@Then("Admin receives {int} OK Status with  batchStatus field {string} in the response body using batchName.")
	public void admin_receives_ok_status_with_batch_status_field_in_the_response_body_using_batch_name(
			int actualStatusCode, String string) {
		assertEquals(response.getStatusCode(), actualStatusCode);
		logger.info("====Positive Case - Get BATCH BY VALID Batch Name after deleting batch- Then");
	}

	// Get BATCH BY VALID Program Id
	@Given("Admin creates GET Request with valid Program Id")
	public void admin_creates_get_request_with_valid_program_id() throws FileNotFoundException {
		request = given().spec(requestSpecification());
		logger.info("====Positive Case - Get BATCH BY VALID Program Id- Given");
	}

	@When("Admin sends HTTPS Request with endpoint with valid program id")
	public void get_batch_by_program_id() {
		String endpoint = routes.getString("Get_BatchByProgramId");
		response = request.header("Authorization", "Bearer " + IdHolder.token)
				.pathParam("programId", IdHolder.programId).get(endpoint).then().log().all().extract()
				.response();
		logger.info("====Positive Case - Get BATCH BY VALID Program Id- When");
	}

	@Then("Admin receives {int} OK Status with response body with valid program id.")
	public void admin_receives_ok_status_with_response_body_with_valid_program_id(int actualStatusCode) {
		assertEquals(response.getStatusCode(), actualStatusCode);
		logger.info("====Positive Case - Get BATCH BY VALID Program Id- Then " + actualStatusCode);
	}

	// PUT Request BY VALID Batch Id and Data
	@Given("Admin creates PUT Request with valid BatchId and Data")
	public void put_request_valid_batchID_data_given() throws FileNotFoundException, IOException {
		// Build the request body including the programId attribute
		requestBody = new JSONObject(cbd.put_dataBuild());
		requestBody.put("programId", IdHolder.programId);
		// Set the request body as a string and create the request
		request = given().spec(requestSpecification()).body(requestBody.toString());
		logger.info("====Positive Case - PUT BATCH BY VALID Batch Id and Data - Given");
	}

	@When("Admin sends HTTPS Request with endpoint with PUT in request body")
	public void put_request_valid_batchID_data_when() {
		response = request.header("Authorization", "Bearer " + IdHolder.token)
				.pathParam("batchId", IdHolder.batchId).when().put(routes.getString("Put_Batch")).then().log().all()
				.extract().response();
		int i = Integer.parseInt(UserKeyJson(response, "batchId"));
		IdHolder.batchId = i;
		IdHolder.batchName = UserKeyJson(response, "batchName");
		logger.info("====Positive Case - PUT BATCH BY VALID Batch Id and Data - When");
	}

	@Then("Admin receives {int} OK Status with updated value in response body with PUT.")
	public void admin_receives_ok_status_with_updated_value_in_response_body_with_put(int actualStatusCode) {
		assertEquals(response.getStatusCode(), actualStatusCode);
		logger.info("====Positive Case - PUT BATCH BY VALID Batch Id and Data- Then " + actualStatusCode);
	}

	// DELETE Batch by valid BatchId
	@Given("Admin creates DELETE Request with valid BatchId")
	public void admin_creates_delete_request_with_valid_batch_id() throws FileNotFoundException {
		request = given().spec(requestSpecification());
		logger.info("====Positive Case - DELETE Batch by valid BatchId - When");
	}

	@When("Admin sends HTTPS Request  with endpoint for delete batch")
	public void delete_batch_by_batchID() {
		String endpoint = routes.getString("Delete_Batch");
		response = request.header("Authorization", "Bearer " + IdHolder.token).pathParam("id", IdHolder.batchId)
				.delete(endpoint).then().log().all().extract().response();
		logger.info("====Positive Case - DELETE Batch by valid BatchId - When");
	}

	@Then("Admin receives {int} Ok status with message for delete batch.")
	public void admin_receives_ok_status_with_message_for_delete_batch(int actualStatusCode) {
		assertEquals(response.getStatusCode(), actualStatusCode);
		logger.info("====Positive Case - DELETE Batch by valid BatchId- Then " + actualStatusCode);
	}
		

}
