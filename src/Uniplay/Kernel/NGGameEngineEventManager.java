package Uniplay.Kernel;

import Uniplay.Base.NGUniplayComponent;
import Uniplay.Base.NGUniplayObject;

import java.util.ArrayList;

public class NGGameEngineEventManager extends NGUniplayComponent implements NGGameEngineEventListener {

    protected ArrayList<NGGameEngineEventHandler> FHandlers;

    protected void DoHandleEvent(String aName, NGGameEngineEvent aEvent) {
        writeLog(10, String.format("DoHandleEvent->[%s]", aName));
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


    public NGGameEngineEventManager(NGUniplayObject aOwner, String aName) {
        super(aOwner, aName);
        FHandlers = new ArrayList<NGGameEngineEventHandler>();
    }

    @Override
    public void handleEvent(String name, NGGameEngineEvent e) {
        DoHandleEvent(name, e);
    }

    public void addHandler(NGGameEngineEventHandler aHandler) {
        FHandlers.add(aHandler);
    }

    public void removeHandler(NGGameEngineEventHandler aHandler) {
        FHandlers.remove(aHandler);
    }

}
