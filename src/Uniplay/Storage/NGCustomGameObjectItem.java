package Uniplay.Storage;

import Uniplay.Base.NGUniplayObject;

public abstract class NGCustomGameObjectItem extends NGUniplayObject {

    protected NGCustomGame FGame;

    public NGCustomGameObjectItem(NGCustomGame aGame) {
        super();
        FGame = aGame;
    }

    public NGCustomGame getGame() {
        return FGame;
    }

}
