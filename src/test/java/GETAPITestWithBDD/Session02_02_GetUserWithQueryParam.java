package GETAPITestWithBDD;

import static io.restassured.RestAssured.*;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class Session02_02_GetUserWithQueryParam {
	
	@Test
	public void getUserTestWithQueryParam() {
		
		RestAssured.baseURI = "https://gorest.co.in";
		
		given().log().all()
			.header("Authorization" , "Bearer 3c233d8cb9f7c3800dce7e30e7d739fd395b4df1d94f6d0a7f389c30ee88a141")
			.queryParam("name", "naveen")
			.queryParam("status", "active")
				.when()
					.get("/public/v2/users")
						.then().log().all()
							.assertThat()
								.statusCode(200)
									.and()
										.contentType(ContentType.JSON);
		
		
		
	}
	
	@Test
	public void getUserTestWithQueryParamUsingMap() {
		
		RestAssured.baseURI = "https://gorest.co.in";
		
		Map<String, String> userQueryMap=new HashMap<String, String>();
		userQueryMap.put("name", "naveen");
		userQueryMap.put("status", "active");
		userQueryMap.put("gender", "female");
		
		given().log().all()
			.header("Authorization" , "Bearer 3c233d8cb9f7c3800dce7e30e7d739fd395b4df1d94f6d0a7f389c30ee88a141")
			.queryParams(userQueryMap)
				.when()
					.get("/public/v2/users")
						.then().log().all()
							.assertThat()
								.statusCode(200)
									.and()
										.contentType(ContentType.JSON);
		
		
		
	}

}
