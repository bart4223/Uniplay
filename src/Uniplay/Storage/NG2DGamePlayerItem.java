package Uniplay.Storage;

public class NG2DGamePlayerItem extends NGGamePlayerItem {

    protected NG2DGamePlayerPosition FPosition;

    public NG2DGamePlayerItem(NGCustomPlayer aPlayer, Integer aMaxLives) {
        super(aPlayer, aMaxLives);
        FPosition = new NG2DGamePlayerPosition();
    }

    public void setPosition(double aX, double aY) {
        FPosition.setX(aX);
        FPosition.setY(aY);
    }

    public NG2DGamePlayerPosition getPosition() {
        return FPosition;
    }

}
