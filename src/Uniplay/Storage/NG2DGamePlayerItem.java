package Uniplay.Storage;

import Uniplay.Kernel.NGGameEngineMemoryAddress;

public class NG2DGamePlayerItem extends NGGamePlayerItem {

    protected NG2DGamePlayerPosition FPosition;
    protected Integer FLayer;

    public NG2DGamePlayerItem(NGCustomPlayer aPlayer, Integer aLayer, Integer aMaxLives) {
        super(aPlayer, aMaxLives);
        FPosition = new NG2DGamePlayerPosition();
        FLayer = aLayer;
    }

    public void setPosition(double aX, double aY) {
        FPosition.setX(aX);
        FPosition.setY(aY);
    }

    public NG2DGamePlayerPosition getPosition() {
        return FPosition;
    }

    public NGGameEngineMemoryAddress getMemoryAddress() {
        return new NGGameEngineMemoryAddress(FLayer, (int)getPosition().getY(), (int)getPosition().getX());
    }

}
