package GETAPITestWithBDD;

import static io.restassured.RestAssured.*;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import static org.hamcrest.Matchers.*;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class Session01_01_ContactAPITest {
	
	@BeforeMethod
	public void setUp() {
		RestAssured.baseURI = "https://thinking-tester-contact-list.herokuapp.com";
	}
	
	@Test
	public void getContactAPITest() {
	
		given().log().all()
			.header("Authorization", "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI2ODg1Yzk5ZjkwMTcyYzAwMTUyMTNmZTMiLCJpYXQiOjE3NTM1OTg4OTB9.-B7X0_BZgFrbnX86x-PyHhQbD5EMKeTKbfi3_5hqsws")
				.when()
					.get("/contacts")
						.then().log().all()
							.assertThat()
								.statusCode(200)
									.and()
										.contentType(ContentType.JSON);
	}
	
	
	@Test
	public void getContactAPIAuthErrorTest() {
		
		given().log().all()
			.header("Authorization", "Bearer --jyoti")
					.when()
						.get("/contacts")
							.then().log().all()
								.assertThat()
									.statusCode(401);
		}
	
	@Test
	public void getContactInvalidTokenTest() {
		
		String errorMsg=given().log().all()
					.header("Authorization", "Bearer --Testing")
						.when()
						  .get("/contacts")
							.then().log().all()
								.extract()
									.path("error");
		
		System.out.println(errorMsg);
		Assert.assertEquals(errorMsg, "Please authenticate.");
		
	}
}
