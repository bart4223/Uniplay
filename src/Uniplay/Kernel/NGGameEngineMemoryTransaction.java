package Uniplay.Kernel;

import Uniplay.Base.NGUniplayObject;

import java.util.ArrayList;
import java.util.UUID;

public class NGGameEngineMemoryTransaction extends NGUniplayObject {

    protected UUID FGUID;
    protected ArrayList<NGGameEngineMemoryCell> FCells;
    protected NGGameEngineMemory FMemory;

    public NGGameEngineMemoryTransaction(NGGameEngineMemory aMemory) {
        super();
        FMemory = aMemory;
        FGUID = UUID.randomUUID();
        FCells = new ArrayList<NGGameEngineMemoryCell>();
    }

    public void add(NGGameEngineMemoryCell aCell) {
        if (FCells.indexOf(aCell) < 0) {
            FCells.add(aCell);
        }
    }

    public ArrayList<NGGameEngineMemoryCell> getCells() {
        return FCells;
    }

    public UUID getGUID() {
        return FGUID;
    }

    public NGGameEngineMemory getMemory() {
        return FMemory;
    }

}
