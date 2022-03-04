## SLogo API Changes

### TEAM

03

### NAMES

Andy He, Shaan Gondalia, Jake Heller, Zach Schrage

## Changes to the Initial APIs

### Backend External

* public Pose getPose();

    * Why was the change made?
        * We discovered listeners, so we do not have to explicity call this function in the
          front-end
    * Major or Minor (how much they affected your teammate's code)
        * Major, this completely upended our turtle communication, but it was ok because we had good
          team communication.
    * Better or Worse (and why)
      * Better because it is cleaner and the listener is more elegent
      
### Backend Internal

* buildCommandTree():

    * Why was the change made?
        * This was done before the knowledge of the stacks
    * Major or Minor (how much they affected your team mate's code)
        * Very minimal, since the backend was implemented a whole new way, so this internal API had
          little effect.
    * Better or Worse (and why)
        * Better, since this way of doing things actually works

### Frontend External

* run() is made private

    * Why was the change made?
        * It is local to the front end, so there is no need for it to be external
    * Major or Minor (how much they affected your team mate's code)
        * Minor, since Andy made this stuff work so it wasn't a big deal.
    * Better or Worse (and why)
        * Better since there are less public methods
        
### Frontend Internal

* Queue<Pose> generateAnimation(Pose pose)

    * Why was the change made?
        * we did not know javafx did a lot of the drawing for us
    * Major or Minor (how much they affected your team mate's code)
        * minor since zack just did it the easier way
    * Better or Worse (and why)
        * Better since it makes the code cleaner and we are using built in functionality.
        


