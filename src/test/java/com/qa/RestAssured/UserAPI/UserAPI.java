package com.qa.RestAssured.UserAPI;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class UserAPI
{
	String getuser_username=null;
	String getpassword=null;
	String putuser=null;
	String deleteuser=null;
	
	@BeforeTest
	public void UserAPIPre_requisit()
	{
		File fileobject=new File("./src/testResources/CreateUser.json");
		RestAssured.baseURI="https://petstore.swagger.io";
       	RequestSpecification request;
    	request=RestAssured.given().log().all().contentType("application/json").log().all().body(fileobject).log().all();
    	Response res=request.post("/v2/user");
    	   	
    	String bodyres=res.getBody().asString();
    	System.out.println(bodyres);
    	
    	
		
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
    	
       	RequestSpecification request2;
    	request2=RestAssured.given().log().all().contentType("application/json").log().all().body(merge);
        Response res2=request2.post("/v2/user/createWithArray");
       	String bodyres2=res2.getBody().asString();
       	System.out.println(bodyres2);
      
    }
  

	
	@Test(priority=2)
	public void GetUser()
    {
    	SoftAssert ass=new SoftAssert();
		RestAssured.baseURI="https://petstore.swagger.io";
    	RequestSpecification request;
    	request=RestAssured.given().log().all().contentType("application/json").log().all();
    	Response res=request.get("/v2/user/Christopher");
    	int code=res.getStatusCode();
    	System.out.println("Status Code:::->"+code);
    	String bodyres=res.getBody().asString();
        System.out.println(bodyres);
       	getuser_username=res.jsonPath().getString("username");
    	getpassword=res.jsonPath().getString("password");
    	ass.assertEquals(code, 200);
    	ass.assertEquals(getuser_username,"Christopher");
    	ass.assertAll();
     }
	
	@Test(priority=3)
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
    	System.out.println(bodyres);
    	System.out.println("Session:::->"+sessionid);
    	ass.assertEquals(code, 200);
    	ass.assertNotNull("sessionid");
    	ass.assertAll();
    	
    }
	
	@Test(priority=4)
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
        System.out.println(bodyres);
    	System.out.println("Session:::->"+sessionid);
    	ass.assertEquals(code, 200);
    	ass.assertNotNull("sessionid");
    	ass.assertAll();
    	
    }
	
	  @Test(priority=5)
      public void Get_withList()
	  {
		  SoftAssert ass=new SoftAssert();
			RestAssured.baseURI="https://petstore.swagger.io";
	    	RequestSpecification request;
	    	request=RestAssured.given().log().all().contentType("application/json").log().all();
	    	Response res=request.get("/v2/user/Roman");
	    	int code=res.getStatusCode();
	    	System.out.println("Status Code:::->"+code);
	    	String bodyres=res.getBody().asString();
	        System.out.println(bodyres);
	        deleteuser=res.jsonPath().getString("username");
	    	getpassword=res.jsonPath().getString("password");
	    	ass.assertEquals(code, 200);
	    	ass.assertEquals(deleteuser,"Roman");
	    	ass.assertAll();
	  }
	
	@Test(priority=6)
	public void Delete_User()
    {
    	SoftAssert ass=new SoftAssert();
		
       	/*************************  now call the Delete Method *******/
    	RequestSpecification request2=RestAssured.given().log().all().contentType("application/json").log().all();
       	Response resput=request2.delete("/v2/user/"+deleteuser);
    	int codemodify=resput.getStatusCode();
    	String bodyresput=resput.getBody().asString();
    	System.out.println(bodyresput);
    	ass.assertEquals(codemodify, 200);
      	ass.assertAll();

    }
	
	@Test(priority=7)
	public void Put_ApiCall()
    {
    	SoftAssert ass=new SoftAssert(); 	
    	File fileobject_put=new File("./src/testResources/PutUser.json");    	    	
    	RequestSpecification request2=RestAssured.given().log().all().contentType("application/json").log().all().body(fileobject_put);
       	Response resput=request2.put("/v2/user/Cardoza");
       	int codemodify=resput.getStatusCode();
    	String bodyresput=resput.getBody().asString();
       	System.out.println(bodyresput);
    	System.out.println("Put Status code::->"+codemodify);
    	
    	ass.assertEquals(codemodify, 200);
        ass.assertAll();
    }
	
	@AfterTest
	public void UserApi_AfterTest()
	{
	 
	  RestAssured.baseURI="https://petstore.swagger.io";
	  
	  RequestSpecification requestafterput;
	  requestafterput=RestAssured.given().log().all().contentType("application/json").log().all();
	  Response resafterput=requestafterput.get("/v2/user/Roman");
      String afterputss=resafterput.getBody().asString();
	  System.out.println("User Deleted "+afterputss);
	  
	  RequestSpecification requestafterdelete=RestAssured.given().log().all().contentType("application/json").log().all();
      Response resafterdelete=requestafterdelete.get("/v2/user/Cardoza_Modified");
  	  String dele=resafterdelete.getBody().asString();
  	  System.out.println(dele);
  	 
	}
}
