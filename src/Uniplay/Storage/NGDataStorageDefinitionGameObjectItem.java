package Uniplay.Storage;

import Uniplay.Base.NGUniplayObjectDefinition;
import Uniwork.Base.NGSerializePropertyItem;

import java.util.concurrent.CopyOnWriteArrayList;

public class NGDataStorageDefinitionGameObjectItem extends NGUniplayObjectDefinition {

    protected String Name = "";
    protected String Classname = "";
    protected CopyOnWriteArrayList<NGSerializePropertyItem> Props;

    public NGDataStorageDefinitionGameObjectItem() {
        super();
    }

    public void setName(String value) { Name = value;}
    public String getName() { return Name; }

    public void setClassname(String value) { Classname = value;}
    public String getClassname() { return Classname; }

    public void setProps(CopyOnWriteArrayList<NGSerializePropertyItem> value) { Props = value;}
    public CopyOnWriteArrayList<NGSerializePropertyItem> getProps() { return Props; }

}
