package Uniplay;

import Uniplay.Base.NGUniplayComponent;
import Uniplay.Base.NGUniplayObjectRegistration;
import Uniplay.Base.NGUniplayObject;
import Uniplay.Base.NGUniplayRegisteredObjectItem;
import Uniplay.Kernel.*;
import Uniwork.Base.*;
import Uniwork.Misc.NGLogEvent;
import Uniwork.Misc.NGLogEventListener;
import Uniwork.Misc.NGLogManager;
import Uniwork.Misc.NGTickGenerator;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;

public final class NGGameEngine extends NGUniplayComponent implements NGLogEventListener, NGUniplayObjectRegistration, NGObjectRequestRegistration {

    protected NGTickGenerator FTickGenerator;
    protected NGObjectRequestBroker FObjectRequestBroker;
    protected ArrayList<NGUniplayRegisteredObjectItem> FRegisteredObjects;
    protected Properties FConfiguration;
    protected NGGameEngineDefinition FDefinition;
    protected String FConfigurationFilename = "";
    protected String FDefinitionFilename = "";
    protected Boolean FRunning = false;
    protected Boolean FConsoleShowLogEntrySource = false;
    protected Boolean FConsoleShowLog = true;
    protected ArrayList<NGLogEventListener> FLogListerener;

    protected void DoRun() {
        writeLog("Uniplay engine is running...");
    }

