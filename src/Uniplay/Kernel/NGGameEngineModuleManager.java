package Uniplay.Kernel;

import Uniplay.Base.NGUniplayComponent;

import java.util.ArrayList;

public class NGGameEngineModuleManager extends NGUniplayComponent {

    protected ArrayList<NGGameEngineModule> FModules;

    protected void removeModule(NGGameEngineModule aModule) {
        aModule.removeEventListener(this);
        FModules.remove(aModule);
        writeLog(String.format("Module [%s] removed.", aModule.getName()));
    }

    protected void addModule(NGGameEngineModule aModule) {
        aModule.setLogManager(FLogManager);
        FModules.add(aModule);
        writeLog(String.format("Module [%s] added.", aModule.getName()));
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

    public NGGameEngineModuleManager(NGUniplayComponent aOwner, String aName) {
        super(aOwner, aName);
        if (aOwner != null) {
            aOwner.addEventListener(this);
        }
        FModules = new ArrayList<NGGameEngineModule>();
    }

    public NGGameEngineModule newModule(Class aClass, String aName) {
        NGGameEngineModule module = null;
        try
        {
            module = (NGGameEngineModule)aClass.getConstructor(NGGameEngineModuleManager.class, String.class).newInstance(this, aName);
            writeLog(String.format("Module [%s] created.", module.getName()));
            addModule(module);
        }
        catch (Exception e) {
            writeError("newModule", e.getMessage());
        }
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

}
