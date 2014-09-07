package Uniplay.Sound;

import Uniplay.Base.NGUniplayObject;
import javafx.scene.media.Media;

import java.io.File;
import java.util.ArrayList;

public class NGSoundItem extends NGUniplayObject {

    protected String FName;
    protected ArrayList<String> FRunEventnames;
    protected ArrayList<String> FStopEventnames;
    protected Media FMedia;

    public NGSoundItem(String aName, String aFilename) {
        super();
        FName = aName;
        File file = new File(aFilename);
        String path = file.getAbsoluteFile().getPath();
        FMedia = new Media(String.format("file://%s", path));
        FRunEventnames = new ArrayList<String>();
        FStopEventnames = new ArrayList<String>();
    }

    public String getName() {
        return FName;
    }

    public Media getMedia() {
        return FMedia;
    }

    public void addRunEventname(String aEventname) {
        FRunEventnames.add(aEventname);
    }

    public void addStopEventname(String aEventname) {
        FStopEventnames.add(aEventname);
    }

    public Boolean playOnEvent(String aEventname) {
        for (String item : FRunEventnames) {
            if (item.equals(aEventname)) {
                return true;
            }
        }
        return false;
    }

    public Boolean stopOnEvent(String aEventname) {
        for (String item : FStopEventnames) {
            if (item.equals(aEventname)) {
                return true;
            }
        }
        return false;
    }

}
