package Uniplay.Control;

import Uniplay.Base.NGUniplayObjectDefinition;
import Uniwork.Base.NGSerializePropertyItem;

import java.util.concurrent.CopyOnWriteArrayList;

public class NGControlCenterDefinitionMimicItem extends NGUniplayObjectDefinition {

    protected CopyOnWriteArrayList<NGSerializePropertyItem> Props;

    protected String Name = "";
    protected String Classname = "";
    protected String Game = "";

    public NGControlCenterDefinitionMimicItem() {
        super();
    }

    public void setProps(CopyOnWriteArrayList<NGSerializePropertyItem> value) { Props = value;}
    public CopyOnWriteArrayList<NGSerializePropertyItem> getProps() { return Props; }

    public void setName(String value) { Name = value;}
    public String getName() { return Name; }

    public void setClassname(String value) { Classname = value;}
    public String getClassname() { return Classname; }

    public void setGame(String value) { Game = value;}
    public String getGame() { return Game; }

}
