package Uniplay.Storage;

public class NG2DObjectPosition extends NGObjectPosition {

    protected double FX;
    protected double FY;

    public NG2DObjectPosition() {
        this(0.0, 0.0);
    }

    public NG2DObjectPosition(double aX, double aY) {
        super();
        FX = aX;
        FY = aY;
    }

    public void setX(double aX) {
        FX = aX;
    }

    public double getX() {
        return FX;
    }

    public void setY(double aY) {
        FY = aY;
    }

    public double getY() {
        return FY;
    }

}
