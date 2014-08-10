package Uniplay.Control;

import Uniplay.Base.NGUniplayComponent;
import Uniplay.Base.NGUniplayObject;

import java.util.ArrayList;

public class NGControlMimicManager extends NGUniplayComponent {

    protected ArrayList<NGControlMimicItem> FMimics;

    public NGControlMimicManager(NGUniplayObject aOwner, String aName) {
        super(aOwner, aName);
        FMimics = new ArrayList<NGControlMimicItem>();
    }

}
