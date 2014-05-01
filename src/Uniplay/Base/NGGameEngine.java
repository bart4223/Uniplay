package Uniplay.Base;

import Uniplay.NGUniplayObject;
import Uniwork.Base.NGLogEvent;
import Uniwork.Base.NGLogEventListener;
import Uniwork.Base.NGLogManager;
import Uniwork.Base.NGTickGenerator;

public class NGGameEngine extends NGUniplayObject implements NGLogEventListener{

    protected NGGameEngineModuleManager FModuleManager;
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
        FLogManager.writeLog(aText);
    }

    @Override
    protected void BeforeInitialize() {
        super.BeforeInitialize();
        FLogManager.addEventListener(this);
        FModuleManager.setLogManager(FLogManager);
        FTickGenerator.setLogManager(FLogManager);
    }

    @Override
    protected void DoInitialize() {
        writeLog("Start Uniplay engine initialization...");
        super.DoInitialize();
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
        super.DoFinalize();
    }

    @Override
    protected void AfterFinalize() {
        super.AfterFinalize();
        writeLog("Uniplay engine stopped!");
    }

    public NGGameEngine(Object aOwner) {
        super();
        FOnwer = aOwner;
        FRunning = false;
        FModuleManager = new NGGameEngineModuleManager(this);
        FLogManager = new NGLogManager();
        FTickGenerator = new NGTickGenerator(10);
    }

    @Override
    public void handleAddLog(NGLogEvent e) {
        System.out.println(e.LogEntry.GetFullAsString("dd.MM.YYYY HH:mm:ss"));
    }

    @Override
    public void handleClearLog() {

    }

    public Boolean getRunning() {
        return FRunning;
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
