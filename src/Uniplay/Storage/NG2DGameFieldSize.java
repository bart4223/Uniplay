package Uniplay.Storage;

import Uniplay.Base.NGUniplayObject;

public class NG2DGameFieldSize extends NGUniplayObject {

    protected double FWidth;
    protected double FHeight;

    public NG2DGameFieldSize(double aWidth, double aHeight) {
        super();
        FWidth = aWidth;
        FHeight = aHeight;
    }

    public double getWidth() {
        return FWidth;
    }

    public void setWidth(double aWidth) {
        FWidth = aWidth;
    }

    public double getHeight() {
        return FHeight;
    }

    public void setHeight(double aHeight) {
        FHeight = aHeight;
    }

    public void clear() {
        setWidth(0);
        setHeight(0);
    }

}
