package Uniplay.Storage;

import Uniplay.Base.NGUniplayObject;

public abstract class NGCustomGameObject extends NGUniplayObject {

    protected NGCustomGame FGame;

    public NGCustomGameObject(NGCustomGame aGame) {
        super();
        FGame = aGame;
    }

    public NGCustomGame getGame() {
        return FGame;
    }

}
