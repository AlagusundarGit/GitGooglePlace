package stepDefinations;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import pojo.GoogleReqBody;
import pojo.Location;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class StepDefination extends Utils {
	
	ResponseSpecification resSpec;
	RequestSpecification req;
	Response response;
	TestDataBuild data=new TestDataBuild();
	static String place_id;

	
	@Given("Add Place Payload")
	public void add_place_payload() throws IOException {
		
		req=given().spec(requestSpecification()).body(data.addPlacePayload());
	}

	@Given("Add Place Payload with {string} {string} {string}")
	public void add_place_payload_with(String name, String language, String address) throws IOException {
	
		req=given().spec(requestSpecification()).body(data.addPlacePayloadOutline(name, language, address));
	}
	
	@When("user calls {string} with {string} http request")
	public void user_calls_with_http_request(String resource, String method) {
		//contructor will be called with valueof resource which you pass
		APIResources resourceAPI=APIResources.valueOf(resource);
		System.out.println(resourceAPI.getResource());
		resSpec=new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		if(method.equalsIgnoreCase("POST"))
		response=req.when().post(resourceAPI.getResource())
				.then().spec(resSpec).extract().response();
		else if(method.equalsIgnoreCase("GET"))
			response=req.when().get(resourceAPI.getResource())
			.then().spec(resSpec).extract().response();
	}
	
	@Then("The API call is success with status code {int}")
	public void the_api_call_is_success_with_status_code(Integer int1) {
		assertEquals(response.getStatusCode(), 200);
		System.out.println(response.asPrettyString());
	}
	
	@Then("{string} in response body is {string}")
	public void in_response_body_is(String keyValue, String Expectedvalue) {
	    assertEquals(getJsonPath(response, keyValue),Expectedvalue);
	    System.out.println(getJsonPath(response, keyValue));
	    System.out.println("Add");
	}
	
	@Then("verify place_Id created maps to {string} using {string}")
	public void verify_place_id_created_maps_to_using(String expectedName, String resource) throws IOException {
		place_id=getJsonPath(response, "place_id");
		req=given().spec(requestSpecification()).queryParam("place_id", place_id);
		user_calls_with_http_request(resource, "GET");
		String actualName=getJsonPath(response, "name");
		assertEquals(expectedName, actualName);
		System.out.println("Get");
	}
	
	@Given("Delete Place Payload")
	public void delete_place_payload() throws IOException {
		req=given().spec(requestSpecification()).body(data.deletePlacePayload(place_id));
		System.out.println("Delete");
	}
	
	
	
}
