package Uniplay.Physics;

import Uniplay.Base.NGUniplayObjectDefinition;

import java.util.concurrent.CopyOnWriteArrayList;

public class NGPhysicsEngineDefinitionBehaviourItem extends NGUniplayObjectDefinition {

    protected String Classname = "";
    protected CopyOnWriteArrayList<NGPhysicsEngineDefinitionPhysicsPrincipleItem> PhysicsPrinciple;

    public NGPhysicsEngineDefinitionBehaviourItem() {
        super();
    }

    public void setClassname(String value) { Classname = value;}
    public String getClassname() { return Classname; }

    public void setPhysicsPrinciple(CopyOnWriteArrayList<NGPhysicsEngineDefinitionPhysicsPrincipleItem> value) { PhysicsPrinciple = value;}
    public CopyOnWriteArrayList<NGPhysicsEngineDefinitionPhysicsPrincipleItem> getPhysicsPrinciple() { return PhysicsPrinciple; }

}
