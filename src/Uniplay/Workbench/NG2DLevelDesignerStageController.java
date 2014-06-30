package Uniplay.Workbench;

import Uniplay.Storage.NG2DLevel;
import javafx.fxml.FXML;

public class NG2DLevelDesignerStageController {

    @FXML
    protected void handleLoadGOF(){
        Designer.loadFromGOF();
    }

    public NG2DLevelDesigner Designer;

    public NG2DLevel Level;

    public void Render() {

    }

}
