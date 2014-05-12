package Uniplay.Kernel;

import Uniplay.Base.NGUniplayComponent;
import Uniplay.Base.NGUniplayObject;
import Uniwork.Base.NGLogEvent;
import Uniwork.Base.NGLogEventListener;
import Uniwork.Base.NGLogManager;
import Uniwork.Base.NGTickGenerator;

public abstract class NGGameEngine extends NGUniplayComponent implements NGLogEventListener, NGGameEngineEventHandlerRegistration{

    public final static String CMP_MEMORY_MANAGER = "MemoryManager";
    public final static String CMP_EVENT_MANAGER  = "EventManager";
    public final static String CMP_MODULE_MANAGER = "ModuleManager";


    protected NGGameEngineModuleManager FModuleManager;
    protected NGGameEngineMemoryManager FMemoryManager;
    protected NGGameEngineEventManager FEventManager;
    protected NGTickGenerator FTickGenerator;
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
        FModuleManager.LoadModules();
    }

    protected void LoadModules() {
        writeLog("Start all modules loading...");
        DoLoadModules();
        writeLog("All modules loaded!");
    }

    @Override
    protected void BeforeInitialize() {
        super.BeforeInitialize();
        FLogManager.addEventListener(this);
        writeLog("Welcome to Uniplay engine...");
        FEventManager.setLogManager(FLogManager);
        FMemoryManager.setLogManager(FLogManager);
        FMemoryManager.addEventListener(FEventManager);
        FModuleManager.setLogManager(FLogManager);
        FTickGenerator.setLogManager(FLogManager);
        CreateModules();
        LoadModules();
    }

    @Override
    protected void DoInitialize() {
        writeLog("Start Uniplay engine initialization...");
        super.DoInitialize();
        FEventManager.Initialize();
        FMemoryManager.Initialize();
        FModuleManager.Initialize();
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
        FModuleManager.Finalize();
        FMemoryManager.Finalize();
        FEventManager.Finalize();
        super.DoFinalize();
    }

    @Override
    protected void AfterFinalize() {
        super.AfterFinalize();
        writeLog("Uniplay engine stopped!");
        writeLog("Bye Bye...");
    }

    public NGGameEngine() {
        this("");
    }

    public NGGameEngine(NGUniplayObject aOwner) {
        this(aOwner, "");
    }

    public NGGameEngine(String aName) {
        this(null, aName);
    }

    public NGGameEngine(NGUniplayObject aOwner, String aName) {
        super(aOwner, aName);
        FRunning = false;
        FMemoryManager = new NGGameEngineMemoryManager(this, CMP_MEMORY_MANAGER);
        FModuleManager = new NGGameEngineModuleManager(this, CMP_MODULE_MANAGER);
        FEventManager = new NGGameEngineEventManager(this, CMP_EVENT_MANAGER);
        FTickGenerator = new NGTickGenerator(10);
        FLogManager = new NGLogManager();
    }

    @Override
    public void handleAddLog(NGLogEvent e) {
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

    @Override
    public void handleEvent(String name, NGGameEngineEvent e) {

    }

    @Override
    public void registerEventHandler(NGGameEngineEventHandler aHandler) {
        FEventManager.addHandler(aHandler);
    }

    @Override
    public void unregisterEventHandler(NGGameEngineEventHandler aHandler) {
        FEventManager.removeHandler(aHandler);
    }

}
