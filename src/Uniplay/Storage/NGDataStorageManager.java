package Uniplay.Storage;

import Uniplay.Base.NGUniplayComponent;
import Uniplay.Base.NGUniplayObject;
import Uniplay.Kernel.NGGameEngineEvent;
import Uniwork.Base.NGObjectSerializer;

public class NGDataStorageManager extends NGUniplayComponent{

    protected NGObjectSerializer FSerializer;

    public NGDataStorageManager(NGUniplayObject aOwner, String aName) {
        super(aOwner, aName);
        //FSerializer = new NGObjectSerializer("", this);
    }

    @Override
    public void handleEvent(String name, NGGameEngineEvent e) {

    }

}
