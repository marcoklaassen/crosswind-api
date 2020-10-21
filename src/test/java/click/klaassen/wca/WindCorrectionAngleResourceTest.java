package click.klaassen.wca;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
class WindCorrectionAngleResourceTest {

  @Test
  void calculateWindCorrectionAngleNegative() {
    given()
            .queryParam("tc", 359)
            .queryParam("tas", 110)
            .queryParam("wind_direction", 270)
            .queryParam("wind_knots", 18)
            .when()
            .get("/wca")
            .then()
            .statusCode(200)
            .body("windCorrectionAngle", is(-10));
  }

  @Test
  void calculateWindCorrectionAnglePositive() {
    given()
            .queryParam("tc", 180)
            .queryParam("tas", 98)
            .queryParam("wind_direction", 210)
            .queryParam("wind_knots", 12)
            .when()
            .get("/wca")
            .then()
            .statusCode(200)
            .body("windCorrectionAngle", is(4));
  }
}
