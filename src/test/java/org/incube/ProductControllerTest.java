package org.incube;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.incube.domain.entities.Product;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;


@QuarkusTest
class ProductControllerTest {

    @Test
    void testGetAllEndpoint() {
        given()
                .when().get("/api/products")
                .then()
                .statusCode(anyOf(is(200), is(204)));
    }

    @Test
    void testGetPageEndpointNegative() {
        given()
                .when().get("/api/products/page/-1")
                .then()
                .statusCode(400)
                .contentType(ContentType.JSON)
                .body("error", equalTo("Invalid Page Number"));
    }

    @Test
    void testGetPageEndpointZero() {
        given()
                .when().get("/api/products/page/0")
                .then()
                .statusCode(400)
                .contentType(ContentType.JSON)
                .body("error", equalTo("Invalid Page Number"));
    }

    @Test
    void testGetPageEndpointPositiveExist() {
        given()
                .when().get("/api/products/page/1")
                .then()
                .statusCode(200);
    }

    @Test
    void testGetPageEndpointPositiveNotExist() {
        given()
                .when().get("/api/products/page/10")
                .then()
                .statusCode(404);
    }

    @Test
    void testGetByIdEndpointNegative() {
        given()
                .when().get("/api/products/product/-1")
                .then()
                .statusCode(400)
                .contentType(ContentType.JSON)
                .body("error", equalTo("Invalid ID Number"));
    }

    @Test
    void testGetByIdEndpointZero() {
        given()
                .when().get("/api/products/product/0")
                .then()
                .statusCode(400)
                .contentType(ContentType.JSON)
                .body("error", equalTo("Invalid ID Number"));
    }

    @Test
    void testGetByIdEndpointPositive() {
        given()
                .when().get("/api/products/product/1")
                .then()
                .statusCode(anyOf(is(200),is(404)));
    }

    @Test
    void testCreateEndpoint1() {
        Product testProduct = new Product("", "test description",
                2.00, "test image link");

        given()
                .contentType(ContentType.JSON)
                .body(testProduct)
                .when().post("/api/products")
                .then()
                .statusCode(400)
                .contentType(ContentType.JSON)
                .body("error", equalTo("Invalid Product Name"));
    }

    @Test
    void testCreateEndpoint2() {
        Product testProduct = new Product("test Name", "test description",
                0.5, "test image link");

        given()
                .contentType(ContentType.JSON)
                .body(testProduct)
                .when().post("/api/products")
                .then()
                .statusCode(400)
                .contentType(ContentType.JSON)
                .body("error", equalTo("Invalid Product Price"));
    }

    @Test
    void testCreateEndpoint3() {
        Product testProduct = new Product("test Name", "test description",
                null, "test image link");

        given()
                .contentType(ContentType.JSON)
                .body(testProduct)
                .when().post("/api/products")
                .then()
                .statusCode(400)
                .contentType(ContentType.JSON)
                .body("error", equalTo("Invalid Product Price"));
    }

    @Test
    void testCreateEndpoint4() {
        Product testProduct = new Product("test Name", null,
                2.00, "test image link");

        given()
                .contentType(ContentType.JSON)
                .body(testProduct)
                .when().post("/api/products")
                .then()
                .statusCode(400)
                .contentType(ContentType.JSON)
                .body("error", equalTo("Invalid Product Description"));
    }

    @Test
    void testCreateEndpoint5() {
        Product testProduct = new Product("test Name", "test description",
                1.00, "test image link");

        given()
                .contentType(ContentType.JSON)
                .body(testProduct)
                .when().post("/api/products")
                .then()
                .statusCode(201);
    }

        /*
        No specific tests for update(PUT) endpoint because its code path is
        the same as create(POST) and getById(GET) code paths combined, which
        are already covered by the above tests.
         */

        /*
        Same goes for the delete(DELETE) endpoint because its code path is
        the same as getById(GET) code path.
         */
}
