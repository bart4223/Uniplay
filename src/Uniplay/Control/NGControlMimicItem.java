package Uniplay.Control;

import Uniplay.Base.NGUniplayObject;

public class NGControlMimicItem extends NGUniplayObject {

    protected NGControlMimic FMimic;

    public NGControlMimicItem(NGControlMimic aMimic) {
        super();
        FMimic = aMimic;
    }

    public NGControlMimic getMimic() {
        return FMimic;
    }

}
