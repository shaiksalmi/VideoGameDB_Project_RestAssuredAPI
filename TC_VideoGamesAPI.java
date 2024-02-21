package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;

import static io.restassured.RestAssured.*;

import org.json.simple.JSONObject;

import org.testng.annotations.Test;

import groovy.json.JsonLexer;


public class TC_VideoGamesAPI {
	@Test(priority=1)
	
	public void test_getAllVideoGames()
	
	{
	given()
	
	.when()
	
	.get("http://localhost:8080/app/videogames")
	
	.then()
	.statusCode(200);
}
	@Test(priority=2)
	public void test_addNewVideoGame()
	{
		HashMap data=new HashMap();
		

		 data.put( "id", "11");
		  data.put("name", "Super-Man");
		 data.put( "releaseDate", "2024-04-21T05:09:40.441Z");
		  data.put("reviewScore", "6");
		data.put( "category" ,"Adventure");
		data.put ( "rating","Universal");
		
Response res =
		 given()
		
		.contentType("application/json")
		
		.body(data)
		
		.when()
	
		.post("http://localhost:8080/app/videogames")
		
		.then()
		
		.statusCode(200)
		.log().body()
		
		.extract().response();

String jsonString = res.asString();

Assert.assertEquals(jsonString.contains("Record Added Successfully"),true);



		}
		
		@Test(priority=3)
		public void test_getVideoGame()
		{
			
			given()
		
			
			.when()
		
			.post("http://localhost:8080/app/videogames")
			
			.then()
			
			.statusCode(200)
			.log().body()
			.body("videoGame.id",equalTo("200"))
			.body("videoGame.name",equalTo("Super-Women"));
			
		}
		
		@Test(priority=4)
		
		public void test_UpdateVideoGame()
		
		{
		
			HashMap data=new HashMap();
			

			 data.put( "id", "55");
			  data.put("name", "Pubg");
			 data.put( "releaseDate", "2024-02-21T05:09:40.441Z");
			  data.put("reviewScore", "8");
			data.put( "category" ,"Adventure");
			data.put ( "rating","Universal");
			
			
			given()
			.contentType("application/json")
			.body(data)
			.when()
			.put("http://localhost:8080/app/videogames")
			.then()
			.statusCode(200)
			.log().body()
			.body("videoGame.id", equalTo("55"))
			.body("videoGame.name", equalTo("Pubg"));
	}
		
		
	@Test(priority=5)
		
		public void test_DeleteVideoGame()
		{
			Response res=
			given()
			.when()
			
			.delete("http://localhost:8080/app/videogames")
			
			.then()
			
			.statusCode(200)
			
			.log().body()
			.extract().response();
			
			String jsonString=res.asString();
			
			Assert.assertEquals(jsonString.contains("Record Deleted Succesfully"),true);
			
		}
}
	
