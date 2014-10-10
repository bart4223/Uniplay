package Uniplay.Physics;

import Uniplay.Base.NGUniplayObject;
import Uniplay.Storage.NGCustomGameObject;

public class NGGameObjectPhysicsAction extends NGUniplayObject {

    protected NGCustomGameObject FTriggerObject;
    protected NGCustomGameObject FAffectedObject;
    protected NGCustomPhysicsAction FPhysicsAction;

    public NGGameObjectPhysicsAction(NGCustomGameObject aTriggerObject, NGCustomGameObject aAffectedObject, NGCustomPhysicsAction aPhysicsAction) {
        super();
        FTriggerObject = aTriggerObject;
        FAffectedObject = aAffectedObject;
        FPhysicsAction = aPhysicsAction;
    }

    public NGCustomGameObject getAffectedObject() {
        return FAffectedObject;
    }

    public NGCustomGameObject getTriggeredObject() {
        return FTriggerObject;
    }

    public NGCustomPhysicsAction getPhysicsAction() {
        return FPhysicsAction;
    }

}
