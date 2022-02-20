package slogo.model.exception;

/**
 * Custom exception thrown when the program has an unrecognized symbol.
 *
 * @author Shaan Gondalia
 */
public class SymbolNotFoundException extends Exception {

    /**
     * Creates custom exception with given error message
     *
     * @param errorMessage the error message to display
     */
    public SymbolNotFoundException(String errorMessage) {
      super(errorMessage);
    }
}
