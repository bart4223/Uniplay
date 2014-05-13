package Uniplay.Base;

import Uniplay.Kernel.NGGameEngineEvent;
import Uniplay.Kernel.NGGameEngineEventListener;
import Uniwork.Base.NGInitializable;
import Uniwork.Base.NGLogManager;

import java.util.ArrayList;

public abstract class NGUniplayComponent extends NGUniplayObject implements NGInitializable, NGGameEngineEventListener{

    protected Boolean FInitialized;
    protected String FName;
    protected NGUniplayObject FOwner;
    protected NGLogManager FLogManager;
    protected ArrayList<NGGameEngineEventListener> FEventListeners;

    protected void writeLog(String aText) {
        writeLog(0, aText);
    }

    protected void writeLog(int aLogLevel, String aText) {
        if (FLogManager != null) {
            FLogManager.writeLog(aLogLevel, aText, getClass().getName());
        }
    }

    @Override
    protected Object DoResolveObject(String aName, Class aClass) {
        if (FName.equals(aName) && aClass.isAssignableFrom(getClass())) {
            return this;
        }
        return super.DoResolveObject(aName, aClass);
    }

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

    public NGUniplayComponent(NGUniplayObject aOwner, String aName) {
        super();
        FName = aName;
        FOwner = aOwner;
        FInitialized = false;
        FEventListeners = new ArrayList<NGGameEngineEventListener>();
        FLogManager = null;
    }

    public String getName() {
        return FName;
    }

    public Object getOwner() {
        return FOwner;
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

    public void setLogManager(NGLogManager aLogManager) {
        FLogManager = aLogManager;
    }

    public NGLogManager getLogManager() {
        return FLogManager;
    }

    public void addEventListener(NGGameEngineEventListener aListener) {
        FEventListeners.add(aListener);
    }

    public void removeEventListener(NGGameEngineEventListener aListener) {
        FEventListeners.remove(aListener);
    }

}
