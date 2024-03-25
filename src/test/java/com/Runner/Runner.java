package com.Runner;

import com.api.pojo.CreateJobRequestPOJO;
import com.api.pojo.Customer;
import com.api.pojo.Customer_address;
import com.api.pojo.Customer_product;
import com.api.pojo.LoginRequestPOJO;
import com.api.pojo.Problems;
import static com.util.Helper.*;

public class Runner {

	public static void main(String[] args) {

		LoginRequestPOJO loginRequestPOJO=new LoginRequestPOJO("iamfd", "password");
		System.out.println(loginRequestPOJO);
		System.out.println(convertToJson(loginRequestPOJO));
		
//		Customer customer= new Customer("firstn", "last", "1232123100", "4234224323", "testt34@test.com", "tt@erwrwe.com");
//		Customer_address custadd = new Customer_address("213", "aprt", "stree1", "lanm", "sdasdasd", "312313", "India", "Assam");
//		Customer_product custprod= new Customer_product("2023-11-30T18:30:00.000Z", "21312331231222", "21312331231222", "21312331231222", "2023-11-30T18:30:00.000Z", "3", "3");
//		Problems problem[]=new Problems[2];
//		problem[0]= new Problems(2, "test1");
//		problem[1]=new Problems(6,"test2");
		CreateJobRequestPOJO createJobRequestPOJO= getCreateJobData();
		String jsonobj= convertToJson(createJobRequestPOJO);
		System.out.println(jsonobj);
		System.out.println("--------------deserialisation-------------");
		Object createJob2=  convertBackToJavaObj(jsonobj);
		System.out.println(createJob2);
		CreateJobRequestPOJO createJob3= convertBackToJavaOtherWay(jsonobj, CreateJobRequestPOJO.class);
		System.out.println(createJob3);
	}

}
