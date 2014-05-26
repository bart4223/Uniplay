package Uniplay.Graphics;

import Uniplay.Kernel.NGGameEngineModuleManager;

public class NG2DGraphicEngine extends NGGraphicEngine {

    protected NG2DRenderEngine FRenderEngine;

    @Override
    protected void DoRender(NGGraphicEngineRenderContext aContext) {

    }

    @Override
    protected void DoInitialize() {
        super.DoInitialize();
        // ToDo
        // Render Engines pro Canvas/Layer erzeugen
    }

    public NG2DGraphicEngine(NGGameEngineModuleManager aManager, String aName) {
        super(aManager, aName);
        FRenderEngine = new NG2DRenderEngine(null);
    }

    public NG2DGraphicEngineDefinition Definition;

}
