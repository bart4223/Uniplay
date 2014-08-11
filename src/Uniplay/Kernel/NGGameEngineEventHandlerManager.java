package Uniplay.Kernel;

import Uniplay.Base.NGUniplayComponent;
import Uniplay.Base.NGUniplayObject;
import Uniplay.NGGameEngineConstants;

import java.util.ArrayList;

public class NGGameEngineEventHandlerManager extends NGUniplayComponent implements NGGameEngineEventListener {

    protected ArrayList<NGGameEngineEventHandler> FHandlers;

    protected void DoHandleEvent(String aName, NGGameEngineEvent aEvent) {
        writeLog(NGGameEngineConstants.DEBUG_LEVEL_EVENTS, String.format("Event handler [%s].DoHandleEvent->[%s]", getName(), aName));
        for (NGGameEngineEventHandler handler : FHandlers) {
            if (handler.getName().equals(aName)) {
                handler.handleEvent(aEvent);
            }
        }
    }

    @Override
    protected void BeforeInitialize() {
        writeLog(String.format("Start event handler [%s] initialization...", FName));
        super.BeforeInitialize();
    }

    @Override
    protected void AfterInitialize() {
        super.AfterInitialize();
        writeLog(String.format("Event handler [%s] initialized!", FName));
    }

    @Override
    protected void BeforeFinalize() {
        writeLog(String.format("Start event handler [%s] release...", FName));
        super.BeforeFinalize();
    }

    @Override
    protected void AfterFinalize() {
        super.AfterFinalize();
        writeLog(String.format("Event handler [%s] released!", FName));
    }


    public NGGameEngineEventHandlerManager(NGUniplayComponent aOwner, String aName) {
        super(aOwner, aName);
        if (aOwner != null) {
            aOwner.addEventListener(this);
        }
        FHandlers = new ArrayList<NGGameEngineEventHandler>();
    }

    @Override
    public void handleEvent(NGUniplayObject caller, String name, NGGameEngineEvent e) {
        DoHandleEvent(name, e);
    }

    public void addHandler(NGGameEngineEventHandler aHandler) {
        FHandlers.add(aHandler);
    }

    public void removeHandler(NGGameEngineEventHandler aHandler) {
        FHandlers.remove(aHandler);
    }

}
