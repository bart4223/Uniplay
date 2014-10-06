package Uniplay.Physics;

import Uniplay.Base.NGUniplayObject;
import Uniplay.Storage.NGCustomGameObject;

public class NGGameObjectPhysicsActionItem extends NGUniplayObject {

    protected NGCustomGameObject FGameObject;
    protected NGCustomPhysicsAction FPhysicsAction;

    public NGGameObjectPhysicsActionItem(NGCustomGameObject aGameObject, NGCustomPhysicsAction aPhysicsAction) {
        super();
        FGameObject = aGameObject;
        FPhysicsAction = aPhysicsAction;
    }

    public NGCustomGameObject getGameObject() {
        return FGameObject;
    }

    public NGCustomPhysicsAction getPhysicsAction() {
        return FPhysicsAction;
    }

}
