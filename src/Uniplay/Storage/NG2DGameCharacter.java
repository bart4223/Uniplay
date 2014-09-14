package Uniplay.Storage;

import Uniplay.Kernel.NGGameEngineMemoryAddress;

public class NG2DGameCharacter extends NGCustomGameCharacter {

    protected NG2DGameCharacterPosition FPosition;
    protected Integer FLayer;

    public NG2DGameCharacter(NGCustomGame aGame, NGCustomPlayer aPlayer) {
        super(aGame, aPlayer);
        FPosition = new NG2DGameCharacterPosition();
        FLayer = 0;
    }

    public void setPosition(double aX, double aY) {
        FPosition.setX(aX);
        FPosition.setY(aY);
    }

    public NG2DGameCharacterPosition getPosition() {
        return FPosition;
    }

    public NGGameEngineMemoryAddress getMemoryAddress() {
        return new NGGameEngineMemoryAddress(FLayer, (int)getPosition().getY(), (int)getPosition().getX());
    }

    public Integer getLayer() {
        return FLayer;
    }

    public void setLayer(Integer aValue) {
        FLayer = aValue;
    }

}
