package Uniplay.Base;

import Uniplay.NGUniplayObject;
import Uniwork.Base.NGLogManager;

public class NGGameEngineModule extends NGUniplayObject {

    protected NGGameEngineModuleManager FManager;
    protected String FName;
    protected NGUniplayObject FModule;
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

    protected void writeLog(String aText) {
        if (FLogManager != null) {
            FLogManager.writeLog(aText);
        }
    }

    public NGGameEngineModule(NGGameEngineModuleManager aManager, String aName, NGUniplayObject aModule) {
        super();
        FManager = aManager;
        FName = aName;
        FModule = aModule;
        FLogManager = null;
    }

    public NGGameEngineModuleManager getManager() {
        return FManager;
    }

    public String getName() {
        return FName;
    }

    public NGUniplayObject getModule() {
        return FModule;
    }

    public void setLogManager(NGLogManager aLogManager) {
        FLogManager = aLogManager;
    }

    public NGLogManager getLogManager() {
        return FLogManager;
    }

}