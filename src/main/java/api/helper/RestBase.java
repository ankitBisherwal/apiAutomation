package api.helper;

import java.util.Map;

import org.testng.Assert;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestBase {
	
	public enum RequestType {
		GET, POST, PUT, DELETE, PATCH;
	}
	
	public static Response getResponse(RequestType reqType, String reqUrl, String reqBody, Map<String, String> pathParam,  int expectedStatusCode) {
		
		RequestSpecification spec = RestAssured.given();
		Response res = null;

		if(reqBody != null) {
			spec.body(reqBody);
			spec.contentType("application/json");
		}
		
		if(pathParam!=null){
			spec.pathParams(pathParam);
		}
		
		
		try {
			switch(reqType) {
			
			case POST:
				res = spec.log().all().when().post(reqUrl);
				break;
			case GET:
				res = spec.log().all().when().get(reqUrl);
				break;
			case PUT:
				res = spec.log().all().when().put(reqUrl);
				break;
			case DELETE:
				res = spec.log().all().when().delete(reqUrl);
				break;
			case PATCH:
				res = spec.log().all().when().patch(reqUrl);
				break;
			default: 
				return null;
			
			}
		}
		catch(Exception e) {
			System.out.println("*******************************  Exception Occured  ****************************** " + e.toString());
		}
		
		Assert.assertEquals(res.statusCode(), expectedStatusCode);
		
		System.out.println(" Api response is : " + res.asString());
		
		return res; 
	}

}
