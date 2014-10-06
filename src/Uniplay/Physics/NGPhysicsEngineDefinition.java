package Uniplay.Physics;

import Uniplay.Kernel.NGGameEngineModuleDefinition;

import java.util.ArrayList;

public class NGPhysicsEngineDefinition extends NGGameEngineModuleDefinition {

    protected ArrayList<NGPhysicsEngineDefinitionBehaviourItem> Behaviours;

    public NGPhysicsEngineDefinition() {
        super();
    }

    public void setBehaviours(ArrayList<NGPhysicsEngineDefinitionBehaviourItem> value) { Behaviours = value;}
    public ArrayList<NGPhysicsEngineDefinitionBehaviourItem> getBehaviours() { return Behaviours; }

}
