package Uniplay.Sound;

import Uniplay.Kernel.NGGameEngineEventHandler;

public class NGSoundSystemEventHandler extends NGGameEngineEventHandler {

    protected NGSoundManager FSoundManager;

    protected void DoHandleEvent() {
        FSoundManager.playSoundOnEvent(FEventName);
    }

    public NGSoundSystemEventHandler(NGSoundManager aSoundManager) {
        super();
        FSoundManager = aSoundManager;
    }

    public NGSoundManager getSoundManager() {
        return FSoundManager;
    }

}
