package Uniplay.Storage;

import Uniplay.Kernel.NGGameEngineMemoryAddress;

public class NG2DGamePlayerItem extends NGCustomGamePlayerItem {

    protected NG2DGamePlayerPosition FPosition;
    protected Integer FLayer;

    public NG2DGamePlayerItem(NGCustomGame aGame, NGCustomPlayer aPlayer, Integer aLayer, Integer aMaxLives) {
        super(aGame, aPlayer, aMaxLives);
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
