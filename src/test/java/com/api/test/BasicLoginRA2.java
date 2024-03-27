package com.api.test;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class BasicLoginRA2 {

	public static void main(String[] args) {

		RestAssured.baseURI="http://139.59.91.96:9000/v1";
		String body="{\r\n"
				+ "    \"username\": \"iamfd\",\r\n"
				+ "    \"password\": \"password\"\r\n"
				+ "}";
		Header myheader=new Header("Content-Type","application/json");
		RequestSpecification request = RestAssured.given();
		Response response = request.header(myheader).body(body).post("login");
		System.out.println(response.asPrettyString());
	}

}
