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