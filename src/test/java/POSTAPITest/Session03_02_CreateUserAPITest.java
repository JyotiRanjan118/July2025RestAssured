package POSTAPITest;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class Session03_02_CreateUserAPITest {
	
	public String getRandomEmailId() {
		return "apiautomation"+System.currentTimeMillis()+"@opencart.com";
	}

	@Test
	public void createUserWithJSONStringTest() {

		RestAssured.baseURI = "https://gorest.co.in";
		
		String emailID= getRandomEmailId();

		given().log().all()
				.header("Authorization", "Bearer 3c233d8cb9f7c3800dce7e30e7d739fd395b4df1d94f6d0a7f389c30ee88a141")
				.contentType(ContentType.JSON)
				.body(" {\r\n" + "        \"name\": \"nath sahoo\",\r\n"
						+ "        \"email\": \""+emailID+"\",\r\n" + "        \"gender\": \"male\",\r\n"
						+ "        \"status\": \"active\"\r\n" + "        \r\n" + "}")
				.when().post("/public/v2/users").then().log().all().assertThat().statusCode(201);
	}

	@Test
	public void createUserWithJSONFileTest() {

		RestAssured.baseURI = "https://gorest.co.in";

		given().log().all()
				.header("Authorization", "Bearer 3c233d8cb9f7c3800dce7e30e7d739fd395b4df1d94f6d0a7f389c30ee88a141")
				.contentType(ContentType.JSON)
				.body(new File("./src/test/resources/user.json"))
				.when().post("/public/v2/users").then().log().all().assertThat().statusCode(201);
	}
	
	@Test
	public void createUserWithJSONFileWithStringReplaceMent() throws IOException {

		RestAssured.baseURI = "https://gorest.co.in";
		
		String emailID=getRandomEmailId();
		
		String rawJson = new String(Files.readAllBytes(Paths.get("./src/test/resources/user.json")));
		
		String updatedJSON=rawJson.replace("{{email}}", emailID);

		Integer userID=given().log().all()
				.header("Authorization", "Bearer 3c233d8cb9f7c3800dce7e30e7d739fd395b4df1d94f6d0a7f389c30ee88a141")
				.contentType(ContentType.JSON)
				.body(updatedJSON)
				.when().post("/public/v2/users").then().log().all().assertThat().statusCode(201)
					.extract()
						.path("id");
		
		System.out.println("user id :"+userID);
	}
}
