package Uniplay.Graphics;

import Uniplay.Base.NGUniplayObjectDefinition;
import Uniwork.Base.NGSerializePropertyItem;

import java.util.concurrent.CopyOnWriteArrayList;

public class NGGraphicEngineDefinitionRenderEngineItem extends NGUniplayObjectDefinition {

    protected String Name = "";
    protected String Classname = "";
    protected String Layername = "";
    protected CopyOnWriteArrayList<NGGraphicEngineDefinitionRenderEngineDisplayControllerItem> DisplayControllers;
    protected CopyOnWriteArrayList<NGSerializePropertyItem> Props;

    public NGGraphicEngineDefinitionRenderEngineItem() {
        super();
    }

    public void setName(String value) { Name = value;}
    public String getName() { return Name; }
    public void setLayername(String value) { Layername = value;}
    public String getLayername() { return Layername; }
    public void setDisplayControllers(CopyOnWriteArrayList<NGGraphicEngineDefinitionRenderEngineDisplayControllerItem> value) { DisplayControllers = value;}
    public CopyOnWriteArrayList<NGGraphicEngineDefinitionRenderEngineDisplayControllerItem> getDisplayControllers() { return DisplayControllers; }
    public void setClassname(String value) { Classname = value;}
    public String getClassname() { return Classname; }
    public void setProps(CopyOnWriteArrayList<NGSerializePropertyItem> value) { Props = value;}
    public CopyOnWriteArrayList<NGSerializePropertyItem> getProps() { return Props; }

}
