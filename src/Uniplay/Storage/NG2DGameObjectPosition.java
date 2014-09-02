package Uniplay.Storage;

public class NG2DGameObjectPosition extends NGGameObjectPosition {

    protected double FX;
    protected double FY;

    public NG2DGameObjectPosition() {
        this(0.0, 0.0);
    }

    public NG2DGameObjectPosition(double aX, double aY) {
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
