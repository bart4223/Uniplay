package Uniplay.Control;

import Uniplay.Base.NGUniplayObject;

public class NGControlMimicItem extends NGUniplayObject {

    protected NGCustomControlMimic FMimic;

    public NGControlMimicItem(NGCustomControlMimic aMimic) {
        super();
        FMimic = aMimic;
    }

    public NGCustomControlMimic getMimic() {
        return FMimic;
    }

    public void Initialize() {
        getMimic().Initialize();
    }

}
