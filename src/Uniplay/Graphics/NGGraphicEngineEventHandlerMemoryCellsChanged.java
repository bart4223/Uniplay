package Uniplay.Graphics;

import Uniplay.NGGameEngineConstants;
import Uniplay.Kernel.NGGameEngineEventHandlerMemoryCellsChanged;
import Uniwork.Misc.NGStrings;

public class NGGraphicEngineEventHandlerMemoryCellsChanged extends NGGameEngineEventHandlerMemoryCellsChanged {

    protected NGGraphicEngine FGraphicEngine;

    @Override
    protected void DoHandleEvent() {
        super.DoHandleEvent();
        String name = FMemory.getName();
        if (name.contains(".")) {
            name = NGStrings.getStringPos(name, "\\.", 2);
        }
        if (name.equals(NGGameEngineConstants.CMP_MAIN_MEMORY)) {
            NGGraphicEngineRenderContext context = new NGGraphicEngineRenderContext(FMemory, FCells);
            FGraphicEngine.Render(context);
        }
    }

    public NGGraphicEngineEventHandlerMemoryCellsChanged(NGGraphicEngine aGraphicEngine) {
        super();
        FGraphicEngine = aGraphicEngine;
    }

}
