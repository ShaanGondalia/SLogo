# DESIGN Document for SLOGO

## NAME(s)

Andy He Shaan Gondalia Jake Heller Zack Schrage

## Role(s)

Andy: Front-End: General Views, Buttons, Displays, Resources, Styling, Design

Zack: Front-End: Front-end Turtle, Front-end Turtle management, Some Buttons, Some Displays

Shaan: Back-end: Compiler, Parser, Reflection for creating commands and invoking methods, Turtle
Manager, Many commands, Testing.

Jake: Turtle, Controller, Color Commands, Color Palette, Compiler, Value,

## Design Goals

* We intended to ensure that adding new commands is as simple as possible, given the possibility of
  multiple languages.
* We intended to allow the user to change the look of any part of the GUI, by putting styling into
  CSS files.
* We intended adding new languages easy, to allow users from around the world to interact with our
  SLOGO application.
* We intended to allow users to add new features or sections to the front-end by reflectively
  creating buttons.
* We allowed for easy extension of turtles due to the non-static nature of our methods, allowing for
  multiple workspaces.

## High-Level Design

* In a high level, our program has a model, view, and controller.
    * The Compiler gets data from the user via the controller.
    * This compiler then updates a back-end turtle that keeps track of position, orientation, color,
      etc.
    * A front end turtle then listens to this change and updates the front-end visuals accordingly.
* The front-end can also interact with the model via the controller.
    * Using the runner class, the controller can be activated to run a command on the click of a
      button or another action, if needed.
* In summary, or controller is critical in the interaction between the front and back-end of our
  project.

## Assumptions or Simplifications

- Model
  - If the compiler has an issue with parsing the text input, it generally will not identify
    exactly where that issue is in the program. This only happens in certain cases.
  - All commands run once for every turtle that is told or asked to follow a command
  - Commands that follow the extended syntax (i.e. indefinite number of parameters using
    parenthesis) return the sum of the independent execution of each command.
- CSS only changed at the beginning of creation of workspace
- Languages other than English / Spanish can be implemented in the future
- Help Screen only requires English - no translations
- Appropriately sized image is used for the turtle (17x22 pixels)

## Changes from the Plan

## How to Add New Features

* To Add New Commands:
  * Define the syntax of the command in the ```Syntax.properties``` and ```<language>.properties``` files
    accordingly
  * Create a class in the model/command package that represents the new command (extending the proper
    abstract class or implementing the proper interface).
  * Add the correct reflexive method name to the Constructor.properties file to identify the
    constructor format of the new command
  * Add the correct number of parameter and list counts for the command in Parameter.properties and
    ListParameter.properties
  * Add the reflexive class path for the new command in Reflection.properties
* To Implement Shape and Background Color:
  * Follow the same steps as adding a new command. 
  * Create class in model to keep track of the background/shape
  * Change TurtleView to also consider Shape when drawing Turtles