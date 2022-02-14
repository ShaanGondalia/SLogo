class TurtleView {

    private Image turtleView;
    private Pose pose;

    //Use Case 1: Generate an animation of incremental change poses given a newPose and a current pose
    public Queue<Pose> generateAnimation(Pose newPose) {
        int change = getSpeed() * distance(this.pose, newPose) / getFPS();
        double dx = (newPose.x() - this.pose.x())/change;
        double dy = (newPose.y() - this.pose.y())/change;
        Queue<Pose> animation = new Queue<Pose>();
        for (int i = 1; i <= change; i++) {
            animation.add(new Pose(this.pose.x() + (dx * i), this.pose.y() + (dy * i)));
        }
        return animation;
    }

    //Use Case 2: get the distance between two poses
    private double distance(Pose p1, Pose p2) {
        return Math.sqrt((p2.y() - p1.y()) + (p2.x() - p1.x()));
    }

    //Use Case 3: Animate the poses using the JavaFX timeline
    private void animate(Queue<Pose> animation) {
        Timeline animation = new Timeline();
        animation.setCycleCount(animation.size());
        animation.getKeyFrames().add(new KeyFrame(Duration.seconds(SECOND_DELAY), e -> step(SECOND_DELAY)));
        animation.play();
    }

    private void step(double SECOND_DELAY) {
        tutleView.setX(animation.get(currPose).x());
        tutleView.setY(animation.get(currPose).y());
        currPose++;
    }

}
