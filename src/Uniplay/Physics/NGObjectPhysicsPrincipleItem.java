package Uniplay.Physics;

import Uniplay.Base.NGUniplayObject;

public class NGObjectPhysicsPrincipleItem extends NGUniplayObject {

    protected NGCustomPhysicsPrinciple FPrinciple;
    protected Boolean FActive;

    public NGObjectPhysicsPrincipleItem(NGCustomPhysicsPrinciple aPrinciple) {
        super();
        FPrinciple = aPrinciple;
        FActive = true;
    }

    public NGCustomPhysicsPrinciple getPrinciple() {
        return FPrinciple;
    }

    public Boolean getActive() {
        return FActive;
    }

    public void setActive(Boolean aActive) {
        FActive = aActive;
    }

}
