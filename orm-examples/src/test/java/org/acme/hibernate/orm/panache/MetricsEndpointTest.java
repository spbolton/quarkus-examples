package org.acme.hibernate.orm.panache;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.text.IsEmptyString.emptyString;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class MetricsEndpointTest {

    @Test
    public void testListAllMetrics() {
        // TODO
      /*  Response response = given()
                .when()
                .put("/metricssql")
                .then()
                .statusCode(200)
                .contentType("application/json")
                .extract().response();
        assertThat(response.jsonPath().getList("name")).containsExactlyInAnyOrder("Country");
        assertThat(response.jsonPath().getList("data")).containsExactlyInAnyOrder(
                "{\"country\":\"Romania\",\"city\":\"Cluj-Napoca\"}");

       */
    }
}
