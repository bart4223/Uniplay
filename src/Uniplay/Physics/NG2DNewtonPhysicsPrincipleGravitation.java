package Uniplay.Physics;

public class NG2DNewtonPhysicsPrincipleGravitation extends NG2DNewtonPhysicsPrinciple {

    public NG2DNewtonPhysicsPrincipleGravitation() {
        super();
    }

    @Override
    public Boolean getAffectsByAction(NGCustomPhysicsAction aPhysicsAction) {
        if (aPhysicsAction instanceof NGPhysicsAction2DMovement) {
            return true;
        }
        else {
            return super.getAffectsByAction(aPhysicsAction);
        }
    }

}
