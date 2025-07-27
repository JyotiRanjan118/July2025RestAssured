package GETAPITestWithBDD;

import static io.restassured.RestAssured.*;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class GoRestAPITest {
	
	@Test
	public void getSingleUser() {
		RestAssured.baseURI="https://gorest.co.in";
		
		Response response=given()
		 	.header("Authorization","Bearer 3c233d8cb9f7c3800dce7e30e7d739fd395b4df1d94f6d0a7f389c30ee88a141")
		 		.when()
		 			.get("/public/v2/users/8034302");
		
		System.out.println("status code "+response.statusCode());
		System.out.println("status code "+response.statusLine());
		
		Assert.assertEquals(response.statusCode(), 200);
		Assert.assertTrue(response.statusLine().contains("200 OK"));
		
		response.prettyPrint();
		
		JsonPath js=response.jsonPath();
		
		int userId=js.getInt("id");
		System.out.println("user id: "+userId);
		Assert.assertEquals(userId, 8034302);
		
		String name=js.getString("name");
		System.out.println("user name "+name);
		Assert.assertEquals(name, "API Automation");
		
		String status= js.getString("status");
		System.out.println("status of user "+status);
		Assert.assertEquals(status, "active");
		
	}
	
	@Test
	public void getAllUserTest() {
		RestAssured.baseURI="https://gorest.co.in";
		
		Response response=given()
		 	.header("Authorization","Bearer 3c233d8cb9f7c3800dce7e30e7d739fd395b4df1d94f6d0a7f389c30ee88a141")
		 		.when()
		 			.get("/public/v2/users");
		
		System.out.println("status code "+response.statusCode());
		System.out.println("status code "+response.statusLine());
		
		Assert.assertEquals(response.statusCode(), 200);
		Assert.assertTrue(response.statusLine().contains("200 OK"));
		
		response.prettyPrint();
		
		JsonPath js=response.jsonPath();
		
		List<Integer> idList=js.getList("id");
		System.out.println("List of the IDs "+idList);
		
		List<String> names=js.getList("name");
		System.out.println("List of names "+names);
		
	}
	

}
