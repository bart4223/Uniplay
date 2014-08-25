package Uniplay.Storage;

import Uniplay.Kernel.NGGameEngineEvent;

public class NG2DGamePlayerPositionChanged extends NGGameEngineEvent {

    protected NG2DGamePlayerItem FPlayerPosition;
    protected NG2DGame FGame;

    public NG2DGamePlayerPositionChanged(Object source, NG2DGamePlayerItem aPlayerPosition) {
        super(source);
        FPlayerPosition = aPlayerPosition;
        FGame = (NG2DGame)source;
    }

    public NG2DGamePlayerItem  getPlayerPosition() {
        return FPlayerPosition;
    }

    public NG2DGame getGame() {
        return FGame;
    }

}
