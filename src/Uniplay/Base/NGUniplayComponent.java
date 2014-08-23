package Uniplay.Base;

import Uniplay.Kernel.NGGameEngineEvent;
import Uniplay.Kernel.NGGameEngineEventListener;
import Uniwork.Base.NGInitializable;
import Uniwork.Misc.NGLogManager;

import java.util.ArrayList;

public abstract class NGUniplayComponent extends NGUniplayObject implements NGInitializable, NGGameEngineEventListener {

    protected Boolean FInitialized;
    protected Boolean FConfigLoaded;
    protected Boolean FDefinitionLoaded;
    protected String FName;
    protected NGUniplayObject FOwner;
    protected NGLogManager FLogManager;
    protected ArrayList<NGGameEngineEventListener> FEventListeners;
    protected ArrayList<NGUniplayComponent> FSubComponents;
    protected NGUniplayObjectDefinition FDefinition;

    protected void writeLog(String aText) {
        writeLog(0, aText);
    }

    protected void writeLog(int aLogLevel, String aText) {
        if (FLogManager != null) {
            FLogManager.writeLog(aLogLevel, aText, getClass().getName());
        }
    }

    protected void writeError(String aMethodName, String aErrorText) {
        writeLog(0, String.format("<<<ERROR>>> at [%s.%s] with exception [%s]!", getClass().getName(), aMethodName, aErrorText));
    }

    protected void writeWarning(String aMethodName, String aWarningText) {
        writeLog(0, String.format("<<<WARNING>>> at [%s.%s] with exception [%s]!", getClass().getName(), aMethodName, aWarningText));
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
        Object result = super.DoResolveObject(aName, aClass);
        if (result == null && FOwner != null) {
            return FOwner.ResolveObject(aName, aClass);
        }
        return result;
    }

    protected void DoHandleEvent(String name, NGGameEngineEvent e) {
    }

    protected void BeforeInitialize() {
        LoadConfiguration();
        if (FConfigLoaded) {
            writeLog(String.format("Configuration from component [%s] loaded.", getName()));
        }
        LoadDefinition();
        if (FDefinitionLoaded) {
            writeLog(String.format("Definition from component [%s] loaded.", getName()));
        }
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
            writeLog(String.format("SubComponent [%s] initialized.", component.getName()));
        }
    }

    protected void InternalInitialize() {
        BeforeInitialize();
        try {
            DoInitialize();
        }
        finally {
            AfterInitialize();
        }
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
            writeLog(String.format("SubComponent [%s] finalized.", component.getName()));
        }
    }

    protected void DoInitialized() {

    }

    protected void Initialized() {
        DoInitialized();
    }

    protected void DoFinalized() {

    }

    protected void Finalized() {
        DoFinalized();
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

    protected void LoadDefinition() {

    }

    protected void CreateSubComponents() {

    }

    protected void addSubComponent(NGUniplayComponent aComponent) {
        aComponent.setLogManager(FLogManager);
        for (NGGameEngineEventListener listener : FEventListeners) {
            aComponent.addEventListener(listener);
        }
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
        FConfigLoaded = false;
        FDefinitionLoaded = false;
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
            Initialized();
        }
    }

    @Override
    public void Finalize() {
        if (FInitialized) {
            InternalFinalize();
            FInitialized = false;
            Finalized();
        }
    }

    public String getConfigurationProperty(String aName) {
        if (!FConfigLoaded && FOwner instanceof NGUniplayComponent) {
            NGUniplayComponent component = (NGUniplayComponent)FOwner;
            String path = String.format("%s.%s", getName(), aName);
            return component.getConfigurationProperty(path);
        }
        return "";
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
