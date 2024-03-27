package com.util;

import com.api.pojo.CreateJobRequestPOJO;
import com.api.pojo.Customer;
import com.api.pojo.Customer_address;
import com.api.pojo.Customer_product;
import com.api.pojo.LoginRequestPOJO;
import com.api.pojo.Problems;
import com.github.javafaker.Faker;
import com.google.gson.Gson;
import static io.restassured.RestAssured.*;

import io.restassured.http.Header;

public class Helper {
	private static Gson gson;
	
	
	public static String convertToJson(Object pojo) {
		gson = new Gson();
		String data=gson.toJson(pojo);
		return data;
	}
	
	public static Object convertBackToJavaObj(String data) {
		gson = new Gson();
		Object obj=gson.fromJson(data, Object.class);
		System.out.println("not entirely a POJO Obj :");
		return obj;		
	}
	public static <T> T convertBackToJavaOtherWay(String data, Class<T> classOfT) {
		gson = new Gson();
		return gson.fromJson(data, classOfT);
	}
	
	public static CreateJobRequestPOJO getCreateJobData() {
		Faker faker= new Faker();
		
		Customer customer= new Customer(faker.name().firstName(), faker.name().lastName(), faker.numerify("99########"), faker.numerify("99########"), faker.internet().emailAddress(), faker.internet().emailAddress());
		Customer_address customer_address = new Customer_address(faker.numerify("##"), faker.name().lastName(), faker.address().firstName(), faker.address().secondaryAddress(), faker.address().city(), "231213", faker.address().country(), faker.address().state());
		Customer_product customer_product= new Customer_product("2023-11-30T18:30:00.000Z", faker.numerify("22############"), faker.numerify("22############"), faker.numerify("22############"), "2023-11-30T18:30:00.000Z", "3", "3");
		Problems problems[]=new Problems[2];
		problems[0]= new Problems(2, "test1");
		problems[1]=new Problems(6,"test2");
		CreateJobRequestPOJO createJobRequestPOJO= new CreateJobRequestPOJO(0, 2, 1, 2, customer, customer_address, customer_product, problems);
		System.out.println("Create Job Request POJO : "+createJobRequestPOJO);
		return createJobRequestPOJO;
		
	}
	
	public static String getTokenFor(String Role) {
		LoginRequestPOJO login=null;
		if(Role.equalsIgnoreCase("FD")) {
			login=new LoginRequestPOJO("iamfd", "password");
		}
		else if(Role.equalsIgnoreCase("SUP")){
			login=new LoginRequestPOJO("iamsup", "password");			
		}
		else if(Role.equalsIgnoreCase("ENG")){
			login=new LoginRequestPOJO("iameng", "password");			
		}
		else if(Role.equalsIgnoreCase("QC")){
			login=new LoginRequestPOJO("iamqc", "password");			
		}
		
		String token=given()
				.when()
		.header(new Header("Content-Type","application/json"))
		.body(convertToJson(login))
		.post("/login")
		.then()
		.extract().jsonPath().getString("data.token");
		return token;
	}
		
	}