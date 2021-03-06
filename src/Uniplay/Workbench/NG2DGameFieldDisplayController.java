package Uniplay.Workbench;

import Uniplay.Kernel.NGGameEngineMemoryAddress;
import Uniplay.Kernel.NGGameEngineMemoryCell;
import Uniplay.Storage.NG2DGameField;
import Uniplay.Storage.NG2DGameFieldLayer;
import Uniwork.Visuals.NGImageDisplayController;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;

public class NG2DGameFieldDisplayController extends NGImageDisplayController {

    protected void ClearCanvas() {
        if (FBackgroundColor.equals(Color.TRANSPARENT)) {
            FGC.clearRect(0, 0, getViewWidth(), getViewHeight());
        }
        else {
            FGC.setFill(FBackgroundColor);
            FGC.fillRect(0, 0, getViewWidth(), getViewHeight());
        }
    }

    @Override
    protected void BeforeRender() {
        setPosition(getPositionX() * FWidth, getPositionY() * FHeight);
        super.BeforeRender();
    }

    @Override
    protected void InternalRender() {
        ClearCanvas();
        for (NG2DGameFieldLayer layer : GameField.getLayers()) {
            if (layer.getCellCount() > 0 ) {
                for (NGGameEngineMemoryCell cell : layer.getCells()) {
                    NGGameEngineMemoryAddress address = cell.getAddress();
                    setPosition(address.getOffset(), address.getBase());
                    setProperty(this, "Background.ImageNumber", cell.getValueAsInteger());
                    super.InternalRender();
                }
            }
        }
    }

    public NG2DGameFieldDisplayController(Canvas aCanvas) {
        this(aCanvas, "");
    }

    public NG2DGameFieldDisplayController(Canvas aCanvas, String aImagename) {
        this(aCanvas, "", aImagename);
    }

    public NG2DGameFieldDisplayController(Canvas aCanvas, String aName, String aImagename) {
        super(aCanvas, aName, aImagename);
    }

    public NG2DGameField GameField;

    @Override
    public Boolean canRender() {
        return super.canRender() && GameField != null;
    }

}
