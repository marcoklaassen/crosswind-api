package click.klaassen.wca;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class WindCorrection {
  private int windCorrectionAngle;
//  private int groundSpeed;
}
