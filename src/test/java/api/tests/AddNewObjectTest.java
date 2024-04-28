package api.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;

import api.services.AddObjectService;
import api.services.GetObjectsService;
import io.restassured.response.Response;

public class AddNewObjectTest {
	
	@Test
	public void addNewObject() throws JsonProcessingException {
		
		AddObjectService addObjectService = new AddObjectService();
		Response createObjectRes = addObjectService.addNewObject();
		
		String objectId = createObjectRes.path("id");
		
		//Assertions for creation of new object
		float actualPrice = createObjectRes.path("data.price");
		float expectedPrice = 2000.99f;
		
		Assert.assertTrue(createObjectRes.path("id").toString().matches("^[a-zA-Z0-9]+$"), "Id is missing");
		Assert.assertEquals(createObjectRes.path("name"), "Apple MacBook Pro M2", "Name is not correct");
		Assert.assertEquals(createObjectRes.path("data.year"), 2021, "Year is not correct");
		Assert.assertEquals(actualPrice, expectedPrice, 0.01,  "Price is not correct");
		Assert.assertEquals(createObjectRes.path("data['CPU model']"), "M1 chip", "Cpu model is not correct");
		Assert.assertEquals(createObjectRes.path("data['Hard disk size']"), "1 TB", "Hard disk size is not correct");
		
		
		GetObjectsService getObjectsService = new GetObjectsService();
		Response res = getObjectsService.getObjectById(objectId);
		
		System.out.println(res.asString());
		
		actualPrice = res.path("data.price");
		
		//Assertions for getting a object by objectId
		Assert.assertEquals(res.path("id"), objectId, "Id is not correct");
		Assert.assertEquals(res.path("name"), "Apple MacBook Pro M2", "Name is not correct");
		Assert.assertEquals(res.path("data.year"), 2021, "Year is not correct");
		Assert.assertEquals(actualPrice, expectedPrice, 0.01,  "Price is not correct");
		Assert.assertEquals(res.path("data['CPU model']"), "M1 chip", "Cpu model is not correct");
		Assert.assertEquals(res.path("data['Hard disk size']"), "1 TB", "Hard disk size is not correct");
	}

}
