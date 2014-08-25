package Uniplay.Graphics;

import Uniwork.Visuals.NGDisplayView;

public class NG2DRenderEngineItem extends NGCustomRenderEngineItem {

    protected NGDisplayView FView;

    public NG2DRenderEngineItem(NGRenderEngine aRenderEngine, int aLayerIndex, double aWidth, double aHeight) {
        super(aRenderEngine, aLayerIndex);
        FView = new NGDisplayView(aWidth, aHeight);
        getRenderEngine().setView(FView);
    }

    public void setViewPosition(double aX, double aY) {
        FView.setPosition(aX, aY);
    }

}
