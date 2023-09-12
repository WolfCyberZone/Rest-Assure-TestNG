import io.restassured.response.Response;
//We need to import statically the given() 
import static io.restassured.RestAssured.given;

import org.testng.asserts.SoftAssert;

import io.restassured.path.json.JsonPath;

//To verify the functionality and correctness of the "Get Employee One" API endpoint.

//Aqui los Datos de las Tablas de Yahira
public class Exercise1 {
	String endpoint ="***********************";
	String id;
	String token = "***********************";

	public static void main(String[] args) {
		
		Exercise1 obj = new Exercise1();
		
		//obj.testcase1();
		
		System.out.println("**************");
	//	obj.testcase2();
		
	//	obj.testcase3();
		obj.testcase4();

	}

	
	//Positive Scenario - Successful Employee Retrieval:
	public void testcase1() {
	id = "105";
	
	Response response;
	
	response = given()
					.header("Authorization", token)
					.queryParam("id", id)
				.when()
					.get(endpoint)
				.then()
					.extract()
					.response();

	int expStatusCode = 200;
	String expFirstName = "John";
	String expLastName = "Cena";
	String expJobTitle = "SDET";
	
	
	//Retrieve the actual result
	
	int actStatusCode = response.getStatusCode();
	
	JsonPath jsonPath = response.jsonPath();
	
	String actFirstName = jsonPath.getString("first_name");
	String actLastName = jsonPath.getString("last_name");
	String actJobTitle = jsonPath.getString("job_id");
 	
	
	//Perfomr Assertions
	SoftAssert softAssertObj =new SoftAssert();
	
	softAssertObj.assertEquals(actStatusCode, expStatusCode, "Status Code Validation Failed");
	softAssertObj.assertEquals(actFirstName, expFirstName, "FirstName Validation Failed");
	softAssertObj.assertEquals(actLastName, expLastName, "LastName Validation Failed");
	softAssertObj.assertEquals(actLastName, expJobTitle, "Job Title Validation Failed");
	
	softAssertObj.assertAll();
	
	}

	
	//Negative Scenario - Invalid Employee ID:
	public void testcase2() {
		id = "12345678";

		Response response;
		
		response = given()
						.header("Authorization", token)
						.queryParam("id", id)
					.when()
						.get(endpoint)
					.then()
						.extract()
						.response();
		
		int expStatusCode = 404;
		String expMessage = "employee was not found";
		
		int actStatusCode = response.getStatusCode();
		
		JsonPath jsonPath = response.jsonPath();
		
 		
		String actMessage = jsonPath.getString("message");
		
		//Perfomr Assertions
		SoftAssert softAssertObj =new SoftAssert();
		
		softAssertObj.assertEquals(actStatusCode, expStatusCode, "Status Code Validation Failed");
		softAssertObj.assertEquals(actMessage, expMessage, "Message Validation Failed");
		
		softAssertObj.assertAll();

	}

	
	//Negative Scenario - Missing Employee ID:
	public void testcase3() {
Response response;
		
		response = given()
						.header("Authorization", token)
					.when()
						.get(endpoint)
					.then()
						.extract()
						.response();
		
		int expStatusCode = 400;
		String expMessage = "employee ID is missing";
		
		int actStatusCode = response.getStatusCode();
		JsonPath jsonPath = response.jsonPath();

		String actMessage = jsonPath.getString("message");
		
		
		//Perfomr Assertions
		SoftAssert softAssertObj =new SoftAssert();
		
		softAssertObj.assertEquals(actStatusCode, expStatusCode, "Status Code Validation Failed");
		softAssertObj.assertEquals(actMessage, expMessage, "Message Validation Failed");
		
		softAssertObj.assertAll();

	}

	//Negative Scenario - Unauthorized Access:
	public void testcase4() {
		id = "105";
		
		Response response;
		
		response = given()
						.queryParam("id", id)
					.when()
						.get(endpoint)
					.then()
						.extract()
						.response();

		int expStatusCode = 401;
		String expMessage = "unauthorized access";

		
		
		int actStatusCode = response.getStatusCode();
		JsonPath jsonPath = response.jsonPath();

		String actMessage = jsonPath.getString("message");
		
		
		//Perfomr Assertions
		SoftAssert softAssertObj =new SoftAssert();
		
		softAssertObj.assertEquals(actStatusCode, expStatusCode, "Status Code Validation Failed");
		softAssertObj.assertEquals(actMessage, expMessage, "Message Validation Failed");
		
		softAssertObj.assertAll();

	}

}