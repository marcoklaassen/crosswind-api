package click.klaassen.crosswind;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static org.eclipse.microprofile.metrics.MetricUnits.MILLISECONDS;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.metrics.annotation.Timed;

@Path("/crosswind")
public class CrosswindResource {

  @Inject CrosswindService service;

  @GET
  @Produces(APPLICATION_JSON)
  @Counted(
      name = "performedCrosswindCalculations",
      description = "How many crosswind calculations have been performed.")
  @Timed(
      name = "crosswindCalculationTimer",
      description = "A measure of how long it takes to calculate the crosswind.",
      unit = MILLISECONDS)
  public Crosswind calculateCrosswind(
      @QueryParam("runway") int runway,
      @QueryParam("wind_direction") int windDirection,
      @QueryParam("wind_knots") int windKnots) {
    return Crosswind.builder()
        .knots(service.calculateCrosswind(runway, windDirection, windKnots))
        .build();
  }
}
