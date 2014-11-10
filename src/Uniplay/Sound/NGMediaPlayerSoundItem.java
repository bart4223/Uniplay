package Uniplay.Sound;

import Uniplay.Base.NGUniplayObject;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

public class NGMediaPlayerSoundItem extends NGUniplayObject {

    public enum Mode {singular, several, repetitive};

    protected NGSoundItem FSoundItem;
    protected MediaPlayer FMediaPlayer;
    protected Mode FMode;
    protected NGSoundManager FManager;

    protected void DoPlay(double aStartTime, double aEndTime) {
        FMediaPlayer.setStartTime(new Duration(aStartTime));
        if (aEndTime > 0.0) {
            FMediaPlayer.setStopTime(new Duration(aEndTime));
        }
        FMediaPlayer.play();
    }

    protected void DoStop() {
        FMediaPlayer.stop();
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
                        stop();
                        removeFromManager();
                        break;
                    case several:
                        stop();
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

    public void play(double aStartTime) {
        play(aStartTime, 0.0);
    }

    public void play(double aStartTime, double aEndTime) {
        DoPlay(aStartTime, aEndTime);
    }

    public Mode getMode() {
        return FMode;
    }

    public void stop() {
        DoStop();
    }

    public MediaPlayer.Status getStatus() {
        return FMediaPlayer.getStatus();
    }

    public Boolean stopOnEvent(String aEventname) {
        return FSoundItem.stopOnEvent(aEventname);
    }

}
