package Uniplay.Storage;

import Uniplay.Kernel.NGGameEngineEventHandler;
import Uniplay.NGGameEngineConstants;

public class NG2DGameEventHandlerPlayerPositionChanged extends NGGameEngineEventHandler {

    protected NG2DGamePlayerItem FPosition;
    protected NG2DGame FGame;

    protected double getCurrentLevelWidth() {
        return FGame.getCurrentLevel().getGameFieldSize().getWidth();
    }

    protected double getCurrentLevelHeight() {
        return FGame.getCurrentLevel().getGameFieldSize().getHeight();
    }

    @Override
    protected void BeforeHandleEvent() {
        super.BeforeHandleEvent();
        NG2DGamePlayerPositionChanged event = (NG2DGamePlayerPositionChanged)FEvent;
        FPosition = event.getPlayerPosition();
        FGame = event.getGame();
    }

    public NG2DGameEventHandlerPlayerPositionChanged() {
        super(NGGameEngineConstants.EVT_GAME_PLAYER_POSITION_CHANGED);
    }

}
