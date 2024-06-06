package api.stepdefinitions;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import api.utils.IdHolder;
import api.utils.RestUtils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class UserLogout extends RestUtils {

	Logger logger = LogManager.getLogger("UserLogout.java");

	RequestSpecification request;
	ResponseSpecification responseSpec;
	Response response;

	//@logout1
	@Given("Admin sets authorization to bearer Token with token")
	public void admin_sets_authorization_to_bearer_token_with_token() throws FileNotFoundException {
	
		request = given().spec(requestSpecification()).header("Authorization","Bearer "+ IdHolder.token);
		
	}
	
	
	@When("Admin calls Get Https method with valid endpoint")
	public void admin_calls_get_https_method_with_valid_endpoint() {
		 response = request.when().get(routes.getString("Get_UserLogouturl")).then().log().all().extract().response();
		 System.out.println(response);
	}

	@Then("Admin receives {int} ok  and response with {string}")
	public void admin_receives_ok_and_response_with(Integer int1, String message) {
		if(response.getStatusCode()==200) {
		assertEquals(message,response.body().asString());
		}
		logger.info("User has logged out successfully");
	}
	//logout2 ----404 Not found-----
	@When("Admin calls Get Https method with invalid endpoint")
	public void admin_calls_get_https_method_with_invalid_endpoint() {
		response = request.when().get(routes.getString("invalidUrl")).then().log().all().extract().response();
		 System.out.println(response);
		 
	}

	@Then("Admin receives {int} Not found")
	public void admin_receives_not_found(int statuscode) {
		logger.info("request sent with invalid endpoint.So it is 404 Not found");
	    assertEquals( response.getStatusCode(),404);
	    
	}

	//@logout3 ----401 unauthorized-----
	@Given("Admin sets No authorization")
	public void admin_sets_no_authorization() throws FileNotFoundException {
		logger.info("Request sent with No Authorization gives 401 unAuthorized");
		request = given().spec(requestSpecification());
		
	}
	
//	@Then("Admin receives status {int} with unauthorized error message	Negative")
//	public void admin_receives_status_with_unauthorized_error_message_negative(Integer int1) {
//		assertEquals(401,response.getStatusCode());
//	}

}

