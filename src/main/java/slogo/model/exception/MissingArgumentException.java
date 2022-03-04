package slogo.model.exception;

/**
 * Custom exception thrown when a command is created without the correct number of arguments.
 *
 * @author Shaan Gondalia
 */
public class MissingArgumentException extends Exception {

  private static final String DEFAULT_ERROR_MESSAGE = "Not Enough Arguments";

  /**
   * Creates custom exception with default error message
   */
  public MissingArgumentException() {
    super(DEFAULT_ERROR_MESSAGE);
  }

  /**
   * Creates custom exception with given error message
   *
   * @param errorMessage the error message to display
   */
  public MissingArgumentException(String errorMessage) {
    super(errorMessage);
  }
}
