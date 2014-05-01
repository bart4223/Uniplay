package Uniplay.Base;

import Uniplay.NGUniplayObject;

public class NGGameEngine extends NGUniplayObject {

    protected NGGameEngineModuleManager FModuleManager;

    @Override
    protected void DoInitialize() {
        super.DoInitialize();
        FModuleManager.Initialize();
    }

    @Override
    protected void DoFinalize() {
        super.DoFinalize();
        FModuleManager.Finalize();
    }

    public NGGameEngine() {
        super();
        FModuleManager = new NGGameEngineModuleManager(this);
    }

}
