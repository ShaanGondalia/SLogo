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

## Changes from the Plan

## How to Add New Features