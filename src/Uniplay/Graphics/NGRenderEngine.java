package Uniplay.Graphics;

import Uniplay.Kernel.NGGameEngineMemoryCell;
import Uniwork.Visuals.NGDisplayController;
import Uniwork.Visuals.NGDisplayManager;
import javafx.scene.canvas.Canvas;

public class NGRenderEngine extends NGDisplayManager {

    @Override
    protected void DoBeforeRender() {
        super.DoBeforeRender();
        // ToDo Setposition from cell data
        for (NGDisplayController dc : FControllers) {
            dc.setProperty(dc, "Value", Cell.getValue());
        }
    }

    public NGRenderEngine(Canvas aCanvas) {
        super(aCanvas);
    }

    public NGGameEngineMemoryCell Cell;

}
