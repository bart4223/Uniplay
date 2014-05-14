package Uniplay;

import Uniplay.Base.NGUniplayComponent;
import Uniplay.Base.NGUniplayObject;
import Uniplay.Kernel.NGGameEngineMemoryManager;
import Uniplay.Kernel.NGGameEngineModuleManager;
import Uniwork.Base.NGLogEvent;
import Uniwork.Base.NGLogEventListener;
import Uniwork.Base.NGLogManager;
import Uniwork.Base.NGTickGenerator;

public final class NGGameEngine extends NGUniplayComponent implements NGLogEventListener {

    public final static String CMP_KERNEL         = "Kernel";
    public final static String CMP_MEMORY_MANAGER = "MemoryManager";
    public final static String CMP_MODULE_MANAGER = "ModuleManager";

    protected NGGameEngineModuleManager FModuleManager;
    protected NGGameEngineMemoryManager FMemoryManager;
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
        FMemoryManager.addEventListener(this);
        FMemoryManager.setLogManager(FLogManager);
        FModuleManager.setLogManager(FLogManager);
        FTickGenerator.setLogManager(FLogManager);
        CreateModules();
        LoadModules();
    }

    @Override
    protected void DoInitialize() {
        writeLog("Start Uniplay engine initialization...");
        super.DoInitialize();
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
        super.DoFinalize();
    }

    @Override
    protected void AfterFinalize() {
        super.AfterFinalize();
        writeLog("Uniplay engine stopped!");
        writeLog("Bye Bye...");
    }

    @Override
    protected void CreateComponents() {
        super.CreateComponents();
        FLogManager = new NGLogManager();
        FLogManager.addEventListener(this);
        writeLog("Welcome to Uniplay engine...");
        writeLog(String.format("Start creation of %s components...", CMP_KERNEL));
        FModuleManager = new NGGameEngineModuleManager(this, CMP_MODULE_MANAGER);
        writeLog(String.format("%s created.", CMP_MODULE_MANAGER));
        FMemoryManager = new NGGameEngineMemoryManager(this, CMP_MEMORY_MANAGER);
        writeLog(String.format("%s created.", CMP_MEMORY_MANAGER));
        FTickGenerator = new NGTickGenerator(10);
        writeLog(String.format("All %s components created.",CMP_KERNEL));
    }

    public NGGameEngine(NGUniplayObject aOwner) {
        super(aOwner, CMP_KERNEL);
        FRunning = false;
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

    public void Startup() {
        Initialize();
        Run();
    }

    public void Shutdown() {
        Stop();
        Finalize();
    }

}
