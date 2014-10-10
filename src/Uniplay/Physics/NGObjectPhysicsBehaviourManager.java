package Uniplay.Physics;

import Uniplay.Base.NGUniplayComponent;
import Uniplay.Base.NGUniplayObject;
import Uniplay.Storage.NGCustomGameObject;

import java.util.ArrayList;

public class NGObjectPhysicsBehaviourManager extends NGUniplayComponent {

    protected ArrayList<NGObjectPhysicsBehaviourItem> FItems;

    public NGObjectPhysicsBehaviourManager(NGUniplayObject aOwner, String aName) {
        super(aOwner, aName);
        FItems = new ArrayList<NGObjectPhysicsBehaviourItem>();
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
            item = new NGObjectPhysicsBehaviourItem(aGameObjectClass);
            FItems.add(item);
        }
        item.addPhysicsPrinciple(aPhysicsPrinciple);
        return item;
    }

}
