package Uniplay.Physics;

public class NG2DNewtonPhysicsPrincipleMomentumConservation extends NG2DNewtonPhysicsPrinciple {

    public NG2DNewtonPhysicsPrincipleMomentumConservation() {
        super();
    }

    @Override
    public Boolean getAffectsByAction(NGCustomPhysicsAction aPhysicsAction) {
        if (aPhysicsAction instanceof NGPhysicsAction2DImpuls) {
            return true;
        }
        else {
            return super.getAffectsByAction(aPhysicsAction);
        }
    }

}
