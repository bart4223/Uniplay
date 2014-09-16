package Uniplay.Graphics;

import Uniplay.Storage.NG2DGameEventHandlerCharacterPositionChanged;
import Uniwork.Visuals.NGDisplayController;

public class NG2DGraphicEngineEventHandlerGameCharacterPositionChanged extends NG2DGameEventHandlerCharacterPositionChanged {

    protected NG2DGraphicEngine F2DGraphicEngine;

    @Override
    protected void DoHandleEvent() {
        super.DoHandleEvent();
        for (NGCustomRenderEngineItem reitem : F2DGraphicEngine.getRenderEngineManager().getItems()) {
            for (NGDisplayController dc : reitem.getDisplayControllers()) {
                if (dc.getInitialized()) {
                    Integer x = 0;
                    double deltaX = (FCharacterPosition.getPosition().getX() * dc.getWidth() - reitem.getDisplayView().getWidth() / 2);
                    if (deltaX > 0) {
                        x = (int) (deltaX / dc.getWidth());
                        if (x * dc.getWidth() + reitem.getDisplayView().getWidth() > getCurrentLevelWidth() * dc.getWidth()) {
                            x = (int) (getCurrentLevelWidth() - reitem.getDisplayView().getWidth() / dc.getWidth());
                        }
                        x = (int) (x * dc.getWidth());
                    }
                    Integer y = 0;
                    double deltaY = (FCharacterPosition.getPosition().getY() * dc.getHeight() - reitem.getDisplayView().getHeight() / 2);
                    if (deltaY > 0) {
                        y = (int) (deltaY / dc.getHeight());
                        if (y * dc.getHeight() + reitem.getDisplayView().getHeight() > getCurrentLevelHeight() * dc.getHeight()) {
                            y = (int) (getCurrentLevelHeight() - reitem.getDisplayView().getHeight() / dc.getHeight());
                        }
                        y = (int) (y * dc.getHeight());
                    }
                    F2DGraphicEngine.setViewPositions(x, y);
                    F2DGraphicEngine.Refesh();
                }
            }
        }
    }

    public NG2DGraphicEngineEventHandlerGameCharacterPositionChanged(NG2DGraphicEngine a2DGraphicEngine) {
        super();
        F2DGraphicEngine = a2DGraphicEngine;
    }

}
