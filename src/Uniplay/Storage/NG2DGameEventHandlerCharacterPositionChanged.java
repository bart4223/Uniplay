package Uniplay.Storage;

import Uniplay.Kernel.NGGameEngineEventHandler;
import Uniplay.NGGameEngineConstants;

public class NG2DGameEventHandlerCharacterPositionChanged extends NGGameEngineEventHandler {

    protected NG2DGameCharacter FCharacterPosition;
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
        NG2DGameCharacterPositionChanged event = (NG2DGameCharacterPositionChanged)FEvent;
        FCharacterPosition = event.getCharacterPosition();
        FGame = event.getGame();
    }

    public NG2DGameEventHandlerCharacterPositionChanged() {
        super(NGGameEngineConstants.EVT_GAME_CHARACTER_POSITION_CHANGED);
    }

}
