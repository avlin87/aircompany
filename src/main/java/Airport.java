import exceptions.PlaneNotFound;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Getter;
import models.MilitaryType;
import planes.ExperimentalPlane;
import planes.MilitaryPlane;
import planes.PassengerPlane;
import planes.Plane;

/**
 * version: 1.1 made by Vitali Shulha 4-Jan-2019.
 */
@AllArgsConstructor
@Getter
public class Airport {

  private final List<? extends Plane> planes;

  /**
   * Method find Passenger plane with max passenger capacity if any
   * otherwise throw PlaneNotFound exception.
   *
   * @return fount PassengerPlane with max capacity.
   */
  public PassengerPlane getPassengerPlaneWithMaxPassengersCapacity() {
    List<PassengerPlane> passengerPlanes = getPassengerPlanes();
    if (passengerPlanes.isEmpty()) {
      throw new PlaneNotFound("Passenger planes not found");
    }
    return passengerPlanes.stream()
        .max(Comparator.comparingInt(PassengerPlane::getPassengersCapacity))
        .orElse(passengerPlanes.get(0));
  }

  public List<MilitaryPlane> getBomberMilitaryPlanes() {
    return getMilitaryPlaneByType(MilitaryType.BOMBER);
  }

  public List<MilitaryPlane> getTransportMilitaryPlanes() {
    return getMilitaryPlaneByType(MilitaryType.TRANSPORT);
  }

  public List<PassengerPlane> getPassengerPlanes() {
    return filterPlanesByType(PassengerPlane.class);
  }

  public List<MilitaryPlane> getMilitaryPlanes() {
    return filterPlanesByType(MilitaryPlane.class);
  }

  public List<ExperimentalPlane> getExperimentalPlanes() {
    return filterPlanesByType(ExperimentalPlane.class);
  }

  public Airport sortByMaxDistance() {
    planes.sort(Comparator.comparingInt(Plane::getMaxFlightDistance));
    return this;
  }

  public Airport sortByMaxSpeed() {
    planes.sort(Comparator.comparingInt(Plane::getMaxSpeed));
    return this;
  }

  public Airport sortByMaxLoadCapacity() {
    planes.sort(Comparator.comparingInt(Plane::getMaxLoadCapacity));
    return this;
  }

  @Override
  public String toString() {
    return "Airport{"
        + "planes=" + planes.toString()
        + '}';
  }


  private List<MilitaryPlane> getMilitaryPlaneByType(MilitaryType type) {
    return getMilitaryPlanes().stream()
        .filter(plane -> plane.getType().equals(type))
        .collect(Collectors.toList());
  }

  private <T extends Plane> List<T> filterPlanesByType(Class<T> planeType) {
    return planes.stream()
        .filter(planeType::isInstance)
        .map(planeType::cast)
        .collect(Collectors.toList());
  }
}
