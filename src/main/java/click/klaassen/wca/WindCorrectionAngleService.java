package click.klaassen.wca;

import static java.lang.Math.sin;
import static java.lang.Math.toRadians;

import java.math.BigDecimal;
import java.math.RoundingMode;
import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
class WindCorrectionAngleService {
  int calculateWindCorrectionAngle(
      int trueCourse, int trueAirSpeed, int windDirection, int windKnots) {
    double wca = ((windKnots * sin(toRadians(windDirection - trueCourse))) * 60) / trueAirSpeed;
    return new BigDecimal(wca).setScale(0, RoundingMode.HALF_DOWN).intValue();
  }
}
