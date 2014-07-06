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
    }

    public NGWorkbenchModule(NGGameEngineModuleManager aManager, String aName) {
        super(aManager, aName);
    }

}
