package exceptions;

/**
 * Exception for case when plane of specified type not found.
 */
public class PlaneNotFound extends RuntimeException {

  public PlaneNotFound(String message) {
    super(message);
  }
}
