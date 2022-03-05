SLogo
====

This project implements a development environment that helps users write programs to draw using a
turtle.

Names:

Shaan Gondalia

Andy He

Jake Heller

Zachary Schrage

### Timeline

Start Date: 2/10/2022

Finish Date: 3/4/2022

Hours Spent:

Andy: 30 hours

Shaan: 40 hours

Jake: 30 hours

### Primary Roles

Andy : Front-End: General Views, Buttons, Displays, Resources, Styling, Design

Shaan : Back-end: Compiler, Parser, Reflection for creating commands and invoking methods, Turtle Manager, Many commands, Testing.

Jake : Turtle, Controller, Color Commands, Color Palette, Compiler, Value, 

### Resources Used

- [Reflection for Methods](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/reflect/Method.html)
- [Reflection for Constructors](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/reflect/Constructor.html)
- [Property Change Event](https://docs.oracle.com/en/java/javase/17/docs/api/java.desktop/java/beans/PropertyChangeEvent.html)
- [Property Change Listener](https://docs.oracle.com/en/java/javase/17/docs/api/java.desktop/java/beans/PropertyChangeListener.html)

### Running the Program

Main class: Main.java

Data files needed:

- Model
    - Constructor.properties: Mapping of Command name to method for handling command construction
    - Handler.properties: Maps parsed text to handler method for compiler
    - ListParameter.properties: Defines how many lists a command takes
    - Parameter.properties: Defines how many parameters a command takes
    - Reflection.properties: Mapping of Command name to java class path for reflection.
    - Property files in exception package
        - Exception messages for each language
    - slogo.languages
        - Defines the keywords for commands in each language, as well as syntax
- View

Features implemented:

- All basic syntax and commands as defined on
  the [course website](https://courses.cs.duke.edu/compsci308/spring22/assign/03_parser/commands.php)
  .
- Visual representation of turtles that change based on the execution of commands
    - UI for displaying errors to the user
    - UI for displaying command history
    - UI for displaying user-defined variables
    - UI for displaying user-defined commands
    - Speed slider for animation of the turtle
- Changing the view of the turtle and IDE (background colors, themes, pen color)
- Help page for commands
- Support for different languages (English and Spanish are implemented, the rest can be implemented
  by translating and adding the related properties files)
- Saving and loading SLogo programs
- JUnit tests for all classes
- Support for multi turtle commands as
  described [here](https://courses.cs.duke.edu/compsci308/spring22/assign/03_parser/commands2_KA9.php#Multiple)
- Support for display commands as
  described [here](https://courses.cs.duke.edu/compsci308/spring22/assign/03_parser/commands2_KA9.php#Multiple)
- Support for extended syntax allowing commands to receive an indefinite number of arguments, as
  described [here](https://courses.cs.duke.edu/compsci308/spring22/assign/03_parser/commands2_KA9.php#Multiple)
- Creation of multiple workspaces, allowing users to create multiple instances of the IDE and turtle
  display
- Interacting with turtles without writing code
    - Execution of commands from history
    - Ability to edit user defined variables

### Notes/Assumptions

Assumptions or Simplifications:

- Model
    - If the compiler has an issue with parsing the text input, it generally will not identify
      exactly where that issue is in the program. This only happens in certain cases.
    - All commands run once for every turtle that is told or asked to follow a command
    - Commands that follow the extended syntax (i.e. indefinite number of parameters using
      parenthesis) return the sum of the independent execution of each command.
- CSS only changed at the beginning of creation of workspace
- Languages other than English / Spanish can be implemented in the future
- Help Screen only requires English - no translations

Interesting data files:

Known Bugs:

Noteworthy Features:

- Support for multiple turtles
    - All multiple turtle commands are implemented, and the creation of any turtle via a command
      will also create a new turtle in the view
    - Context object is flexible object that basically tells compiler what to do based on its state.
  This design allowed us to easily extend to different types of syntax and commands

### Impressions

Shaan - This project was challenging and required a lot of thought about creating a modular and
extendable design. Reflection had a bit of a learning curve, and building the compiler to be
readible and modifiable was a big challenge. I also learned the importance of creating a
well-defined API and sticking with it, as that is necessary for integration between teams.