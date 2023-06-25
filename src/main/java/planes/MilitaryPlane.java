package planes;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import models.MilitaryType;

/**
 * MilitaryPlane - representation of military type of plane.
 */
@EqualsAndHashCode(callSuper = true)
@Getter
public class MilitaryPlane extends Plane {

  private final MilitaryType type;

  public MilitaryPlane(String model, int maxSpeed, int maxFlightDistance,
      int maxLoadCapacity, MilitaryType type) {
    super(model, maxSpeed, maxFlightDistance, maxLoadCapacity);
    this.type = type;
  }


  @Override
  public String toString() {
    return super.toString().replace("}", ", type=" + type + '}');
  }
}
