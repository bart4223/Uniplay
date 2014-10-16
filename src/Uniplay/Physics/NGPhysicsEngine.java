package Uniplay.Physics;

import Uniplay.Base.NGUniplayComponent;
import Uniplay.Base.NGUniplayObjectRegistration;
import Uniplay.Kernel.NGGameEngineModule;
import Uniplay.Kernel.NGGameEngineModuleManager;
import Uniplay.Kernel.NGTaskManager;
import Uniplay.NGGameEngineConstants;
import Uniplay.Storage.NGCustomGameObject;
import Uniwork.Misc.NGTickEvent;
import Uniwork.Misc.NGTickListener;
import javafx.application.Platform;

public abstract class NGPhysicsEngine extends NGGameEngineModule implements NGTickListener {

    public static void processThread(final NGObjectPhysicsProcessor aProcessor) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                aProcessor.Execute();
            }
        });
    }

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
    protected void AfterInitialize() {
        super.AfterInitialize();
        NGTaskManager tm = (NGTaskManager)ResolveObject(NGGameEngineConstants.CMP_TASK_MANAGER, NGTaskManager.class);
        tm.addPeriodicTask("ObjectPhysicsProcessor", 5);
        tm.addListener("ObjectPhysicsProcessor", this);
        tm.startPeriodicTask("ObjectPhysicsProcessor");
    }

    @Override
    protected void CreateSubComponents() {
        super.CreateSubComponents();
        NGUniplayComponent component = new NGObjectPhysicsBehaviourManager(this, NGGameEngineConstants.CMP_PHYSICS_BEHAVIOUR_MANAGER);
        addSubComponent(component);
        component = new NGObjectPhysicsProcessor(this, NGGameEngineConstants.CMP_PHYSICS_PROCESSOR);
        addSubComponent(component);
    }

    @Override
    protected void registerObjects() {
        super.registerObjects();
        NGUniplayObjectRegistration registration = (NGUniplayObjectRegistration)ResolveObject(NGUniplayObjectRegistration.class);
        registration.registerObject(getPhysicsProcessor().getName(), getPhysicsProcessor());
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

    @Override
    public void handleTick(NGTickEvent e) {
        processThread(getPhysicsProcessor());
    }

}
