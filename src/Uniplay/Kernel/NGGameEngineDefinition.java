package Uniplay.Kernel;

import Uniplay.Base.NGUniplayComponentDefinition;

import java.util.concurrent.CopyOnWriteArrayList;

public class NGGameEngineDefinition extends NGUniplayComponentDefinition {

    protected CopyOnWriteArrayList<NGGameEngineDefinitionModuleItem> Modules;

    public NGGameEngineDefinition() {
        super();
    }

    public void setModules(CopyOnWriteArrayList<NGGameEngineDefinitionModuleItem> value) { Modules = value;}
    public CopyOnWriteArrayList<NGGameEngineDefinitionModuleItem> getModules() { return Modules; }

}
