package resources;

import static io.restassured.RestAssured.given;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utils {
public static	RequestSpecification request;
	
	public RequestSpecification requestSpecification() throws IOException {
		if(request==null) {
PrintStream log=new PrintStream(new File("logging.txt"));
            RestAssured.baseURI="https://rahulshettyacademy.com";
		    request=new RequestSpecBuilder().setBaseUri(getGlobalValue("baseUrl")).addQueryParam("key", "qaclick123")
		    		.addFilter(RequestLoggingFilter.logRequestTo(log))
		    		.addFilter(ResponseLoggingFilter.logResponseTo(log))
		    		.setContentType(ContentType.JSON).build();
	        return request;
		}
		return request;
		
	}
	
	//2
	
	
	public static String getGlobalValue(String key) throws IOException {
		Properties prop=new Properties();
		FileInputStream fis=new FileInputStream("C:\\Users\\Sandeep\\Desktop\\MadhuriStudyMaterials\\MadhuriNewWorkspace\\RestAssuredBDDFramework\\src\\test\\java\\resources\\global.properties");
		prop.load(fis);
		
		return prop.getProperty(key);
		
		
		
	}
	
	public String getJsonPath(Response response,String key) {
		String resp=response.asString();
		JsonPath js=new JsonPath(resp);
		return js.get(key).toString();
		
	}

}
