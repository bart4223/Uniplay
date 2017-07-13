package Uniplay.Sound;

import Uniplay.Base.NGUniplayComponent;
import Uniplay.Base.NGUniplayObject;
import javafx.scene.media.MediaPlayer;

import java.util.concurrent.CopyOnWriteArrayList;

public class NGSoundManager extends NGUniplayComponent {

    protected CopyOnWriteArrayList<NGSoundItem> FItems;
    protected CopyOnWriteArrayList<NGMediaPlayerSoundItem> FMediaPlayerItems;

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

    protected void DoPlaySound(NGSoundItem aItem, NGMediaPlayerSoundItem.Mode aMode, double aStartTime, double aEndTime) {
        NGMediaPlayerSoundItem sounditem;
        if (aMode == NGMediaPlayerSoundItem.Mode.several) {
            sounditem = getMediaPlayerSoundItem(aItem.getName());
            if (sounditem == null) {
                sounditem = new NGMediaPlayerSoundItem(this, aItem, aMode);
                addMediaPlayerItem(sounditem, aStartTime, aEndTime);
            }
            else {
                sounditem.play(aStartTime, aEndTime);
            }
        } else {
            sounditem = new NGMediaPlayerSoundItem(this, aItem, aMode);
            addMediaPlayerItem(sounditem, aStartTime, aEndTime);
        }
    }

    protected void removeMediaPlayerItem(NGMediaPlayerSoundItem aItem) {
        FMediaPlayerItems.remove(aItem);
    }

    protected void DoStopSound(NGMediaPlayerSoundItem aItem) {
        if (aItem.getStatus() == MediaPlayer.Status.PLAYING) {
            writeLog(String.format("Sound [%s] stop playing!", aItem.getSoundItem().getName()));
            aItem.stop();
        }
    }

    public NGSoundManager(NGUniplayObject aOwner, String aName) {
        super(aOwner, aName);
        FItems = new CopyOnWriteArrayList<NGSoundItem>();
        FMediaPlayerItems = new CopyOnWriteArrayList<NGMediaPlayerSoundItem>();
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

    public void playSound(String aName, NGMediaPlayerSoundItem.Mode aMode) {
        playSound(aName, aMode, 0.0, 0.0);
    }

    public void playSound(String aName, NGMediaPlayerSoundItem.Mode aMode, double aStartTime, double aEndTime) {
        NGSoundItem item = getSoundItem(aName);
        if (item != null)
            playSound(item, aMode, aStartTime, aEndTime);
    }

    public void playSound(NGSoundItem aItem, NGMediaPlayerSoundItem.Mode aMode) {
        playSound(aItem, aMode, 0.0, 0.0);
    }

    public void playSound(NGSoundItem aItem, NGMediaPlayerSoundItem.Mode aMode, double aStartTime, double aEndTime) {
         DoPlaySound(aItem, aMode, aStartTime, aEndTime);
    }

    public void stopSound(String aName) {
        for (NGMediaPlayerSoundItem item : FMediaPlayerItems) {
            if (item.getSoundItem().getName().equals(aName)) {
                DoStopSound(item);
            }
        }
    }

    public void stopAllSounds() {
        int i = 0;
        while (i < FMediaPlayerItems.size()) {
            NGMediaPlayerSoundItem item = FMediaPlayerItems.get(i);
            DoStopSound(item);
            if (item.getMode() == NGMediaPlayerSoundItem.Mode.singular) {
                FMediaPlayerItems.remove(i);
            }
            else {
                i++;
            }
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
                DoStopSound(item);
            }
        }
    }

    public Boolean IsPlayingSound(String aName) {
        NGMediaPlayerSoundItem item = getMediaPlayerSoundItem(aName);
        Boolean result = item != null;
        if (result) {
            result = item.getStatus() == MediaPlayer.Status.PLAYING;
        }
        return result;
    }

    public Integer GetSoundsPlayed() {
        return FMediaPlayerItems.size();
    }

}
