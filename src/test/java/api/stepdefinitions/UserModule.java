package api.stepdefinitions;


import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import api.resourses.*;
import api.utils.RestUtils;
import api.utils.IdHolder;
import api.utils.LoggerLoad;
//import api.utils.*;
public class UserModule extends RestUtils {
	
	 Logger logger = LogManager.getLogger("UserModule.java");
	 
	
	UserModuledata UserModuledata= new UserModuledata();
	UserpostMissManddata UserpostMissManddata=new UserpostMissManddata();
	UserPostInvaliddata UserPostInvaliddata=new UserPostInvaliddata();
	 UserLogin User=new  UserLogin();
	RequestSpecification request;
	ResponseSpecification responseSpec;
	Response response;
	String asString;
	public static String token;
	//LoggerLoad log = new LoggerLoad();
	
	 @Given("Admin sets Authorization token")
     public void admin_sets_authorization() throws FileNotFoundException, IOException {
    	 User.add_user_login_payload();
    	 User.admin_calls_post_https_method_with_valid_endpoint();
    	
     }
	 
	
	
 //	(=================POST request with all mandatory fields===============================)
	
	@Given("Admin creates POST request with all mandatory fields")
	public void admin_creates_post_request_with_all_mandatory_fields() throws FileNotFoundException, IOException {
		 
		//request=given().spec(requestSpecification()).header("Authorization","Bearer "+TokenHolder.token);
		request=given().spec(requestSpecification()).body( UserModuledata.usermodulePostdata()).header("Authorization","Bearer "+IdHolder.token);
		 System.out.println(IdHolder.token );
		 
		
		 logger.info("===========POST request with all mandatory fields=====================  ");
	}

	@When("Admin sends HTTPS Request with endpoint")
	public void admin_sends_https_request_with_endpoint() {
		 response = request.when().post(routes.getString("Post_Userurl"));
		 asString = response.then().log().all().extract().asString();
		//.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("Schema/UserModule/PostSchema.json")) 
		 IdHolder.userId1 =  UserKeyJson(response,"userId");
		  System.out.println("userId1 ="  +IdHolder.userId1);
		  
		  logger.info("===========User POST request =====================  ");
	}

	@Then("Admin receives {int} Created Status with response body.")
	public void admin_receives_created_status_with_response_body(int int1) {
		assertEquals(response.getStatusCode(),201);
		logger.info("User Schema Validation is successful");
	}
	
//	(=================POST request with all mandatory fields and an additional fields===============================)
	
	
	@Given("Admin creates POST request with all mandatory fields and an additional fields")
	public void admin_creates_post_request_with_all_mandatory_fields_and_an_additional_fields() throws FileNotFoundException, IOException {
		request=given()
				.spec(requestSpecification())
				.headers("Authorization","Bearer "+IdHolder.token)
				.body( UserModuledata.usermodulePostMandata());
		logger.info("Create User with Mandatory Fields");
	}
	
	
	@When("Admin sends POST HTTPS Request with mandatory and additional fields")
	public void admin_sends_post_https_request_with_mandatory_and_additional_fields() {
		logger.info("Request sent with valid Endpoint and request body");
		response = request.when().post(routes.getString("Post_Userurl"));
		 asString = response.then().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("Schema/UserModule/PostSchema.json")).log().all().extract().asString();
		

			IdHolder.userId2 =  UserKeyJson(response,"userId");
			
			System.out.println("******UserId2 :" + IdHolder.userId2);	
	}
	@Then("Admin receives {int} Created Status")
	public void admin_receives_created_status(Integer int1) {
		assertEquals(response.getStatusCode(),201);
	}
	
	
//	(=======================================GET Get All Roles===================================================)	

		@When("Admin sends HTTPS Request with GET All Roles endpoint")
		public void admin_sends_https_request_with_get_all_roles_endpoint() {
			response = request.when()
					 .get(routes.getString("Get_AllRoles"))
					 .then().log().all().extract().response();
			    System.out.println(response);
			    logger.info("Request to get all Roles");
				
		}
