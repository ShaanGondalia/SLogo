# Cell Society API Lab Discussion
### Jake Heller (jeh113), Andy He (ash98), Zack Schrage (zjs5), Shaan Gondalia (sg491)
### TEAM 03


## Current Simulation API

### External

* Identified Classes/Methods

Model.Simulation

* Goals

Provides View with a way to get and change cell states upon user click 
Provides View with a way to get the current simulation type
Provides View with a map of possible game cell states to a count for statistics display

* Contract

The View must use a List of Doubles as the parameter for the setParameters method

* Services

The View is able to get the cell informaiton, edit that information and get a count of the total number of cells to be able to run a statistical analysis on it.


### Internal

* Identified Classes/Methods

int nextState(int i, int j);
returns next state of cell

step();
steps simulation

* Goals

Allow Simulation to be extended to create new kinds of simulations and features

* Contract

anything related to a specific cell takes in i, j parameters

* Services

steps simulation
accessing next state


## Wish Simulation API

### External

* Goals
    * step method to run simulation
    * getSingleCellState to pass to the view
    * getNumberOfCells for statistics
* Contract
    * step will never have any parameters
    * model will always contain the cell information
* Services
    * the simulation will allow for the view class to easily display all relevent information about
      the simulation that is going on. However, it will not let any other classes change what is
      going on in the simulation, and it will not reveal the details of how the simulation is run.


### Internal

* Goals

* Contract

* Services
