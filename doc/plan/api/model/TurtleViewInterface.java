package slogo;

/**
 * This is the external API for the view. It recieves information about the status of a turtle from
 * the model, such as its position and bearing. It also has some of the internal API methods that
 * the commands need to use to update the status of the turtle in the back end.
 *
 * The methods that are external/internal are stated below.
 */

public interface TurtleViewInterface {

    /**
     * External - Uses its current pose and an input pose to generate a sequence of incremental poses acting as an animation
     * @param pose the new Pose of the turtle
     * @return a queue of Poses to be animated
     */
    Queue<Pose> generateAnimation(Pose pose);

    /**
     * Internal - Iterates over a queue of poses 
     * Uses the asistance of a JavaFX Timeline to sequentialy update the turtle image by calling updateImage()
     * @param cmds the new Pose of the turtle
     */
    void animate(Queue<Pose> cmds);

    /**
     * Internal - Makes an incremental change to the turtle image object
     * @param x the new x position of the turtle
     * @param y the new y position of the turtle
     * @param theta the new theta of the turtle
     */
    void updateImage(double x, double y, double theta);

}
