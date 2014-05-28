package Uniplay.Graphics;

import Uniplay.Base.NGUniplayObject;

public class NGRenderEngineItem extends NGUniplayObject {

    protected int FLayerIndex;
    protected NGRenderEngine FRenderEngine;

    public NGRenderEngineItem(NGRenderEngine aRenderEngine, int aLayerIndex) {
        super();
        FRenderEngine = aRenderEngine;
        FLayerIndex = aLayerIndex;
    }

    public int getLayerIndex() {
        return FLayerIndex;
    }

    public NGRenderEngine getRenderEngine() {
        return FRenderEngine;
    }

}
