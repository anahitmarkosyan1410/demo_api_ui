package apiTests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import jdk.jfr.Description;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;


public class SummaryApiTest {

    @Test
    @Description("This is test which check message summuries")
    public void checkSummary(){
        String requestBody = "{ \"threads\": [\"slack:123\", \"email:456\"] }";
        RestAssured.given().contentType(ContentType.JSON).
                body(requestBody).
                when().
                post("").
                then().
                statusCode(200).
                body("summury", Matchers.notNullValue());
    }
    public void checkSummaryTestNg(){
        String requestBody = "{ \"threads\": [\"slack:123\", \"email:456\"] }";
       Response response =  RestAssured.given().contentType(ContentType.JSON).
                body(requestBody).
                when().
                post("");

        Assert.assertEquals(response.getStatusCode(),200);
        String message = response.jsonPath().getString("summary");
        Assert.assertNotNull(message);
    }
}
