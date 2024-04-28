package api.services;

import api.helper.RestBase;
import api.requests.AddObjectRequest;
import api.requests.AddObjectRequestData;
import io.restassured.response.Response;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


public class AddObjectService extends RestBase{
	
	AddObjectRequest addObjectRequest = new AddObjectRequest();
	AddObjectRequestData addObjectRequestData = new AddObjectRequestData();
	
	String apiUrl = "https://api.restful-api.dev/objects";
	
	ObjectMapper mapper = new ObjectMapper();
	
	/**
	 * This method is used to create a new object and return the response
	 * @return
	 * @throws JsonProcessingException
	 */
	public Response addNewObject() throws JsonProcessingException {
        
        String requestBody = null;

        try {
            
            addObjectRequest.setName("Apple MacBook Pro M2");
            
            setObjectData(addObjectRequestData, 2021, 2000.99, "M1 chip", "1 TB");
            addObjectRequest.setData(addObjectRequestData);

            addObjectRequest.setData(addObjectRequestData);
            requestBody = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(addObjectRequest);
        } catch (JsonProcessingException e) {
            System.out.println("Error processing JSON: " + e);
            return null;
        }

        try {
            Response res = getResponse(RequestType.POST, apiUrl, requestBody, null, 200);
            return res;
        } catch (Exception e) {
        	System.out.println("Error sending POST request: " + e);
            return null;
        }
	
	}
	
	/**
	 * This method is used to create a new object and return the id of that object
	 * @return
	 * @throws JsonProcessingException
	 */
	public String addNewObjectAndReturnId() throws JsonProcessingException {
		
        String requestBody = null;
        Response res = null;
        String objectId = null;

        try {
            addObjectRequest.setName("Apple MacBook Pro M2");
            setObjectData(addObjectRequestData, 2023, 2049.99, "M2 chip", "1 TB");
            addObjectRequest.setData(addObjectRequestData);

            requestBody = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(addObjectRequest);
            res = getResponse(RequestType.POST, apiUrl, requestBody, null, 200);

            objectId = res.path("id");
        } catch (Exception e) {
            System.out.println("Error during API request or response handling" + e);
        }

        return objectId;
		
	}
	
	/**
	 * This method is used to set request data
	 * @param dataObject
	 * @param year
	 * @param price
	 * @param cpuModel
	 * @param hardDiskSize
	 */
	private void setObjectData(AddObjectRequestData dataObject, int year, double price, String cpuModel, String hardDiskSize) {
		
		dataObject.setYear(year);
		dataObject.setPrice(price);
		dataObject.setCpuModel(cpuModel);
		dataObject.setHardDiskSize(hardDiskSize);
		
	}
	
	

}
