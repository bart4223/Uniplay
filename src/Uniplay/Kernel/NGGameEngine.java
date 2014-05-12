package Uniplay.Kernel;

import Uniplay.Base.NGUniplayObject;
import Uniwork.Base.NGLogEvent;
import Uniwork.Base.NGLogEventListener;
import Uniwork.Base.NGLogManager;
import Uniwork.Base.NGTickGenerator;

public abstract class NGGameEngine extends NGUniplayObject implements NGLogEventListener, NGGameEngineEventHandlerRegistration{

    protected NGGameEngineModuleManager FModuleManager;
    protected NGGameEngineMemoryManager FMemoryManager;
    protected NGGameEngineEventManager FEventManager;
    protected NGLogManager FLogManager;
    protected NGTickGenerator FTickGenerator;
    protected Object FOnwer;
    protected Boolean FRunning;

    protected void DoRun() {
        FTickGenerator.SetAllEnabled(true);
        writeLog("Uniplay engine is running...");
    }

    protected void DoStop() {
        FTickGenerator.SetAllEnabled(false);
        writeLog("Uniplay engine is on hold...");
    }

    protected void writeLog(String aText) {
        writeLog(0, aText);
    }

    protected void writeLog(int aLogLevel, String aText) {
        FLogManager.writeLog(aLogLevel, aText, getClass().getName());
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

    public NGGameEngine(Object aOwner) {
        super();
        FOnwer = aOwner;
        FRunning = false;
        FMemoryManager = new NGGameEngineMemoryManager(this);
        FModuleManager = new NGGameEngineModuleManager(this);
        FEventManager = new NGGameEngineEventManager(this);
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
    public void registerEventHandler(NGGameEngineEventHandler aHandler) {
        FEventManager.addHandler(aHandler);
    }

    @Override
    public void unregisterEventHandler(NGGameEngineEventHandler aHandler) {
        FEventManager.removeHandler(aHandler);
    }

}
