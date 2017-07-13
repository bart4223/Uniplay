package Uniplay.Physics;

import Uniplay.Kernel.NGGameEngineModuleDefinition;

import java.util.concurrent.CopyOnWriteArrayList;

public class NGPhysicsEngineDefinition extends NGGameEngineModuleDefinition {

    protected CopyOnWriteArrayList<NGPhysicsEngineDefinitionBehaviourItem> Behaviours;

    public NGPhysicsEngineDefinition() {
        super();
    }

    public void setBehaviours(CopyOnWriteArrayList<NGPhysicsEngineDefinitionBehaviourItem> value) { Behaviours = value;}
    public CopyOnWriteArrayList<NGPhysicsEngineDefinitionBehaviourItem> getBehaviours() { return Behaviours; }

}
