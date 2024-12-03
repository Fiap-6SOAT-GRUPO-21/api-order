package br.com.api_order.cucumber;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GetAllOrdersSteps {

    private Response response;

    @Given("there are existing orders in the system")
    public void thereAreExistingOrdersInTheSystem() {
        // Mock or setup pre-existing orders if using an in-memory database or stub
    }

    @Given("there are no orders in the system")
    public void thereAreNoOrdersInTheSystem() {
        // Ensure the orders table or repository is empty
    }

    @When("I send a GET request to {string}")
    public void iSendAGETRequestTo(String endpoint) {
        response = RestAssured
                .given()
                .basePath(endpoint)
                .when()
                .get();
    }

    @Then("the response status should be {int}")
    public void theResponseStatusShouldBe(int statusCode) {
        assertEquals(statusCode, response.getStatusCode());
    }

    @Then("the response should contain a list of orders")
    public void theResponseShouldContainAListOfOrders() {
        response.then()
                .body("$", not(empty()))
                .body("[0].id", notNullValue())
                .body("[0].customerName", notNullValue())
                .body("[0].totalAmount", notNullValue());
    }

    @Then("each order should have a valid ID, customer name, and total amount")
    public void eachOrderShouldHaveAValidIdCustomerNameAndTotalAmount() {
        response.then()
                .body("every { it.id }", everyItem(notNullValue()))
                .body("every { it.customerName }", everyItem(notNullValue()))
                .body("every { it.totalAmount }", everyItem(notNullValue()));
    }

    @Then("the response body should be empty")
    public void theResponseBodyShouldBeEmpty() {
        assertEquals("", response.getBody().asString().trim());
    }

}