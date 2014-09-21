package Uniplay.Storage;

import Uniplay.Kernel.NGGameEngineMemoryAddress;

public class NG2DGameObject extends NGCustomGameObject {

    protected NG2DObjectPosition FPosition;
    protected Integer FLayer;

    public NG2DGameObject(NGCustomGame aGame) {
        super(aGame);
        FPosition = new NG2DObjectPosition();
        FLayer = 0;
    }

    public void setPosition(double aX, double aY) {
        FPosition.setX(aX);
        FPosition.setY(aY);
    }

    public NG2DObjectPosition getPosition() {
        return FPosition;
    }

    public Integer getLayer() {
        return FLayer;
    }

    public void setLayer(Integer aValue) {
        FLayer = aValue;
    }

    public NGGameEngineMemoryAddress getMemoryAddress() {
        return new NGGameEngineMemoryAddress(FLayer, (int)FPosition.getY(), (int)FPosition.getX());
    }

    public Boolean IsFromAddress(NGGameEngineMemoryAddress aAddress) {
        return getMemoryAddress().equals(aAddress);
    }

}
