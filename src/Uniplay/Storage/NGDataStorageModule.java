package Uniplay.Storage;

import Uniplay.Base.NGUniplayComponent;
import Uniplay.Base.NGUniplayObjectRegistration;
import Uniplay.Kernel.NGGameEngineConstants;
import Uniplay.Kernel.NGGameEngineModule;
import Uniplay.Kernel.NGGameEngineModuleManager;

public class NGDataStorageModule extends NGGameEngineModule {

    protected NG2DLevelManager getLevelManager() {
        NG2DLevelManager manager = (NG2DLevelManager)ResolveObject(NGGameEngineConstants.CMP_2DLEVEL_MANAGER, NG2DLevelManager.class);
        return manager;
    }

    @Override
    protected void CreateSubComponents() {
        super.CreateSubComponents();
        NGUniplayComponent component = new NG2DLevelManager(this, NGGameEngineConstants.CMP_2DLEVEL_MANAGER);
        addSubComponent(component);
    }

    @Override
    protected void AfterInitialize() {
        super.AfterInitialize();
        NGUniplayObjectRegistration registration = (NGUniplayObjectRegistration)ResolveObject(NGUniplayObjectRegistration.class);
        registration.registerObject(NGGameEngineConstants.CMP_2DLEVEL_MANAGER, getLevelManager());
    }

    public NGDataStorageModule(NGGameEngineModuleManager aManager, String aName) {
        super(aManager, aName);
    }

}