//		(=======================================Get All Users===================================================)	
	
		@When("Admin sends HTTPS Request with valid endpoint")
		public void admin_sends_https_request_with_valid_endpoint() {
		   		response = request.when()
					 .get(routes.getString("Get_Alluser"))
					 .then().log().all().extract().response();
			    System.out.println(response);
			    logger.info("Request to get all Users");
		}
		//(=======================================Get user by userId===================================================)
	
		@Given("Admin creates GET Request with valid AdminId")
		public void admin_creates_get_request_with_valid_admin_id() throws FileNotFoundException, IOException {
			request=given()
					.spec(requestSpecification())
					.headers("Authorization","Bearer "+IdHolder.token)
					.pathParam("id", IdHolder.userId2);
		}
		@When("Admin sends HTTPS Request with a valid endpoint")
		public void admin_sends_https_request_with_a_valid_endpoint() {
			response = request.when()
					 .get(routes.getString("Get_UserinformationbyUserId"))
					 .then().log().all().extract().response();
			    System.out.println(response);
			logger.info("Request to get User by UserId");
		}
	//(=======================================get all user with Roles===================================================)
	
		
		@When("Admin sends HTTPS Request with an endpoint")
		public void admin_sends_https_request_with_an_endpoint() {
			response = request.when()
					 .get(routes.getString("Get_allUserswithroles"))
					 .then().log().all().extract().response();
			    System.out.println(response);
		}

 //	(=================GET Request with active and inactive Admins endpoint===============================)
	
	@Given("Admin creates GET Request with active and inactive Admins endpoint")
	public void admin_creates_get_request_with_active_and_inactive_admins_endpoint() throws FileNotFoundException {
	   	
		request=given().spec(requestSpecification()).header("Authorization","Bearer "+IdHolder.token).queryParam("Id", "all/R01/R02/R03");;
		// response = request.when().post(routes.getString("Post_Userurl"));
		logger.info("=========== Get count of active and inactive users  HTTPS Request =====================  ");	
	}
	
	@When("Admin sends Get count of active and inactive users  HTTPS Request with endpoint")
	public void admin_sends_get_count_of_active_and_inactive_users_https_request_with_endpoint() {
		 response = request
					.when().get(routes.getString("Get_countofactiveandinactiveusers"))
					.then().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("Schema/UserModule/GetActiveandinactiveSchema.json"))
					.spec(resSpecBuilder()).statusCode(200).log().all().extract().response();
	}

	@Then("Admin receives {int} OK")
	public void admin_receices_ok(Integer int1) {
		assertEquals(response.getStatusCode(),200);
	}
	
	
	//(=================================Get All Active Users HTTPS Request=====================================)  	
	
	@Given("Admin creates GET Request")
	public void admin_creates_get_request() throws FileNotFoundException {
		
		request=given().spec(requestSpecification()).header("Authorization","Bearer "+IdHolder.token);
		// response = request.when().post(routes.getString("Post_Userurl"));
		
	}
	
	@When("Admin sends Get All Active Users HTTPS Request with endpoint")
	public void admin_sends_get_all_active_users_https_request_with_endpoint() {
	   
		response = request
				.when().get(routes.getString("Get_AllActiveUsers"))
				.then()
				.spec(resSpecBuilder()).statusCode(200).log().all().extract().response();
		logger.info("=========== Get All Active Users HTTPS Request=====================  ");
	}
	
	//(=======================Assign Update User Role Program Batch Status================================)  
	
	@Given("Admin creates PUT Request with valid data in request body")
	public void admin_creates_put_request_with_valid_data_in_request_body() throws FileNotFoundException {
		request=given().spec(requestSpecification()).body(UserModuledata.UpdateUserRoleProgramBatchStatus(IdHolder.programId, IdHolder.batchId))
				.header("Authorization","Bearer "+IdHolder.token).pathParam("userId", IdHolder.userId2);
		
		 System.out.println("ProgramId ="  +IdHolder.programId);
		 System.out.println("BatchId ="  +IdHolder.batchId);
	
	}

	@When("Admin sends PUT HTTPS Request with User Role Program Batch Status endpoint")
	public void admin_sends_put_https_request_with_user_role_program_batch_status_endpoint() {
	   
		response = request
				.when().put(routes.getString("update_UpdateUserRoleProgramBatch"))
				.then().spec(resSpecBuilder()) 
				.statusCode(200).log().all().extract().response();
	}

	
	
//	(=============================== Get User by Program Batches==========================================)	
	
	@Given("Admin creates GET Request with valid batch Id")
	public void admin_creates_get_request_with_valid_batch_id() throws FileNotFoundException {
	   
		request=given().spec(requestSpecification()).header("Authorization","Bearer "+IdHolder.token).pathParam("Id",IdHolder.batchId);
	
	}
//8635
	@When("Admin sends Get User by Program Batches HTTPS Request with endpoint")
	public void admin_sends_get_user_by_program_batches_https_request_with_endpoint() {
		
		response = request
				.when().get(routes.getString("Get_UserbyProgramBatches"))
				.then().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("Schema/UserModule/GetProgramBatchSchema.json"))
				.spec(resSpecBuilder()).statusCode(200).log().all().extract().response();
		logger.info("=========== Get User by Program Batches=====================  ");
	}
	
	//(=============================== Gets Users for Program============================================ );	
	
	@Given("Admin creates GET Request with valid program Id")
	public void admin_creates_get_request_with_valid_program_id() throws FileNotFoundException {
	    
		request=given().spec(requestSpecification()).header("Authorization","Bearer "+IdHolder.token).pathParam("programId", IdHolder.programId);
		
		
	}
