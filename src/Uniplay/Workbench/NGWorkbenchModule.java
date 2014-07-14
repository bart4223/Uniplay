package Uniplay.Workbench;

import Uniplay.Kernel.NGGameEngineConstants;
import Uniplay.Kernel.NGGameEngineModule;
import Uniplay.Kernel.NGGameEngineModuleManager;

public class NGWorkbenchModule extends NGGameEngineModule {

    @Override
    protected void CreateSubComponents() {
        super.CreateSubComponents();
        NGWorkbenchManager manager = new NGWorkbenchManager(this, NGGameEngineConstants.CMP_WORKBENCH_MANAGER);
        addSubComponent(manager);
        registerEventHandler(new NGWorkbenchManagerEventHandlerKernelInitialized(manager));
        registerEventHandler(new NGWorkbenchManagerEventHandlerKernelStarted(manager));
    }

    @Override
    protected void AfterInitialize() {
        super.AfterInitialize();
        NGWorkbenchDefintion def = (NGWorkbenchDefintion)FDefinition;
        getWorkbenchManager().getLevelDesignerManager().setDefinitions(def.getLevelDesignerManager());
    }

    public NGWorkbenchManager getWorkbenchManager() {
        return (NGWorkbenchManager)getSubComponent(NGGameEngineConstants.CMP_WORKBENCH_MANAGER);
    }

    public NGWorkbenchModule(NGGameEngineModuleManager aManager, String aName) {
        super(aManager, aName);
    }

}
