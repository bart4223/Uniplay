package Uniplay.Storage;

import Uniplay.Kernel.NGGameEngineEvent;

public class NG2DGameObjectEvent extends NGGameEngineEvent {

    protected NG2DGameObject FGameObject;
    protected NG2DGame FGame;

    public NG2DGameObjectEvent(Object source, NG2DGameObject aGameObject) {
        super(source);
        FGameObject = aGameObject;
        FGame = (NG2DGame)source;
    }

    public NG2DGameObject getGameObject() {
        return FGameObject;
    }

    public NG2DGame getGame() {
        return FGame;
    }

}
