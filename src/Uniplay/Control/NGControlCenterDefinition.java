package Uniplay.Control;

import Uniplay.Kernel.NGGameEngineModuleDefinition;

import java.util.concurrent.CopyOnWriteArrayList;

public class NGControlCenterDefinition extends NGGameEngineModuleDefinition {

    protected CopyOnWriteArrayList<NGControlCenterDefinitionMimicItem> MimicItems;

    public NGControlCenterDefinition() {
        super();
    }

    public void setMimicItems(CopyOnWriteArrayList<NGControlCenterDefinitionMimicItem> value) { MimicItems = value;}
    public CopyOnWriteArrayList<NGControlCenterDefinitionMimicItem> getMimicItems() { return MimicItems; }

}