//16765
	@When("Admin sends Gets Users for Program HTTPS Request with endpoint")
	public void admin_sends_gets_users_for_program_https_request_with_endpoint() {
	   
		response = request
				.when().get(routes.getString("Get_UsersforProgram"))
				.then().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("Schema/UserModule/GetProgramBatchSchema.json"))
				.spec(resSpecBuilder()).statusCode(200).log().all().extract().response();
		logger.info("=========== Gets Users for Program=====================  ");
	}

	//(=============================== Gets Users with valid role ID===============================  );
	
	@Given("Admin creates GET Request with valid role ID")
	public void admin_creates_get_request_with_valid_role_id() throws FileNotFoundException {
		request=given().spec(requestSpecification()).header("Authorization","Bearer "+IdHolder.token).pathParam("roleId","R03");
	}

	@When("Admin sends Gets Users by roleId HTTPS Request with endpoint")
	public void admin_sends_gets_users_by_role_id_https_request_with_endpoint() {
		response = request
				.when().get(routes.getString("Get_UsersbyroleId"))
				.then()
				.spec(resSpecBuilder()).statusCode(200).log().all().extract().response();
		
		logger.info("=========== Gets Users with valid role ID=====================  ");
	}
	
	//(=============================== Get all users by facets/ filter (v2 users)===============================  );
	
			@Given("Admin creates GET Request with filters")
			public void admin_creates_get_request_with_filters() throws FileNotFoundException {
				request = given().spec(requestSpecification()).header("Authorization", "Bearer " + IdHolder.token);
				logger.info("Request for Get All users by RoleId v2");
			}

			@When("Admin sends HTTPS Request with filters endpoint")
			public void admin_sends_https_request_with_filters_endpoint() {
				response = request.when().get(routes.getString("Get_UsersbyroleIdV2"));
				asString = response.then().log().all().extract().asString();
				System.out.println(response);
			}

			@Then("Admin receives {string} OK")
			public void admin_receives_ok(String statuscode) {
				int GetAllstatuscode = response.getStatusCode();
				logger.info("Response Status is= " + GetAllstatuscode);
				if (GetAllstatuscode == 200) {
					response.then().statusCode(Integer.parseInt(statuscode));
					response.then().assertThat().header("Connection", "keep-alive");
					logger.info("User request is successful");

				} else if (GetAllstatuscode == 400) {
					logger.info("User Request unsuccessful");
				}
			}
	//(================================= update user by AdminID with mandatory fields=================================  );
			
			
			@Given("Admin creates PUT Request with valid data in request body \\(values only in mandatory fields)")
			public void admin_creates_put_request_with_valid_data_in_request_body_values_only_in_mandatory_fields()
					throws IOException {

				logger.info("---update user by Admin Id---");
			//	user_Id = IdHolder.userId;
				request = given().spec(requestSpecification())
						// .pathParam("userId",user_Id)
						.body(UserModuledata.userPUTdata()).header("Authorization", "Bearer " + IdHolder.token);

			}

			@When("Admin sends HTTPS request for update by userId with endpoiint")
			public void admin_sends_https_request_for_update_by_user_id_with_endpoiint() {
				response = request.when().put(routes.getString("Get_Alluser") + "/" + IdHolder.userId2);
				asString = response.then().log().all().extract().asString();
				System.out.println(response);
				logger.info("updated userVisaStatus :" + UserKeyJson(response, "userVisaStatus"));
			}
	//(======================================= update user by Rolestatus=====================================  );
			

			@Given("Admin creates PUT Request with valid user data in request body")
			public void admin_creates_put_request_with_valid_user_data_in_request_body() throws FileNotFoundException, IOException {
				logger.info("---update user by Rolestatus---");
				//user_Id = IdHolder.userId;
				request = given().spec(requestSpecification())
						.body(UserModuledata.userPUTRoleStatusdata()).header("Authorization", "Bearer " + IdHolder.token);

			}

			@When("Admin sends HTTPS Request for update by roleStatus with endpoint")
			public void admin_sends_https_request_for_update_by_role_status_with_endpoint() {
				
				response = request.when().put(routes.getString("Post_Userurl") + "/" + IdHolder.userId2);
				asString = response.then().log().all().extract().asString();
				System.out.println(response);
				logger.info("updated userVisaStatus :" + UserKeyJson(response, "userVisaStatus"));
				logger.info("---Updated user by Rolestatus----");
			}
