package Uniplay.Base;

import Uniplay.NGUniplayObject;
import Uniwork.Base.NGLogManager;


import java.util.ArrayList;

public class NGGameEngineModuleManager extends NGUniplayObject {

    protected ArrayList<NGGameEngineModule> FModules;
    protected NGLogManager FLogManager;
    protected NGUniplayObject FOwner;

    protected void addModule(NGGameEngineModule aModule) {
        FModules.add(aModule);
    }

    protected void removeModule(NGGameEngineModule aModule) {
        FModules.remove(aModule);
    }

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
        writeLog("Start all modules finalization...");
        super.BeforeFinalize();
    }

    @Override
    protected void AfterFinalize() {
        super.AfterFinalize();
        writeLog("All modules finalized!");
    }

    @Override
    protected void DoFinalize() {
        super.DoFinalize();
        for (NGGameEngineModule module : FModules) {
            module.Finalize();
        }
    }

    protected void writeLog(String aText) {
        if (FLogManager != null) {
            FLogManager.writeLog(aText);
        }
    }

    public NGGameEngineModuleManager(NGUniplayObject aOwner) {
        super();
        FOwner = aOwner;
        FModules = new ArrayList<NGGameEngineModule>();
        FLogManager = null;
    }

    public NGGameEngineModule addModule(String aName, NGUniplayObject aModule) {
        NGGameEngineModule module = new NGGameEngineModule(this, aName, aModule);
        module.setLogManager(FLogManager);
        addModule(module);
        return module;
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
