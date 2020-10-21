package click.klaassen.crosswind;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@QuarkusTest
public class CrosswindResourceTest {

  @ParameterizedTest
  // runway, wind_direction, wind_knots, expected crosswind
  @CsvSource({
    "150, 180, 18, 9, RIGHT",
    "330, 300, 12, 6, LEFT",
    "50, 100, 14, 11, RIGHT",
    "230, 180, 9, 7, LEFT",
  })
  public void testCrosswindEndpoint(
      int runway,
      int windDirection,
      int windKnots,
      int expectedCrosswind,
      Direction expectedDirection) {
    given()
        .queryParam("runway", runway)
        .queryParam("wind_direction", windDirection)
        .queryParam("wind_knots", windKnots)
        .when()
        .get("/crosswind")
        .then()
        .statusCode(200)
        .body("knots", is(expectedCrosswind))
        .body("direction", is(expectedDirection.toString()));
  }
}
