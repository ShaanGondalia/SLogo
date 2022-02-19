package slogo.model;

import java.util.ArrayList;
import java.util.List;
import slogo.model.command.turtle.Forward;

public class ModelTester {

  public static void main(String[] args){
    Turtle t = new Turtle();
    List<Double> fArgs = new ArrayList<>();
    fArgs.add(50.0);
    Forward f = new Forward(fArgs, t);
    System.out.println(t.getPose().x());
    f.execute();
    System.out.println(t.getPose().x());
  }
}
