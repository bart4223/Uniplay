package Uniplay.Graphics;

import Uniplay.Kernel.NGGameEngineModuleManager;

public class NG2DGraphicEngine extends NGGraphicEngine {

    @Override
    protected void registerEventHandlers() {
        super.registerEventHandlers();
        registerEventHandler(new NG2DGraphicEngineEventHandlerGamePlayerPositionChanged(this));
    }

    public NG2DGraphicEngine(NGGameEngineModuleManager aManager, String aName) {
        super(aManager, aName);
    }

    public void setViewPositions(double aX, double aY) {
        for (NGCustomRenderEngineItem item : getRenderEngineManager().getItems()) {
            if (item instanceof NG2DRenderEngineItem) {
                NG2DRenderEngineItem reitem = (NG2DRenderEngineItem)item;
                reitem.setViewPosition(aX, aY);
            }
        }
    }

}
