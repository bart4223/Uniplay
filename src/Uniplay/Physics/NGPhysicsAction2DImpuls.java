package Uniplay.Physics;

import Uniwork.Graphics.NGVector2D;

public class NGPhysicsAction2DImpuls extends NGCustomPhysicsAction {

    protected double FAmount;
    protected NGVector2D FDirection;

    public NGPhysicsAction2DImpuls(double aAmount, NGVector2D aDirection) {
        super();
        FAmount = aAmount;
        FDirection = aDirection;
    }

    public double getAmount() {
        return FAmount;
    }

    public NGVector2D getDirection() {
        return FDirection;
    }

}
