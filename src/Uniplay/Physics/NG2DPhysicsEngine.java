package Uniplay.Physics;

import Uniplay.Base.NGUniplayComponent;
import Uniplay.Kernel.NGGameEngineModuleManager;
import Uniplay.NGGameEngineConstants;

public class NG2DPhysicsEngine extends NGPhysicsEngine {

    @Override
    protected void BeforeInitialize() {
        super.BeforeInitialize();
        NGObjectPhysicsProcessor pro = getPhysicsProcessor();
        pro.setBehaviourManager(getPhysicsBehaviourManager());
    }

    @Override
    protected void CreateSubComponents() {
        super.CreateSubComponents();
        NGUniplayComponent component = new NGObjectPhysicsBehaviourManager(this, NGGameEngineConstants.CMP_PHYSICS_BEHAVIOUR_MANAGER);
        addSubComponent(component);
        component = new NGObjectPhysicsProcessor(this, NGGameEngineConstants.CMP_PHYSICS_PROCESSOR);
        addSubComponent(component);
    }

    protected NGObjectPhysicsBehaviourManager getPhysicsBehaviourManager() {
        return (NGObjectPhysicsBehaviourManager)getSubComponent(NGGameEngineConstants.CMP_PHYSICS_BEHAVIOUR_MANAGER);
    }

    protected NGObjectPhysicsProcessor getPhysicsProcessor() {
        return (NGObjectPhysicsProcessor)getSubComponent(NGGameEngineConstants.CMP_PHYSICS_PROCESSOR);
    }

    public NG2DPhysicsEngine(NGGameEngineModuleManager aManager, String aName) {
        super(aManager, aName);
    }

}
