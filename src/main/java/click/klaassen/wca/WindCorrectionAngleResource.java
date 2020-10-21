package click.klaassen.wca;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static org.eclipse.microprofile.metrics.MetricUnits.MILLISECONDS;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.metrics.annotation.Timed;

@Path("/wca")
public class WindCorrectionAngleResource {

  @Inject WindCorrectionAngleService service;

  @GET
  @Produces(APPLICATION_JSON)
  @Counted(
      name = "performedWcaCalculations",
      description = "How many WCA calculations have been performed.")
  @Timed(
      name = "wcaCalculationTimer",
      description = "A measure of how long it takes to calculate the wca.",
      unit = MILLISECONDS)
  public WindCorrection calculateWindCorrectionAngle(
      @QueryParam("tc") int trueCourse,
      @QueryParam("tas") int trueAirSpeed,
      @QueryParam("wind_direction") int windDirection,
      @QueryParam("wind_knots") int windKnots) {
    return WindCorrection.builder()
        .windCorrectionAngle(
            service.calculateWindCorrectionAngle(
                trueCourse, trueAirSpeed, windDirection, windKnots))
        .build();
  }
}
