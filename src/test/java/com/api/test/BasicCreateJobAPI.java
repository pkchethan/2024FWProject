package com.api.test;

import static com.util.Helper.convertToJson;
import static com.util.Helper.getCreateJobData;
import static io.restassured.RestAssured.*;

import org.hamcrest.Matchers;

import io.restassured.http.Header;
import io.restassured.response.Response;

public class BasicCreateJobAPI {
	static {
		baseURI="http://139.59.91.96:9000/v1";
	}

	public static void main(String[] args) {
		Header h1= new Header("Content-Type","application/json");
		Header h2= new Header("Authorization","eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6NCwiZmlyc3RfbmFtZSI6ImZkIiwibGFzdF9uYW1lIjoiZmQiLCJsb2dpbl9pZCI6ImlhbWZkIiwibW9iaWxlX251bWJlciI6Ijg4OTk3NzY2NTUiLCJlbWFpbF9pZCI6Im1hcmtAZ21haWwuY29tIiwicGFzc3dvcmQiOiI1ZjRkY2MzYjVhYTc2NWQ2MWQ4MzI3ZGViODgyY2Y5OSIsInJlc2V0X3Bhc3N3b3JkX2RhdGUiOm51bGwsImxvY2tfc3RhdHVzIjowLCJpc19hY3RpdmUiOjEsIm1zdF9yb2xlX2lkIjo1LCJtc3Rfc2VydmljZV9sb2NhdGlvbl9pZCI6MSwiY3JlYXRlZF9hdCI6IjIwMjEtMTEtMDNUMDg6MDY6MjMuMDAwWiIsIm1vZGlmaWVkX2F0IjoiMjAyMS0xMS0wM1QwODowNjoyMy4wMDBaIiwicm9sZV9uYW1lIjoiRnJvbnREZXNrIiwic2VydmljZV9sb2NhdGlvbiI6IlNlcnZpY2UgQ2VudGVyIEEiLCJpYXQiOjE3MTEzNzc5NTV9.40HLbqyseiPfmALB81IBVTkPzI8sNLclCmTzCA34Oy4");
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
			.extract().jsonPath().getString("data.job_number");
		System.out.println(jobNumber);
		
	}

}
	