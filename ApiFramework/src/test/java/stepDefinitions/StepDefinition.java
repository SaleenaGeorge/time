package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.RegisterDevice;
import resourses.TestDataBuildDevices;
import resourses.Utils;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;

public class StepDefinition extends Utils{
	
	RequestSpecification res;
	ResponseSpecification resspec;
	Response response;
	static String device_id;
	TestDataBuildDevices data=new TestDataBuildDevices();
	

	@Given("Register device pay load with {string} {string} {string} {string} {string} {string}")
	public void register_device_pay_load_with(String access_id, String device_name, String station_type, String user_identifier, String user_identifiertype, String contact_list) throws IOException {
	res=RestAssured.given().spec(requestSpecification()).body(data.registerDevicePayLoad(access_id, device_name, station_type, user_identifier, user_identifiertype, contact_list));
	    }
	
	@When("user calls {string} with {string} request")
	public void user_calls_with_request(String endpoint, String requestMethod)  throws IOException {
		resspec=new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		if(requestMethod.equalsIgnoreCase("POST"))
		response= res.when().post(getGlobalValue(endpoint));
		else if(requestMethod.equalsIgnoreCase("GET"))
		response= res.when().get(getGlobalValue(endpoint));
		else if(requestMethod.equalsIgnoreCase("delete"))
		response= res.when().delete(getGlobalValue(endpoint)+"/"+device_id);
	}
	
	@Then("the API call is success with status code {int}")
	public void the_api_call_is_success_with_status_code(Integer int1) {
		assertEquals(response.getStatusCode(),200);
	}
	
	@Then("{string} in response body is {string}")
	public void in_response_body_is(String keyValue,String value) {
		assertEquals(getJsonPath(response,keyValue).toString(),value);
	}
	
	@Then("verify device_id created maps to {string} using {string}")
	public void verify_device_id_created_maps_to_using(String expecteddisplay_name, String endpoint) throws IOException {
	    device_id=getJsonPath(response,"id");
	    res=RestAssured.given().spec(requestSpecification());
	    response= res.when().get(getGlobalValue(endpoint)+"/"+device_id);
	    
	    String actualdisplay_name=getJsonPath(response,"station_name");
	    assertEquals(actualdisplay_name,expecteddisplay_name);
	}
	
	@Given("Delete device request")
	public void delete_device_request() throws IOException {
		 res=RestAssured.given().spec(requestSpecification());
	}
}
