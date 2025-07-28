package GETAPITestWithBDD;

import static io.restassured.RestAssured.*;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.*;

import java.util.HashMap;
import java.util.Map;

import io.restassured.RestAssured;

public class Session02_03_GetUserWithPathParamTest {
	
	@DataProvider
	public Object[][] getUserData() {
		return new Object[][] {
			{8036392,"Quas nobis vinco pecunia vere."},
			{8036421,"Nisi crux careo accusantium cunae aestas inventore dicta."}
		};
	}
	
	@Test(dataProvider = "getUserData")
	public void getUserPostWithPathParamsTest(int userID,String title) {
		
		RestAssured.baseURI = "https://gorest.co.in";
		
		given().log().all()
			.header("Authorization","Bearer 3c233d8cb9f7c3800dce7e30e7d739fd395b4df1d94f6d0a7f389c30ee88a141")
			.pathParam("userid", userID)
			
			.when()
				.get("/public/v2/users/{userid}/posts")
			.then().log().all()
				.assertThat()
					.statusCode(200)
					.and()
					.body("title", hasItem(title));
	}
	
	
	@Test
	public void getUserPostWithPathParamsUsingHashMapTest() {
		
		RestAssured.baseURI = "https://reqres.in/";
		
		//https://reqres.in/
//		/api
//		/user
//		?page=2
		
		Map<String, String> pathParamMap=new HashMap<String, String>();
		pathParamMap.put("firstPath", "api");
		pathParamMap.put("secondPath", "users");
		
		given().log().all()
			.pathParams(pathParamMap)
			.queryParam("page", 2)	
			.when()
				.get("/{firstPath}/{secondPath}")
			.then().log().all()
				.assertThat()
					.statusCode(200);
	}


}
