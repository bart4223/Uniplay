package Uniplay.Storage;

import Uniplay.Kernel.NGGameEngineEvent;

public abstract class NGCustomGameObjectEvent extends NGGameEngineEvent {

    protected NGCustomGameObject FGameObject;
    protected NGCustomGame FGame;

    public NGCustomGameObjectEvent(Object source, NGCustomGameObject aGameObject) {
        super(source);
        FGameObject = aGameObject;
        FGame = (NGCustomGame)source;
    }

    public NGCustomGameObject getGameObject() {
        return FGameObject;
    }

    public NGCustomGame getGame() {
        return FGame;
    }

}
