package Uniplay.Kernel;

import Uniplay.Base.NGUniplayObject;
import Uniwork.Base.NGLogManager;

public abstract class NGGameEngineModule extends NGUniplayObject {

    protected NGGameEngineModuleManager FManager;
    protected String FName;
    protected NGLogManager FLogManager;

    @Override
    protected void BeforeInitialize() {
        writeLog(String.format("Start module [%s] initialization...", FName));
        super.BeforeInitialize();
    }

    @Override
    protected void AfterInitialize() {
        super.AfterInitialize();
        writeLog(String.format("Module [%s] initialized!", FName));
    }

    @Override
    protected void BeforeFinalize() {
        writeLog(String.format("Start module [%s] shutdown...", FName));
        super.BeforeFinalize();
    }

    @Override
    protected void AfterFinalize() {
        super.AfterFinalize();
        writeLog(String.format("Module [%s] stopped!", FName));
    }

    @Override
    protected Object DoResolveObject(String aName, Class aClass) {
        Object result = super.DoResolveObject(aName, aClass);
        if (result == null) {
            return FManager.ResolveObject(aName, aClass);
        }
        return result;
    }

    protected void writeLog(String aText) {
        if (FLogManager != null) {
            FLogManager.writeLog(aText, this.getClass().getName());
        }
    }

    protected void DoLoad() {

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

    public void registerEventHandler(NGGameEngineEventHandler aHandler) {
        NGGameEngineEventHandlerRegistration reg = (NGGameEngineEventHandlerRegistration)ResolveObject(NGGameEngineEventHandlerRegistration.class);
        reg.registerEventHandler(aHandler);
    }

    public void unregisterEventHandler(NGGameEngineEventHandler aHandler) {
        NGGameEngineEventHandlerRegistration reg = (NGGameEngineEventHandlerRegistration)ResolveObject(NGGameEngineEventHandlerRegistration.class);
        reg.unregisterEventHandler(aHandler);
    }

    public void Load() {
        DoLoad();
        writeLog(String.format("Module [%s] loaded.", FName));
    }

}
