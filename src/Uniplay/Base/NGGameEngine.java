package Uniplay.Base;

import Uniplay.NGUniplayObject;
import Uniwork.Base.NGLogEvent;
import Uniwork.Base.NGLogEventListener;
import Uniwork.Base.NGLogManager;

public class NGGameEngine extends NGUniplayObject implements NGLogEventListener{

    protected NGGameEngineModuleManager FModuleManager;
    protected NGLogManager FLogManager;
    protected Object FOnwer;
    protected Boolean FRunning;

    protected void DoRun() {
        writeLog("Uniplay engine running...");
    }

    protected void DoStop() {
        writeLog("Uniplay engine stopping...");
    }

    protected void writeLog(String aText) {
        FLogManager.writeLog(aText);
    }

    @Override
    protected void BeforeInitialize() {
        super.BeforeInitialize();
        FLogManager.addEventListener(this);
        FModuleManager.setLogManager(FLogManager);
    }

    @Override
    protected void DoInitialize() {
        writeLog("Start Uniplay engine initialization...");
        super.DoInitialize();
        FModuleManager.Initialize();
    }
    @Override
    protected void AfterInitialize() {
        super.AfterInitialize();
        writeLog("Uniplay engine initialized!");
    }

    @Override
    protected void DoFinalize() {
        writeLog("Start Uniplay engine shutdown...");
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
        FModuleManager = new NGGameEngineModuleManager(this);
        FLogManager = new NGLogManager();
        FOnwer = aOwner;
        FRunning = false;
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
