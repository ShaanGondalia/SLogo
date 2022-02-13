//Use case 1: Turtle is moved forward 50 meters (back-end)

//First, create forward command by extending the proper class
abstract class TurtleCommand implements CommandInterface{...}
class ForwardCommand implements TurtleCommand{
  public ForwardCommand(double pixels, TurtleModel tm){
    super(tm);
    this.pixels = pixels;
  }
  @Override
  public void execute(){
    // Do math to find new position of turtle
    newPose = getNewPose(tm.getPose)
    tm.setPose(newPose)
  }
}

// create commands and run on model
public void test{
  TurtleModel tm = new TurtleModel();
  ForwardCommand fd50 = new ForwardCommand(50, tm);

  fd50.execute();
}


//Use case 2: Query turtle's visibility
//First, create showing query by extending the proper class
abstract class TurtleQuery implements CommandInterface{...}
class ShowingQuery implements TurtleQuery{
  public ShowingQuery(TurtleModel tm){
    super(tm);
  }
  @Override
  public double returnValue(){
    return tm.isVisible() ? 1 : 0;
  }
}

  // create commands and run on model
  public void test{
    TurtleModel tm = new TurtleModel();
    TurtleQuery tq = new TurtleQuery(tm);

    double visible = tq.returnValue();
  }



//Use case 3: Controller builds command tree and executes commands

public class Controller{

  public void run(String input){
    Parser p = new Parser();
    Tree<Command> commands = p.buildCommandTree();
    postOrderTraversal(commands);
  }

  private void postOrderTraversal(Tree<Command> commands){
    // do post order traversal of tree, calling command.execute() whenever necessary
  }
}
