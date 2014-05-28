package Uniplay.Graphics;

import Uniplay.Kernel.NGGameEngineModule;
import Uniplay.Kernel.NGGameEngineModuleManager;

public abstract class NGGraphicEngine extends NGGameEngineModule {

    protected void DoRender(NGGraphicEngineRenderContext aContext) {

    }

    @Override
    protected void BeforeInitialize() {
        super.BeforeInitialize();
        registerEventHandler(new NGGraphicEngineEventHandlerMemoryCellsChanged(this));
    }

    public NGGraphicEngine(NGGameEngineModuleManager aManager, String aName) {
        super(aManager, aName);
    }

    public void Render(NGGraphicEngineRenderContext aContext) {
        DoRender(aContext);
    }

}
