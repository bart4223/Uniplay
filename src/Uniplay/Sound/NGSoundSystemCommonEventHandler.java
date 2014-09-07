package Uniplay.Sound;

import Uniplay.Kernel.NGGameEngineEventHandler;

public class NGSoundSystemCommonEventHandler extends NGGameEngineEventHandler {

    protected NGSoundManager FSoundManager;

    protected void DoHandleEvent() {
        FSoundManager.PlayOrStopSoundOnEvent(FEventName);
    }

    public NGSoundSystemCommonEventHandler(NGSoundManager aSoundManager) {
        super();
        FSoundManager = aSoundManager;
    }

    public NGSoundManager getSoundManager() {
        return FSoundManager;
    }

}
