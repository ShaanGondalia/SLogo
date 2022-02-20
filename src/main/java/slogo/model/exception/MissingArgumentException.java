package slogo.model.exception;

/**
 * Custom exception thrown when a command is created without the correct number of arguments.
 *
 * @author Shaan Gondalia
 */
public class MissingArgumentException extends Exception {

  /**
   * Creates custom exception with given error message
   *
   * @param errorMessage the error message to display
   */
  public MissingArgumentException(String errorMessage) {
    super(errorMessage);
  }
}
