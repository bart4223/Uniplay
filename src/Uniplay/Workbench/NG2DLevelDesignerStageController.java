package Uniplay.Workbench;

import Uniplay.Storage.NG2DLevel;
import Uniwork.Visuals.NGDisplayView;
import Uniwork.Visuals.NGGrid2DDisplayController;
import Uniwork.Visuals.NGStageController;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.ScrollEvent;
import javafx.scene.paint.Color;

public class NG2DLevelDesignerStageController extends NGStageController {

    protected NGGrid2DDisplayController FDCGrid;
    protected NG2DGameFieldDisplayController FDCGameField;
    protected NGDisplayView FView;

    @FXML
    public Canvas Layer1;

    @FXML
    public Canvas Layer0;

    @FXML
    protected void handleLoadGOF(){
        Designer.loadFromGOF();
    }

    protected void HandleMouseScrolled(ScrollEvent t) {
        if (Level != null) {
            double x = FView.getPositionX() - t.getDeltaX();
            if (x < 0) {
                x = 0;
            }
            double xmax = Level.getGameFieldSize().getWidth() * Designer.getGridSize() - FView.getWidth();
            if (x > xmax) {
                x = xmax;
            }
            double y = FView.getPositionY() - t.getDeltaY();
            if (y < 0) {
                y = 0;
            }
            double ymax = Level.getGameFieldSize().getHeight() * Designer.getGridSize() - FView.getHeight();
            if (y > ymax) {
                y = ymax;
            }
            FView.setPosition(x, y);
            RenderScene();
        }
    }

    @Override
    protected void CreateDisplayController() {
        FView = new NGDisplayView(Layer0.getWidth(), Layer0.getHeight());
        FDCGrid = new NGGrid2DDisplayController(Layer0);
        FDCGrid.setView(FView);
        FDCGrid.GridColor = Color.DARKGRAY;
        registerDisplayController(FDCGrid);
        FDCGameField = new NG2DGameFieldDisplayController(Layer1);
        FDCGameField.setView(FView);
        FDCGameField.MaxImageNumber = -1;
        registerDisplayController(FDCGameField);
    }

    @Override
    protected void DoInitialize() {
        super.DoInitialize();
        Layer0.setOnScroll(new EventHandler<ScrollEvent>() {
            @Override
            public void handle(ScrollEvent t) {
                HandleMouseScrolled(t);
            }
        });
    }

    @Override
    protected void DoBeforeRenderScene() {
        super.DoBeforeRenderScene();
        FDCGrid.GridDistance = Designer.getGridSize();
        FDCGameField.setImageName(Designer.getImageName());
        if (Level != null) {
            FDCGameField.GameField = Level.getGameField();
            FDCGrid.GridWidth = Level.getGameFieldSize().getWidth() * Designer.getGridSize();
            FDCGrid.GridHeight = Level.getGameFieldSize().getHeight() * Designer.getGridSize();
        }
    }

    public NG2DLevelDesigner Designer;

    public NG2DLevel Level;

}
