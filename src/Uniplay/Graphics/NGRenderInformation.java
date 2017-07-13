package Uniplay.Graphics;

import Uniwork.Base.NGPropertyItem;
import Uniwork.Visuals.NGDisplayController;

import java.util.concurrent.CopyOnWriteArrayList;

public interface NGRenderInformation {

    public Boolean IsRenderEngineResponsible(NGCustomRenderEngineItem aRenderEngine);
    public String getResponsibleDisplayControllerName(NGCustomRenderEngineItem aRenderEngine);
    public CopyOnWriteArrayList<NGPropertyItem> getDisplayControllerProps(String aRenderEngine, NGDisplayController aDisplayController);
    public CopyOnWriteArrayList<NGPropertyItem> getDisplayControllerLayerProps(String aRenderEngine, NGDisplayController aDisplayController);
    public Integer getValueForDisplayController(String aRenderEngine, NGDisplayController aDisplayController);

}
