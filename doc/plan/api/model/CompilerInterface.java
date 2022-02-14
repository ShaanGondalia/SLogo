package slogo;

/**
 * This is the internal API for the resolving references in the model
 *
 * public interface CompilerInterface
 *
 */
public interface CommandInterface{

  /**
   * Internal - resolves variables names
   * @return a double with the value of the desired variable
   */
  public double resolveVariable(String variableName);

  /**
   * Internal - Returns the double value that is associated with a command
   * @return the double value that a command returns (as specificed)
   */
  public double resolveMethod();
}