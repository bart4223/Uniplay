package Uniplay.Kernel;

import Uniplay.Base.NGUniplayObject;

public interface NGGameEngineEventListener {

    public void handleEvent(NGUniplayObject caller, String name, NGGameEngineEvent e);

}
