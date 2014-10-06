package Uniplay.Physics;

import Uniplay.Storage.NG2DObjectPosition;

public class NGPhysicsAction2DMovement extends NGCustomPhysicsAction {

    protected NG2DObjectPosition FPosition;

    public NGPhysicsAction2DMovement(NG2DObjectPosition aPosition) {
        super();
        FPosition = aPosition;
    }

    public NG2DObjectPosition getPosition() {
        return FPosition;
    }

}
