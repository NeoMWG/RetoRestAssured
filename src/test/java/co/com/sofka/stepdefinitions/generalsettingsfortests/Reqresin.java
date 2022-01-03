package co.com.sofka.stepdefinitions.generalsettingsfortests;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.ErrorLoggingFilter;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import org.apache.log4j.PropertyConfigurator;

import static co.com.sofka.util.Log4jValues.LOG4J_PROPERTIES_FILE_PATH;

public class Reqresin {
    private static final String BASE_URL = "https://reqres.in/";
    private static final String BASE_PATH = "api/";
    protected static final String RESOURCE_CREATE = "/users";
    protected static final String RESOURCE_SINGLE_USER = "/users/2";

    protected void generalSetUp(){
        settingsForRestAssured();
        setUpLog4j();
    }

    private void settingsForRestAssured(){
        RestAssured.baseURI = BASE_URL;
        RestAssured.basePath = BASE_PATH;
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter(), new ErrorLoggingFilter());
        RestAssured.requestSpecification = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .build();
    }

    private void setUpLog4j() {
        PropertyConfigurator.configure(System.getProperty("user.dir") + LOG4J_PROPERTIES_FILE_PATH.getValue());
    }

}