//(======================================= update user by RoleID=====================================  );	
			

			@Given("Admin creates PUT Request with valid user data to with roleID")
			public void admin_creates_put_request_with_valid_user_data_to_with_role_id()
					throws FileNotFoundException, IOException {
				logger.info("---update user by RoleId---");
				request = given().spec(requestSpecification()).body(UserModuledata.updateUserbyRoleID())
						.header("Authorization", "Bearer " + IdHolder.token).pathParam("userID", IdHolder.userId2);

			}

			@When("Admin sends HTTPS Request for update User by roleID")
			public void admin_sends_https_request_for_update_user_by_role_id() {
				response = request.when().put(routes.getString("update_UserRoleID")).then().log().all().extract().response();
				System.out.println(response);
				logger.info("updated user with roleID ");

			}
	//(======================================= update user by Loginstatus=====================================  );	
		
			
			@Given("Admin creates PUT Request with Admin login status by Admin ID")
			public void admin_creates_put_request_with_admin_login_status_by_admin_id() throws FileNotFoundException {
				logger.info("---update user by LoginEmail/Status---");
				request = given().spec(requestSpecification()).body(UserModuledata.updateUserByLoginStatus())
						.header("Authorization", "Bearer " + IdHolder.token).pathParam("userId", IdHolder.userId2);
				
				System.out.println("LoginStatusUserId2 :" +IdHolder.userId2);

			}

			@When("Admin sends HTTPS Request for update User with Admin login status by Admin ID")
			public void admin_sends_https_request_for_update_user_with_admin_login_status_by_admin_id() {
				response = request.when().put(routes.getString("update_UserLoginStatus")).then().log().all().extract().response();
				System.out.println(response);
				logger.info("updated user with LoginEmail/Status ");
			}


	//(==================================== Negative TestCases===========================================  );
			
	//(==================================== active and inactive Admins with no authorization	===========================================  )
	
	@Given("Admin creates no authorization GET Request")
public void admin_creates_authorization_get_request() throws FileNotFoundException {
		request=given().spec(requestSpecification());
		
		logger.info("===========Negative TestCases=====================  ");
	}

	
	
	@When("Admin sends HTTPS Request with endpoint	no authorization")
	public void admin_sends_https_request_with_endpoint_no_authorization() {
		 response = request
					.when().get(routes.getString("Get_countofactiveandinactiveusers"))
					.then()
					.spec(resSpecBuilder()).statusCode(401).log().all().extract().response();
		 logger.info("===========Negative no authorization =====================  ");
	  	}
	
	//(====================================active and inactive Admins with invalid endpoint===========================================  )
	
	@When("Admin sends HTTPS Request with endpoint with invalid endpoint")
	public void admin_sends_https_request_with_endpoint_with_invalid_endpoint() throws FileNotFoundException {
	    
		 response =request
					.when().get(routes.getString("invalidUrl"))
					.then()
					.spec(resSpecBuilder()).statusCode(404).log().all().extract().response();
		 
		 logger.info("===========Negative with invalid endpoint =====================  ");
	}

	@Then("Admin receives status {int} with Not Found error message	Negative")
	public void admin_receives_status_with_not_found_error_message_negative(Integer int1) {
		assertEquals(404,response.getStatusCode());
	}
	//(====================================active and inactive Admins by invalid role ID===========================================  )	 
	
	@Given("Admin creates GET Request with active and inactive Admins Negative endpoint")
	public void admin_creates_get_request_with_active_and_inactive_admins_negative_endpoint() throws FileNotFoundException {
		
		request=given().spec(requestSpecification()).header("Authorization","Bearer"+IdHolder.token).queryParam("Id", "R3");
		
		
	}

	@When("Admin sends HTTPS Request with endpoint	invalid role ID")
	public void admin_sends_https_request_with_endpoint_invalid_role_id() {
	    
		 response = request
					.when().get(routes.getString("Get_countofactiveandinactiveusers"))
					.then()
					.spec(resSpecBuilder()).statusCode(401).log().all().extract().response();
		 
		 logger.info("===========Negative of active and inactive Admins with invalid role ID =====================  ");
	}
	@Then("Admin receives status {int} with unauthorized error message	Negative")
	public void admin_receives_status_with_unauthorized_error_message_negative(Integer int1) {
		assertEquals(401,response.getStatusCode());
	}
	
	//(====================================Get All Active Users===========================================  )	 
	@When("Admin sends HTTPS Request with all active Admins with invalid endpoint")
	public void admin_sends_https_request_with_all_active_admins_with_invalid_endpoint() {
		 response = request
					.when().get(routes.getString("invalidUrl"))
					.then()
					.spec(resSpecBuilder()).statusCode(404).log().all().extract().response();
		 
		 logger.info("===========Negative with invalid endpoint =====================  ");
		 
}
	
	@When("Admin sends HTTPS Request with endpoint with no auth")
	public void admin_sends_https_request_with_endpoint_with_no_auth() {
		 response = request
					.when().get(routes.getString("Get_AllActiveUsers"))
					.then()
					.spec(resSpecBuilder()).statusCode(401).log().all().extract().response();
	 }

	@Then("Admin receives status {int} with Unauthorized")
	public void admin_receives_status_with_unauthorized(Integer int1) {
		assertEquals(401,response.getStatusCode());
	}
	

	//(====================================Get invalid batchId===========================================  )	
	@Given("Admin creates GET Request  with invalid batchId")
	public void admin_creates_get_request_with_invalid_batch_id() throws FileNotFoundException {
	   
		request=given().spec(requestSpecification()).header("Authorization","Bearer "+IdHolder.token).pathParam("Id",IdHolder.inactive_id );
	}
	
	@When("Admin sends GET Request  with invalid batchId by Program Batches endpoint")
	public void admin_sends_get_request_with_invalid_batch_id_by_program_batches_endpoint() {
		response = request
				.when().get(routes.getString("Get_UserbyProgramBatches"))
				.then()
				.spec(resSpecBuilder()).statusCode(404).log().all().extract().response();
		
		logger.info("===========Negative with invalid batchId =====================  ");
	}
	
	
