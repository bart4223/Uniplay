package Uniplay.Workbench;

import Uniplay.Storage.NG2DLevel;
import Uniwork.Visuals.NGGrid2DDisplayController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ResourceBundle;

public class NG2DLevelDesignerStageController implements Initializable {

    protected NGGrid2DDisplayController FDCLayerGrid;
    protected NG2DGameFieldDisplayController FDCGameField;

    @FXML
    public Canvas Layer0;

    @FXML
    public Canvas LayerGrid;

    @FXML
    protected void handleLoadGOF(){
        Designer.loadFromGOF();
    }

    public NG2DLevelDesigner Designer;

    public NG2DLevel Level;

    public void initialize(URL url, ResourceBundle resourceBundle) {
        FDCLayerGrid = new NGGrid2DDisplayController(LayerGrid);
        FDCLayerGrid.GridColor = Color.DARKGRAY;
        FDCLayerGrid.Initialize();
        FDCGameField = new NG2DGameFieldDisplayController(Layer0);
        FDCGameField.MaxImageNumber = -1;
        FDCGameField.Initialize();
    }

    public void Render() {
        FDCLayerGrid.DrawGrid = true;
        FDCLayerGrid.GridDistance = Designer.getGridSize();
        FDCLayerGrid.Render();
        if (Level != null) {
            FDCGameField.GameField = Level.getGameField();
            FDCGameField.setImageName(Designer.getImageName());
            FDCGameField.Render();
        }
    }

}
