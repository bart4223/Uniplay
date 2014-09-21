package Uniplay.Storage;

import Uniplay.Base.NGUniplayObject;

public abstract class NGCustomGameObject extends NGUniplayObject {

    protected NGCustomGame FGame;
    protected double FMass;

    public NGCustomGameObject(NGCustomGame aGame) {
        super();
        FGame = aGame;
        FMass = 0.0;
    }

    public NGCustomGame getGame() {
        return FGame;
    }

    public double getMass() {
        return FMass;
    }

}
