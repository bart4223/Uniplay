package Uniplay.Workbench;

import javafx.fxml.FXML;

public class NG2DLevelDesignerStageController {

    @FXML
    protected void handleLoadGOF(){
        Designer.loadFromGOF();
    }

    public NG2DLevelDesigner Designer;

}
