package Uniplay.Misc;

import Uniwork.Visuals.NGGrid2DDisplayController;
import Uniwork.Visuals.NGStageController;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;

public class NGSplashStageController extends NGStageController {

    protected NGGrid2DDisplayController FDCGrid;

    public NGSplashManager Manager;

    @FXML
    public Canvas Layer0;

    @FXML
    public Canvas Layer1;

    @Override
    protected void CreateDisplayController() {
        super.CreateDisplayController();
        FDCGrid = new NGGrid2DDisplayController(Layer0);
        FDCGrid.GridColor = Color.DARKGRAY;
        registerDisplayController(FDCGrid);
    }

    @Override
    protected void DoBeforeRenderScene() {
        super.DoBeforeRenderScene();
        FDCGrid.GridDistance = Manager.GridDistance;
    }

}
