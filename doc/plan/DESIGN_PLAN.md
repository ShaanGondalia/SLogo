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
      execute and returnValue method.
    * The CommandInterface is open for extension, as any command that executes will extend the
      CommandInterface.

#### External View

#### Internal View

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

## Design Considerations

## Test Plan

## Team Responsibilities

* Team Member #1

* Team Member #2

* Team Member #3

* Team Member #4
