package api.testcases;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints2;
import api.payload.User;
import io.restassured.response.Response;

public class UserTest2 {

	Faker faker;
	User userPayload;
	public Logger logger;
	@BeforeClass
	public void generateTestData() {
		faker = new Faker();
		userPayload= new User();
		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setUsername(faker.name().username());
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		userPayload.setPassword(faker.internet().password(5,10));
		userPayload.setPhone(faker.phoneNumber().cellPhone());
		
		//obtain logger
		logger=LogManager.getLogger("RestAssuredAutomationFramework_test");
		
	}
	
	@Test(priority=1)
	public void testCreateUser() {
		Response response=UserEndPoints2.createUser(userPayload);
		
		//log response
		response.then().log().all();
		
		//validation
		Assert.assertEquals(response.getStatusCode(), 200);
		
		logger.info("Create user exceuted");
	}
	
	@Test(priority=2)
	public void testGetUser() {
		Response response=UserEndPoints2.getUser(this.userPayload.getUsername());
		
		System.out.println("Get User Data");
		//log response
		response.then().log().all();
		
		//validation
		Assert.assertEquals(response.getStatusCode(), 200);
		
		logger.info("Get user exceuted");
	}
	
	@Test(priority=3)
	public void testUpdateUser() {
		
		userPayload.setFirstName(faker.name().firstName());
		Response response=UserEndPoints2.updateUser(this.userPayload.getUsername(),userPayload);
		
		System.out.println("Update User Data");
		//log response
		response.then().log().all();
		
		//validation
		Assert.assertEquals(response.getStatusCode(), 200);
		
		//Read user data to check if first name is updated or not
		Response responsePostUpdate=UserEndPoints2.getUser(this.userPayload.getUsername());
		responsePostUpdate.then().log().all();
		
		logger.info("Update user exceuted");
	}
	
	@Test(priority=4)
	public void testDeleteUser() {
		
		Response response=UserEndPoints2.deleteUser(this.userPayload.getUsername());
		
		System.out.println("Delete User Data");
		//log response
		response.then().log().all();
		
		//validation
		Assert.assertEquals(response.getStatusCode(), 200);
		
		logger.info("Delete user exceuted");
	}
}
