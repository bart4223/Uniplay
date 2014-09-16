package Uniplay.Graphics;

import Uniwork.Visuals.NGDisplayController;

import java.util.ArrayList;

public interface NGRenderEngineInformation {

    public Boolean IsRenderEngineResponsible(NGCustomRenderEngineItem aRenderEngine);
    public String getResponsibleDisplayControllerName(NGCustomRenderEngineItem aRenderEngine);
    public ArrayList<NGDisplayControllerLayerProp> getDisplayControllerLayerProps(String aRenderEngine, NGDisplayController aDisplayController);
    public Integer getValueForDisplayController(String aRenderEngine, NGDisplayController aDisplayController);

}
