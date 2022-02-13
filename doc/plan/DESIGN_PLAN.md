# SLogo Design Plan

### NAMES - Jake Heller (jeh113), Andy He (ash98), Zack Schrage (zjs5), Shaan Gondalia (sg491)

### TEAM 03

## Introduction

Our team is implementing a simplified version of
the [Logo Programming Language](https://en.wikipedia.org/wiki/Logo_(programming_language)), known as
SLogo. We aim to create a design that has a clear separation of the Model, View and Controller,
using well-defined internal and external APIs to allow for communication between modules. We will
build a UI that allows users to input commands that interact with the turtle, displaying the
turtle's changes as these commands are run. The Model will be responsible for parsing the user's
commands and executing them on a back-end representation of the turtle. The controller will handle
the interface between the model and view elements.

## Overview

TODO: Insert UML Diagram here


## User Interface

The interface will be separated into separate windows, all of which will show up upon
initialization. Each of the windows will represent something, aka. Commands, Variables, and
TurtleView just to start.

The user will interact only with the CommandWindow, as to keep things simple. Colors will mostly be
gray, black, and white for the command and variable windows, while the turtle window will be more
colorful as necessitated by the specifications. Attached is a wireframe.

Erroneous situations will be reported through JavaFX alerts, as again to keep things simple. Such
situations include bad functions, by showing an alert, and these errors will be handled by an Errors
Front-end class.

## Design Details

The four APIs that were created are:

#### External Model

* Provides methods for building a tree of commands based on the user input.
    * The controller will grab the text input from the view and call this API to build a tree of
      commands that it will execute.
    * The controller will execute commands in the tree via post-order traversal. The controller does
      not care about the specific type of command or its return value (that data is internal).
* Provides methods for obtaining the state of the TurtleModel
    * These methods will be called by the controller to pass the Pose (position and orientation) of
      the turtle to the TurtleView.
    * Also provides methods for other metadata about the turtle, such as whether it is visible or
      whether the pen is down.
        * These states need to be stored in the model because commands only operate on the back-end
          representation of the turtle. There is no way for a command to operate directly on the
          TurtleView, as per the MVC architecture.

#### Internal Model

* Provides an interface for executing commands on the back-end representation of the turtle. The
  specifics of internal model API can be found in CommandInterface and TurtleModelInterface.
    * The CommandInterface will be extended by specific types of commands (such as TurtleCommands,
      TurtleQueries, etc.).
    * The methods of the CommandInterface are closed for modifications. Each command needs an
      execute() and returnValue() method.
    * The CommandInterface is open for extension, as any command that executes will extend the
      CommandInterface.

#### External View

#### Internal View

## Design Considerations

### Model and View representations of the Turtle

We discussed how we should separate the model and view representations of the turtles. The current solution is to have a TurtleModel and TurtleView. The TurtleModel is the object that commands directly update (as that should happen only in the model). THe View is updated whenever the model is updated, calling an external api that returns data about the model. The TurtleModel also contains information that is only really relavant to the view, such as the visibility of the turtle. This is necessary for our solution because the command PenDown must operate on the TurtleModel (back-end representation of the turtle), so that state must be stored in the model somewhere.

An alternative to this solution would be to allow commands to operate directly on the TurtleView as well. This would allow the removal of certain view-specific instance variables from the model. Ultimately, we did not choose this option as it adds too much complexity and potentially violates the Model View separation princple (Commands are part of the model, they should not interact with the View directly).

### When to update the view

Our current solution does not use threading, so we need to explicitly figure out when the view gets updated. The current solution is to have the controller update the view after each command in the command tree is executed.

An alternate solution would be to update the view in the commands themselves. Once again, we thought that this solution added too much complexity and violated the MVC architecture.

## Test Plan

### Specific Testing Strategies

#### Small Classes

We will be using small classes to make testing the entire functionality of classes as simple as
possible. For instance, we will have a separate class for each command (each of which only has 2
public methods and a constructor). We can write JUnit tests for each of these classes to verify that
their behavior matches the expected behavior.

#### Throwing Exceptions

Methods will never return null if they fail. Instead, they will always throw an exception. This
helps with negative testing, as we can check that the correct exceptions are thrown whenever an
error occurs.

### Project Feature Test Scenarios

#### Turtle Model Updating

* Updating the position of the turtle (simple xy translation)
    * Use setPose() command to set the Pose of the turtle
    * Can check that the position of the turtle was updated correctly via getPose()
* Updating the bearing of the turtle
    * Use setPose() command to set the Pose of the turtle
    * Can check that the orientation of the turtle was updated correctly via getPose()
* Negative - Set the position of the turtle to a value that is too high
    * Use setPose() command to set the Pose of the turtle
    * Check to see that setPose() throws a correct error stating that the position was too large.

#### Command Parsing

* Creating a simple FD 50 command from text input
    * Use buildCommandTree() to build a command tree for the input.
    * Make sure that the Tree only has one command and that it has the correct type.
* Create a chain of Commands (FD FD 50)
    * Use buildCommandTree() to build a command tree for the input.
    * Check the size and shape of the command tree by traversing the tree. Check the types of each
      command.
* Attempt to parse an incorrect input (50 FD)
    * Use buildCommandTree() to build a command tree for the input.
    * Verify that buildCommandTree() throws the correct exception for an incorrectly formatted
      command.

#### Command Execution

* Execute a forward command on a turtle
    * Create the command through a constructor.
    * Check the return value of the command
    * Check the state of the turtle before and after the command has been executed.
* Execute a command with an incorrect parameter.
    * Check that a command throws an exception if incorrect parameters are passed in to its
      constructor.
* Execute a chain of commands on a turtle
    * Check the state of the turtle after each command is executed. Make sure that the returned
      values match the expected results.

#### Turtle View updating

* Updating the position of the turtle (simple xy translation)
    * Use setPose() command to set the Pose of the turtle
    * Verify the JavaFX components of the turtle view have been changed correctly.
* Updating the bearing of the turtle
    * Use setPose() command to set the Pose of the turtle
    * Verify the JavaFX components of the turtle view have been changed correctly.
* Negative - Set the position of the turtle outside the window
    * Use setPose() command to set the Pose of the turtle
    * Make sure that the view throws an exception if the turtle moves outside the window (or
      corrects it somehow).

## Team Responsibilities

* Andy - View.

* Zack - View.

* Jake - Model. Working on command parsing and building the input tree.

* Shaan - Model. Working on creating the commands and creating the turtle model.
