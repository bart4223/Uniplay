package Uniplay.Graphics;

import Uniplay.Storage.NGCustomGameObjectEventHandlerExecuted;

public class NGGraphicEngineEventHandlerGameObjectExecuted extends NGCustomGameObjectEventHandlerExecuted {

    protected NGGraphicEngine FGraphicEngine;

    @Override
    protected void DoHandleEvent() {
        super.DoHandleEvent();
        NGGraphicEngineRenderContext context = new NGGraphicEngineRenderContext(FGameObject);
        FGraphicEngine.Render(context);
    }

    public NGGraphicEngineEventHandlerGameObjectExecuted(NGGraphicEngine aGraphicEngine) {
        super();
        FGraphicEngine = aGraphicEngine;
    }

}
