package planes;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * Plane - base class for Planes.
 */
@AllArgsConstructor
@EqualsAndHashCode
@Getter
public abstract class Plane {

  private final String model;
  private final int maxSpeed;
  private final int maxFlightDistance;
  private final int maxLoadCapacity;

  @Override
  public String toString() {
    return "Plane{"
        + "model='" + model + '\''
        + ", maxSpeed=" + maxSpeed
        + ", maxFlightDistance=" + maxFlightDistance
        + ", maxLoadCapacity=" + maxLoadCapacity
        + '}';
  }
}
