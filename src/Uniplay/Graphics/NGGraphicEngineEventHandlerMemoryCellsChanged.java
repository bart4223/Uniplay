package Uniplay.Graphics;

import Uniplay.Kernel.NGGameEngineEventHandlerMemoryCellsChanged;

public class NGGraphicEngineEventHandlerMemoryCellsChanged extends NGGameEngineEventHandlerMemoryCellsChanged {

    protected NGGraphicEngine FGraphicEngine;

    protected void DoHandleEvent() {
        NGGraphicEngineRenderContext context = new NGGraphicEngineRenderContext(FCells);
        FGraphicEngine.Render(context);
    }

    public NGGraphicEngineEventHandlerMemoryCellsChanged(NGGraphicEngine aGraphicEngine) {
        super();
        FGraphicEngine = aGraphicEngine;
    }

}
