package GETAPITestWithBDD;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class Session02_01_ProductAPITest {

	@Test
	public void getProductTest() {
		RestAssured.baseURI = "https://fakestoreapi.in/api";

		Response response = given().when().get("/products");

		System.out.println(response.statusCode());
		System.out.println(response.statusLine());

		response.prettyPrint();

		JsonPath js = response.jsonPath();
		List<Integer> ids = js.getList("products.id");
		System.out.println(ids);

		List<Double> priceList = js.getList("products.price");
		System.out.println(priceList);

		List<Double> discountList = js.getList("products.discount");
		System.out.println(discountList);

		for (int i = 0; i < ids.size(); i++) {
			int id = ids.get(i);
			Object price = priceList.get(i);
			Object rate = discountList.get(i);

			System.out.println("ID: " + id + " price " + price + " rate " + rate);
		}

	}
	
	@Test
	public void getProductCountTest() {
		
		RestAssured.baseURI = "https://fakestoreapi.com";
		
			get("/products")
					.then()
						.assertThat()
							.statusCode(200)
								.and()
									.body("$.size()", equalTo(20));
								
		
	}

}
