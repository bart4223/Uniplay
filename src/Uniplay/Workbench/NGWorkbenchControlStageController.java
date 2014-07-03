package Uniplay.Workbench;

import Uniplay.Kernel.NGGameEngineConstants;
import javafx.fxml.FXML;

public class NGWorkbenchControlStageController {

    @FXML
    protected void handleLevelDesigner(){
        Manager.newLevelDesigner(NGGameEngineConstants.CMP_WORKBENCH_LEVELDESIGNER);
    }

    public NGWorkbenchManager Manager;

}
