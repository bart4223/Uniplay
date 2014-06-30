package Uniplay.Storage;

import Uniplay.Base.NGUniplayComponent;
import Uniplay.Kernel.NGGameEngineConstants;
import Uniplay.Kernel.NGGameEngineModule;
import Uniplay.Kernel.NGGameEngineModuleManager;

public class NGDataStorageModule extends NGGameEngineModule {

    @Override
    protected void CreateSubComponents() {
        super.CreateSubComponents();
        NGUniplayComponent component = new NG2DLevelManager(this, NGGameEngineConstants.CMP_2DLEVEL_MANAGER);
        addSubComponent(component);
    }

    public NGDataStorageModule(NGGameEngineModuleManager aManager, String aName) {
        super(aManager, aName);
    }

}
