package Uniplay.Graphics;

import Uniplay.Base.NGUniplayObjectDefinition;
import Uniwork.Base.NGSerializePropertyItem;

import java.util.ArrayList;

public class NGGraphicEngineDefinitionRenderEngineItem extends NGUniplayObjectDefinition {

    protected String Name = "";
    protected String Classname = "";
    protected String Layername = "";
    protected Integer LayerIndex = 0;
    protected ArrayList<NGGraphicEngineDefinitionRenderEngineDisplayControllerItem> DisplayControllers;
    protected ArrayList<NGSerializePropertyItem> Props;

    public NGGraphicEngineDefinitionRenderEngineItem() {
        super();
    }

    public void setName(String value) { Name = value;}
    public String getName() { return Name; }
    public void setLayername(String value) { Layername = value;}
    public String getLayername() { return Layername; }
    public void setLayerIndex(Integer value) { LayerIndex = value;}
    public Integer getLayerIndex() { return LayerIndex; }
    public void setDisplayControllers(ArrayList<NGGraphicEngineDefinitionRenderEngineDisplayControllerItem> value) { DisplayControllers = value;}
    public ArrayList<NGGraphicEngineDefinitionRenderEngineDisplayControllerItem> getDisplayControllers() { return DisplayControllers; }
    public void setClassname(String value) { Classname = value;}
    public String getClassname() { return Classname; }
    public void setProps(ArrayList<NGSerializePropertyItem> value) { Props = value;}
    public ArrayList<NGSerializePropertyItem> getProps() { return Props; }

}
