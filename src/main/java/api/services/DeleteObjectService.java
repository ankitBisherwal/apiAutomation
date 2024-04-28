package api.services;

import api.helper.RestBase;
import io.restassured.response.Response;
import java.util.*;

public class DeleteObjectService extends RestBase{
	
	String apiUrl = "https://api.restful-api.dev/objects/{objectId}";
	private Map<String, String> pathParams = new HashMap<>();
	
	/**
	 * This method is used to delete an object
	 * @param objectId
	 * @return
	 */
	public Response deleteObject(String objectId) {
		
		pathParams.put("objectId", objectId);
        
        try {
            Response res = getResponse(RequestType.DELETE, apiUrl, null, pathParams, 200);
            return res;
        } catch (Exception e) {
        	System.out.println("Error sending DELETE request: " + e);
            return null;
        }
	
	}
}
