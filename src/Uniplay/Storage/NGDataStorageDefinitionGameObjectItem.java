package Uniplay.Storage;

import Uniplay.Base.NGUniplayObjectDefinition;
import Uniwork.Base.NGSerializePropertyItem;

import java.util.ArrayList;

public class NGDataStorageDefinitionGameObjectItem extends NGUniplayObjectDefinition {

    protected String Name = "";
    protected String Classname = "";
    protected ArrayList<NGSerializePropertyItem> Props;

    public NGDataStorageDefinitionGameObjectItem() {
        super();
    }

    public void setName(String value) { Name = value;}
    public String getName() { return Name; }

    public void setClassname(String value) { Classname = value;}
    public String getClassname() { return Classname; }

    public void setProps(ArrayList<NGSerializePropertyItem> value) { Props = value;}
    public ArrayList<NGSerializePropertyItem> getProps() { return Props; }

}
