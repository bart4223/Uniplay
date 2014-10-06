package Uniplay.Physics;

import Uniplay.Base.NGUniplayComponent;
import Uniplay.Kernel.NGGameEngineModule;
import Uniplay.Kernel.NGGameEngineModuleManager;
import Uniplay.NGGameEngineConstants;
import Uniplay.Storage.NGCustomGameObject;

public abstract class NGPhysicsEngine extends NGGameEngineModule {

    @Override
    protected void BeforeInitialize() {
        super.BeforeInitialize();
        NGObjectPhysicsProcessor pro = getPhysicsProcessor();
        NGObjectPhysicsBehaviourManager bm = getPhysicsBehaviourManager();
        pro.setBehaviourManager(bm);
        for (NGPhysicsEngineDefinitionBehaviourItem bi : Definition.getBehaviours()) {
            try {
                Class GOcl = NGCustomGameObject.class.getClassLoader().loadClass(bi.getClassname());
                for (NGPhysicsEngineDefinitionPhysicsPrincipleItem pi : bi.getPhysicsPrinciple()) {
                    Class PPcl = NGCustomPhysicsPrinciple.class.getClassLoader().loadClass(pi.getClassname());
                    bm.addItem(GOcl, PPcl);
                }
            }
            catch (Exception e) {
                writeError("BeforeInitialize", e.getMessage());
            }
        }
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

    public NGPhysicsEngine(NGGameEngineModuleManager aManager, String aName) {
        super(aManager, aName);
    }

    public NGPhysicsEngineDefinition Definition;

}
