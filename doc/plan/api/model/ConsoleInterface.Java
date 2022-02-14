package slogo;

/**
 * This is the external API for the console, which is basically the IDE portion of the front-end
 */

public interface ConsoleInterface {

  /**
   * External - takes what is currently in editor, then compiles and runs code
   */
  void run();

  /**
   * External - method to display variables
   * @return all the currently defined variables
   */
  List<Variable> showVariables();

  /**
   * External - method to display user-defined methods
   * @return list of methods (Command objectsO
   */
  List<Command> showMethods;

}
