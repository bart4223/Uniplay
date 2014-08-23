package Uniplay.Storage;

import Uniplay.Kernel.NGGameEngineEvent;

public class NG2DGamePlayerPositionChanged extends NGGameEngineEvent {

    protected NG2DGamePlayerItem FPlayerPosition;

    public NG2DGamePlayerPositionChanged(Object source, NG2DGamePlayerItem aPlayerPosition) {
        super(source);
        FPlayerPosition = aPlayerPosition;
    }

    public NG2DGamePlayerItem  getPlayerPosition() {
        return FPlayerPosition;
    }

}
