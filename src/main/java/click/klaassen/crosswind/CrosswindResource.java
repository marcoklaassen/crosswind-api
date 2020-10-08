package click.klaassen.crosswind;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

@Path("/crosswind")
public class CrosswindResource {

  @Inject CrosswindService service;

  @GET
  @Produces(APPLICATION_JSON)
  public Crosswind hello(
      @QueryParam("runway") int runway,
      @QueryParam("wind_direction") int windDirection,
      @QueryParam("wind_knots") int windKnots) {
    return Crosswind.builder()
        .knots(service.calculateCrosswind(runway, windDirection, windKnots))
        .build();
  }
}
