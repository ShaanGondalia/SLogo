
//Use case 1: 3 views are initialized and shown to the user

class Main{
    public void start(){
        Windows windows = new Windows(size);
        windows.applyStyleSheets("default.css");
        windows.getResources("English.properties");
        windows.displayAll();
    }
}

//Use case 2: Variables Displayed to the variable window.

class Windows{
    public void updateVariables(){
        Map<String, Integer> map = model.getVariableMap();
        myVariableDisplay.displayEach(map);
    }
}

// Use case 3: User wants to change the mode from light to dark by updating the color
// of the background of TurtleDispaly

// A similar function will be added for changing images

class TurtleDisplay(){
    public void readNewCSS(String address){ //internal frontend API
        pane.setStyleSheet(formatted(address));
        updatePane();
    }
    private void formatted(String adrdess){
        // format file address
    }
    private void updatePane(){
        // maybe need to put the selected stylesheet in affect
    }
}