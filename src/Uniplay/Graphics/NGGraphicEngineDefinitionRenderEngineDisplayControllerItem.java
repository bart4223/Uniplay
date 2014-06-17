package Uniplay.Graphics;

import Uniplay.Base.NGUniplayObjectDefinition;
import Uniwork.Base.NGSerializePropertyItem;

import java.util.ArrayList;

public class NGGraphicEngineDefinitionRenderEngineDisplayControllerItem extends NGUniplayObjectDefinition {

    protected String Name = "";
    protected String Classname = "";
    protected Integer Pixelsize = 1;
    protected String Imagename = "";
    protected ArrayList<NGSerializePropertyItem> Props;

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
    public void setProps(ArrayList<NGSerializePropertyItem> value) { Props = value;}
    public ArrayList<NGSerializePropertyItem> getProps() { return Props; }

}
