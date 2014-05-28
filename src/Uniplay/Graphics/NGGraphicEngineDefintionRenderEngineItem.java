package Uniplay.Graphics;

import Uniplay.Base.NGUniplayObjectDefinition;

import java.util.ArrayList;

public class NGGraphicEngineDefintionRenderEngineItem extends NGUniplayObjectDefinition {

    protected String Name = "";
    protected String Classname = "";
    protected String Layername = "";
    protected Integer LayerIndex = 0;
    protected String ValuePropName = "";
    protected ArrayList<NGGraphicEngineDefintionRenderEngineDisplayControllerItem> DisplayControllers;

    public NGGraphicEngineDefintionRenderEngineItem() {
        super();
    }

    public void setName(String value) { Name = value;}
    public String getName() { return Name; }
    public void setLayername(String value) { Layername = value;}
    public String getLayername() { return Layername; }
    public void setLayerIndex(Integer value) { LayerIndex = value;}
    public Integer getLayerIndex() { return LayerIndex; }
    public void setDisplayControllers(ArrayList<NGGraphicEngineDefintionRenderEngineDisplayControllerItem> value) { DisplayControllers = value;}
    public ArrayList<NGGraphicEngineDefintionRenderEngineDisplayControllerItem> getDisplayControllers() { return DisplayControllers; }
    public void setClassname(String value) { Classname = value;}
    public String getClassname() { return Classname; }
    public void setValuepropname(String value) { ValuePropName = value;}
    public String getValuepropname() { return ValuePropName; }

}
