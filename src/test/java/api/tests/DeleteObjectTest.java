package api.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import api.services.AddObjectService;
import api.services.DeleteObjectService;
import io.restassured.response.Response;

public class DeleteObjectTest {
	
	@Test
	public void deleteObject() throws JsonProcessingException {
		
		AddObjectService addObjectService = new AddObjectService();
		String objectId = addObjectService.addNewObjectAndReturnId();
		
		//Assertions
		Assert.assertTrue(objectId.matches("^[a-zA-Z0-9]+$"), "Id is not correct");
		
		DeleteObjectService deleteObjectService = new DeleteObjectService();
		
		Response res = deleteObjectService.deleteObject(objectId);
		
		System.out.println("***************** Response of deleting a object is *********************");
		System.out.println(res.asString());
		
		//Assertions
		String expectedMessage = "Object with id = " + objectId + " has been deleted.";
		Assert.assertEquals(res.path("message"), expectedMessage, "Object is not deleted");
		
	}

}
