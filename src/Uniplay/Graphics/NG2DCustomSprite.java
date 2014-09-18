package Uniplay.Graphics;

import Uniplay.Base.NGUniplayObject;

public abstract class NG2DCustomSprite extends NGUniplayObject implements NGRenderInformation {

    protected Integer FID;

    public NG2DCustomSprite() {
        super();
    }

    public Integer getID() {
        return FID;
    }

    public void setID(Integer aID) {
        FID = aID;
    }

}
