package com.qa.RestAssured_APIMethods;

import java.io.File;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class PutCall_UpdateUser 
{

	
	String getuser_username=null;
	String getpassword=null;
	String putuser=null;
	String deleteuser=null;
	
	/************************PUT /user​/{username} - Updated user************************/
	

	@Test(priority=6)
	public void Put_ApiCall()
    {
    	SoftAssert ass=new SoftAssert();
		RestAssured.baseURI="https://petstore.swagger.io";
		
    	/***  First calll the get call to check whether given request is present or not *****/
		
		RequestSpecification request;
    	request=RestAssured.given().log().all().contentType("application/json").log().all();
    	
       	Response res=request.get("/v2/user/Cardoza");
       	
    	int code=res.getStatusCode();
    	
    	    String bodyres=res.getBody().asString();
    	    putuser=res.jsonPath().getString("username");
    	    System.out.println(putuser);
    	System.out.println("\n****************************Get_API Response before Put****************************\n");
    	System.out.println(bodyres);
    	System.out.println("\n*************************End Get_API Response****************************\n");
    	
    	/*************************  now call the Put Method *******/
    	
    	File fileobject_put=new File("D:\\eclipse\\API_InterviewCoding\\JsonFiles\\PutUser.json");    	    	
    	RequestSpecification request2=RestAssured.given().log().all().contentType("application/json").log().all().body(fileobject_put);
    	
       	Response resput=request2.put("/v2/user/"+putuser);
       	
    	int codemodify=resput.getStatusCode();
    	
    	String bodyresput=resput.getBody().asString();
    	
    	System.out.println("\n****************************Put_API Response****************************\n");
    	System.out.println(bodyresput);
    	System.out.println("Put Status code::->"+codemodify);
    	ass.assertEquals(codemodify, 200);
    	System.out.println("\n*************************End Put_API Response****************************\n");
    	
    	System.out.println("\n****************************Get_API Response After Put****************************\n");
    	RequestSpecification requestafterput;
    	requestafterput=RestAssured.given().log().all().contentType("application/json").log().all();
    	 Response resafterput=requestafterput.get("/v2/user/Cardoza_Modified");
    
    	 String afterputss=resafterput.getBody().asString();
    	 System.out.println(afterputss);
    	 System.out.println("\n****************************Get_API Response Ends After Put and current user get modified ****************************\n");
    	 ass.assertAll();
    }
	
}
