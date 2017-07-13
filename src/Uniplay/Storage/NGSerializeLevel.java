package Uniplay.Storage;

import Uniplay.Base.NGUniplayObjectDefinition;
import Uniwork.Base.NGSerializePropertyItem;

import java.util.concurrent.CopyOnWriteArrayList;

public class NGSerializeLevel extends NGUniplayObjectDefinition {

    protected String Name;
    protected String Caption;
    protected CopyOnWriteArrayList<NGSerializePropertyItem> Props;

    public NGSerializeLevel() {
        super();
    }

    public void setName(String value) {
        Name = value;
    }

    public String getName() {
        return Name;
    }

    public void setCaption(String value) {
        Caption = value;
    }

    public String getCaption() {
        return Caption;
    }

    public void setProps(CopyOnWriteArrayList<NGSerializePropertyItem> Value) {
        Props = Value;
    }

    public CopyOnWriteArrayList<NGSerializePropertyItem> getProps() {
        return Props;
    }

}
