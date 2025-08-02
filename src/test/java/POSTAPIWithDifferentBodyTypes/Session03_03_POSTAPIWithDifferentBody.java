package POSTAPIWithDifferentBodyTypes;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

import java.io.File;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class Session03_03_POSTAPIWithDifferentBody {
	
	@Test
	public void bodyWithTextTest() {
		
		RestAssured.baseURI = "https://postman-echo.com";
		
		String payLoad = "hi this jyoti here";
		
		given().log().all()
			.contentType(ContentType.TEXT)
				.body(payLoad)
				.when()
					.post("/post")
				.then().log().all()
					.assertThat()
						.statusCode(200);
	}
	
	@Test
	public void bodyWithJavaScriptTest() {
		
		RestAssured.baseURI = "https://postman-echo.com";
		
		String payLoad = "<html>\r\n"
				+ "<body>\r\n"
				+ "\r\n"
				+ "<h2>What Can JavaScript Do?</h2>\r\n"
				+ "\r\n"
				+ "<p id=\"demo\">JavaScript can change HTML content.</p>\r\n"
				+ "\r\n"
				+ "<button type=\"button\" onclick=\"document.getElementById('demo').innerHTML = 'Hello JavaScript!'\">Click Me!</button>\r\n"
				+ "\r\n"
				+ "</body>\r\n"
				+ "</html>";
		
		given().log().all()
			.contentType("application/javascript;charset=utf-8")
				.body(payLoad)
				.when()
					.post("/post")
				.then().log().all()
					.assertThat()
						.statusCode(200);
	}
	
	@Test
	public void bodyWithHtmlTest() {
		
		RestAssured.baseURI = "https://postman-echo.com";
		
		String payLoad = "<html>\r\n"
				+ "<body>\r\n"
				+ "\r\n"
				+ "<h2>The kbd Element</h2>\r\n"
				+ "\r\n"
				+ "<p>The kbd element is used to define keyboard input:</p>\r\n"
				+ "\r\n"
				+ "<p>Save the document by pressing <kbd>Ctrl + S</kbd></p>\r\n"
				+ "\r\n"
				+ "</body>\r\n"
				+ "</html>";
		
		given().log().all()
			.contentType(ContentType.HTML)
				.body(payLoad)
				.when()
					.post("/post")
				.then().log().all()
					.assertThat()
						.statusCode(200);
	}
	
	@Test
	public void bodyWithXMLTest() {
		
		RestAssured.baseURI = "https://postman-echo.com";
		
		String payLoad = "<person>\r\n"
				+ "    <name>John Doe</name>\r\n"
				+ "    <age>30</age>\r\n"
				+ "    <gender>Male</gender>\r\n"
				+ "    <contact>\r\n"
				+ "        <email>johndoe@example.com</email>\r\n"
				+ "        <phone>123-456-7890</phone>\r\n"
				+ "    </contact>\r\n"
				+ "</person>";
		
		given().log().all()
			.contentType("application/xml;charset=utf-8")
				.body(payLoad)
				.when()
					.post("/post")
				.then().log().all()
					.assertThat()
						.statusCode(200);
	}
	
	@Test
	public void bodyWithMultiPartTest() {
		
		RestAssured.baseURI = "https://postman-echo.com";
	
		given().log().all()
			.contentType(ContentType.MULTIPART)
			.multiPart("gitCommand",new File("C:\\Users\\Pritiranjan\\Desktop\\Git command.txt"))
			.multiPart("name","jyoti")
		.when()
			.post("/post")
			.then().log().all()
				.assertThat()
					.statusCode(200);
	}
	
	@Test
	public void bodyWithPDFFileTest() {
		
		RestAssured.baseURI = "https://postman-echo.com";
	
		given().log().all()
			.contentType("application/pdf")
			.body(new File("D:\\Software Testing\\IntelliJIDEA_ReferenceCard.pdf"))
		.when()
			.post("/post")
			.then().log().all()
				.assertThat()
					.statusCode(200);
	}
	
	@Test
	public void bodyWithImageFileTest() {
		
		RestAssured.baseURI = "https://postman-echo.com";
	
		given().log().all()
			.contentType("image/jpg")
			.body(new File("C:\\Users\\Pritiranjan\\Downloads\\what-is-a-jpeg-featured.jpg"))
		.when()
			.post("/post")
			.then().log().all()
				.assertThat()
					.statusCode(200);
	}
}
