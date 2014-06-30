package Uniplay.Workbench;

import javafx.fxml.FXML;

public class NGWorkbenchControlStageController {

    @FXML
    protected void handleLevelDesigner(){
        Manager.newLevelDesigner();
    }

    public NGWorkbenchManager Manager;


}
