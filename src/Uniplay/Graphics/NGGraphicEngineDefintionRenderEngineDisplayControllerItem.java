package Uniplay.Graphics;

import Uniplay.Base.NGUniplayObjectDefinition;

public class NGGraphicEngineDefintionRenderEngineDisplayControllerItem extends NGUniplayObjectDefinition {

    protected String Name = "";
    protected String Classname = "";

    public NGGraphicEngineDefintionRenderEngineDisplayControllerItem() {
        super();
    }

    public void setName(String value) { Name = value;}
    public String getName() { return Name; }
    public void setClassname(String value) { Classname = value;}
    public String getClassname() { return Classname; }

}
