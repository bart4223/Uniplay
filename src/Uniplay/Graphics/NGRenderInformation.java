package Uniplay.Graphics;

import Uniwork.Base.NGPropertyItem;
import Uniwork.Visuals.NGDisplayController;

import java.util.ArrayList;

public interface NGRenderInformation {

    public Boolean IsRenderEngineResponsible(NGCustomRenderEngineItem aRenderEngine);
    public String getResponsibleDisplayControllerName(NGCustomRenderEngineItem aRenderEngine);
    public ArrayList<NGPropertyItem> getDisplayControllerProps(String aRenderEngine, NGDisplayController aDisplayController);
    public ArrayList<NGPropertyItem> getDisplayControllerLayerProps(String aRenderEngine, NGDisplayController aDisplayController);
    public Integer getValueForDisplayController(String aRenderEngine, NGDisplayController aDisplayController);

}
