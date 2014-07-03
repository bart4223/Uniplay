package Uniplay.Storage;

import Uniplay.Base.NGUniplayComponent;
import Uniplay.Base.NGUniplayObject;

import java.util.ArrayList;

public class NG2DLevelManager extends NGUniplayComponent {

    protected ArrayList<NG2DLevel> FLevels;

    public NG2DLevelManager(NGUniplayObject aOwner, String aName) {
        super(aOwner, aName);
        FLevels = new ArrayList<NG2DLevel>();
    }

    public NG2DLevel addLevel(String aName, NG2DGameFieldSize aSize) {
        NG2DLevel level = new NG2DLevel(aName, aSize);
        level.setLogManager(getLogManager());
        FLevels.add(level);
        return level;
    }

    public NG2DLevel getLevel(String aName) {
        for (NG2DLevel level : FLevels) {
            if (level.getName().equals(aName)) {
                return level;
            }
        }
        return null;
    }

}
