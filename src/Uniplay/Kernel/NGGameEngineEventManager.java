package Uniplay.Kernel;

import Uniplay.Base.NGUniplayComponent;
import Uniplay.Base.NGUniplayObject;
import Uniplay.NGGameEngineConstants;

import java.util.ArrayList;

public class NGGameEngineEventManager extends NGUniplayComponent implements NGGameEngineEventListener, NGGameEngineEventHandlerRegistration {

    protected ArrayList<NGGameEngineEventHandler> FHandlers;

    protected void DoHandleEvent(String aName, NGGameEngineEvent aEvent) {
        writeLog(NGGameEngineConstants.DEBUG_LEVEL_EVENTS, String.format("Event manager [%s].DoHandleEvent->[%s]", getName(), aName));
        for (NGGameEngineEventHandler handler : FHandlers) {
            if (handler.getName().equals(aName)) {
                handler.handleEvent(aEvent);
            }
        }
    }

    @Override
    protected void BeforeInitialize() {
        writeLog(String.format("Start event manager [%s] initialization...", FName));
        super.BeforeInitialize();
    }

    @Override
    protected void AfterInitialize() {
        super.AfterInitialize();
        writeLog(String.format("Event manager [%s] initialized!", FName));
    }

    @Override
    protected void BeforeFinalize() {
        writeLog(String.format("Start event manager [%s] release...", FName));
        super.BeforeFinalize();
    }

    @Override
    protected void AfterFinalize() {
        super.AfterFinalize();
        writeLog(String.format("Event manager [%s] released!", FName));
    }

    protected void addHandler(NGGameEngineEventHandler aHandler) {
        FHandlers.add(aHandler);
    }

    protected void removeHandler(NGGameEngineEventHandler aHandler) {
        FHandlers.remove(aHandler);
    }

    public NGGameEngineEventManager(NGUniplayComponent aOwner, String aName) {
        super(aOwner, aName);
        FHandlers = new ArrayList<NGGameEngineEventHandler>();
    }

    @Override
    public void handleEvent(NGUniplayObject caller, String name, NGGameEngineEvent e) {
        DoHandleEvent(name, e);
    }

    @Override
    public void registerEventHandler(NGGameEngineEventHandler aHandler) {
        addHandler(aHandler);
    }

    @Override
    public void unregisterEventHandler(NGGameEngineEventHandler aHandler) {
        removeHandler(aHandler);
    }
}
