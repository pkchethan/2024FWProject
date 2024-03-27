package com.api.test;

import com.api.pojo.LoginRequestPOJO;
import static com.util.Helper.*;

import static io.restassured.RestAssured.*;

import static org.hamcrest.Matchers.*;

import java.io.File;

import io.restassured.http.Header;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class BasicLoginRA {
	static {
		baseURI="http://139.59.91.96:9000/v1";
		}
	public static void main(String[] args) {
		
		File schemaFile=new File(System.getProperty("user.dir")+"/src/test/resources/LoginSchema.json");
		
		
		//Body from login POJO serialised
		LoginRequestPOJO login=new LoginRequestPOJO("iamfd", "password");
		Header myheader=new Header("Content-Type","application/json");
		String token=	given()
			//.when()
				.header(myheader)
		//	.and()
				.body(convertToJson(login))
		//	.and()
				.log().all()       //Till this all the request details will be logged
				.post("login")
			.then()
				.log().all()      //response related details wil be logged
				.assertThat().statusCode(200)
			.and()
				.time(lessThan(2000L))
			.and()
				.body("message", equalToIgnoringCase("Success"))
			.and()
				.body(JsonSchemaValidator.matchesJsonSchema(schemaFile))  //Json Schema Validaiton
			.and()
				.extract().jsonPath().getString("data.token");
		
		System.out.println("Token Extracted is: "+token);
	}

}
