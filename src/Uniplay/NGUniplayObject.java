package Uniplay;

import Uniplay.Kernel.NGGameEngineEvent;
import Uniplay.Kernel.NGGameEngineEventListener;
import Uniwork.Base.NGInitializable;
import Uniwork.Base.NGObject;

import java.util.ArrayList;

public abstract class NGUniplayObject extends NGObject implements NGInitializable{

    protected Boolean FInitialized;
    protected ArrayList<NGGameEngineEventListener> FEventListeners;

    protected void BeforeInitialize() {

    }

    protected void AfterInitialize() {

    }

    protected void DoInitialize() {

    }

    protected void InternalInitialize() {
        BeforeInitialize();
        DoInitialize();
        AfterInitialize();
    }

    protected void BeforeFinalize() {

    }

    protected void AfterFinalize() {

    }

    protected void InternalFinalize() {
        BeforeFinalize();
        DoFinalize();
        AfterFinalize();
    }

    protected void DoFinalize() {

    }

    protected void raiseEvent(String aName, NGGameEngineEvent event) {
        for (NGGameEngineEventListener listener : FEventListeners) {
            listener.handleEvent(aName, event);
        }
    }

    public NGUniplayObject() {
        super();
        FInitialized = false;
        FEventListeners = new ArrayList<NGGameEngineEventListener>();
    }

    @Override
    public void Initialize() {
        if (!FInitialized) {
            InternalInitialize();
            FInitialized = true;
        }
    }

    @Override
    public void Finalize() {
        if (FInitialized) {
            InternalFinalize();
            FInitialized = false;
        }
    }

    public void addEventListener(NGGameEngineEventListener aListener) {
        FEventListeners.add(aListener);
    }

    public void removeEventListener(NGGameEngineEventListener aListener) {
        FEventListeners.remove(aListener);
    }

}
