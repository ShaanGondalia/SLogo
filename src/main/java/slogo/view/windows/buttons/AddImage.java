package slogo.view.windows.buttons;

import javafx.stage.FileChooser;
import javafx.stage.Stage;
import slogo.view.util.ButtonUtil;

import java.io.*;
import java.nio.channels.FileChannel;

/**
 * Button that opens a file chooser to select an image to be added to a list of turtle view images
 *
 * @author Zack Schrage
 */
public class AddImage implements IDEButton {

    public static final String imagePath = "src/main/resources/view/img/";

    @Override
    public void doAction(ButtonUtil info) {
        FileChooser fc = new FileChooser();
        File file = fc.showOpenDialog(new Stage());
        if (file != null) {
            try {
                FileChannel src = new FileInputStream(file).getChannel();
                FileChannel dest = new FileOutputStream(new File(imagePath + file.getName())).getChannel();
                dest.transferFrom(src, 0, src.size());
            } catch (Exception e) {
                //Make this a custom exception
                e.printStackTrace();
            }
        }
    }

}
