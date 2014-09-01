package Uniplay.Control;

import Uniplay.Kernel.NGGameEngineModuleDefinition;

import java.util.ArrayList;

public class NGControlCenterDefinition extends NGGameEngineModuleDefinition {

    protected ArrayList<NGControlCenterDefinitionMimicItem> MimicItems;

    public NGControlCenterDefinition() {
        super();
    }

    public void setMimicItems(ArrayList<NGControlCenterDefinitionMimicItem> value) { MimicItems = value;}
    public ArrayList<NGControlCenterDefinitionMimicItem> getMimicItems() { return MimicItems; }

}
