package Uniplay.Physics;

import Uniplay.Base.NGUniplayObject;

public abstract class NGCustomPhysicsPrinciple extends NGUniplayObject {

    protected NGGameObjectPhysicsAction FCurrentGOPhysicsAction;

    protected void BeforeExecute() {

    }

    protected void DoExecute() {

    }

    protected void AfterExecute() {

    }

    public NGCustomPhysicsPrinciple() {
        super();
        FCurrentGOPhysicsAction = null;
    }

    public Boolean getAffectsByAction(NGCustomPhysicsAction aPhysicsAction) {
        return false;
    }

    public void Execute() {
        if (FCurrentGOPhysicsAction != null) {
            BeforeExecute();
            try
            {
                DoExecute();
            }
            finally {
                AfterExecute();
            }
        }
    }

    public void setCurrentGOPhysicsAction(NGGameObjectPhysicsAction aGOPhysicsAction) {
        FCurrentGOPhysicsAction = aGOPhysicsAction;
    }

    public NGGameObjectPhysicsAction getCurrentGOPhysicsAction() {
        return FCurrentGOPhysicsAction;
    }

}
