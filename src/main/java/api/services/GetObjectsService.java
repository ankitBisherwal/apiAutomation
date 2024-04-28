package api.services;

import java.util.HashMap;
import java.util.Map;

import api.helper.RestBase;
import api.helper.RestBase.RequestType;
import io.restassured.response.Response;

public class GetObjectsService extends RestBase{

	String apiUrl = "https://api.restful-api.dev/objects";
	private Map<String, String> pathParams = new HashMap<>();
	
	/**
	 * This method is used to get all the objects
	 * @return
	 */
	public Response getAllObjects() {
        
        try {
            Response res = getResponse(RequestType.GET, apiUrl, null, null, 200);
            return res;
        } catch (Exception e) {
        	System.out.println("Error sending GET request: " + e);
            return null;
        }
	
	}
	
	/**
	 * This method is used to get one object using objectId
	 * @param objectId
	 * @return
	 */
	public Response getObjectById(String objectId) {
		
		apiUrl = "https://api.restful-api.dev/objects/{objectId}";
		pathParams.put("objectId", objectId);
		
		try {
            Response res = getResponse(RequestType.GET, apiUrl, null, pathParams, 200);
            return res;
        } catch (Exception e) {
        	System.out.println("Error sending GET request: " + e);
            return null;
        }
		
	}
}
