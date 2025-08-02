package GETAPIWithNonBDD;

import java.util.Iterator;
import java.util.List;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Session03_01_GetContactsAPITest {

	@Test
	public void getContactsTest() {

		RestAssured.baseURI = "https://thinking-tester-contact-list.herokuapp.com";

		RequestSpecification request = RestAssured.given();

		request.header("Authorization",
				"Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI2ODg1Yzk5ZjkwMTcyYzAwMTUyMTNmZTMiLCJpYXQiOjE3NTM1OTg4OTB9.-B7X0_BZgFrbnX86x-PyHhQbD5EMKeTKbfi3_5hqsws");

		Response response = request.get("/contacts");

		System.out.println(response.statusCode());
		System.out.println(response.statusLine());

		response.prettyPrint();

		String contentType = response.header("Content-Type");
		System.out.println(contentType);

		Headers headers = response.headers();
		List<Header> headerList = headers.asList();
		System.out.println(headerList.size());

		for (Header e : headerList) {
			String name = e.getName();
			String value = e.getValue();

			System.out.println(name + " " + value);

		}

	}
}
