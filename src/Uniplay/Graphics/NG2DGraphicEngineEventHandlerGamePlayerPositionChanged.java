package Uniplay.Graphics;

import Uniplay.Storage.NG2DGameEventHandlerPlayerPositionChanged;
import Uniwork.Visuals.NGDisplayController;

public class NG2DGraphicEngineEventHandlerGamePlayerPositionChanged extends NG2DGameEventHandlerPlayerPositionChanged {

    protected NG2DGraphicEngine F2DGraphicEngine;

    @Override
    protected void DoHandleEvent() {
        super.DoHandleEvent();
        NGCustomRenderEngine re = F2DGraphicEngine.getRenderEngineManager().getRenderEngine("DEFAULT");
        NGDisplayController dc = re.getController("DEFAULT");
        if (dc.getInitialized()) {
            Integer x = 0;
            double deltaX = (FPosition.getPosition().getX() * dc.getWidth() - re.getView().getWidth() / 2);
            if (deltaX > 0) {
                x = (int)(deltaX / dc.getWidth());
                if (x * dc.getWidth() + re.getView().getWidth() > getCurrentLevelWidth() * dc.getWidth()) {
                    x = (int)(getCurrentLevelWidth() - re.getView().getWidth() / dc.getWidth());
                }
                x = (int)(x * dc.getWidth());
            }
            Integer y = 0;
            double deltaY = (FPosition.getPosition().getY() * dc.getHeight() - re.getView().getHeight() / 2);
            if (deltaY > 0) {
                y = (int)(deltaY / dc.getHeight());
                if (y * dc.getHeight() + re.getView().getHeight() > getCurrentLevelHeight() * dc.getHeight()) {
                    y = (int)(getCurrentLevelHeight() - re.getView().getHeight() / dc.getHeight());
                }
                y = (int)(y * dc.getHeight());
            }
            F2DGraphicEngine.setViewPositions(x, y);
            F2DGraphicEngine.Refesh();
        }
    }

    public NG2DGraphicEngineEventHandlerGamePlayerPositionChanged(NG2DGraphicEngine a2DGraphicEngine) {
        super();
        F2DGraphicEngine = a2DGraphicEngine;
    }

}
