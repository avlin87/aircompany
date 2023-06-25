package planes;

import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * PassengerPlane - representation of passenger plane.
 */
@EqualsAndHashCode(callSuper = true)
@Getter
public class PassengerPlane extends Plane {

  private final int passengersCapacity;

  public PassengerPlane(String model, int maxSpeed, int maxFlightDistance, int maxLoadCapacity,
      int passengersCapacity) {
    super(model, maxSpeed, maxFlightDistance, maxLoadCapacity);
    this.passengersCapacity = passengersCapacity;
  }

  @Override
  public String toString() {
    return super.toString().replace("}",
        ", passengersCapacity=" + passengersCapacity + '}');
  }

}
