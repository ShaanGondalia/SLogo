## Refactoring Lab Discussion

### TEAM

03

### NAMES

Andy He, Shaan Gondalia, Jake Heller, Zack Schrage

## Issues in Current Code

### Compiler Class

* Very long method, will refactor to shorten down the method
* Nested conditionals that should be cleaned up.
* Maybe has dependencies issues
* Lots of intra-file duplication

### VariablesAndCommandsSection Class

* Perhaps should be two separate classes, since it has both the variables and commands
* Lots of private static variables that should get moved to properties files
* Some repeated code in the creation of the files and running the commands that can be fixed.

### Main class

* Delete unused functions
* Get ready to support multiple windows

## Refactoring Plan

* What are the code's biggest issues?
    * The code's biggest issues are in the compiler, since the methods are long and there are magic
      values.
    * Additionally, there are a lot of magic values in the view classes.

* Which issues are easy to fix and which are hard?
    * The view refactoring is very easy to fix because its trivial to just put values in a
      properties file.
    * The model refactoring is more complex because there is actual logic that may need to be moved
      around.

* What are good ways to implement the changes "in place"?
    * A way to implement the changes in place is by communication with the team to ensure there are
      no merge conflicts.

## Refactoring Work

* Compiler Long Methods
    * We are fixing that by splitting up the method into smaller subroutines.
    
* VariablesAndCommands class's magic values
    * We are fixing that by creating a new ResourceBundle in the Resources folder that contains the
      string.

