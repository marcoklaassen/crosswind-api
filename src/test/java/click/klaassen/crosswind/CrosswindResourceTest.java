package click.klaassen.crosswind;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class CrosswindResourceTest {

  @Test
  public void testCrosswindEndpoint() {
    given()
        .queryParam("runway", 150)
        .queryParam("wind_direction", 180)
        .queryParam("wind_knots", 18)
        .when()
        .get("/crosswind")
        .then()
        .statusCode(200)
        .body("knots", is(9));
  }
}
