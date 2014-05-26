package Uniplay.Base;

import Uniplay.Kernel.NGGameEngineEvent;
import Uniplay.Kernel.NGGameEngineEventListener;
import Uniwork.Base.NGInitializable;
import Uniwork.Misc.NGLogManager;

import java.util.ArrayList;

public abstract class NGUniplayComponent extends NGUniplayObject implements NGInitializable, NGGameEngineEventListener {

    protected Boolean FInitialized;
    protected String FName;
    protected NGUniplayObject FOwner;
    protected NGLogManager FLogManager;
    protected ArrayList<NGGameEngineEventListener> FEventListeners;
    protected ArrayList<NGUniplayComponent> FSubComponents;

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
        for (NGUniplayComponent component : FSubComponents) {
            if (component.getName().equals(aName) && aClass.isAssignableFrom(component.getClass())) {
                return component;
            }
        }
        return super.DoResolveObject(aName, aClass);
    }

    protected void DoHandleEvent(String name, NGGameEngineEvent e) {
        for (NGGameEngineEventListener listener : FEventListeners) {
            if (!e.getSource().equals(this))
                listener.handleEvent(this, name, e);
        }
    }

    protected void BeforeInitialize() {
        LoadConfiguration();
        CreateSubComponents();
        for (NGUniplayComponent component : FSubComponents) {
            component.setLogManager(FLogManager);
        }
    }

    protected void AfterInitialize() {

    }

    protected void DoInitialize() {
        for (NGUniplayComponent component : FSubComponents) {
            component.Initialize();
            writeLog(String.format("Component [%s] initialized.", component.getName()));
        }
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
        for (NGUniplayComponent component : FSubComponents) {
            component.Finalize();
            writeLog(String.format("Component [%s] finalized.", component.getName()));
        }
    }

    protected NGUniplayComponent getSubComponent(String aName) {
        for (NGUniplayComponent component : FSubComponents) {
            if (component.getName().equals(aName)) {
                return component;
            }
        }
        return null;
    }

    protected void LoadConfiguration() {

    }

    protected void CreateSubComponents() {

    }

    protected void addSubComponent(NGUniplayComponent aComponent) {
        FSubComponents.add(aComponent);
    }

    protected void removeSubComponent(NGUniplayComponent aComponent) {
        FSubComponents.remove(aComponent);
    }

    protected void raiseEvent(String aName, NGGameEngineEvent event) {
        for (NGGameEngineEventListener listener : FEventListeners) {
            listener.handleEvent(this, aName, event);
        }
    }

    public NGUniplayComponent(NGUniplayObject aOwner, String aName) {
        super();
        FName = aName;
        FOwner = aOwner;
        FEventListeners = new ArrayList<NGGameEngineEventListener>();
        FSubComponents = new ArrayList<NGUniplayComponent>();
        FInitialized = false;
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

    @Override
    public void handleEvent(NGUniplayObject caller, String name, NGGameEngineEvent e) {
        if (caller != this) {
            DoHandleEvent(name, e);
        }
    }

}
