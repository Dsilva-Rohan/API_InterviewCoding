package com.qa.RestAssured_APIMethods;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class DeleCall_DeleteUser
{
	

	String getuser_username=null;
	String getpassword=null;
	String putuser=null;
	String deleteuser=null;
	
/************************Delete /user​/{username} - delete user************************/
	
	@Test(priority=7)
	public void Delete_TheUserSession()
    {
    	SoftAssert ass=new SoftAssert();
		RestAssured.baseURI="https://petstore.swagger.io";
		
    	
		
		RequestSpecification request;
    	request=RestAssured.given().log().all().contentType("application/json").log().all();
    	
       	Response res=request.get("/v2/user/Roman");
       	
    	int code=res.getStatusCode();
    	
    	    String bodyres=res.getBody().asString();
    	    deleteuser=res.jsonPath().getString("username");
    	    System.out.println(putuser);
    	    System.out.println("\n****************************Get_API Response****************************\n");
    	    System.out.println(bodyres);
    	    
    	    System.out.println("\n******************Here Get_API Response Ends Before Delete****************************\n");
    	
    	/*************************  now call the Delete Method *******/
    	
    		
    	RequestSpecification request2=RestAssured.given().log().all().contentType("application/json").log().all();
    	
       	Response resput=request2.delete("/v2/user/"+deleteuser);
       	
    	int codemodify=resput.getStatusCode();
    	
    	String bodyresput=resput.getBody().asString();
    	
    	System.out.println("\n****************************Delete_API Response****************************\n");
    	System.out.println(bodyresput);
    	ass.assertEquals(codemodify, 200);
    	
    	System.out.println("\n****************************Delete_API Response Ends****************************\n");
    	
    	System.out.println("\n****************************Get_API Response After Delete****************************\n");
    	RequestSpecification requestafterput;
    	requestafterput=RestAssured.given().log().all().contentType("application/json").log().all();
    	 Response resafterput=requestafterput.get("/v2/user/"+deleteuser);
    
    	 String afterputss=resafterput.getBody().asString();
    	 System.out.println(afterputss);
    	 
    	 System.out.println("\n******************Here UserShould not available after delete****************************\n");
    	 
    	 ass.assertAll();
    	 
    	
    	
    }
}
