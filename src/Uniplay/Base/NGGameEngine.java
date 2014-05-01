package Uniplay.Base;

import Uniplay.NGUniplayObject;
import Uniwork.Base.NGLogEvent;
import Uniwork.Base.NGLogEventListener;
import Uniwork.Base.NGLogManager;

public class NGGameEngine extends NGUniplayObject implements NGLogEventListener{

    protected NGGameEngineModuleManager FModuleManager;
    protected NGLogManager FLogManager;

    @Override
    protected void DoInitialize() {
        super.DoInitialize();
        FLogManager.addEventListener(this);
        FModuleManager.Initialize();
    }

    @Override
    protected void DoFinalize() {
        FModuleManager.Finalize();
        super.DoFinalize();
    }

    public NGGameEngine() {
        super();
        FModuleManager = new NGGameEngineModuleManager(this);
        FLogManager = new NGLogManager();
    }

    @Override
    public void handleAddLog(NGLogEvent e) {
        System.out.println(e.LogEntry.GetFullAsString());
    }

    @Override
    public void handleClearLog() {

    }
}
