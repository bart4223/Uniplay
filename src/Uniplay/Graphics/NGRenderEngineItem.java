package Uniplay.Graphics;

import Uniplay.Base.NGUniplayObject;

public class NGRenderEngineItem extends NGUniplayObject {

    protected int FLayerIndex;
    protected NG2DRenderEngine FRenderEngine;

    public NGRenderEngineItem(NG2DRenderEngine aRenderEngine, int aLayerIndex) {
        super();
        FRenderEngine = aRenderEngine;
        FLayerIndex = aLayerIndex;
    }

    public int getLayerIndex() {
        return FLayerIndex;
    }

    public NG2DRenderEngine getRenderEngine() {
        return FRenderEngine;
    }

}
