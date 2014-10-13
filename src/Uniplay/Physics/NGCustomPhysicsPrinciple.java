package Uniplay.Physics;

import Uniplay.Base.NGUniplayObject;
import Uniplay.NGGameEngineConstants;

public abstract class NGCustomPhysicsPrinciple extends NGUniplayObject {

    protected NGGameObjectPhysicsAction FCurrentGOPhysicsAction;
    protected NGObjectPhysicsProcessor FPhysicsProcessor;

    protected void BeforeExecute() {

    }

    protected void DoExecute() {

    }

    protected void AfterExecute() {

    }

    protected NGObjectPhysicsProcessor getPhysicsProcessor() {
        if (FPhysicsProcessor == null) {
            FPhysicsProcessor = (NGObjectPhysicsProcessor)ResolveObject(NGGameEngineConstants.CMP_PHYSICS_PROCESSOR, NGObjectPhysicsProcessor.class);
        }
        return FPhysicsProcessor;
    }

    public NGCustomPhysicsPrinciple() {
        super();
        FCurrentGOPhysicsAction = null;
        FPhysicsProcessor = null;
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
