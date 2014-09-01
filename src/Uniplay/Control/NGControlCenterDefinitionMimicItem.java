package Uniplay.Control;

import Uniplay.Base.NGUniplayObjectDefinition;
import Uniwork.Base.NGSerializePropertyItem;

import java.util.ArrayList;

public class NGControlCenterDefinitionMimicItem extends NGUniplayObjectDefinition {

    protected ArrayList<NGSerializePropertyItem> Props;

    protected String Name = "";
    protected String Classname = "";
    protected String Game = "";

    public NGControlCenterDefinitionMimicItem() {
        super();
    }

    public void setProps(ArrayList<NGSerializePropertyItem> value) { Props = value;}
    public ArrayList<NGSerializePropertyItem> getProps() { return Props; }

    public void setName(String value) { Name = value;}
    public String getName() { return Name; }

    public void setClassname(String value) { Classname = value;}
    public String getClassname() { return Classname; }

    public void setGame(String value) { Game = value;}
    public String getGame() { return Game; }

}
