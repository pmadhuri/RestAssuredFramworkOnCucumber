package stepDefinations;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.cucumber.core.resource.Resource;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.AddPlace;
import pojo.Location;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

public class StepDefination extends Utils {
	RequestSpecification requ;
	ResponseSpecification res;
	Response response;
	TestDataBuild data=new TestDataBuild();
	 static String place_ID;

@Given("add place payload with Payload {string} {string} {string}")
public void add_place_payload_with_payload(String name, String language, String address) throws IOException {
	    
		requ=given().spec(requestSpecification()).body(data.addPlacePayload(name,language,address));	
		
		//*******step is not more requred bcz 200 validated below method************res=new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		
	
	   
	}

@When("user calla {string} with {string} http request")
public void user_calla_with_http_request(String resources, String method) {
	    // Write code here that turns the phrase above into concrete actions
		APIResources resorceAPI=APIResources.valueOf(resources);
		System.out.println(resorceAPI.getResource());
		
		
		res=new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		 if(method.equalsIgnoreCase("Post")) {
			 response=requ.when().post(resorceAPI.getResource()); 
			  }else if(method.equalsIgnoreCase("get")) 
				  response=requ.when().post(resorceAPI.getResource());
			  else if(method.equalsIgnoreCase("delete"))
				  response=requ.when().post(resorceAPI.getResource());
			  
		 
				 //.then().spec(res).extract().response();
		
	    
	}
	@Then("the api call is success with status code {int}")
	public void the_api_call_is_success_with_status_code(Integer int1) {
	    // Write code here that turns the phrase above into concrete actions
		assertEquals(response.getStatusCode(), 200);
	   
	}
	@Then("{string} code in response body is {string}")
	public void code_in_response_body_is(String key, String ExpectedValue) {
	    // Write code here that turns the phrase above into concrete actions
//		String resp=response.asString();
//		JsonPath js=new JsonPath(resp);
	//	js.get(key);
		assertEquals(getJsonPath(response, key), ExpectedValue);
		}
	

@Then("Verify place_ID created maps to {string} using {string}")
public void verify_place_id_created_maps_to_using(String expectedName, String resources) throws IOException {
	 place_ID=getJsonPath(response, "place_id");
	requ=given().spec(requestSpecification()).queryParam("place_id", place_ID);
	 user_calla_with_http_request(resources, "GET");
	 String ActualName=getJsonPath(response, "name");
	 assertEquals(ActualName, expectedName);
	 
  
}

@Given("^DeletePlace Payload$")
public void deleteplace_payload() throws Throwable {
 requ =given().spec(requestSpecification()).body(data.deletePlacePayload(place_ID));
}

}
