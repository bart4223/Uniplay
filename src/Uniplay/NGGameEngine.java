package Uniplay;

import Uniplay.Base.NGUniplayComponent;
import Uniplay.Base.NGUniplayComponentRegistration;
import Uniplay.Base.NGUniplayObject;
import Uniplay.Base.NGUniplayRegisteredComponentItem;
import Uniplay.Kernel.NGGameEngineMemoryManager;
import Uniplay.Kernel.NGGameEngineModuleManager;
import Uniwork.Misc.NGLogEvent;
import Uniwork.Misc.NGLogEventListener;
import Uniwork.Misc.NGLogManager;
import Uniwork.Misc.NGTickGenerator;

import java.util.ArrayList;

public final class NGGameEngine extends NGUniplayComponent implements NGLogEventListener, NGUniplayComponentRegistration {

    public final static String CMP_KERNEL         = "Kernel";
    public final static String CMP_MEMORY_MANAGER = "MemoryManager";
    public final static String CMP_MODULE_MANAGER = "ModuleManager";

    protected NGTickGenerator FTickGenerator;
    protected ArrayList<NGUniplayRegisteredComponentItem> FRegisteredComponents;
    protected Boolean FRunning;

    protected void DoRun() {
        FTickGenerator.SetAllEnabled(true);
        writeLog("Uniplay engine is running...");
    }

    protected void DoStop() {
        FTickGenerator.SetAllEnabled(false);
        writeLog("Uniplay engine is on hold...");
    }

    protected void DoCreateModules() {

    }

    protected void CreateModules() {
        writeLog("Start modules creation...");
        DoCreateModules();
        writeLog("All modules created!");
    }

    protected void DoLoadModules() {
        NGGameEngineModuleManager component = (NGGameEngineModuleManager)getSubComponent(CMP_MODULE_MANAGER);
        component.LoadModules();
    }

    protected void LoadModules() {
        writeLog("Start all modules loading...");
        DoLoadModules();
        writeLog("All modules loaded!");
    }

    @Override
    protected void BeforeInitialize() {
        super.BeforeInitialize();
        NGUniplayComponent component = getSubComponent(CMP_MEMORY_MANAGER);
        component.addEventListener(this);
        FTickGenerator.setLogManager(FLogManager);
        LoadModules();
    }

    @Override
    protected void DoInitialize() {
        writeLog("Start Uniplay engine initialization...");
        super.DoInitialize();
        FTickGenerator.Initialize();
    }

    @Override
    protected void AfterInitialize() {
        super.AfterInitialize();
        writeLog("Uniplay engine initialized!");
    }

    @Override
    protected void DoFinalize() {
        writeLog("Start Uniplay engine shutdown...");
        FTickGenerator.Finalize();
        super.DoFinalize();
    }

    @Override
    protected void AfterFinalize() {
        super.AfterFinalize();
        writeLog("Uniplay engine stopped!");
        writeLog("Bye Bye...");
    }

    @Override
    protected void CreateSubComponents() {
        super.CreateSubComponents();
        FLogManager = new NGLogManager();
        FLogManager.addEventListener(this);
        writeLog("Welcome to Uniplay engine...");
        writeLog(String.format("Start creation of %s sub components...", CMP_KERNEL));
        NGUniplayComponent component = new NGGameEngineModuleManager(this, CMP_MODULE_MANAGER);
        addSubComponent(component);
        writeLog(String.format("%s created.", CMP_MODULE_MANAGER));
        component = new NGGameEngineMemoryManager(this, CMP_MEMORY_MANAGER);
        addSubComponent(component);
        writeLog(String.format("%s created.", CMP_MEMORY_MANAGER));
        writeLog(String.format("All %s sub components created.",CMP_KERNEL));
        CreateModules();
    }

    @Override
    protected Object DoResolveObject(String aName, Class aClass) {
        NGUniplayComponent component = getRegisteredComponent(aName);
        if (aClass.isAssignableFrom(component.getClass())) {
            return component;
        }
        return super.DoResolveObject(aName, aClass);
    }

    protected NGUniplayComponent getRegisteredComponent(String aName) {
        NGUniplayRegisteredComponentItem item = getRegisteredComponentItem(aName);
        if (item != null) {
            return item.getComponent();
        }
        return null;
    }

    protected NGUniplayRegisteredComponentItem getRegisteredComponentItem(String aName) {
        for (NGUniplayRegisteredComponentItem item : FRegisteredComponents) {
            if (item.getName().equals(aName)) {
                return item;
            }
        }
        return null;
    }

    public NGGameEngine(NGUniplayObject aOwner) {
        super(aOwner, CMP_KERNEL);
        FRegisteredComponents = new ArrayList<NGUniplayRegisteredComponentItem>();
        FTickGenerator = new NGTickGenerator(10);
        FRunning = false;
    }

    @Override
    public void handleAddLog(NGLogEvent e) {
        // ToDo
        System.out.println(e.LogEntry.GetFullAsString("YYYY/MM/dd HH:mm:ss", FLogManager.getLogLevel() > 0));
    }

    @Override
    public void handleClearLog() {

    }

    public Boolean getRunning() {
        return FRunning;
    }

    public void setDebugLevel(int aDebugLevel) {
        FLogManager.setLogLevel(aDebugLevel);
    }

    public int getDebugLevel() {
        return FLogManager.getLogLevel();
    }

    public void Run() {
        if (!FRunning) {
            DoRun();
            FRunning = true;
        }
    }

    public void Stop() {
        if (FRunning) {
            DoStop();
            FRunning = false;
        }
    }

    public void Startup() {
        Initialize();
        Run();
    }

    public void Shutdown() {
        Stop();
        Finalize();
    }

    @Override
    public void registerComponent(String aName, NGUniplayComponent aComponent) {
        NGUniplayRegisteredComponentItem item = new NGUniplayRegisteredComponentItem(aName, aComponent);
        FRegisteredComponents.add(item);
    }

    @Override
    public void unregisterComponent(String aName, NGUniplayComponent aComponent) {
        NGUniplayRegisteredComponentItem item = getRegisteredComponentItem(aName);
        if (item != null && item.getComponent().equals(aComponent)) {
            FRegisteredComponents.remove(item);
        }
    }

}
