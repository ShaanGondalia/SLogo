//Use case 1: User types in commands and presses 'run'

public class API{

  public void run(String input){
    Parser p = new Parser();
    for (String line in input):
      Tree<Command> commands = parser.compileLine(line);
      executeCommands(commands); // from ShaanUseCases.java
  }

// user case 2: un
