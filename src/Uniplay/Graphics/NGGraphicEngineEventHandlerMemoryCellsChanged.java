package Uniplay.Graphics;

import Uniplay.Kernel.NGGameEngineConstants;
import Uniplay.Kernel.NGGameEngineEventHandlerMemoryCellsChanged;

public class NGGraphicEngineEventHandlerMemoryCellsChanged extends NGGameEngineEventHandlerMemoryCellsChanged {

    protected NGGraphicEngine FGraphicEngine;

    @Override
    protected void DoHandleEvent() {
        super.DoHandleEvent();
        if (FMemory.getName().equals(NGGameEngineConstants.CMP_MAIN_MEMORY)) {
            NGGraphicEngineRenderContext context = new NGGraphicEngineRenderContext(FMemory, FCells);
            FGraphicEngine.Render(context);
        }
    }

    public NGGraphicEngineEventHandlerMemoryCellsChanged(NGGraphicEngine aGraphicEngine) {
        super();
        FGraphicEngine = aGraphicEngine;
    }

}
