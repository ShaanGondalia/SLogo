package slogo.view.windows.buttons;

import slogo.view.turtle.TurtleView;
import slogo.view.util.ButtonUtil;

public class SpawnTurtle implements IDEButton {

    @Override
    public void doAction(ButtonUtil info) {
        TurtleView tv = new TurtleView();
        info.c().addTurtle(tv);
        //addTurtleView(tv);
    }
}
