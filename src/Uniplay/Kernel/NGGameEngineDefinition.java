package Uniplay.Kernel;

import Uniplay.Base.NGUniplayComponentDefinition;

import java.util.ArrayList;

public class NGGameEngineDefinition extends NGUniplayComponentDefinition {

    protected ArrayList<NGGameEngineDefinitionModuleItem> Modules;

    public NGGameEngineDefinition() {
        super();
    }

    public void setModules(ArrayList<NGGameEngineDefinitionModuleItem> value) { Modules = value;}
    public ArrayList<NGGameEngineDefinitionModuleItem> getModules() { return Modules; }

}
