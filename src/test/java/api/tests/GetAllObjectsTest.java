package api.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.*;

import api.services.GetObjectsService;
import io.restassured.response.Response;

public class GetAllObjectsTest {
	
	@Test
	public void getAllObjects() {
		
		GetObjectsService getAllObjectsService = new GetObjectsService();
		
		Response res = getAllObjectsService.getAllObjects();
		
		System.out.println("***************** Response of getting all object is *********************");
		System.out.println(res.asString());
		
		List<JsonObject> dataObjects = res.jsonPath().getList("");
		int size = dataObjects.size();
		Assert.assertEquals(size, 13, "Data is incomplete");
		
	}

}
