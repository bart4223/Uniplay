package Uniplay.Graphics;

import Uniplay.Base.NGUniplayObjectDefinition;

public class NGGraphicEngineDefinitionEventHandlerItem extends NGUniplayObjectDefinition {

    protected String Classname = "";

    public NGGraphicEngineDefinitionEventHandlerItem() {
        super();
    }

    public void setClassname(String value) { Classname = value;}
    public String getClassname() { return Classname; }

    public Boolean Created = false;

}
