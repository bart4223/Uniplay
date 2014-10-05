package Uniplay.Storage;

import Uniplay.Base.NGUniplayObject;
import Uniplay.Base.NGUniplayObjectPhysicsProperties;

public abstract class NGCustomGameObject extends NGUniplayObject {

    protected NGCustomGame FGame;
    protected NGUniplayObjectPhysicsProperties FPhysics;

    public NGCustomGameObject(NGCustomGame aGame) {
        super();
        FGame = aGame;
        FPhysics = new NGUniplayObjectPhysicsProperties();
        FPhysics.Mass = 0.0;
    }

    public NGCustomGame getGame() {
        return FGame;
    }

    public double getMass() {
        return FPhysics.Mass;
    }

}
