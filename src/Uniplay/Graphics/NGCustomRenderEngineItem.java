package Uniplay.Graphics;

import Uniplay.Base.NGUniplayObject;
import Uniplay.Kernel.NGGameEngineMemoryCell;

public abstract class NGCustomRenderEngineItem extends NGUniplayObject {

    protected int FLayerIndex;
    protected NGCustomRenderEngine FRenderEngine;

    public NGCustomRenderEngineItem(NGCustomRenderEngine aRenderEngine, Integer aLayerIndex) {
        super();
        FRenderEngine = aRenderEngine;
        FLayerIndex = aLayerIndex;
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
        FRenderEngine.Initialize();
    }

    public void Render() {
        FRenderEngine.Render();
    }

    public void setCell(NGGameEngineMemoryCell aCell) {
        FRenderEngine.Cell = aCell;
    }

    public NGCustomRenderEngine getRenderEngine() {
        return FRenderEngine;
    }

    public int getLayerIndex() {
        return FLayerIndex;
    }

}
