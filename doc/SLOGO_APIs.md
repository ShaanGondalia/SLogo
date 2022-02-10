# SLogo API Design Lab Discussion

### Jake Heller (jeh113), Andy He (ash98), Zack Schrage (zjs5), Shaan Gondalia (sg491)

### TEAM 03

## Planning Questions

* What behaviors (methods) should the Turtle have and what service should it provide?
    * move - moves the turtle
    * rotate - rotates the turtle
* When does parsing need to take place and what does it need to start properly?
    * we parse command-by-command, and it needs a recognized keyword to start properly
* What is the result of parsing (not the details of the algorithm) and who receives it?
    * the result is a command that needs to be run on the turtle, and both the controller and view
      receives it.
* When are errors detected and how are they reported?
    * Errors are detected when there is incorrect syntax, and they are reported through an alert
      displayed by the user.
* What do different commands need to know, when do they know it, and how do they get it?
    * move need a distance, for example, they know it after the text is parsed, through the parser
      API
* What behaviors does the result of a command need to have to be used by the View?
    * how long the command is running for, step-based
* How is the View updated after a command has completed execution?
    * the view has a queue of commands to run, and after it finishes the next command is run
    * there is a updateView method that is being controlled by an animation with a potentially
      changing frame rate.
* What value would Controller(s) have in mediating between the Model and View?
    * the controller mediates the transfer of information
    * So, say there is a updated movement in the model, the controller would pass this information
      to the view and that would have
    * It will tell the model when to parse new code and then the information from that parsing and
      the movement will be passed to the view.

## APIs

### Model/Backend External API

* Goals

* Contract

* Services

### View/Frontend External API

* Goals

* Contract

* Services

### Model/Backend Internal API

* Goals

* Contract

* Services

### View/Frontend Internal API

* Goals

* Contract

* Services

## Design

### Backend Design CRCs

This class's purpose or value is to represent a customer's order:
![Order Class CRC Card](order_crc_card.png "Order Class")

This class's purpose or value is to represent a customer's order:

|Order| |
|---|---|
|boolean isInStock(OrderLine)         |OrderLine|
|double getTotalPrice(OrderLine)      |Customer|
|boolean isValidPayment (Customer)    | |
|void deliverTo (OrderLine, Customer) | |

This class's purpose or value is to represent a customer's order:

```java
public class Order {

  // returns whether or not the given items are available to order
  public boolean isInStock(OrderLine items)

  // sums the price of all the given items
  public double getTotalPrice(OrderLine items)

  // returns whether or not the customer's payment is valid
  public boolean isValidPayment(Customer customer)

  // dispatches the items to be ordered to the customer's selected address
  public void deliverTo(OrderLine items, Customer customer)
}
 ```

This class's purpose or value is to manage something:

```java
public class Something {

  // sums the numbers in the given data
  public int getTotal(Collection<Integer> data)

  // creates an order from the given data
  public Order makeOrder(String structuredData)
}
```

### Frontend Design CRCs

This class's purpose or value is to represent a customer's order:
![Order Class CRC Card](order_crc_card.png "Order Class")

This class's purpose or value is to represent a customer's order:

|Order| |
|---|---|
|boolean isInStock(OrderLine)         |OrderLine|
|double getTotalPrice(OrderLine)      |Customer|
|boolean isValidPayment (Customer)    | |
|void deliverTo (OrderLine, Customer) | |

This class's purpose or value is to represent a customer's order:

```java
public class Order {

  // returns whether or not the given items are available to order
  public boolean isInStock(OrderLine items)

  // sums the price of all the given items
  public double getTotalPrice(OrderLine items)

  // returns whether or not the customer's payment is valid
  public boolean isValidPayment(Customer customer)

  // dispatches the items to be ordered to the customer's selected address
  public void deliverTo(OrderLine items, Customer customer)
}
 ```

This class's purpose or value is to manage something:

```java
public class Something {

  // sums the numbers in the given data
  public int getTotal(Collection<Integer> data)

  // creates an order from the given data
  public Order makeOrder(String structuredData)
}
```

### Use Cases

* The user types 'fd 50' in the command window, sees the turtle move in the display window leaving a
  trail, and has the command added to the environment's history.

* The user types '50 fd' in the command window and sees an error message that the command was not
  formatted correctly.

* The user types 'pu fd 50 pd fd 50' in the command window and sees the turtle move twice (once
  without a trail and once with a trail).

* The user changes the color of the environment's background.
