package api.stepdefinitions;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.IOException;
import api.utils.RestUtils;
import api.utils.IdHolder;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import api.resourses.*;

public class UserLogin extends RestUtils {
	
	UserLogindata UserLogindata= new UserLogindata(); 
	RequestSpecification request;
	ResponseSpecification responseSpec;
	Response response;
	public static String token;
	
	@Given("Add UserLogin Payload")
	public void add_user_login_payload() throws FileNotFoundException, IOException {
		
		request=given().spec(requestSpecification()).body( UserLogindata.dataBuild());
		}
	
  @When("Admin calls Post Https method  with valid endpoint")
	public void admin_calls_post_https_method_with_valid_endpoint() {
	
	 response = request.when().post(routes.getString("Post_UserLoginurl")).then().log().all().extract().response();
	
		IdHolder.token =  UserKeyJson(response,"token");
		  System.out.println("Token ="  +IdHolder.token);
	/* token=UserKeyJson(response,"token");
		System.out.println("Token ="  +token);	*/
		
	}

	@Then("Admin receives {int} created with auto generated token")
	public void admin_receives_created_with_auto_generated_token(Integer int1) {
		
		assertEquals(response.getStatusCode(),200);
	}
	
	@When("Admin calls Post Https method  with invalid endpoint")
	public void admin_calls_post_https_method_with_invalid_endpoint() {
		response = request.when().post(routes.getString("Post_Userurl")).then().log().all().extract().response();
	}

	@Then("Admin receives {int} unauthorized")
	public void admin_receives_unauthorized(Integer int1) {
		assertEquals(401,response.getStatusCode());
	}

	@Given("Admin creates request with invalid credentials")
	public void admin_creates_request_with_invalid_credentials() throws FileNotFoundException, IOException {
		request=given().spec(requestSpecification()).body( UserLogindata.invaliddataBuild());
	}

	@Then("Admin receives {int} Bad request")
	public void admin_receives_bad_request(Integer int1) {
		assertEquals(400,response.getStatusCode());
	}


}
