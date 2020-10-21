package click.klaassen.wca;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@QuarkusTest
class WindCorrectionAngleResourceTest {

  @ParameterizedTest
  // tc, tas, wind direction, wind knots, expected wca
  @CsvSource({
    "359, 110, 270, 18, -10",
    "180, 98, 210, 12, 4",
    "270, 140, 320, 20, 7",
  })
  void calculateWindCorrectionAngle(
      int trueCourse, int trueAirSpeed, int windDirection, int windKnots, int expectedWca) {
    given()
        .queryParam("tc", trueCourse)
        .queryParam("tas", trueAirSpeed)
        .queryParam("wind_direction", windDirection)
        .queryParam("wind_knots", windKnots)
        .when()
        .get("/wca")
        .then()
        .statusCode(200)
        .body("windCorrectionAngle", is(expectedWca));
  }
}
