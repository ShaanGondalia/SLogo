package slogo.model;

import slogo.model.turtle.Turtle;

public class ModelTester {

  public static final String PROGRAM = "CS PU HT TOWARDS 50 SUM 50 XCOR BK 100 PD FD * PI 100";
  //public static final String PROGRAM = "+ 50 fd 75";

  public static void main(String[] args) throws Exception {
    Compiler c = new Compiler("English");
    Turtle turtle = new Turtle();
    //c.run(PROGRAM);
  }
}
