package planes;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import models.ClassificationLevel;

/**
 * ExperimentalPlane - representation of experimental plane.
 */
@EqualsAndHashCode(callSuper = true)
@Getter
public class ExperimentalPlane extends Plane {

  private final ClassificationLevel classificationLevel;

  public ExperimentalPlane(String model, int maxSpeed, int maxFlightDistance, int maxLoadCapacity,
      ClassificationLevel classificationLevel) {
    super(model, maxSpeed, maxFlightDistance, maxLoadCapacity);
    this.classificationLevel = classificationLevel;
  }

  @Override
  public String toString() {
    return "ExperimentalPlane{" + "model='" + getModel() + '\'' + '}';
  }
}
