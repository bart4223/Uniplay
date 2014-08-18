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

    protected void addMediaPlayerItem(NGMediaPlayerSoundItem aItem) {
        FMediaPlayerItems.add(aItem);
        aItem.play();
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

    public void playSound(String aName, NGMediaPlayerSoundItem.Mode aMode) {
        NGSoundItem item = getSoundItem(aName);
        playSound(item, aMode);
    }

    public void playSound(NGSoundItem aItem, NGMediaPlayerSoundItem.Mode aMode) {
        NGMediaPlayerSoundItem sounditem = new NGMediaPlayerSoundItem(this, aItem, aMode);
        addMediaPlayerItem(sounditem);
    }

    public void stopSound(String aName) {
        NGMediaPlayerSoundItem item = getMediaPlayerSoundItem(aName);
        stopSound(item);
    }

    public void stopAllSounds() {
        for (NGMediaPlayerSoundItem item : FMediaPlayerItems) {
            stopSound(item);
        }
    }

}
