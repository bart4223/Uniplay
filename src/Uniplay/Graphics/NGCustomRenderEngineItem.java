package Uniplay.Graphics;

import Uniplay.Base.NGUniplayObject;
import Uniplay.Kernel.NGGameEngineMemoryCell;
import Uniwork.Visuals.NGDisplayController;
import Uniwork.Visuals.NGDisplayView;

public abstract class NGCustomRenderEngineItem extends NGUniplayObject {

    protected NGCustomRenderEngine FRenderEngine;
    protected NGGameEngineMemoryCell FCell;
    protected Integer FLayerIndex;

    protected void DoInitialize() {
        FRenderEngine.Initialize();
    }

    protected void DoRender() {
        FRenderEngine.setCurrentController((String)FCell.getPropValue("DisplayControllerName"));
        FRenderEngine.Cell = FCell;
        FRenderEngine.Render();
    }

    public NGCustomRenderEngineItem(NGCustomRenderEngine aRenderEngine, Integer aLayerIndex) {
        super();
        FRenderEngine = aRenderEngine;
        FLayerIndex = aLayerIndex;
        FCell = null;
    }

    @Override
    public void setProperty(Object aObject, String aName, Object aValue) {
        int index = aName.indexOf(".");
        if (index >= 0) {
            String name = aName.substring(0, index);
            if (name.equals("RenderEngine")) {
                FRenderEngine.setProperty(FRenderEngine, aName.substring(index + 1, aName.length()), aValue);
            }
            else
                super.setProperty(aObject, aName, aValue);
        }
        else
            super.setProperty(aObject, aName, aValue);
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

    public void setDisplayView(NGDisplayView aView) {
        FRenderEngine.setView(aView);
    }

    public NGDisplayView getDisplayView() {
        return FRenderEngine.getView();
    }

    public int getLayerIndex() {
        return FLayerIndex;
    }

    public String getName() {
        return FRenderEngine.getName();
    }

}
