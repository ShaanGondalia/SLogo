package slogo;

/**
 * This is the external API for the parser in the model. It provides an interface for parsing commands and returning a tree of commands.
 *
 */
public interface ParserInterface{

  /**
   * Builds a tree of command objects based on the input from the controller.
   * @param input the text input of the commands that the user entered in the view
   * @return A tree of commands that represents the entire chain of commands the user input.
   * @throws Exception if the parser cannot interpret the string
   */
  public Tree<Command> buildCommandTree(String input) throws Exception;

}