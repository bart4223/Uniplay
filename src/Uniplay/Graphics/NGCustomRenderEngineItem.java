package Uniplay.Graphics;

import Uniplay.Base.NGUniplayObject;

public abstract class NGCustomRenderEngineItem extends NGUniplayObject {

    protected int FLayerIndex;
    protected NGRenderEngine FRenderEngine;

    public NGCustomRenderEngineItem(NGRenderEngine aRenderEngine, Integer aLayerIndex) {
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

    public void Initialize() {
        FRenderEngine.Initialize();
    }

    public NGRenderEngine getRenderEngine() {
        return FRenderEngine;
    }

    public int getLayerIndex() {
        return FLayerIndex;
    }

}