    protected void DoStop() {
        FTickGenerator.SetAllEnabled(false);
        writeLog("Uniplay engine is on hold...");
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
                writeLog(e.getMessage());
            }
        }
    }

    protected void CreateModules() {
        writeLog("Start modules creation...");
        DoCreateModules();
        writeLog("All modules created!");
    }

    @Override
    protected Boolean LoadConfiguration() {
        super.LoadConfiguration();
        Boolean result = FConfigurationFilename.length() > 0;
        if (result) {
            try {
                InputStream is = new FileInputStream(FConfigurationFilename);
                FConfiguration.load(is);
                FDefinitionFilename = FConfiguration.getProperty("DefinitionFilename");
                FConsoleShowLogEntrySource = Boolean.valueOf(FConfiguration.getProperty("ConsoleShowLogEntrySource"));
                FConsoleShowLog = Boolean.valueOf(FConfiguration.getProperty("ConsoleShowLog"));
                FLogManager.setLogLevel(Integer.parseInt(FConfiguration.getProperty("Debuglevel")));
            }
            catch ( Exception e) {
                result = false;
                writeLog(e.getMessage());
            }
        }
        writeLog("Welcome to Uniplay engine...");
        return result;
    }

    @Override
    protected Boolean LoadDefinition() {
        Boolean result = FDefinitionFilename.length() > 0;
        if (result) {
            NGObjectXMLDeserializerFile loader = new NGObjectXMLDeserializerFile(null, FDefinitionFilename);
            loader.deserializeObject();
            FDefinition = (NGGameEngineDefinition)loader.getTarget();
            result = FDefinition != null;
        }
        return result;
    }

    @Override
    protected void BeforeInitialize() {
        super.BeforeInitialize();
        NGUniplayComponent manager = getMemoryManager();
        manager.addEventListener(this);
        FTickGenerator.setLogManager(FLogManager);
    }

    @Override
    protected void DoInitialize() {
        writeLog("Start Uniplay engine initialization...");
        super.DoInitialize();
        FTickGenerator.Initialize();
    }

    @Override
    protected void AfterInitialize() {
        super.AfterInitialize();
        writeLog("Uniplay engine initialized!");
    }

    @Override
    protected void DoFinalize() {
        writeLog("Start Uniplay engine shutdown...");
        FTickGenerator.Finalize();
        super.DoFinalize();
    }

    @Override
    protected void AfterFinalize() {
        super.AfterFinalize();
        writeLog("Uniplay engine stopped!");
        writeLog("Bye Bye...");
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
        Object result = null;
        if (aName.length() == 0) {
            for (NGUniplayRegisteredObjectItem item : FRegisteredObjects) {
                if (aClass.isAssignableFrom(item.getObject().getClass())) {
                    return item.getObject();
                }
            }
        }
        else {
            NGObject object = getRegisteredObject(aName);
            if (object != null && aClass.isAssignableFrom(object.getClass())) {
                return object;
            }
        }
        return super.DoResolveObject(aName, aClass);
    }

    protected NGObject getRegisteredObject(String aName) {
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

    protected NGUniplayRegisteredObjectItem getRegisteredComponentItem(String aName) {
        for (NGUniplayRegisteredObjectItem item : FRegisteredObjects) {
            if (item.getName().equals(aName)) {
                return item;
            }
        }
        return null;
    }

    protected void DoInvoke(NGObjectRequestItem aRequest) {
        FObjectRequestBroker.Invoke(aRequest);
    }

    public NGGameEngine(NGUniplayObject aOwner) {
        super(aOwner, NGGameEngineConstants.CMP_KERNEL);
        FRegisteredObjects = new ArrayList<NGUniplayRegisteredObjectItem>();
        FLogListerener = new ArrayList<NGLogEventListener>();
        FLogManager = new NGLogManager();
        FLogManager.addEventListener(this);
        FConfiguration = new Properties();
        FTickGenerator = new NGTickGenerator(10);
        FTickGenerator.setLogManager(FLogManager);
        registerObject(NGGameEngineConstants.OBJ_TICKGENERATOR, FTickGenerator);
        FObjectRequestBroker = new NGObjectRequestBroker(this);
        FObjectRequestBroker.setLogManager(FLogManager);
        registerObject(NGGameEngineConstants.OBJ_OBJECTREQUESTBROKER, FObjectRequestBroker);
    }

    @Override
    public void handleAddLog(NGLogEvent e) {
        if (FConsoleShowLog) {
            System.out.println(e.LogEntry.GetFullAsString("YYYY/MM/dd HH:mm:ss", FConsoleShowLogEntrySource));
        }
        for (NGLogEventListener listener : FLogListerener) {
            listener.handleAddLog(e);
        }
    }

    @Override
    public void handleClearLog() {

    }

    public Boolean getRunning() {
        return FRunning;
    }

    public void Run() {
        if (!FRunning) {
            DoRun();
            FRunning = true;
        }
    }

    public void Stop() {
        if (FRunning) {
            DoStop();
            FRunning = false;
        }
    }

    public void Startup() {
        Initialize();
        Run();
    }

    public void Shutdown() {
        Stop();
        Finalize();
    }

    public void addLogListener(NGLogEventListener aLogListener) {
        FLogListerener.add(aLogListener);
    }

    public void removeLogListener(NGLogEventListener aLogListener) {
        FLogListerener.remove(aLogListener);
    }

    public void setConfigurationFilename(String aFilename) {
        FConfigurationFilename = aFilename;
    }

    public String getConfigurationFilename() {
        return FConfigurationFilename;
    }

    public void Invoke(NGObjectRequestItem aRequest) {
        DoInvoke(aRequest);
    }

    @Override
    public void registerObject(String aName, NGObject aObject) {
        NGUniplayRegisteredObjectItem item = new NGUniplayRegisteredObjectItem(aName, aObject);
        FRegisteredObjects.add(item);
    }

    @Override
    public void unregisterObject(String aName, NGObject aObject) {
        NGUniplayRegisteredObjectItem item = getRegisteredComponentItem(aName);
        if (item != null && item.getObject().equals(aObject)) {
            FRegisteredObjects.remove(item);
        }
    }

    @Override
    public void registerObjectRequest(String aName, Object aObject, String aMethod, String aObjectMethod) {
        NGObjectRequestObject object = FObjectRequestBroker.addObject(aName, aObject);
        object.addMethod(aMethod, aObjectMethod);
    }

}
