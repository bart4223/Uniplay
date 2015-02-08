package Uniplay.Storage;

import Uniplay.Kernel.NGGameEngineEventHandler;
import Uniplay.NGGameEngineConstants;

public class NGCustomGameObjectEventHandlerExecuted extends NGGameEngineEventHandler {

    protected NGCustomGameObject FGameObject;

    @Override
    protected void BeforeHandleEvent() {
        super.BeforeHandleEvent();
        NGCustomGameObjectEvent event = (NGCustomGameObjectEvent)FEvent;
        FGameObject = event.getGameObject();
    }

    public NGCustomGameObjectEventHandlerExecuted() {
        super(NGGameEngineConstants.EVT_GAME_OBJECT_EXECUTED);
    }

}
