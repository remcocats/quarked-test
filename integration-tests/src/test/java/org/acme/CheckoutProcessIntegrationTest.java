package org.acme;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

import io.quarkus.test.common.http.TestHTTPResource;
import io.restassured.http.ContentType;
import java.net.URL;
import javax.inject.Inject;
import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
@QuarkusTestResource(InfrastructureTestResource.class)
public class CheckoutProcessIntegrationTest {

    @Test
    public void should_do_a_checkout() throws InterruptedException {

        ShoppingBasket shoppingBasket = ShoppingBasketObjectMother.theHobbiBasket();
        given()
            .contentType(ContentType.JSON)
            .body(shoppingBasket)
            .when()
            .post("/checkout")
            .then()
            .log().ifValidationFails()
            .statusCode(201);
    }

}