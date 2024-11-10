package resources;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utils {
	
	public static RequestSpecification reqSpec;
	
	public RequestSpecification requestSpecification() throws IOException {
	
		if(reqSpec==null) {
		PrintStream log=new PrintStream(new FileOutputStream("logging.txt"));	
		reqSpec=new RequestSpecBuilder().setBaseUri(getGlobalValue("baseUrl"))
				.setRelaxedHTTPSValidation()
				.setContentType(ContentType.JSON).addQueryParam("key","qaclick123")
				.addFilter(RequestLoggingFilter.logRequestTo(log))
				.addFilter(ResponseLoggingFilter.logResponseTo(log)).build();
				return reqSpec;
		}
		return reqSpec;
		}
	
	public static String getGlobalValue(String key) throws IOException {
		Properties prob=new Properties();
		FileInputStream fis=new FileInputStream("C:\\Users\\AS00892314\\Downloads\\APIFramework\\2APIFramework\\src\\test\\java\\resources\\Global.properties");
		prob.load(fis);
		return prob.getProperty(key);
	}
	
	public String getJsonPath(Response response,String key) {
		String responseBody=response.asPrettyString();
		JsonPath js=new JsonPath(responseBody);
		return js.get(key).toString();
	}
}
