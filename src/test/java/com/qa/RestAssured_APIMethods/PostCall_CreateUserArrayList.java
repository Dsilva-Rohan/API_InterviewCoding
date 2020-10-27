package com.qa.RestAssured_APIMethods;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class PostCall_CreateUserArrayList
{
/************************POST /user​/createWithList - Creates list of users with given input array************************/
	
	@Test(priority=5)
	public void CreateUser_withList()
    {
    	SoftAssert ass=new SoftAssert();
		RestAssured.baseURI="https://petstore.swagger.io";
		
    	Map<String, String> createUserlist1=new HashMap<String, String>();
    	createUserlist1.put("id","333");
    	createUserlist1.put("username", "Roman");
    	createUserlist1.put("firstName", "Gabrial");
    	createUserlist1.put("lastName", "Rien");
    	createUserlist1.put("email", "Rien@gmail.com");
    	createUserlist1.put("password", "Rien@12345");
    	createUserlist1.put("phone", "9876543210");
    	createUserlist1.put("userStatus", "1");
    	
    	Map<String, String> createUserlist2=new HashMap<String, String>();
    	createUserlist2.put("id","444");
    	createUserlist2.put("username", "Cardoza");
    	createUserlist2.put("firstName", "Peter");
    	createUserlist2.put("lastName", "Sabestain");
    	createUserlist2.put("email", "Sabestain@gmail.com");
    	createUserlist2.put("password", "Sabestain@12345");
    	createUserlist2.put("phone", "8876543210");
    	createUserlist2.put("userStatus", "1");
    	
    	List<Map<String,String>> merge=new ArrayList<Map<String, String>>();
    	merge.add(createUserlist1);
    	merge.add(createUserlist2);
    	
    	RequestSpecification request;
    	request=RestAssured.given().log().all().contentType("application/json").log().all().body(merge);
    	
       	Response res=request.post("/v2/user/createWithArray");
       	
    	int code=res.getStatusCode();
    	
    	System.out.println("Status Code:::->"+code);
    	String bodyres=res.getBody().asString();
    	
    	
    	
    	System.out.println("\n****************************API Response****************************\n");
    	System.out.println(bodyres);
    	System.out.println("\n*******************End Creates list of users with given input array****************************\n");
    	
    	ass.assertEquals(code, 200);
        //ass.assertNotNull(user);
    	ass.assertAll();
    	
    }
	
}
