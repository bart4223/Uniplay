package Uniplay.Kernel;

import java.util.concurrent.CopyOnWriteArrayList;

public class NGGameEngineEventMemoryCellsChanged extends NGGameEngineEvent {

    protected CopyOnWriteArrayList<NGGameEngineMemoryCell> FCells;
    protected NGGameEngineMemory FMemory;

    public NGGameEngineEventMemoryCellsChanged(Object source, NGGameEngineMemory aMemory, CopyOnWriteArrayList<NGGameEngineMemoryCell> aCells) {
        super(source);
        FMemory = aMemory;
        FCells = aCells;
    }

    public CopyOnWriteArrayList<NGGameEngineMemoryCell> getCells() {
        return FCells;
    }

    public NGGameEngineMemory getMemory() {
        return FMemory;
    }

}
