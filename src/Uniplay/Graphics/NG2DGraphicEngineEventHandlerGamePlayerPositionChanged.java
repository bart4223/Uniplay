package Uniplay.Graphics;

import Uniplay.Storage.NG2DGameEventHandlerPlayerPositionChanged;

public class NG2DGraphicEngineEventHandlerGamePlayerPositionChanged extends NG2DGameEventHandlerPlayerPositionChanged {

    protected NG2DGraphicEngine F2DGraphicEngine;

    @Override
    protected void DoHandleEvent() {
        super.DoHandleEvent();
        // ToDo
        F2DGraphicEngine.setViewPositions(FPosition.getPosition().getX(), FPosition.getPosition().getY());
    }

    public NG2DGraphicEngineEventHandlerGamePlayerPositionChanged(NG2DGraphicEngine a2DGraphicEngine) {
        super();
        F2DGraphicEngine = a2DGraphicEngine;
    }

}
