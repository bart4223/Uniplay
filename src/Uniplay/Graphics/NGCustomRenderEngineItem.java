package Uniplay.Graphics;

import Uniplay.Base.NGUniplayObject;
import Uniplay.Kernel.NGGameEngineMemoryCell;
import Uniplay.NGGameEngineConstants;
import Uniwork.Visuals.NGDisplayController;
import Uniwork.Visuals.NGDisplayView;

import java.util.ArrayList;

public abstract class NGCustomRenderEngineItem extends NGUniplayObject {

    protected NGCustomRenderEngine FRenderEngine;
    protected NGGameEngineMemoryCell FCell;

    protected void DoInitialize() {
        FRenderEngine.Initialize();
    }

    protected void DoRender() {
        FRenderEngine.setCurrentController((String)FCell.getProperty(FCell, NGGameEngineConstants.PROP_GRAPHIC_DISPLAYCONTROLLER_NAME));
        FRenderEngine.Cell = FCell;
        FRenderEngine.Render();
    }

    public NGCustomRenderEngineItem(NGCustomRenderEngine aRenderEngine) {
        super();
        FRenderEngine = aRenderEngine;
        FCell = null;
    }

    @Override
    public Boolean setProperty(Object aObject, String aName, Object aValue) {
        Boolean res;
        Integer index = aName.indexOf(".");
        if (index >= 0) {
            String name = aName.substring(0, index);
            if (name.equals("RenderEngine")) {
                res = FRenderEngine.setProperty(FRenderEngine, aName.substring(index + 1, aName.length()), aValue);
            }
            else {
                res = super.setProperty(aObject, aName, aValue);
            }
        }
        else {
            res = super.setProperty(aObject, aName, aValue);
        }
        return res;
    }

    @Override
    public Object getProperty(Object aObject, String aName) {
        int index = aName.indexOf(".");
        if (index >= 0) {
            String name = aName.substring(0, index);
            if (name.equals("RenderEngine")) {
                return FRenderEngine.getProperty(FRenderEngine, aName.substring(index + 1, aName.length()));
            }
            else
                return super.getProperty(aObject, aName);
        }
        else
            return super.getProperty(aObject, aName);
    }

    public void Initialize() {
        DoInitialize();
    }

    public void Render() {
        DoRender();
    }

    public void setCell(NGGameEngineMemoryCell aCell) {
        FCell = aCell;
    }

    public NGGameEngineMemoryCell getCell() {
        return FCell;
    }

    public void addDisplayController(NGDisplayController aDisplayController) {
        FRenderEngine.addController(aDisplayController);
    }

    public NGDisplayController getDisplayController(String aName) {
        return FRenderEngine.getController(aName);
    }

    public NGDisplayController getCurrentDisplayControllers() {
        return FRenderEngine.getCurrentController();
    }

    public ArrayList<NGDisplayController> getDisplayControllers() {
        return FRenderEngine.getControllers();
    }

    public void setDisplayView(NGDisplayView aView) {
        FRenderEngine.setView(aView);
    }

    public NGDisplayView getDisplayView() {
        return FRenderEngine.getView();
    }

    public String getName() {
        return FRenderEngine.getName();
    }

}
