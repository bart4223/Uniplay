package Uniplay;

import Uniplay.Base.*;
import Uniplay.Kernel.*;
import Uniwork.Base.*;
import Uniwork.Misc.*;
import javafx.application.Platform;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;

import static java.lang.Thread.sleep;

public final class NGGameEngine extends NGUniplayComponent implements NGLogEventListener, NGUniplayObjectRegistration, NGObjectRequestRegistration, NGLogEventListenerRegistration, NGGameEngineLoggingManagement, NGObjectRequestInvoker {

    protected ArrayList<NGUniplayRegisteredObjectItem> FRegisteredObjects;
    protected Properties FConfiguration;
    protected NGGameEngineDefinition FDefinition;
    protected String FConfigurationFilename = "";
    protected String FDefinitionFilename = "";
    protected Boolean FRunning = false;
    protected Boolean FConsoleShowLogEntrySource = false;
    protected Boolean FConsoleShowLog = true;
    protected Boolean FShowSplash = false;
    protected ArrayList<NGLogEventListener> FLogListener;

    protected static void startupThread(final NGGameEngine aGameEngine) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                if (!aGameEngine.getSplashFinished()) {
                    startupThread(aGameEngine);
                }
                else {
                    aGameEngine.Start();
                }
            }
        });
    }

    protected void DoStartup() {
        Initialize();
        if (FShowSplash) {
            NGSplashManager splash = getSplashManager();
            splash.InitRun();
            startupThread(this);
            splash.Run();
        }
        else {
            Start();
        }
    }

    protected void DoShutdown() {
        Stop();
        Finalize();
    }
    protected void DoStart() {
        writeLog("Uniplay engine is on starting...");
    }

    protected void DoStarted() {
        writeLog("Uniplay engine is running.");
        raiseKernelStarted();
    }

    protected void DoStop() {
        writeLog("Uniplay engine is on stopping...");
        getTaskManager().stopAllPeriodicTasks();
    }

    protected void DoStopped() {
        raiseKernelStopped();
        writeLog("Uniplay engine is stopped.");
    }

    protected void DoCreateModules() {
        NGGameEngineModuleManager manager = getModuleManager();
        for (NGGameEngineDefinitionModuleItem item : FDefinition.getModules()) {
            try {
                Class cl = NGGameEngineModule.class.getClassLoader().loadClass(item.getClassname());
                NGGameEngineModule module = manager.newModule(cl, item.getName());
                module.setCaption(item.getCaption());
                module.setConfigurationFilename(item.getConfigurationFilename());
            }
            catch (Exception e) {
                writeError("DoCreateModules", e.getMessage());
            }
        }
    }

    protected void CreateModules() {
        writeLog("Start modules creation...");
        DoCreateModules();
        writeLog("All modules created!");
    }

    protected String getConfigrationProperty(String aName) {
        if (FConfigLoaded) {
            return FConfiguration.getProperty(aName);
        }
        return "";
    }

    protected void raiseKernelInitialized() {
        NGGameEngineEventKernelInitialized event = new NGGameEngineEventKernelInitialized(this);
        raiseEvent(NGGameEngineConstants.EVT_KERNEL_INITIALIZED, event);
    }

    protected void raiseKernelFinalized() {
        NGGameEngineEventKernelFinalized event = new NGGameEngineEventKernelFinalized(this);
        raiseEvent(NGGameEngineConstants.EVT_KERNEL_FINALIZED, event);
    }

    protected void raiseKernelStarted() {
        NGGameEngineEventKernelStarted event = new NGGameEngineEventKernelStarted(this);
        raiseEvent(NGGameEngineConstants.EVT_KERNEL_STARTED, event);
    }

    protected void raiseKernelStopped() {
        NGGameEngineEventKernelStopped event = new NGGameEngineEventKernelStopped(this);
        raiseEvent(NGGameEngineConstants.EVT_KERNEL_STOPPED, event);
    }

    @Override
    protected void LoadConfiguration() {
        super.LoadConfiguration();
        Boolean result = FConfigurationFilename.length() > 0;
        if (result) {
            try {
                InputStream is = new FileInputStream(FConfigurationFilename);
                FConfiguration.load(is);
                FConfigLoaded = true;
                FDefinitionFilename = getConfigrationProperty("DefinitionFilename");
                FConsoleShowLogEntrySource = Boolean.valueOf(getConfigrationProperty("ConsoleShowLogEntrySource"));
                FConsoleShowLog = Boolean.valueOf(getConfigrationProperty("ConsoleShowLog"));
                FLogManager.setLogLevel(Integer.parseInt(getConfigrationProperty("Debuglevel")));
            }
            catch ( Exception e) {
                writeError("LoadConfiguration", e.getMessage());
            }
        }
        writeLog("Welcome to Uniplay engine...");
    }

    @Override
    protected void LoadDefinition() {
        if (FDefinitionFilename.length() > 0) {
            NGObjectXMLDeserializerFile loader = new NGObjectXMLDeserializerFile(null, FDefinitionFilename);
            loader.deserializeObject();
            FDefinition = (NGGameEngineDefinition)loader.getTarget();
            FDefinitionLoaded = FDefinition != null;
        }
    }

    @Override
    protected void BeforeInitialize() {
        super.BeforeInitialize();
        NGUniplayComponent manager = getMemoryManager();
        manager.addEventListener(this);
    }

    @Override
    protected void DoInitialize() {
        writeLog("Start Uniplay engine initialization...");
        super.DoInitialize();
    }

    @Override
    protected void AfterInitialize() {
        super.AfterInitialize();
        writeLog("Uniplay engine initialized!");
    }

    @Override
    protected void DoFinalize() {
        writeLog("Start Uniplay engine shutdown...");
        super.DoFinalize();
    }

    @Override
    protected void AfterFinalize() {
        super.AfterFinalize();
        writeLog("Uniplay engine stopped!");
        writeLog("Bye Bye...");
    }

    @Override
    protected void DoInitialized() {
        super.DoInitialized();
        raiseKernelInitialized();
    }

    @Override
    protected void DoFinalized() {
        super.DoFinalize();
        raiseKernelFinalized();
    }

    @Override
    protected void CreateSubComponents() {
        super.CreateSubComponents();
        writeLog(String.format("Start creation of %s sub components...", getName()));
        NGUniplayComponent component = new NGGameEngineMemoryManager(this, NGGameEngineConstants.CMP_MEMORY_MANAGER);
        addSubComponent(component);
        writeLog(String.format("%s created.", component.getName()));
        component = new NGGameEngineModuleManager(this, NGGameEngineConstants.CMP_MODULE_MANAGER);
        addSubComponent(component);
        writeLog(String.format("%s created.", component.getName()));
        component = new NGTaskManager(this, NGGameEngineConstants.CMP_TASK_MANAGER);
        addSubComponent(component);
        writeLog(String.format("%s created.", component.getName()));
        component = new NGUniplayObjectRequestBroker(this, NGGameEngineConstants.CMP_OBJECTREQUESTBROKER);
        addSubComponent(component);
        writeLog(String.format("%s created.", component.getName()));
        component = new NGSplashManager(this, NGGameEngineConstants.CMP_SPLASH_MANAGER);
        addSubComponent(component);
        writeLog(String.format("%s created.", component.getName()));
        writeLog(String.format("All %s sub components created.", getName()));
        if (FDefinition != null) {
            CreateModules();
        }
        else {
            writeLog(String.format("No definition for module creation!", getName()));
        }
    }

    @Override
    protected Object DoResolveObject(String aName, Class aClass) {
        if (aName.length() == 0) {
            for (NGUniplayRegisteredObjectItem item : FRegisteredObjects) {
                if (aClass.isAssignableFrom(item.getObject().getClass())) {
                    return item.getObject();
                }
            }
        }
        else {
            Object object = getRegisteredObject(aName);
            if (object != null && aClass.isAssignableFrom(object.getClass())) {
                return object;
            }
        }
        return super.DoResolveObject(aName, aClass);
    }

    protected Object getRegisteredObject(String aName) {
        NGUniplayRegisteredObjectItem item = getRegisteredComponentItem(aName);
        if (item != null) {
            return item.getObject();
        }
        return null;
    }

    protected NGGameEngineModuleManager getModuleManager() {
        return (NGGameEngineModuleManager)getSubComponent(NGGameEngineConstants.CMP_MODULE_MANAGER);
    }

    protected NGGameEngineMemoryManager getMemoryManager() {
        return (NGGameEngineMemoryManager)getSubComponent(NGGameEngineConstants.CMP_MEMORY_MANAGER);
    }

    protected NGTaskManager getTaskManager() {
        return (NGTaskManager)getSubComponent(NGGameEngineConstants.CMP_TASK_MANAGER);
    }

    protected NGUniplayObjectRequestBroker getObjectRequestBroker() {
        return (NGUniplayObjectRequestBroker)getSubComponent(NGGameEngineConstants.CMP_OBJECTREQUESTBROKER);
    }

    protected NGSplashManager getSplashManager() {
        return (NGSplashManager)getSubComponent(NGGameEngineConstants.CMP_SPLASH_MANAGER);
    }

    protected NGUniplayRegisteredObjectItem getRegisteredComponentItem(String aName) {
        for (NGUniplayRegisteredObjectItem item : FRegisteredObjects) {
            if (item.getName().equals(aName)) {
                return item;
            }
        }
        return null;
    }

    protected Boolean getSplashFinished() {
        return getSplashManager().getFinished();
    }

    protected void DoInvoke(NGObjectRequestItem aRequest) {
        getObjectRequestBroker().Invoke(aRequest);
    }

    public NGGameEngine(NGUniplayObject aOwner) {
        super(aOwner, NGGameEngineConstants.CMP_KERNEL);
        FRegisteredObjects = new ArrayList<NGUniplayRegisteredObjectItem>();
        FLogListener = new ArrayList<NGLogEventListener>();
        FLogManager = new NGLogManager();
        FLogManager.addEventListener(this);
        FConfiguration = new Properties();
        FShowSplash = true;
    }

    @Override
    public void handleAddLog(NGLogEvent e) {
        if (FConsoleShowLog) {
            System.out.println(e.LogEntry.GetFullAsString("YYYY/MM/dd HH:mm:ss", getConsoleShowLogEntrySource()));
        }
        for (NGLogEventListener listener : FLogListener) {
            listener.handleAddLog(e);
        }
    }

    @Override
    public void handleClearLog() {

    }

    public Boolean getRunning() {
        return FRunning;
    }

    public void Start() {
        if (!FRunning) {
            DoStart();
            FRunning = true;
            DoStarted();
        }
    }

    public void Stop() {
        if (FRunning) {
            DoStop();
            FRunning = false;
            DoStopped();
        }
    }

    public void Startup() {
        DoStartup();
    }

    public void Shutdown() {
        DoShutdown();
    }

    public void setConfigurationFilename(String aFilename) {
        FConfigurationFilename = aFilename;
    }

    public String getConfigurationFilename() {
        return FConfigurationFilename;
    }

    public Boolean getConsoleShowLogEntrySource() {
        return FConsoleShowLogEntrySource;
    }

    @Override
    public void Invoke(NGObjectRequestItem aRequest) {
        DoInvoke(aRequest);
    }

    @Override
    public void registerObject(String aName, Object aObject) {
        NGUniplayRegisteredObjectItem item = new NGUniplayRegisteredObjectItem(aName, aObject);
        FRegisteredObjects.add(item);
    }

    @Override
    public void unregisterObject(String aName, Object aObject) {
        NGUniplayRegisteredObjectItem item = getRegisteredComponentItem(aName);
        if (item != null && item.getObject().equals(aObject)) {
            FRegisteredObjects.remove(item);
        }
    }

    @Override
    public void registerObjectRequest(String aName, Object aObject, String aMethod, String aObjectMethod) {
        NGObjectRequestObject object = getObjectRequestBroker().registerObject(aName, aObject);
        object.addMethod(aMethod, aObjectMethod);
    }

    @Override
    public void addLogListener(NGLogEventListener aLogListener) {
        FLogListener.add(aLogListener);
    }

    @Override
    public void removeLogListener(NGLogEventListener aLogListener) {
        FLogListener.remove(aLogListener);
    }

    @Override
    public void clearLog() {
        FLogManager.clearLog();
    }

    @Override
    public String getCompleteLog() {
        return FLogManager.getCompleteLog();
    }

    @Override
    public String getCompleteLog(String aFormat) {
        return FLogManager.getCompleteLog(aFormat, getConsoleShowLogEntrySource());
    }

    @Override
    public Boolean getShowLogEntrySource() {
        return getConsoleShowLogEntrySource();
    }

}