//(====================================Get invalid program Id===========================================  )
	
	@Given("Admin creates GET Request with invalid program Id")
	public void admin_creates_get_request_with_invalid_program_id() throws FileNotFoundException {
	    
		request=given().spec(requestSpecification()).header("Authorization","Bearer "+IdHolder.token).pathParam("programId", IdHolder.inactive_id);
	}

	@When("Admin sends GET Request  with invalid program Id by Program  endpoint")
	public void admin_sends_get_request_with_invalid_program_id_by_program_endpoint() {
		
		response = request
				.when().get(routes.getString("Get_UsersforProgram"))
				.then()
				.spec(resSpecBuilder()).statusCode(404).log().all().extract().response();
		
		logger.info("===========Negative with invalid ProgramId =====================  ");
	}
	//(====================================Get invalid role ID===========================================  )
	
	@Given("Admin creates GET Request for GET with invalid role ID")
	public void admin_creates_get_request_for_get_with_invalid_role_id() throws FileNotFoundException {
	     
		request=given().spec(requestSpecification()).header("Authorization","Bearer "+IdHolder.token).pathParam("roleId",IdHolder.inactive_id);
	}

	@When("Admin sends GET HTTPS Request  endpoint with	invalid role ID")
	public void admin_sends_get_https_request_endpoint_with_invalid_role_id() {
		response = request
				.when().get(routes.getString("Get_UsersbyroleId"))
				.then()
				.spec(resSpecBuilder()).statusCode(404).log().all().extract().response();
		
		logger.info("===========Negative with invalid role ID endpoint =====================  ");
	}
	
	//================================================================================================================
	
	// -------@Get_Admin_with_filters_401-----------

		@Given("Admin creates GET Request with filters with no authorization")
		public void admin_creates_get_request_with_filters_with_no_authorization() throws FileNotFoundException {
			request = given().spec(requestSpecification());
			logger.info("====Negative Admin_with_filters_401 for role v2=======");
		}

		@When("Admin sends HTTPS Request with filters with no authorization with endpoint")
		public void admin_sends_https_request_with_filters_with_no_authorization_with_endpoint() {
			response = request.when().get(routes.getString("Get_UsersbyroleIdV2"));
			asString = response.then().log().all().extract().asString();

		}

		@Then("Admin receives status {string} with Unauthorized message")
		public void admin_receives_status_with_unauthorized_message(String statuscode) {
			assertEquals(statuscode, response.getStatusCode());
			logger.info("====401 unAuthorized=======");
		}

		// -----@Get_Admin_with_filters_404------

		@Given("Admin creates GET Request with filters with invalid endpoint")
		public void admin_creates_get_request_with_filters_with_invalid_endpoint() throws FileNotFoundException {
			request = given().spec(requestSpecification()).header("Authorization", "Bearer " + IdHolder.token);
			logger.info("Request for Get All users by RoleId v2");
		}

		@When("Admin sends HTTPS Request with filters with invalid endpoint")
		public void admin_sends_https_request_with_filters_with_invalid_endpoint() {
			response = request.when().get(routes.getString("invalidUrl"));
			asString = response.then().log().all().extract().asString();
		}

		@Then("Admin receives status {string} with Not Found error message")
		public void admin_receives_status_with_not_found_error_message(String statuscode) {
			assertEquals(statuscode, response.getStatusCode());
			logger.info("====404 Not Found=======");
		}

		// ----@PUT_Admin_info_401-----

		@Given("Admin creates PUT Request with valid data in request body with No Authorization")
		public void admin_creates_put_request_with_valid_data_in_request_body_with_no_authorization()
				throws FileNotFoundException, IOException {
			logger.info("---Negative update user by Admin Id with No Authorization---");
			request = given().spec(requestSpecification()).body(UserModuledata.userPUTdata());
		}

		@When("Admin sends HTTPS request with endpoiint with No Authorization")
		public void admin_sends_https_request_with_endpoiint_with_no_authorization() {
			response = request.when().put(routes.getString("Get_Alluser") + "/" + IdHolder.userId1);
			asString = response.then().log().all().extract().asString();
		}
		
		// ----@PUT_Admin_info_404---

		@Given("Admin creates PUT Request with invalid AdminID in request body")
		public void admin_creates_put_request_with_invalid_admin_id_in_request_body()
				throws FileNotFoundException, IOException {
			request = given().spec(requestSpecification()).body(UserModuledata.userPUTdata())
					.header("Authorization", "Bearer " + IdHolder.token).pathParam("userId", 6765);
		}

		@When("Admin sends HTTPS request with endpoiint with update Admin with invalid AdminID")
		public void admin_sends_https_request_with_endpoiint_with_update_admin_with_invalid_admin_id() {

			response = request.when().put(routes.getString("update_UpdateUser"));
			asString = response.then().log().all().extract().asString();
			logger.info("-------404 update user by AdminId----");
		}
		// ----@PUT_Admin_info_400----

		@Given("Admin creates PUT Request with valid AdminID and invalid data")
		public void admin_creates_put_request_with_valid_admin_id_and_invalid_data()
				throws FileNotFoundException, IOException {
			request = given().spec(requestSpecification()).body(UserModuledata.userPUTRoleStatusdata())
					.header("Authorization", "Bearer " + IdHolder.token).pathParam("userId", IdHolder.userId1);
			
		}

		@When("Admin sends HTTPS request with endpoiint with update Admin with AdminID and invalid data")
		public void admin_sends_https_request_with_endpoiint_with_update_admin_with_admin_id_and_invalid_data() {
			response = request.when().put(routes.getString("update_UpdateUser"));
			asString = response.then().log().all().extract().asString();
			logger.info("-------400 Bad Request update AdminId with invalid data----");
		}

		@Then("Admin receives status {string} Bad Request")
		public void admin_receives_status_bad_request(String statuscode) {
			assertEquals(statuscode, response.getStatusCode());
			logger.info("====400 Bad Request=======");
		}

		// ---@Put_byRoleId_400----

		@Given("Admin creates PUT Request with invalid Adminid by roleID")
		public void admin_creates_put_request_with_invalid_adminid_by_role_id() throws FileNotFoundException {
			logger.info("---Negative update user by RoleId---");
			request = given().spec(requestSpecification()).body(UserModuledata.updateUserbyRoleID())
					.header("Authorization", "Bearer " + IdHolder.token).pathParam("userID", 8743);
		}

		@When("Admin sends HTTPS Request for update User by roleId with invalid Adminid")
		public void admin_sends_https_request_for_update_user_by_role_id_with_invalid_adminid() {
			response = request.when().put(routes.getString("update_UserRoleID")).then().log().all().extract().response();
			
		}
		
		// ---@Put_byRoleId_400----
		
		@Given("Admin creates PUT Request with already existing Adminid by roleID")
		public void admin_creates_put_request_with_already_existing_adminid_by_role_id() throws FileNotFoundException {
			request = given().spec(requestSpecification()).body(UserModuledata.updateUserbyRoleID())
					.header("Authorization", "Bearer " + IdHolder.token).pathParam("userID", IdHolder.userId1);
		}

		@When("Admin sends HTTPS Request for update User by roleId with already existing Adminid")
		public void admin_sends_https_request_for_update_user_by_role_id_with_already_existing_adminid() {
			response = request.when().put(routes.getString("update_UserRoleID")).then().log().all().extract().response();
		}

		// ---@Put_byRoleId_400----
		
		@Given("Admin creates PUT Request with invalid request body by roleID")
		public void admin_creates_put_request_with_invalid_request_body_by_role_id() throws FileNotFoundException {
			logger.info("---PUT Request with invalid request body by roleID----");
			request = given().spec(requestSpecification())
					.header("Authorization", "Bearer " + IdHolder.token).pathParam("userID", IdHolder.userId1);
		}

	//===========================================================================================================================
		@Then("Admin receives status {int} with Not Found error message")
		public void admin_receives_status_with_not_found_error_message(Integer int1) {
			assertEquals(404,response.getStatusCode());
			assertEquals(response.header("Content-Type"),"application/json");
		}
		
		@Given("Admin creates GET Request without Authorization")
		public void admin_creates_get_request_without_authorization() throws FileNotFoundException {
			request=given()
					.spec(requestSpecification());
		}
		
		@Then("Admin receives {int} Bad Request Status with error message")
		public void admin_receives_bad_request_status_with_error_message(Integer int1) {
			assertEquals(400,response.getStatusCode());
		}
		
		@When("Admin sends HTTPS Request with invalid endpointId")
		public void admin_sends_https_request_with_invalid_endpoint_id() {
			response = request.when()
					 .get(routes.getString("Invalid_Endpoint_Id"))
					 .then().log().all().extract().response();
			    System.out.println(response);
		}
		@Given("Admin creates GET Request with valid AdminId and with No Auth")
		public void admin_creates_get_request_with_valid_admin_id_and_with_no_auth() throws FileNotFoundException {
			request=given()
					.spec(requestSpecification())
					.pathParam("id", IdHolder.userId1);
		}
		@Given("Admin creates GET Request with invalid AdminId")
		public void admin_creates_get_request_with_invalid_admin_id() throws FileNotFoundException {
			request=given()
					.spec(requestSpecification())
					.headers("Authorization","Bearer "+IdHolder.token)
					.pathParam("id", IdHolder.invalidId);
		}
		
		@When("Admin sends HTTPS Request with endpoint as Alluserswithroles")
		public void admin_sends_https_request_with_endpoint_as_alluserswithroles() {
			response = request.when()
					 .get(routes.getString("Get_allUserswithroles"))
					 .then().log().all().extract().response();
			    System.out.println(response);
			    logger.info("Request to get all users with Roles");
		}
		
		@Given("Admin creates POST request with all mandatory fields and additional fields")
		public void admin_creates_post_request_with_all_mandatory_fields_and_additional_fields() throws FileNotFoundException, IOException {
			request=given()
					.spec(requestSpecification())
					.headers("Authorization","Bearer "+IdHolder.token)
					.body(UserPostInvaliddata.dataBuild());
			      logger.info("Create User with Invalid data");
		}
		@Given("Admin creates POST request with missing mandatory fields in request body")
		public void admin_creates_post_request_with_missing_mandatory_fields_in_request_body() throws FileNotFoundException, IOException {
			request=given()
					.spec(requestSpecification())
					.headers("Authorization","Bearer "+IdHolder.token)
					.body(UserpostMissManddata.dataBuild());
			    logger.info("Create User with Missing data");
		}
		@Given("Admin creates POST request with all mandatory fields and additional fields with No Auth")
		public void admin_creates_post_request_with_all_mandatory_fields_and_additional_fields_with_no_auth() throws FileNotFoundException, IOException {
			request=given()
					.spec(requestSpecification())
					//.headers("Authorization","Bearer "+IdHolder.token)
					.body(UserModuledata.usermodulePostdata());
		}
		
		@When("Admin sends a HTTPS Request with endpoint no authorization")
		public void admin_sends_a_https_request_with_endpoint_no_authorization() {
			response = request
					.when().get(routes.getString("Get_countofactiveandinactiveusers"))
					.then().log().all().extract().response();
					//.spec(resSpecBuilder()).statusCode(401).log().all().extract().response();
		 logger.info("===========Negative no authorization =====================  ");
		}
		
		//---@Put_byLoginStatus_400---
		
		@Given("Admin creates PUT Request with Admin login status by Admin ID with invalid request body")
		public void admin_creates_put_request_with_admin_login_status_by_admin_id_with_invalid_request_body() throws FileNotFoundException {
			logger.info("---400 update user by LoginEmail/Status---");
			request = given().spec(requestSpecification()).body(UserModuledata.updateUserByLoginStatus_invalidBody())
					.header("Authorization", "Bearer " + IdHolder.token).pathParam("userID", IdHolder.userId1);
		}

		@When("Admin sends HTTPS Request for update User with Admin login status by Admin ID with invalid request body")
		public void admin_sends_https_request_for_update_user_with_admin_login_status_by_admin_id_with_invalid_request_body() {
			response = request.when().put(routes.getString("update_UserLoginStatus")).then().log().all().extract().response();
		}


