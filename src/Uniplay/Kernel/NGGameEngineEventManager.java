package Uniplay.Kernel;

import Uniplay.NGUniplayObject;
import Uniwork.Base.NGLogManager;

import java.util.ArrayList;

public class NGGameEngineEventManager extends NGUniplayObject implements NGGameEngineEventListener {

    protected NGUniplayObject FOwner;
    protected NGLogManager FLogManager;
    protected ArrayList<NGGameEngineEventHandler> FHandlers;

    protected void writeLog(String aText) {
        if (FLogManager != null) {
            FLogManager.writeLog(aText, getClass().getSimpleName());
        }
    }

    protected void DoHandleEvent(String aName, NGGameEngineEvent aEvent) {
        writeLog(String.format("DoHandleEvent %s", aName, 5));
        for (NGGameEngineEventHandler handler : FHandlers) {
            if (handler.getName().equals(aName)) {
                handler.handleEvent(aEvent);
            }
        }
    }

    @Override
    protected void BeforeInitialize() {
        writeLog("Start event handler initialization...");
        super.BeforeInitialize();
    }

    @Override
    protected void AfterInitialize() {
        super.AfterInitialize();
        writeLog("Event handler initialized!");
    }

    @Override
    protected void BeforeFinalize() {
        writeLog("Start event handler release...");
        super.BeforeFinalize();
    }

    @Override
    protected void AfterFinalize() {
        super.AfterFinalize();
        writeLog("Event handler released!");
    }


    public NGGameEngineEventManager(NGUniplayObject aOwner) {
        super();
        FOwner = aOwner;
        FLogManager = null;
        FHandlers = new ArrayList<NGGameEngineEventHandler>();
    }

    public NGUniplayObject getOwner() {
        return FOwner;
    }

    @Override
    public void handleEvent(String name, NGGameEngineEvent e) {
        DoHandleEvent(name, e);
    }

    public void setLogManager(NGLogManager aLogManager) {
        FLogManager = aLogManager;
    }

    public NGLogManager getLogManager() {
        return FLogManager;
    }

    public void addHandler(NGGameEngineEventHandler aHandler) {
        FHandlers.add(aHandler);
    }

    public void removeHandler(NGGameEngineEventHandler aHandler) {
        FHandlers.remove(aHandler);
    }

}
