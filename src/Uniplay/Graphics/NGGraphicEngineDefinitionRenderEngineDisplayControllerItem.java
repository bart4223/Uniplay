package Uniplay.Graphics;

import Uniplay.Base.NGUniplayObjectDefinition;
import Uniwork.Base.NGSerializePropertyItem;

import java.util.concurrent.CopyOnWriteArrayList;

public class NGGraphicEngineDefinitionRenderEngineDisplayControllerItem extends NGUniplayObjectDefinition {

    protected String Name = "";
    protected String Classname = "";
    protected Integer Pixelsize = 1;
    protected String Imagename = "";
    protected CopyOnWriteArrayList<NGSerializePropertyItem> Props;
    protected CopyOnWriteArrayList<NGGraphicEngineDefinitionRenderEngineDisplayControllerLayerItem> Layers;

    public NGGraphicEngineDefinitionRenderEngineDisplayControllerItem() {
        super();
    }

    public void setName(String value) { Name = value;}
    public String getName() { return Name; }
    public void setClassname(String value) { Classname = value;}
    public String getClassname() { return Classname; }
    public void setPixelsize(Integer value) { Pixelsize = value;}
    public Integer getPixelsize() { return Pixelsize; }
    public void setImagename(String value) { Imagename = value;}
    public String getImagename() { return Imagename; }
    public void setProps(CopyOnWriteArrayList<NGSerializePropertyItem> value) { Props = value;}
    public CopyOnWriteArrayList<NGSerializePropertyItem> getProps() { return Props; }
    public void setLayers(CopyOnWriteArrayList<NGGraphicEngineDefinitionRenderEngineDisplayControllerLayerItem> value) { Layers = value;}
    public CopyOnWriteArrayList<NGGraphicEngineDefinitionRenderEngineDisplayControllerLayerItem> getLayers() { return Layers; }

}
