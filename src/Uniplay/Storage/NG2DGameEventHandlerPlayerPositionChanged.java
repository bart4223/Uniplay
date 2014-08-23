package Uniplay.Storage;

import Uniplay.Kernel.NGGameEngineEventHandler;
import Uniplay.NGGameEngineConstants;

public class NG2DGameEventHandlerPlayerPositionChanged extends NGGameEngineEventHandler {

    protected NG2DGamePlayerItem FPosition;

    @Override
    protected void BeforeHandleEvent() {
        super.BeforeHandleEvent();
        NG2DGamePlayerPositionChanged event = (NG2DGamePlayerPositionChanged)FEvent;
        FPosition = event.getPlayerPosition();
    }

    public NG2DGameEventHandlerPlayerPositionChanged() {
        super(NGGameEngineConstants.EVT_2DGAME_PLAYER_POSITION_CHANGED);
    }

}
