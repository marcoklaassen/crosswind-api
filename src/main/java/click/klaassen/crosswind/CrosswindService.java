package click.klaassen.crosswind;

import static java.lang.Math.sin;
import static java.lang.Math.toRadians;

import java.math.BigDecimal;
import java.math.RoundingMode;
import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
class CrosswindService {
  int calculateCrosswind(int runway, int windDirection, int windKnots) {
    return new BigDecimal(windKnots * sin(toRadians(runway - windDirection)))
        .setScale(0, RoundingMode.UP)
        .intValue();
  }
}
