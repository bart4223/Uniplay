package Uniplay.Storage;

import Uniplay.Base.NGUniplayObjectDefinition;
import Uniwork.Base.NGSerializePropertyItem;

import java.util.ArrayList;

public class NGSerializeLevel extends NGUniplayObjectDefinition {

    protected String Name;
    protected String Caption;
    protected ArrayList<NGSerializePropertyItem> Props;

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

    public void setProps(ArrayList<NGSerializePropertyItem> Value) {
        Props = Value;
    }

    public ArrayList<NGSerializePropertyItem> getProps() {
        return Props;
    }

}
