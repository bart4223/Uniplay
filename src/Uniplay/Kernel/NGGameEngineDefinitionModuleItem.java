package Uniplay.Kernel;

import Uniplay.Base.NGUniplayObjectDefinition;

public class NGGameEngineDefinitionModuleItem extends NGUniplayObjectDefinition {

    protected String Name = "";
    protected String Caption = "";
    protected String Classname = "";
    protected String ConfigurationFilename = "";

    public NGGameEngineDefinitionModuleItem() {
        super();
    }

    public void setName(String value) { Name = value;}
    public String getName() { return Name; }
    public void setCaption(String value) { Caption = value;}
    public String getCaption() { return Name; }
    public void setClassname(String value) { Classname = value;}
    public String getClassname() { return Classname; }
    public void setConfigurationFilename(String value) { ConfigurationFilename = value;}
    public String getConfigurationFilename() { return ConfigurationFilename; }

}
