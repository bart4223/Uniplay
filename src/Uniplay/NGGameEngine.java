package Uniplay;

import Uniplay.Base.NGUniplayComponent;
import Uniplay.Base.NGUniplayComponentRegistration;
import Uniplay.Base.NGUniplayObject;
import Uniplay.Base.NGUniplayRegisteredComponentItem;
import Uniplay.Kernel.*;
import Uniwork.Base.NGObjectXMLDeserializerFile;
import Uniwork.Base.NGObjectXMLSerializer;
import Uniwork.Base.NGObjectXMLSerializerFile;
import Uniwork.Misc.NGLogEvent;
import Uniwork.Misc.NGLogEventListener;
import Uniwork.Misc.NGLogManager;
import Uniwork.Misc.NGTickGenerator;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;

public final class NGGameEngine extends NGUniplayComponent implements NGLogEventListener, NGUniplayComponentRegistration {

    public final static String CMP_KERNEL         = "Kernel";
    public final static String CMP_MEMORY_MANAGER = "MemoryManager";
    public final static String CMP_MODULE_MANAGER = "ModuleManager";

    protected NGTickGenerator FTickGenerator;
    protected ArrayList<NGUniplayRegisteredComponentItem> FRegisteredComponents;
    protected Properties FConfiguration;
    protected NGGameEngineDefinition FDefinition;
    protected String FConfigurationFilename = "";
    protected String FDefinitionFilename = "";
    protected Boolean FRunning = false;
    protected Boolean FConsoleShowLogEntrySource = false;
    protected Boolean FConsoleShowLog = true;

    protected void DoRun() {
        FTickGenerator.SetAllEnabled(true);
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
                manager.newModule(cl, item.getName());
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

    protected void DoLoadModules() {
        NGGameEngineModuleManager component = (NGGameEngineModuleManager)getSubComponent(CMP_MODULE_MANAGER);
        component.LoadModules();
    }

    protected void LoadModules() {
        writeLog("Start all modules loading...");
        DoLoadModules();
        writeLog("All modules loaded!");
    }

    @Override
    protected void LoadConfiguration() {
        super.LoadConfiguration();
        if (FConfigurationFilename.length() > 0) {
            try {
                InputStream is = new FileInputStream(FConfigurationFilename);
                FConfiguration.load(is);
                FDefinitionFilename = FConfiguration.getProperty("DefinitionFilename");
                FConsoleShowLogEntrySource = Boolean.valueOf(FConfiguration.getProperty("ConsoleShowLogEntrySource"));
                FConsoleShowLog = Boolean.valueOf(FConfiguration.getProperty("ConsoleShowLog"));
            }
            catch ( Exception e) {
                writeLog(e.getMessage());
            }
        }
    }

    @Override
    protected void LoadDefinition() {
        /*
        FDefinition = new NGGameEngineDefinition();
        NGGameEngineDefinitionModuleItem item = new NGGameEngineDefinitionModuleItem();
        item.setName("2DGraphicEngine");
        item.setClassname("Uniplay.Graphics.NG2DGraphicEngine");
        item.setConfigurationFilename("resources/modules/2DGraphicEngine/config.ucf");
        FDefinition.setModules(new ArrayList<NGGameEngineDefinitionModuleItem>());
        FDefinition.getModules().add(item);
        NGObjectXMLSerializer ser = new NGObjectXMLSerializerFile(FDefinition, null, FDefinitionFilename);
        ser.serializeObject();
        */
        NGObjectXMLDeserializerFile loader = new NGObjectXMLDeserializerFile(null, FDefinitionFilename);
        loader.deserializeObject();
        FDefinition = (NGGameEngineDefinition)loader.getTarget();
    }

    @Override
    protected void BeforeInitialize() {
        super.BeforeInitialize();
        NGUniplayComponent component = getSubComponent(CMP_MEMORY_MANAGER);
        component.addEventListener(this);
        FTickGenerator.setLogManager(FLogManager);
        LoadModules();
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
        FLogManager = new NGLogManager();
        FLogManager.setLogLevel(Integer.parseInt(FConfiguration.getProperty("Debuglevel")));
        FLogManager.addEventListener(this);
        writeLog("Welcome to Uniplay engine...");
        writeLog(String.format("Start creation of %s sub components...", CMP_KERNEL));
        NGUniplayComponent component = new NGGameEngineModuleManager(this, CMP_MODULE_MANAGER);
        addSubComponent(component);
        writeLog(String.format("%s created.", CMP_MODULE_MANAGER));
        component = new NGGameEngineMemoryManager(this, CMP_MEMORY_MANAGER);
        addSubComponent(component);
        writeLog(String.format("%s created.", CMP_MEMORY_MANAGER));
        writeLog(String.format("All %s sub components created.",CMP_KERNEL));
        CreateModules();
    }

    @Override
    protected Object DoResolveObject(String aName, Class aClass) {
        NGUniplayComponent component = getRegisteredComponent(aName);
        if (aClass.isAssignableFrom(component.getClass())) {
            return component;
        }
        return super.DoResolveObject(aName, aClass);
    }

    protected NGUniplayComponent getRegisteredComponent(String aName) {
        NGUniplayRegisteredComponentItem item = getRegisteredComponentItem(aName);
        if (item != null) {
            return item.getComponent();
        }
        return null;
    }

    protected NGGameEngineModuleManager getModuleManager() {
        return (NGGameEngineModuleManager)getSubComponent(CMP_MODULE_MANAGER);
    }

    protected NGUniplayRegisteredComponentItem getRegisteredComponentItem(String aName) {
        for (NGUniplayRegisteredComponentItem item : FRegisteredComponents) {
            if (item.getName().equals(aName)) {
                return item;
            }
        }
        return null;
    }

    public NGGameEngine(NGUniplayObject aOwner) {
        super(aOwner, CMP_KERNEL);
        FRegisteredComponents = new ArrayList<NGUniplayRegisteredComponentItem>();
        FConfiguration = new Properties();
        FTickGenerator = new NGTickGenerator(10);
    }

    @Override
    public void handleAddLog(NGLogEvent e) {
        if (FConsoleShowLog) {
            System.out.println(e.LogEntry.GetFullAsString("YYYY/MM/dd HH:mm:ss", FConsoleShowLogEntrySource));
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

    @Override
    public void registerComponent(String aName, NGUniplayComponent aComponent) {
        NGUniplayRegisteredComponentItem item = new NGUniplayRegisteredComponentItem(aName, aComponent);
        FRegisteredComponents.add(item);
    }

    @Override
    public void unregisterComponent(String aName, NGUniplayComponent aComponent) {
        NGUniplayRegisteredComponentItem item = getRegisteredComponentItem(aName);
        if (item != null && item.getComponent().equals(aComponent)) {
            FRegisteredComponents.remove(item);
        }
    }

    public void setConfigurationFilename(String aFilename) {
        FConfigurationFilename = aFilename;
    }

    public String getConfigurationFilename() {
        return FConfigurationFilename;
    }

}
