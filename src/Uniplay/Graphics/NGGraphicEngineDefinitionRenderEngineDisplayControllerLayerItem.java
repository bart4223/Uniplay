package Uniplay.Graphics;

import Uniplay.Base.NGUniplayObjectDefinition;

public class NGGraphicEngineDefinitionRenderEngineDisplayControllerLayerItem extends NGUniplayObjectDefinition {

    protected String Name = "";
    protected String Imagename = "";

    public NGGraphicEngineDefinitionRenderEngineDisplayControllerLayerItem() {
        super();
    }

    public void setName(String value) { Name = value;}
    public String getName() { return Name; }
    public void setImagename(String value) { Imagename = value;}
    public String getImagename() { return Imagename; }

}
