package Uniplay.Physics;

import Uniplay.Base.NGUniplayComponent;
import Uniplay.Base.NGUniplayObject;
import Uniplay.Storage.NGCustomGameObject;

import java.util.concurrent.CopyOnWriteArrayList;

public class NGObjectPhysicsBehaviourManager extends NGUniplayComponent {

    protected CopyOnWriteArrayList<NGObjectPhysicsBehaviourItem> FItems;

    public NGObjectPhysicsBehaviourManager(NGUniplayObject aOwner, String aName) {
        super(aOwner, aName);
        FItems = new CopyOnWriteArrayList<NGObjectPhysicsBehaviourItem>();
    }

    public NGObjectPhysicsBehaviourItem getItem(Class<NGCustomGameObject> aGameObjectClass) {
        for (NGObjectPhysicsBehaviourItem item : FItems) {
            if (item.getGameObjectClass().equals(aGameObjectClass)) {
                return item;
            }
        }
        return null;
    }

    public NGObjectPhysicsBehaviourItem addItem(Class<NGCustomGameObject> aGameObjectClass, Class<NGCustomPhysicsPrinciple> aPhysicsPrinciple) {
        NGObjectPhysicsBehaviourItem item = getItem(aGameObjectClass);
        if (item == null) {
            item = new NGObjectPhysicsBehaviourItem(this, aGameObjectClass);
            FItems.add(item);
        }
        item.addPhysicsPrinciple(aPhysicsPrinciple);
        return item;
    }

}
