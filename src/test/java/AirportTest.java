import exceptions.PlaneNotFound;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import models.ClassificationLevel;
import models.MilitaryType;
import org.testng.Assert;
import org.testng.Assert.ThrowingRunnable;
import org.testng.annotations.Test;
import planes.ExperimentalPlane;
import planes.MilitaryPlane;
import planes.PassengerPlane;
import planes.Plane;

public class AirportTest {

  private static final List<Plane> planes = Arrays.asList(
      new PassengerPlane("Boeing-737", 900, 12000, 60500, 164),
      new PassengerPlane("Boeing-737-800", 940, 12300, 63870, 192),
      new PassengerPlane("Boeing-747", 980, 16100, 70500, 242),
      new PassengerPlane("Airbus A320", 930, 11800, 65500, 188),
      new PassengerPlane("Airbus A330", 990, 14800, 80500, 222),
      new PassengerPlane("Embraer 190", 870, 8100, 30800, 64),
      new PassengerPlane("Sukhoi Superjet 100", 870, 11500, 50500, 140),
      new PassengerPlane("Bombardier CS300", 920, 11000, 60700, 196),
      new MilitaryPlane("B-1B Lancer", 1050, 21000, 80000, MilitaryType.BOMBER),
      new MilitaryPlane("B-2 Spirit", 1030, 22000, 70000, MilitaryType.BOMBER),
      new MilitaryPlane("B-52 Stratofortress", 1000, 20000, 80000, MilitaryType.BOMBER),
      new MilitaryPlane("F-15", 1500, 12000, 10000, MilitaryType.FIGHTER),
      new MilitaryPlane("F-22", 1550, 13000, 11000, MilitaryType.FIGHTER),
      new MilitaryPlane("C-130 Hercules", 650, 5000, 110000, MilitaryType.TRANSPORT),
      new ExperimentalPlane("Bell X-14", 277, 482, 500, ClassificationLevel.SECRET),
      new ExperimentalPlane("Ryan X-13 Vertijet", 560, 307, 500, ClassificationLevel.TOP_SECRET)
  );

  private static final PassengerPlane planeWithMaxPassengerCapacity
      = new PassengerPlane("Boeing-747", 980, 16100, 70500, 242);

  @Test
  public void testGetTransportMilitaryPlanes() {
    Airport airport = new Airport(planes);
    List<MilitaryPlane> transportMilitaryPlanes = airport.getTransportMilitaryPlanes();
    boolean flag = false;
    for (MilitaryPlane militaryPlane : transportMilitaryPlanes) {
      if ((militaryPlane.getType() == MilitaryType.TRANSPORT)) {
        flag = true;
        break;
      }
    }
    Assert.assertTrue(flag);
  }

  @Test
  public void testGetPassengerPlaneWithMaxCapacitySuccessfully() {
    System.out.println("TEST testGetPassengerPlaneWithMaxCapacity started!");
    Airport airport = new Airport(planes);
    PassengerPlane expectedPlaneWithMaxPassengersCapacity = airport.getPassengerPlaneWithMaxPassengersCapacity();
    Assert.assertEquals(planeWithMaxPassengerCapacity, expectedPlaneWithMaxPassengersCapacity);
  }

  @Test
  public void testGetPassengerPlaneWithMaxCapacityThrow() {
    System.out.println("TEST testGetPassengerPlaneWithMaxCapacity started!");
    Airport airport = new Airport(new ArrayList<>());

    ThrowingRunnable runnable = airport::getPassengerPlaneWithMaxPassengersCapacity;

    Assert.assertEquals(
        Assert.expectThrows(PlaneNotFound.class, runnable).getMessage(),
        "Passenger planes not found");
  }

  @Test
  public void testSortByMaxLoadCapacity() {
    Airport airport = new Airport(planes)
        .sortByMaxLoadCapacity();
    List<? extends Plane> planesSortedByMaxLoadCapacity = airport.getPlanes();

    boolean nextPlaneMaxLoadCapacityIsHigherThanCurrent = true;
    for (int i = 0; i < planesSortedByMaxLoadCapacity.size() - 1; i++) {
      Plane currentPlane = planesSortedByMaxLoadCapacity.get(i);
      Plane nextPlane = planesSortedByMaxLoadCapacity.get(i + 1);
      if (currentPlane.getMaxLoadCapacity() > nextPlane.getMaxLoadCapacity()) {
        nextPlaneMaxLoadCapacityIsHigherThanCurrent = false;
        break;
      }
    }
    Assert.assertTrue(nextPlaneMaxLoadCapacityIsHigherThanCurrent);
  }

  @Test
  public void testSortByMaxSpeed() {
    Airport airport = new Airport(planes)
        .sortByMaxSpeed();
    List<? extends Plane> planesSortedByMaxSpeed = airport.getPlanes();

    boolean nextPlaneMaxSpeedIsHigherThanCurrent = true;
    for (int i = 0; i < planesSortedByMaxSpeed.size() - 1; i++) {
      Plane currentPlane = planesSortedByMaxSpeed.get(i);
      Plane nextPlane = planesSortedByMaxSpeed.get(i + 1);
      if (currentPlane.getMaxSpeed() > nextPlane.getMaxSpeed()) {
        nextPlaneMaxSpeedIsHigherThanCurrent = false;
        break;
      }
    }
    Assert.assertTrue(nextPlaneMaxSpeedIsHigherThanCurrent);
  }

  @Test
  public void testSortByMaxDistance() {
    Airport airport = new Airport(planes)
        .sortByMaxDistance();
    List<? extends Plane> planesSortedByMaxDistance = airport.getPlanes();

    boolean nextPlaneMaxDistanceIsHigherThanCurrent = true;
    for (int i = 0; i < planesSortedByMaxDistance.size() - 1; i++) {
      Plane currentPlane = planesSortedByMaxDistance.get(i);
      Plane nextPlane = planesSortedByMaxDistance.get(i + 1);
      if (currentPlane.getMaxFlightDistance() > nextPlane.getMaxFlightDistance()) {
        nextPlaneMaxDistanceIsHigherThanCurrent = false;
        break;
      }
    }
    Assert.assertTrue(nextPlaneMaxDistanceIsHigherThanCurrent);
  }

  @Test
  public void testHasAtLeastOneBomberInMilitaryPlanes() {
    Airport airport = new Airport(planes);
    List<MilitaryPlane> bomberMilitaryPlanes = airport.getBomberMilitaryPlanes();
    for (MilitaryPlane militaryPlane : bomberMilitaryPlanes) {
      if ((militaryPlane.getType() != MilitaryType.BOMBER)) {
        Assert.fail("Test failed!");
      }
    }
  }

  @Test
  public void testExperimentalPlanesHasClassificationLevelHigherThanUnclassified() {
    Airport airport = new Airport(planes);
    List<ExperimentalPlane> ExperimentalPlanes = airport.getExperimentalPlanes();
    boolean hasUnclassifiedPlanes = false;
    for (ExperimentalPlane experimentalPlane : ExperimentalPlanes) {
      if (experimentalPlane.getClassificationLevel() == ClassificationLevel.UNCLASSIFIED) {
        hasUnclassifiedPlanes = true;
        break;
      }
    }
    Assert.assertFalse(hasUnclassifiedPlanes);
  }
}
