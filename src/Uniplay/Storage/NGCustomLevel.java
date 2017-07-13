package Uniplay.Storage;

import Uniplay.Base.NGUniplayObject;
import Uniwork.Base.NGPropertyList;
import Uniwork.Base.NGSerializePropertyItem;
import Uniwork.Misc.NGLogManager;

import java.util.concurrent.CopyOnWriteArrayList;

public abstract class NGCustomLevel extends NGUniplayObject {

    protected NGPropertyList FProps;
    protected String FName;
    protected String FCaption;
    protected NGLogManager FLogManager;

    protected void writeLog(String aLog) {
        writeLog(0, aLog);
    }

    protected void writeLog(Integer aDebugLevel, String aLog) {
        if (FLogManager != null) {
            FLogManager.writeLog(aDebugLevel, aLog);
        }
    }

    protected void assignToULF(NGSerializeLevel aLevel) {
        aLevel.setName(getName());
        aLevel.setCaption(getCaption());
        aLevel.setProps(new CopyOnWriteArrayList<NGSerializePropertyItem>());
        getProps().AssignTo(aLevel.getProps());
    }

    protected void assignFromULF(NGSerializeLevel aLevel) {
        FName = aLevel.getName();
        setCaption(aLevel.getCaption());
        getProps().AssignFrom(aLevel.getProps());
    }

    @Override
    protected void DoAssignTo(Object aObject) {
        if (aObject instanceof NGSerializeLevel) {
            assignToULF((NGSerializeLevel)aObject);
        }
    }

    @Override
    protected void DoAssignFrom(Object aObject) {
        if (aObject instanceof NGSerializeLevel) {
            assignFromULF((NGSerializeLevel)aObject);
        }
    }

    public NGCustomLevel(String aName) {
        super();
        FName = aName;
        FCaption = "";
        FProps = new NGPropertyList();
        FLogManager = null;
    }

    public void clear() {
        FProps.clear();
    }

    public String getName() {
        return FName;
    }

    public String getCaption() {
        return FCaption;
    }

    public void setCaption(String aCaption) {
        FCaption = aCaption;
    }

    public void setLogManager(NGLogManager aLogManager) {
        FLogManager = aLogManager;
    }

    public NGLogManager getLogManager() {
        return FLogManager;
    }

    public NGPropertyList getProps() {
        return FProps;
    }

    public void setProp(String aName, Object aValue) {
        FProps.set(aName, aValue);
    }

    public Object getProp(String aName) {
        return FProps.get(aName);
    }

}
