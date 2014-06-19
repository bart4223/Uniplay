package Uniplay.Test;

import Uniplay.Kernel.NGGameEngineModule;
import Uniplay.Kernel.NGGameEngineModuleManager;

public class NGTestModule extends NGGameEngineModule {

    @Override
    protected void AfterInitialize() {
        super.AfterInitialize();
        //NGGameEngineMemoryManager manager = (NGGameEngineMemoryManager)ResolveObject(NGGameEngineConstants.CMP_MEMORY_MANAGER, NGGameEngineMemoryManager.class);
        //writeLog(manager.toString());
        //Object obj = ResolveObject("GameFieldController.Layer0", Canvas.class);
        //writeLog(obj.toString());
    }

    public NGTestModule(NGGameEngineModuleManager aManager, String aName) {
        super(aManager, aName);
    }

}
