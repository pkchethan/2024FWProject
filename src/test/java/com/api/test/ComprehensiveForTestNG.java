package com.api.test;

import static com.util.Helper.convertToJson;
import static com.util.Helper.getCreateJobData;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalToIgnoringCase;
import static org.hamcrest.Matchers.lessThan;

import java.io.File;

import org.hamcrest.Matchers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.api.pojo.LoginRequestPOJO;
import com.util.Helper;

import io.restassured.http.Header;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;

public class ComprehensiveForTestNG {
	private String token;
	private Header h1;
	private Header h2;
	private File createJobSchema;
	static {
		baseURI="http://139.59.91.96:9000/v1";
	}
	@BeforeMethod(description="basic setup method to setup header")
	public void setUp() {
		 h1= new Header("Content-Type","application/json");
		 h2= new Header("Authorization",Helper.getTokenFor("FD"));
	}
	
	//@Test(description="This is a Login api request",groups= {"Sanity","Smoke"}, priority=2)
	public void LoginAPI() {
		LoginRequestPOJO login=new LoginRequestPOJO("iamfd", "password");
	//	Header myheader=new Header("Content-Type","application/json");
		 token=	
			given()
				.header(h1)
				.body(convertToJson(login))
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
				.extract().jsonPath().getString("data.token");
		
		System.out.println("Token Extracted is: "+token);
	}

	@Test(description="This is a create job api request",groups= {"Sanity","Smoke"},priority=3)
	public void CreateJob() {
		System.out.println("Token value from instance: "+token);
		System.out.println("header value from 1st"+h2);
		createJobSchema=new File(System.getProperty("user.dir")+"/src/test/resources/CreateJobResponseSchema.json");
		String  jobNumber= given()
			.header(h1)		
			.header(h2)
		.and()
			.body(convertToJson(getCreateJobData()))
			.log().all()
		.when()
			.post("/job/create")
			.then()
			.log().all()
			.body("message", Matchers.containsString("Job created successfull"))
			.body("data.mst_warrenty_status_id", Matchers.equalTo(1))
		.and()
			.body(JsonSchemaValidator.matchesJsonSchema(createJobSchema))
		.and()
			.extract().jsonPath().getString("data.job_number");
		System.out.println(jobNumber);
		
	}
	
	@Test(description="User details api reqiuest",groups= {"Sanity","Smoke"},priority=4)
    public void userDetails() {
    String id=	given()
    	.header(h2)
    	.log().all()
    	.get("/userdetails")
    	.then()
    	.log().all()
    	.body("message",Matchers.containsString("Success"))
    	.body("data.login_id",Matchers.equalToIgnoringCase("iamfd"))
    	.and()
    	.extract().jsonPath().getString("data.id");
       System.out.println("ID extracted "+id);
    	
    }


}
	