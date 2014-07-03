package Uniplay.Kernel;

import Uniplay.Base.NGUniplayComponent;
import Uniplay.Base.NGUniplayObjectRegistration;
import Uniplay.NGGameEngine;
import Uniwork.Base.NGObject;
import Uniwork.Base.NGObjectXMLDeserializerFile;
import Uniwork.Misc.NGLogManager;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public abstract class NGGameEngineModule extends NGUniplayComponent implements NGGameEngineEventHandlerRegistration, NGUniplayObjectRegistration {

    protected NGGameEngineModuleManager FManager;
    protected String FCaption;
    protected Properties FConfiguration;
    protected String FConfigurationFilename;
    protected String FDefinitionFilename;

    @Override
    protected void BeforeInitialize() {
        writeLog(String.format("Start module [%s] initialization...", FName));
        super.BeforeInitialize();
    }

    @Override
    protected void AfterInitialize() {
        super.AfterInitialize();
        writeLog(String.format("Module [%s] initialized!", FName));
    }

    @Override
    protected void BeforeFinalize() {
        writeLog(String.format("Start module [%s] shutdown...", FName));
        super.BeforeFinalize();
    }

    @Override
    protected void AfterFinalize() {
        super.AfterFinalize();
        writeLog(String.format("Module [%s] stopped!", FName));
    }

    @Override
    protected Object DoResolveObject(String aName, Class aClass) {
        Object result = super.DoResolveObject(aName, aClass);
        if (result == null && FManager != null) {
            return FManager.ResolveObject(aName, aClass);
        }
        return result;
    }

    @Override
    protected void CreateSubComponents() {
        super.CreateSubComponents();
        NGUniplayComponent component = new NGGameEngineEventHandlerManager(this, getEventHandlerManagerName());
        addSubComponent(component);
    }

    @Override
    protected void LoadConfiguration() {
        super.LoadConfiguration();
        if (FConfigurationFilename.length() > 0) {
            try {
                InputStream is = new FileInputStream(getConfigurationFilename());
                FConfiguration.load(is);
                FConfigLoaded = true;
                FDefinitionFilename = getConfigurationProperty("DefinitionFilename");
            }
            catch (Exception e) {
                writeError("LoadConfiguration", e.getMessage());
            }
        }
    }

    @Override
    protected void LoadDefinition() {
        if (FDefinitionFilename.length() > 0) {
            NGObjectXMLDeserializerFile loader = new NGObjectXMLDeserializerFile(null, FDefinitionFilename);
            loader.setLogManager(getLogManager());
            loader.deserializeObject();
            FDefinition = (NGGameEngineModuleDefinition)loader.getTarget();
            FDefinitionLoaded = FDefinition != null;
            setProperty(this, "Definition", loader.getTarget());
        }
    }

    protected String getEventHandlerManagerName() {
        return String.format("EventHandler%s", FName);
    }

    protected NGGameEngineEventHandlerManager getEventHandlerManager() {
        return (NGGameEngineEventHandlerManager)getSubComponent(getEventHandlerManagerName());
    }

    public NGGameEngineModule(NGGameEngineModuleManager aManager, String aName) {
        super(aManager, aName);
        FConfiguration = new Properties();
        FManager = aManager;
        if (FManager != null) {
            FManager.addEventListener(this);
        }
        FCaption = "";
        FDefinitionFilename = "";
        FConfigurationFilename = "";
    }

    public NGGameEngineModuleManager getManager() {
        return FManager;
    }

    public void setCaption(String aCaption) {
        FCaption = aCaption;
    }

    public String getCaption() {
        return FCaption;
    }

    public void setLogManager(NGLogManager aLogManager) {
        super.setLogManager(aLogManager);
        NGUniplayComponent component = getEventHandlerManager();
        if (component != null) {
            component.setLogManager(aLogManager);
        }
    }

    public void setConfigurationFilename(String aFilename) {
        FConfigurationFilename = aFilename;
    }

    public String getConfigurationFilename() {
        return FConfigurationFilename;
    }

    public String getConfigurationProperty(String aName) {
        String res = "";
        if (FConfigLoaded) {
            res = FConfiguration.getProperty(aName);
        }
        if (res == null) {
            res = "";
        }
        return res;
    }

    @Override
    public void registerEventHandler(NGGameEngineEventHandler aHandler) {
        NGGameEngineEventHandlerManager component = (NGGameEngineEventHandlerManager)getSubComponent(getEventHandlerManagerName());
        component.addHandler(aHandler);
    }

    @Override
    public void unregisterEventHandler(NGGameEngineEventHandler aHandler) {
        NGGameEngineEventHandlerManager component = (NGGameEngineEventHandlerManager)getSubComponent(getEventHandlerManagerName());
        component.removeHandler(aHandler);
    }

    @Override
    public void registerObject(String aName, NGObject aObject) {
        NGGameEngine ge = (NGGameEngine)ResolveObject(NGGameEngine.class);
        ge.registerObject(aName, aObject);
    }

    @Override
    public void unregisterObject(String aName, NGObject aObject) {
        NGGameEngine ge = (NGGameEngine)ResolveObject(NGGameEngine.class);
        ge.unregisterObject(aName, aObject);
    }

}
