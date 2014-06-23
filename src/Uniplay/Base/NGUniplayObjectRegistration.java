package Uniplay.Base;

import Uniwork.Base.NGObject;

public interface NGUniplayObjectRegistration {

    public void registerObject(String aName, NGObject aObject);
    public void unregisterObject(String aName, NGObject aObject);

}
