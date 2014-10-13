package Uniplay.Physics;

import Uniplay.Base.NGUniplayObject;
import Uniplay.Storage.NGCustomGameObject;
import java.util.ArrayList;

public class NGObjectPhysicsBehaviourItem extends NGUniplayObject {

    protected NGObjectPhysicsBehaviourManager FManager;
    protected Class<NGCustomGameObject> FGameObjectClass;
    protected ArrayList<NGObjectPhysicsPrincipleItem> FPhysicsPrinciples;

    public NGObjectPhysicsBehaviourItem(NGObjectPhysicsBehaviourManager aManager, Class<NGCustomGameObject> aGameObjectClass) {
        super();
        FManager = aManager;
        FGameObjectClass = aGameObjectClass;
        FPhysicsPrinciples = new ArrayList<NGObjectPhysicsPrincipleItem>();
    }

    public NGObjectPhysicsBehaviourManager getManager() {
        return FManager;
    }

    public Class<NGCustomGameObject> getGameObjectClass() {
        return FGameObjectClass;
    }

    public void addPhysicsPrinciple(Class<NGCustomPhysicsPrinciple> aPhysicsPrincipleClass) {
        try {
            NGCustomPhysicsPrinciple principle = aPhysicsPrincipleClass.getConstructor().newInstance();
            principle.setResolver(FManager);
            NGObjectPhysicsPrincipleItem item = new NGObjectPhysicsPrincipleItem(principle);
            item.setActive(true);
            FPhysicsPrinciples.add(item);
        } catch (Exception e) {
        }
    }

    public ArrayList<NGObjectPhysicsPrincipleItem> getPhysicsPrinciples() {
        return FPhysicsPrinciples;
    }

}
