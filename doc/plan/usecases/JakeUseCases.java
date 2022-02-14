//Use case 1: User types in commands and presses 'run'

public class API{

  public void run(String input){
    Parser p = new Parser();
    for (String line in input):
      Tree<Command> commands = parser.compileLine(line);
      executeCommands(commands); // from ShaanUseCases.java
  }
}

// use case 2: rotate 45 degrees to the left

public class LeftCommand implements CommandInterface;

TurtleModel turtle = new Turtle();
Command left45 = new LeftCommand(45, turtle);
left45.execute();

// use case 3: resolve variable name

String variable = ":length"
if (variable.substring(0, 1).equals(":")) {
  double value = Compiler.resolveVariable(variable);
}