package Uniplay.Physics;

import Uniplay.Base.NGUniplayObject;
import Uniplay.Storage.NGCustomGameObject;
import java.util.ArrayList;

public class NGObjectPhysicsBehaviourItem extends NGUniplayObject {

    protected Class<NGCustomGameObject> FGameObjectClass;
    protected ArrayList<NGObjectPhysicsPrincipleItem> FPhysicsPrinciples;

    public NGObjectPhysicsBehaviourItem(Class<NGCustomGameObject> aGameObjectClass) {
        super();
        FGameObjectClass = aGameObjectClass;
        FPhysicsPrinciples = new ArrayList<NGObjectPhysicsPrincipleItem>();
    }

    public Class<NGCustomGameObject> getGameObjectClass() {
        return FGameObjectClass;
    }

    public void addPhysicsPrinciple(Class<NGCustomPhysicsPrinciple> aPhysicsPrincipleClass) {
        try {
            NGObjectPhysicsPrincipleItem principle = new NGObjectPhysicsPrincipleItem((aPhysicsPrincipleClass.getConstructor().newInstance()));
            principle.setActive(true);
            FPhysicsPrinciples.add(principle);
        } catch (Exception e) {
        }
    }

    public ArrayList<NGObjectPhysicsPrincipleItem> getPhysicsPrinciples() {
        return FPhysicsPrinciples;
    }

}
