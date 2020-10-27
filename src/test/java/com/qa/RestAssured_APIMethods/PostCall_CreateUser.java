package com.qa.RestAssured_APIMethods;

import java.io.File;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class PostCall_CreateUser 
{
	/************************ ​POST /user - Create user Call API ************************/
	@Test(priority=1)
	public void CreateUser()
    {
    	SoftAssert ass=new SoftAssert();
    	File fileobject=new File("D:\\eclipse\\API_InterviewCoding\\JsonFiles\\CreateUser.json");
		RestAssured.baseURI="https://petstore.swagger.io";
    	
    	
    	RequestSpecification request;
    	request=RestAssured.given().log().all().contentType("application/json").log().all().body(fileobject).log().all();
    	Response res=request.post("/v2/user");
    	int code=res.getStatusCode();
    	System.out.println("Status Code:::->"+code);
    	String bodyres=res.getBody().asString();
    	
    	
    	//System.out.println("Created users Username:::->"+user_username);
    	System.out.println("\n****************************API Response****************************\n");
    	System.out.println(bodyres);
    	System.out.println("\n*******************END API Response CreateUser****************************\n");
        
    	ass.assertEquals(code, 200);
    	ass.assertNotNull(bodyres);
    	ass.assertAll();
    }
}
