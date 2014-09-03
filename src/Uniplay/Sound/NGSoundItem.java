package Uniplay.Sound;

import Uniplay.Base.NGUniplayObject;
import javafx.scene.media.Media;

import java.io.File;
import java.util.ArrayList;

public class NGSoundItem extends NGUniplayObject {

    protected String FName;
    protected ArrayList<String> FEventnames;
    protected Media FMedia;

    public NGSoundItem(String aName, String aFilename) {
        super();
        FName = aName;
        File file = new File(aFilename);
        String path = file.getAbsoluteFile().getPath();
        FMedia = new Media(String.format("file://%s", path));
        FEventnames = new ArrayList<String>();
    }

    public String getName() {
        return FName;
    }

    public Media getMedia() {
        return FMedia;
    }

    public void addEventname(String aEventname) {
        FEventnames.add(aEventname);
    }

    public Boolean playOnEvent(String aEventname) {
        for (String item : FEventnames) {
            if (item.equals(aEventname)) {
                return true;
            }
        }
        return false;
    }

}
