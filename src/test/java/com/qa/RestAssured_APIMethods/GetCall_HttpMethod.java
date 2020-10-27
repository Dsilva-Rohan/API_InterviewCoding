package com.qa.RestAssured_APIMethods;


import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GetCall_HttpMethod
{
	
	String getuser_username=null;
	String getpassword=null;
	String putuser=null;
	String deleteuser=null;
	
	
	
	/************************ GET /user​/{username} - Get user by user name ************************/
	
	@Test(priority=1)
	public void Get_TheUser()
    {
    	SoftAssert ass=new SoftAssert();
		RestAssured.baseURI="https://petstore.swagger.io";
    	
    	
    	RequestSpecification request;
    	request=RestAssured.given().log().all().contentType("application/json").log().all();
    	
    	Response res=request.get("/v2/user/Christopher");
    	int code=res.getStatusCode();
    	
    	System.out.println("Status Code:::->"+code);
    	
    	String bodyres=res.getBody().asString();
    	
    	System.out.println("\n ****************************API Response****************************\n");
    	System.out.println(bodyres);
    	System.out.println("\n*******************END API Response Get the User****************************\n");
    	getuser_username=res.jsonPath().getString("username");
    	getpassword=res.jsonPath().getString("password");
    	ass.assertEquals(code, 200);
    	ass.assertEquals(getuser_username,"Christopher");
    	ass.assertAll();
    	
    }
	

	/************************ GET /user​/login - Login_user into the system************************/
	
	@Test(priority=2)
	public void Get_TheUserSession()
    {
    	SoftAssert ass=new SoftAssert();
		RestAssured.baseURI="https://petstore.swagger.io";
		
    	Map<String, String> session=new HashMap<String, String>();
    	session.put("username",getuser_username);
    	session.put("password", getpassword);
    	
    	RequestSpecification request;
    	request=RestAssured.given().log().all().contentType("application/json").log().all().queryParams(session);
    	
       	Response res=request.get("/v2/user/login");
       	
    	int code=res.getStatusCode();
    	
    	System.out.println("Status Code:::->"+code);
    	String bodyres=res.getBody().asString();
    	String sessionid=res.jsonPath().getString("message");
    	
    	System.out.println("\n****************************API Response****************************\n");
    	System.out.println(bodyres);
    	System.out.println("\n*******************END API Response CreateSession User****************************\n");
    	System.out.println("Session:::->"+sessionid);
    	ass.assertEquals(code, 200);
    	ass.assertNotNull("sessionid");
    	ass.assertAll();
    	
    }
	
/************************ GET /user​/login - Logout user into the system************************/
	
	@Test(priority=3)
	public void LoggOff_session_User()
    {
    	SoftAssert ass=new SoftAssert();
		RestAssured.baseURI="https://petstore.swagger.io";
		
    	
    	
    	RequestSpecification request;
    	request=RestAssured.given().log().all().contentType("application/json").log().all();
    	
       	Response res=request.get("/v2/user/logout");
       	
    	int code=res.getStatusCode();
    	
    	System.out.println("Status Code:::->"+code);
    	String bodyres=res.getBody().asString();
    	String sessionid=res.jsonPath().getString("message");
    	
    	System.out.println("\n****************************API Response****************************\n");
    	System.out.println(bodyres);
    	System.out.println("\n*******************END API Response Logout User****************************\n");
    	System.out.println("Session:::->"+sessionid);
    	ass.assertEquals(code, 200);
    	ass.assertNotNull("sessionid");
    	ass.assertAll();
    	
    }

}
