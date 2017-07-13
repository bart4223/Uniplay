package Uniplay.Kernel;

import Uniplay.Base.NGUniplayObject;

import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

public class NGGameEngineMemoryTransaction extends NGUniplayObject {

    protected UUID FGUID;
    protected CopyOnWriteArrayList<NGGameEngineMemoryCell> FCells;
    protected NGGameEngineMemory FMemory;

    public NGGameEngineMemoryTransaction(NGGameEngineMemory aMemory) {
        super();
        FMemory = aMemory;
        FGUID = UUID.randomUUID();
        FCells = new CopyOnWriteArrayList<NGGameEngineMemoryCell>();
    }

    public void add(NGGameEngineMemoryCell aCell) {
        if (FCells.indexOf(aCell) < 0) {
            FCells.add(aCell);
        }
    }

    public CopyOnWriteArrayList<NGGameEngineMemoryCell> getCells() {
        return FCells;
    }

    public UUID getGUID() {
        return FGUID;
    }

    public NGGameEngineMemory getMemory() {
        return FMemory;
    }

}
