package Uniplay.Workbench;

import Uniplay.Base.NGUniplayComponent;
import Uniplay.Kernel.NGGameEngineConstants;
import Uniplay.Kernel.NGGameEngineModule;
import Uniplay.Kernel.NGGameEngineModuleManager;

public class NGWorkbenchModule extends NGGameEngineModule {

    @Override
    protected void CreateSubComponents() {
        super.CreateSubComponents();
        NGUniplayComponent component = new NGWorkbenchManager(this, NGGameEngineConstants.CMP_WORKBENCH_MANAGER);
        addSubComponent(component);
    }

    public NGWorkbenchModule(NGGameEngineModuleManager aManager, String aName) {
        super(aManager, aName);
    }

}
