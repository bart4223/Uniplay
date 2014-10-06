package Uniplay.Physics;

import Uniplay.Base.NGUniplayObjectDefinition;

import java.util.ArrayList;

public class NGPhysicsEngineDefinitionBehaviourItem extends NGUniplayObjectDefinition {

    protected String Classname = "";
    protected ArrayList<NGPhysicsEngineDefinitionPhysicsPrincipleItem> PhysicsPrinciple;

    public NGPhysicsEngineDefinitionBehaviourItem() {
        super();
    }

    public void setClassname(String value) { Classname = value;}
    public String getClassname() { return Classname; }

    public void setPhysicsPrinciple(ArrayList<NGPhysicsEngineDefinitionPhysicsPrincipleItem> value) { PhysicsPrinciple = value;}
    public ArrayList<NGPhysicsEngineDefinitionPhysicsPrincipleItem> getPhysicsPrinciple() { return PhysicsPrinciple; }

}
