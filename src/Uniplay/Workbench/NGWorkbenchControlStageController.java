package Uniplay.Workbench;

import Uniplay.Kernel.NGGameEngineConstants;
import Uniwork.Visuals.NGStageController;
import javafx.fxml.FXML;

public class NGWorkbenchControlStageController extends NGStageController{

    @FXML
    protected void handleLevelDesigner(){
        Manager.newLevelDesigner(NGGameEngineConstants.CMP_WORKBENCH_LEVELDESIGNER);
    }

    public NGWorkbenchManager Manager;

}
