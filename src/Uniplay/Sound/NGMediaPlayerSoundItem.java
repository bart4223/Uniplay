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
                        removeFromManager();
                        break;
                    case repetitive :
                        play();
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

    public void play() {
        play(0.0);
    }

    public void play(double aStartTime) {
        FMediaPlayer.setStartTime(new Duration(aStartTime));
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

}
