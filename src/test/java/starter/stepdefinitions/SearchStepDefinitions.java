package starter.stepdefinitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;

import java.util.List;

import static org.hamcrest.Matchers.equalTo;

import org.assertj.core.api.Assertions;

public class SearchStepDefinitions {

    private static final String URL = "https://waarkoop-server.herokuapp.com/api/v1/search/demo/";
    public Response response;
    @When("he searches for {string}")
    public void heSearchesItem(String item) {
        response = SerenityRest.given().contentType("application/json")
                .header("Content-Type", "application/json")
                .when().get(URL + item);
    }

    @Then("the API should return status {int}")
    public void verifyResponseStatusCode(int statusCode) {
        SerenityRest.restAssuredThat(response -> response.statusCode(statusCode));
    }

    @Then("the API should return error message {string}")
    public void verifyErrorMessage(String errorMessage) {
        SerenityRest.restAssuredThat(response -> response.body("detail.message", equalTo(errorMessage)));
    }

    @When("he sees displayed results contain title {string}")
    public void displayedResultContainsTitle(String title) {
        List<String> allTitles = response.jsonPath().getList("title");

        Assertions.assertThat(allTitles).contains(title);
    }

    @When("he sees displayed results does not contain title {string}")
    public void displayedResultDoesNotContainTitle(String title) {
        List<String> allTitles = response.jsonPath().getList("title");

        Assertions.assertThat(allTitles).doesNotContain(title);
    }
}
