package Uniplay.Sound;

import Uniplay.Base.NGUniplayComponent;
import Uniplay.Base.NGUniplayObject;
import javafx.scene.media.MediaPlayer;

import java.util.ArrayList;

public class NGSoundManager extends NGUniplayComponent {

    protected ArrayList<NGSoundItem> FItems;
    protected ArrayList<NGMediaPlayerSoundItem> FMediaPlayerItems;

    protected NGSoundItem getSoundItem(String aName) {
        for (NGSoundItem item : FItems) {
            if (item.getName().equals(aName)) {
                return item;
            }
        }
        return null;
    }

    protected NGMediaPlayerSoundItem getMediaPlayerSoundItem(String aName) {
        for (NGMediaPlayerSoundItem item : FMediaPlayerItems) {
            if (item.getSoundItem().getName().equals(aName) ) {
                return item;
            }
        }
        return null;
    }

    protected void addMediaPlayerItem(NGMediaPlayerSoundItem aItem, double aStartTime, double aEndTime) {
        FMediaPlayerItems.add(aItem);
        aItem.play(aStartTime, aEndTime);
        writeLog(String.format("Sound [%s] play with media player...", aItem.getSoundItem().getName()));
    }

    protected void removeMediaPlayerItem(NGMediaPlayerSoundItem aItem) {
        FMediaPlayerItems.remove(aItem);
    }

    protected void stopSound(NGMediaPlayerSoundItem aItem) {
        if (aItem.getStatus() == MediaPlayer.Status.PLAYING) {
            aItem.stop();
            writeLog(String.format("Sound [%s] stop playing!", aItem.getSoundItem().getName()));
        }
    }

    public NGSoundManager(NGUniplayObject aOwner, String aName) {
        super(aOwner, aName);
        FItems = new ArrayList<NGSoundItem>();
        FMediaPlayerItems = new ArrayList<NGMediaPlayerSoundItem>();
    }

    public NGSoundItem addSound(String aName, String aFileName) {
        NGSoundItem item = new NGSoundItem(aName, aFileName);
        FItems.add(item);
        writeLog(String.format("Sound [%s] added.", item.getName()));
        return item;
    }

    public void playSound(String aName) {
        playSound(aName, 0.0, 0.0);
    }

    public void playSound(String aName, double aStartTime, double aEndTime) {
        playSound(aName, NGMediaPlayerSoundItem.Mode.singular, aStartTime, aEndTime);
    }

    public void playSound(String aName, NGMediaPlayerSoundItem.Mode aMode, double aStartTime, double aEndTime) {
        NGSoundItem item = getSoundItem(aName);
        playSound(item, aMode, aStartTime, aEndTime);
    }

    public void playSound(NGSoundItem aItem, NGMediaPlayerSoundItem.Mode aMode) {
        playSound(aItem, aMode, 0.0, 0.0);
    }

    public void playSound(NGSoundItem aItem, NGMediaPlayerSoundItem.Mode aMode, double aStartTime, double aEndTime) {
        NGMediaPlayerSoundItem sounditem = new NGMediaPlayerSoundItem(this, aItem, aMode);
        addMediaPlayerItem(sounditem, aStartTime, aEndTime);
    }

    public void stopSound(String aName) {
        for (NGMediaPlayerSoundItem item : FMediaPlayerItems) {
            if (item.getSoundItem().getName().equals(aName)) {
                stopSound(item);
            }
        }
    }

    public void stopAllSounds() {
        for (NGMediaPlayerSoundItem item : FMediaPlayerItems) {
            stopSound(item);
        }
    }

    public void PlayOrStopSoundOnEvent(String aEventName) {
        for (NGSoundItem item : FItems) {
            if (item.playOnEvent(aEventName)) {
                playSound(item, NGMediaPlayerSoundItem.Mode.singular);
            }
        }
        for (NGMediaPlayerSoundItem item : FMediaPlayerItems) {
            if (item.stopOnEvent(aEventName)) {
                stopSound(item);
            }
        }
    }

    public Boolean IsPlaySound(String aName) {
        return getMediaPlayerSoundItem(aName) != null;
    }

    public Integer GetSoundsPlayed() {
        return FMediaPlayerItems.size();
    }

}
