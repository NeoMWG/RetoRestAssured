package co.com.sofka.stepdefinitions.stepdefinitionsclasses;

import co.com.sofka.stepdefinitions.generalsettingsfortests.Reqresin;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpStatus;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Assertions;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class CreateUserStepDefinition extends Reqresin {

    private static final Logger LOGGER = Logger.getLogger(CreateUserStepDefinition.class);
    private RequestSpecification request;
    private Response response;

    @Given("a person is on the user creation page and enters the username {string} and job {string}")
    public void aPersonIsOnTheUserCreationPageAndEntersTheUsernameAndJob(String username, String job) {

        try{

            generalSetUp();

            request = given().body("{\n" +
                    "    \"name\": \"" + username + "\",\n" +
                    "    \"job\": \"" + job + "\"\n" +
                    "}");

        }catch (Exception e){
            LOGGER.warn(e.getMessage(), e);
            Assertions.fail(e.getMessage());
        }
    }
    @When("this person sends the service request")
    public void thisPersonSendsTheServiceRequest() {

        try{

            response = request
                    .post(RESOURCE_CREATE);

        }catch (Exception e){
            LOGGER.warn(e.getMessage(), e);
            Assertions.fail(e.getMessage());
        }
    }
    @Then("the person should be able to view a message in response")
    public void thePersonShouldBeAbleToViewAMessageInResponse() {

        try{

            response
                    .then()
                    .statusCode(HttpStatus.SC_CREATED)
                    .body("name", equalTo("morpheus"),
                            "job",equalTo("leader"));


        }catch (Exception e){
            LOGGER.warn(e.getMessage(), e);
            Assertions.fail(e.getMessage());
        }
    }

}
