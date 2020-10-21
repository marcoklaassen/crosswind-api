package click.klaassen.crosswind;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Crosswind {
  private int knots;
  private Direction direction;
}
