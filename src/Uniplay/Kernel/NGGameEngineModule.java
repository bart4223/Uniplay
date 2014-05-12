package Uniplay.Kernel;

import Uniplay.Base.NGUniplayComponent;

public abstract class NGGameEngineModule extends NGUniplayComponent {

    protected NGGameEngineModuleManager FManager;
    protected String FCaption;

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

    protected void DoLoad() {

    }

    public NGGameEngineModule(NGGameEngineModuleManager aManager, String aName) {
        super(aManager, aName);
        FManager = aManager;
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

    @Override
    public void handleEvent(String name, NGGameEngineEvent e) {

    }

}
