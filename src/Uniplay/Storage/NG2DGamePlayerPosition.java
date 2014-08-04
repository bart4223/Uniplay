package Uniplay.Storage;

public class NG2DGamePlayerPosition extends NGGamePlayerPosition {

    protected Integer FX;
    protected Integer FY;

    public NG2DGamePlayerPosition() {
        this(0, 0);
    }

    public NG2DGamePlayerPosition(Integer aX, Integer aY) {
        super();
        FX = aX;
        FY = aY;
    }

    public void setX(Integer aX) {
        FX = aX;
    }

    public Integer getX() {
        return FX;
    }

    public void setY(Integer aY) {
        FY = aY;
    }

    public Integer getY() {
        return FY;
    }

}
