package Uniplay.Kernel;

import Uniplay.NGUniplayObject;
import Uniwork.Base.NGLogManager;

import java.util.ArrayList;

public class NGGameEngineModuleManager extends NGUniplayObject {

    protected ArrayList<NGGameEngineModule> FModules;
    protected NGLogManager FLogManager;
    protected NGUniplayObject FOwner;

    @Override
    protected void BeforeInitialize() {
        writeLog("Start all modules initialization...");
        super.BeforeInitialize();
    }

    @Override
    protected void AfterInitialize() {
        super.AfterInitialize();
        writeLog("All modules initialized!");
    }

    @Override
    protected void DoInitialize() {
        super.DoInitialize();
        for (NGGameEngineModule module : FModules) {
            module.Initialize();
        }
    }

    @Override
    protected void BeforeFinalize() {
        writeLog("Start all modules shutdown...");
        super.BeforeFinalize();
    }

    @Override
    protected void AfterFinalize() {
        super.AfterFinalize();
        writeLog("All modules stopped!");
    }

    @Override
    protected void DoFinalize() {
        super.DoFinalize();
        for (NGGameEngineModule module : FModules) {
            module.Finalize();
        }
    }

    @Override
    protected Object DoResolveObject(String aName, Class aClass) {
        Object result = super.DoResolveObject(aName, aClass);
        if (result == null) {
            return FOwner.ResolveObject(aName, aClass);
        }
        return result;
    }

    protected void writeLog(String aText) {
        writeLog(0, aText);
    }

    protected void writeLog(int aLogLevel, String aText) {
        if (FLogManager != null) {
            FLogManager.writeLog(aLogLevel, aText, getClass().getName());
        }
    }

    public NGGameEngineModuleManager(NGUniplayObject aOwner) {
        super();
        FOwner = aOwner;
        FModules = new ArrayList<NGGameEngineModule>();
        FLogManager = null;
    }

    public void addModule(NGGameEngineModule aModule) {
        aModule.setLogManager(FLogManager);
        FModules.add(aModule);
    }

    public void removeModule(NGGameEngineModule aModule) {
        FModules.remove(aModule);
    }

    public void removeModule(String aName) {
        NGGameEngineModule module = getModule(aName);
        removeModule(module);
    }

    public NGGameEngineModule getModule(String aName) {
        for (NGGameEngineModule module : FModules) {
            if (module.getName().equals(aName)) {
                return module;
            }
        }
        return null;
    }

    public void setLogManager(NGLogManager aLogManager) {
        FLogManager = aLogManager;
    }

    public NGLogManager getLogManager() {
        return FLogManager;
    }

}