//---@Put_byLoginStatus_404---

@Given("Admin creates PUT Request with Admin login status by Admin ID with invalid AdminId")
public void admin_creates_put_request_with_admin_login_status_by_admin_id_with_invalid_admin_id() throws FileNotFoundException {
	logger.info("---404 update user by LoginEmail/Status---");
	request = given().spec(requestSpecification()).body(UserModuledata.updateUserByLoginStatus())
			.header("Authorization", "Bearer " + IdHolder.token).pathParam("userID", IdHolder.inactive_id);
}

@When("Admin sends HTTPS Request for update User with Admin login status by Admin ID with invalid AdminId")
public void admin_sends_https_request_for_update_user_with_admin_login_status_by_admin_id_with_invalid_admin_id() {
	response = request.when().put(routes.getString("update_UserLoginStatus")).then().log().all().extract().response();
	
}

//---@Put_byLoginStatus_401---

@Given("Admin creates PUT Request with Admin login status by Admin ID with NoAuth")
public void admin_creates_put_request_with_admin_login_status_by_admin_id_with_no_auth() throws FileNotFoundException {
	logger.info("---404 update user by LoginEmail/Status---");
	request = given().spec(requestSpecification()).body(UserModuledata.updateUserByLoginStatus())
			.pathParam("userID",IdHolder.userId1);
}

