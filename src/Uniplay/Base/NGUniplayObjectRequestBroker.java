package Uniplay.Base;

import Uniwork.Base.NGObjectRequestBroker;
import Uniwork.Base.NGObjectRequestItem;
import Uniwork.Base.NGObjectRequestObject;
import Uniwork.Misc.NGLogManager;

public class NGUniplayObjectRequestBroker extends NGUniplayComponent {

    protected NGObjectRequestBroker FORB;

    public NGUniplayObjectRequestBroker(NGUniplayObject aOwner, String aName) {
        super(aOwner, aName);
        FORB = new NGObjectRequestBroker(this);
    }

    public void Invoke(NGObjectRequestItem aRequest) {
        FORB.Invoke(aRequest);
    }

    public NGObjectRequestObject registerObject(String aName, Object aObject) {
        return FORB.addObject(aName, aObject);
    }

    public void setLogManager(NGLogManager aLogManager) {
        super.setLogManager(aLogManager);
        FORB.setLogManager(aLogManager);
    }

}
