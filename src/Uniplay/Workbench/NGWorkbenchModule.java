package Uniplay.Workbench;

import Uniplay.Base.NGUniplayComponent;
import Uniplay.NGGameEngineConstants;
import Uniplay.Kernel.NGGameEngineModule;
import Uniplay.Kernel.NGGameEngineModuleManager;

public class NGWorkbenchModule extends NGGameEngineModule {

    @Override
    protected void registerEventHandlers() {
        super.registerEventHandlers();
        NGWorkbenchManager manager = getWorkbenchManager();
        registerEventHandler(new NGWorkbenchManagerEventHandlerKernelInitialized(manager));
        registerEventHandler(new NGWorkbenchManagerEventHandlerKernelStarted(manager));
    }

    @Override
    protected void CreateSubComponents() {
        super.CreateSubComponents();
        NGUniplayComponent component = new NGWorkbenchManager(this, NGGameEngineConstants.CMP_WORKBENCH_MANAGER);
        addSubComponent(component);
    }

    @Override
    protected void AfterInitialize() {
        super.AfterInitialize();
        NGWorkbenchDefinition def = (NGWorkbenchDefinition)FDefinition;
        getWorkbenchManager().getLevelDesignerManager().setDefinitions(def.getLevelDesignerManager());
    }

    public NGWorkbenchManager getWorkbenchManager() {
        return (NGWorkbenchManager)getSubComponent(NGGameEngineConstants.CMP_WORKBENCH_MANAGER);
    }

    public NGWorkbenchModule(NGGameEngineModuleManager aManager, String aName) {
        super(aManager, aName);
    }

}
