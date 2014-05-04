package Uniplay.Kernel;

import Uniplay.NGUniplayObject;
import Uniwork.Base.NGLogManager;

public abstract class NGGameEngineModule extends NGUniplayObject {

    protected NGGameEngineModuleManager FManager;
    protected String FName;
    protected NGLogManager FLogManager;

    @Override
    protected void BeforeInitialize() {
        writeLog(String.format("Start module %s initialization...", FName));
        super.BeforeInitialize();
    }

    @Override
    protected void AfterInitialize() {
        super.AfterInitialize();
        writeLog(String.format("Module %s initialized!", FName));
    }

    @Override
    protected void BeforeFinalize() {
        writeLog(String.format("Start module %s shutdown...", FName));
        super.BeforeFinalize();
    }

    @Override
    protected void AfterFinalize() {
        super.AfterFinalize();
        writeLog(String.format("Module %s stopped!", FName));
    }

    protected void writeLog(String aText) {
        if (FLogManager != null) {
            FLogManager.writeLog(aText);
        }
    }

    public NGGameEngineModule(NGGameEngineModuleManager aManager, String aName) {
        super();
        FManager = aManager;
        FName = aName;
        FLogManager = null;
    }

    public NGGameEngineModuleManager getManager() {
        return FManager;
    }

    public String getName() {
        return FName;
    }

    public void setLogManager(NGLogManager aLogManager) {
        FLogManager = aLogManager;
    }

    public NGLogManager getLogManager() {
        return FLogManager;
    }

}
