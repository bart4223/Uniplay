package Uniplay.Sound;

import Uniplay.Base.NGUniplayObject;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

public class NGMediaPlayerSoundItem extends NGUniplayObject {

    public enum Mode {singular, repetitive};

    protected NGSoundItem FSoundItem;
    protected MediaPlayer FMediaPlayer;
    protected Mode FMode;
    protected NGSoundManager FManager;

    protected void play(double aStartTime) {
        play(aStartTime, 0.0);
    }

    protected void removeFromManager() {
        getManager().removeMediaPlayerItem(this);
    }

    public NGMediaPlayerSoundItem(NGSoundManager aManager, NGSoundItem aSoundItem, Mode aMode) {
        super();
        FManager = aManager;
        FSoundItem = aSoundItem;
        FMediaPlayer = new MediaPlayer(aSoundItem.getMedia());
        FMode = aMode;
        FMediaPlayer.setOnEndOfMedia(new Runnable() {
            @Override
            public void run() {
                switch (FMode) {
                    case singular:
                        FMediaPlayer.stop();
                        removeFromManager();
                        break;
                    case repetitive :
                        play(0.0);
                        break;
                }
            }});
    }

    public NGSoundManager getManager() {
        return FManager;
    }

    public MediaPlayer getMediaPlayer() {
        return FMediaPlayer;
    }

    public NGSoundItem getSoundItem() {
        return FSoundItem;
    }

    public void play(double aStartTime, double aEndTime) {
        FMediaPlayer.setStartTime(new Duration(aStartTime));
        if (aEndTime > 0.0) {
            FMediaPlayer.setStopTime(new Duration(aEndTime));
        }
        FMediaPlayer.play();
    }

    public Mode getMode() {
        return FMode;
    }

    public void stop() {
        FMediaPlayer.stop();
    }

    public MediaPlayer.Status getStatus() {
        return FMediaPlayer.getStatus();
    }

    public Boolean stopOnEvent(String aEventname) {
        return FSoundItem.stopOnEvent(aEventname);
    }

}
