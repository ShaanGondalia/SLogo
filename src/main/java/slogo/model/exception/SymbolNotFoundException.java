package slogo.model.exception;

/**
 * Custom exception thrown when the program has an unrecognized symbol.
 *
 * @author Shaan Gondalia
 */
public class SymbolNotFoundException extends Exception {

  private static final String DEFAULT_ERROR_MESSAGE = "Symbol Not Found";

  /**
   * Creates custom exception with default error message
   */
  public SymbolNotFoundException() {
    super(DEFAULT_ERROR_MESSAGE);
  }

  /**
   * Creates custom exception with given error message
   *
   * @param errorMessage the error message to display
   */
  public SymbolNotFoundException(String errorMessage) {
    super(errorMessage);
  }
}
