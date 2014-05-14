package Uniplay.Kernel;

import Uniplay.Base.NGUniplayComponent;
import Uniwork.Misc.NGLogManager;

public abstract class NGGameEngineModule extends NGUniplayComponent implements NGGameEngineEventHandlerRegistration {

    protected NGGameEngineModuleManager FManager;
    protected NGGameEngineEventHandlerManager FEventHandlerManager;
    protected String FCaption;

    @Override
    protected void BeforeInitialize() {
        writeLog(String.format("Start module [%s] initialization...", FName));
        super.BeforeInitialize();
    }

    @Override
    protected void DoInitialize() {
        super.DoInitialize();
        FEventHandlerManager.Initialize();
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
    protected void DoFinalize() {
        super.DoFinalize();
        FEventHandlerManager.Finalize();
    }

    @Override
    protected void AfterFinalize() {
        super.AfterFinalize();
        writeLog(String.format("Module [%s] stopped!", FName));
    }

    @Override
    protected Object DoResolveObject(String aName, Class aClass) {
        Object result = super.DoResolveObject(aName, aClass);
        if (result == null && FManager != null) {
            return FManager.ResolveObject(aName, aClass);
        }
        return result;
    }

    @Override
    protected void CreateComponents() {
        super.CreateComponents();
        FEventHandlerManager = new NGGameEngineEventHandlerManager(this, String.format("%s", FName));
    }

    protected void DoLoad() {

    }

    public NGGameEngineModule(NGGameEngineModuleManager aManager, String aName) {
        super(aManager, aName);
        FManager = aManager;
        if (FManager != null) {
            FManager.addEventListener(this);
        }
        FCaption = "";
    }

    public NGGameEngineModuleManager getManager() {
        return FManager;
    }

    public void setCaption(String aCaption) {
        FCaption = aCaption;
    }

    public String getCaption() {
        return FCaption;
    }

    public void Load() {
        DoLoad();
        writeLog(String.format("Module [%s] loaded.", FName));
    }

    public void setLogManager(NGLogManager aLogManager) {
        super.setLogManager(aLogManager);
        FEventHandlerManager.setLogManager(aLogManager);
    }

    @Override
    public void registerEventHandler(NGGameEngineEventHandler aHandler) {
        FEventHandlerManager.addHandler(aHandler);
    }

    @Override
    public void unregisterEventHandler(NGGameEngineEventHandler aHandler) {
        FEventHandlerManager.removeHandler(aHandler);
    }

}
