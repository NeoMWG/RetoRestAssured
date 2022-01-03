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

public class SingleUserStepDefinition extends Reqresin {

    private static final Logger LOGGER = Logger.getLogger(SingleUserStepDefinition.class);
    private RequestSpecification request;
    private Response response;

    @Given("someone has pre-registered")
    public void someoneHasPreRegistered() {

        try{

            generalSetUp();
            request = given();

        }catch(Exception e){
            LOGGER.warn(e.getMessage(), e);
            Assertions.fail(e.getMessage());
        }
    }
    @When("this person sends the request")
    public void thisPersonSendsTheRequest() {
        try {

            response = request
                    .get(RESOURCE_SINGLE_USER);

        } catch (Exception e){
            LOGGER.warn(e.getMessage(), e);
            Assertions.fail(e.getMessage());
        }
    }
    @Then("the person should be able to get the registered information")
    public void thePersonShouldBeAbleToGetTheRegisteredInformation() {
        try {
            response
                    .then()
                    .statusCode(HttpStatus.SC_OK)
                    .body("data.id",equalTo(2),
                            "data.email", equalTo("janet.weaver@reqres.in"),
                            "data.first_name", equalTo("Janet"),
                            "data.last_name", equalTo("Weaver"),
                            "data.avatar", equalTo("https://reqres.in/img/faces/2-image.jpg"),
                            "support.url", equalTo("https://reqres.in/#support-heading"),
                            "support.text", equalTo("To keep ReqRes free, contributions towards server costs are appreciated!"));

        } catch (Exception e){
            LOGGER.warn(e.getMessage(), e);
            Assertions.fail(e.getMessage());
        }
    }

}
