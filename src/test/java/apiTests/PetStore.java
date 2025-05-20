package apiTests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class PetStore {
    private String baseURI = "https://petstore.swagger.io/v2/pet";
    private static final Logger LOGGER = LogManager.getLogger(PetStore.class);

    @BeforeClass
    public void setUp(){
        RestAssured.baseURI = baseURI;
    }

    @Test
    public void getPet(){
        LOGGER.info("Getting pet by id = 1");
       Response response =  RestAssured.given()
               .contentType(ContentType.JSON)
               .when()
               .get("/1");
        Assert.assertEquals(response.getStatusCode(),200);
        int id = response.jsonPath().get("id");
        Assert.assertEquals(id,1);
    }

    @Test
    public void getPetMatchers(){
       RestAssured.given()
                .contentType(ContentType.JSON)
                .when()
                .get("/1")
                .then()
                .statusCode(200)
                .body("id", Matchers.equalTo(1));
    }

    @Test
    public void postPetByID(){
        String body = "{\n" +
                "  \"id\": 999,\n" +
                "  \"category\": {\n" +
                "    \"id\": 0,\n" +
                "    \"name\": \"string\"\n" +
                "  },\n" +
                "  \"name\": \"doggie\",\n" +
                "  \"photoUrls\": [\n" +
                "    \"string\"\n" +
                "  ],\n" +
                "  \"tags\": [\n" +
                "    {\n" +
                "      \"id\": 0,\n" +
                "      \"name\": \"string\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"status\": \"available\"\n" +
                "}\n";

        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .post();
//                .then()
//                .statusCode(200)
//                .body("id",Matchers.equalTo(999))
//                .body("name",Matchers.equalTo("doggie"));
        Assert.assertEquals(response.getStatusCode(),200);
        String name = response.jsonPath().getString("name");
        Assert.assertEquals(name,"doggie");
    }

    @Test
    public void deletePet(){
        RestAssured.given().contentType(ContentType.JSON)
                .when()
                .delete("/999")
                .then()
                .statusCode(200);
    }
}
