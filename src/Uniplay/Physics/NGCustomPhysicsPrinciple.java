package Uniplay.Physics;

import Uniplay.Base.NGUniplayObject;

public abstract class NGCustomPhysicsPrinciple extends NGUniplayObject {

    public NGCustomPhysicsPrinciple() {
        super();
    }

    public Boolean getAffectsByAction(NGCustomPhysicsAction aPhysicsAction) {
        return false;
    }

}
