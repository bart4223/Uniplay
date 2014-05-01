package Uniplay.Base;

import Uniwork.Base.NGLogEvent;
import Uniwork.Base.NGLogEventListener;
import Uniwork.Base.NGLogManager;
import Uniwork.Base.NGTickGenerator;

public class NGGameEngine extends NGUniplayObject implements NGLogEventListener{

    protected NGGameEngineModuleManager FModuleManager;
    protected NGGameEngineMemoryManager FMemoryManager;
    protected NGLogManager FLogManager;
    protected NGTickGenerator FTickGenerator;
    protected Object FOnwer;
    protected Boolean FRunning;
    protected int FDebugLevel;

    protected void DoRun() {
        FTickGenerator.SetAllEnabled(true);
        writeLog("Uniplay engine is running...");
    }

    protected void DoStop() {
        FTickGenerator.SetAllEnabled(false);
        writeLog("Uniplay engine is on hold...");
    }

    protected void writeLog(String aText) {
        FLogManager.writeLog(aText, getClass().getSimpleName());
    }

    @Override
    protected void BeforeInitialize() {
        super.BeforeInitialize();
        FLogManager.addEventListener(this);
        writeLog("Welcome to Uniplay engine...");
        FMemoryManager.setLogManager(FLogManager);
        FModuleManager.setLogManager(FLogManager);
        FTickGenerator.setLogManager(FLogManager);
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

    public NGGameEngine(Object aOwner) {
        super();
        FOnwer = aOwner;
        FRunning = false;
        FMemoryManager = new NGGameEngineMemoryManager(this);
        FModuleManager = new NGGameEngineModuleManager(this);
        FTickGenerator = new NGTickGenerator(10);
        FLogManager = new NGLogManager();
        FDebugLevel = 0;
    }

    @Override
    public void handleAddLog(NGLogEvent e) {
        System.out.println(e.LogEntry.GetFullAsString("YYYY/MM/dd HH:mm:ss", FDebugLevel > 0));
    }

    @Override
    public void handleClearLog() {

    }

    public Boolean getRunning() {
        return FRunning;
    }

    public void setDebugLevel(int aDebugLevel) {
        FDebugLevel = aDebugLevel;
    }

    public int getDebugLevel() {
        return FDebugLevel;
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

}