@When("Admin sends HTTPS Request for update User with Admin login status by Admin ID with NoAuth")
public void admin_sends_https_request_for_update_user_with_admin_login_status_by_admin_id_with_no_auth() {
	response = request.when().put(routes.getString("update_UserLoginStatus")).then().log().all().extract().response();
	
}

//------------------------------------Delete user negative Scenarios--------------------------------------

		//---@Del_User_404---
		
		@Given("Admin creates DELETE Request with Admin with invalid Admin ID")
		public void admin_creates_delete_request_with_admin_with_invalid_admin_id() throws FileNotFoundException {
			logger.info("---404 Delete User by userId---");
			request = given().spec(requestSpecification()).header("Authorization", "Bearer " + IdHolder.token)
					.pathParam("userID",1387);
		}

		@When("Admin sends HTTPS Request for delete User with invalid Admin ID")
		public void admin_sends_https_request_for_delete_user_with_invalid_admin_id() {
			response = request.when().put(routes.getString("delete_DeleteUser")).then().log().all().extract().response();
			logger.info("=========== DELETE Request with Admin with invalid Admin ID=====================  ");
		}

		//---@Del_User_401---
		
		@Given("Admin creates DELETE Request with Admin with NoAuth")
		public void admin_creates_delete_request_with_admin_with_no_auth() throws FileNotFoundException {
			logger.info("---404 Delete User by userId---");
			request = given().spec(requestSpecification())
					.pathParam("userID",IdHolder.userId1);
		}

		@When("Admin sends HTTPS Request for delete User with NoAuth")
		public void admin_sends_https_request_for_delete_user_with_no_auth() {
			response = request.when().delete(routes.getString("delete_DeleteUser")).then().log().all().extract().response();
		
			logger.info("=========== DELETE HTTPS Request for delete User with NoAuth=====================  ");
		}

		//---@Del_User_404---
		
		@Given("Admin creates DELETE Request with Admin with invalid endpoint")
		public void admin_creates_delete_request_with_admin_with_invalid_endpoint() throws FileNotFoundException {
			logger.info("---404 Delete User by userId---");
			request = given().spec(requestSpecification()).header("Authorization", "Bearer " + IdHolder.token)
					.pathParam("userID",IdHolder.userId1);
		}

		@When("Admin sends HTTPS Request for delete User with invalid endpoint")
		public void admin_sends_https_request_for_delete_user_with_invalid_endpoint() {
			response = request.when().delete(routes.getString("invalidUrl")).then().log().all().extract().response();
			
			logger.info("=========== DELETE HTTPS Request for invalid endpoint=====================  ");
		}
		
		// ---@Del_User_405---

		@When("Admin sends HTTPS DELETE Request for delete User with invalid endpoint")
		public void admin_sends_https_delete_request_for_delete_user_with_invalid_endpoint() {
			response = request.when().delete(routes.getString("Get_Alluser")).then().log().all().extract().response();
		}

		@Then("Admin receives status {string} with Method Not Allowed message")
		public void admin_receives_status_with_method_not_allowed_message(String statuscode) {
			assertEquals(statuscode, response.getStatusCode());
			logger.info("====405 Method Not Allowed=======");
		}
		
		@Then("Admin receives status {int} with Unauthorized message")
		public void admin_receives_status_with_unauthorized_message(Integer int1) {
			assertEquals(401,response.getStatusCode());
		}
	
	
	}
