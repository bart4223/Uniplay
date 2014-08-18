package Uniplay.Sound;

import Uniplay.Base.NGUniplayObject;
import javafx.scene.media.Media;

import java.io.File;

public class NGSoundItem extends NGUniplayObject {

    protected String FName;
    protected Media FMedia;

    public NGSoundItem(String aName, String aFilename) {
        super();
        FName = aName;
        File file = new File(aFilename);
        String path = file.getAbsoluteFile().getPath();
        FMedia = new Media(String.format("file://%s", path));
    }

    public String getName() {
        return FName;
    }

    public Media getMedia() {
        return FMedia;
    }

}
