package apiTests;

import io.restassured.RestAssured;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class BaseApiTest {
    protected final String baseURI = System.getProperty("baseURL","http://localhost:8080");

    @BeforeClass
    public void setUp(){
        RestAssured.baseURI = baseURI;
    }

    @AfterClass
    public void afterClass(){}
}
