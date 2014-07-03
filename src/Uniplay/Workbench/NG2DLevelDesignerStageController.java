package Uniplay.Workbench;

import Uniplay.Storage.NG2DLevel;
import Uniwork.Visuals.NGDisplayView;
import Uniwork.Visuals.NGGrid2DDisplayController;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.ScrollEvent;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ResourceBundle;

public class NG2DLevelDesignerStageController implements Initializable {

    protected NGGrid2DDisplayController FDCLayerGrid;
    protected NG2DGameFieldDisplayController FDCGameField;
    protected NGDisplayView FView;

    @FXML
    public Canvas Layer0;

    @FXML
    public Canvas LayerGrid;

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
            double ymax = Level.getGameFieldSize().getWidth() * Designer.getGridSize() - FView.getHeight();
            if (y > ymax) {
                y = ymax;
            }
            FView.setPosition(x, y);
            RenderScene();
        }
    }

    public NG2DLevelDesigner Designer;

    public NG2DLevel Level;

    public void initialize(URL url, ResourceBundle resourceBundle) {
        FDCLayerGrid = new NGGrid2DDisplayController(LayerGrid);
        FDCLayerGrid.GridColor = Color.DARKGRAY;
        FDCLayerGrid.Initialize();
        FView = new NGDisplayView(LayerGrid.getWidth(), LayerGrid.getHeight());
        FView.setPosition(0, 0);
        FDCGameField = new NG2DGameFieldDisplayController(Layer0);
        FDCGameField.setView(FView);
        FDCGameField.MaxImageNumber = -1;
        FDCGameField.Initialize();
        LayerGrid.setOnScroll(new EventHandler<ScrollEvent>() {
            @Override
            public void handle(ScrollEvent t) {
                HandleMouseScrolled(t);
            }
        });
    }

    public void RenderScene() {
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
