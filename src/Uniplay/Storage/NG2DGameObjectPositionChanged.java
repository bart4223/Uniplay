package Uniplay.Storage;

import Uniplay.Kernel.NGGameEngineEvent;

public class NG2DGameObjectPositionChanged extends NGGameEngineEvent {

    protected NG2DGameObject FObjectPosition;
    protected NG2DGame FGame;

    public NG2DGameObjectPositionChanged(Object source, NG2DGameObject aObjectPosition) {
        super(source);
        FObjectPosition = aObjectPosition;
        FGame = (NG2DGame)source;
    }

    public NG2DGameObject getObjectPosition() {
        return FObjectPosition;
    }

    public NG2DGame getGame() {
        return FGame;
    }

}
