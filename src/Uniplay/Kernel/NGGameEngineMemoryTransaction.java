package Uniplay.Kernel;

import Uniwork.Base.NGObject;

import java.util.ArrayList;

public class NGGameEngineMemoryTransaction extends NGObject {

    protected Boolean FInTransaction;
    protected ArrayList<NGGameEngineMemoryCell> FCells;

    protected void clear() {
        FCells.clear();
    }

    public NGGameEngineMemoryTransaction() {
        super();
        FCells = new ArrayList<NGGameEngineMemoryCell>();
        FInTransaction = false;
    }

    public void add(NGGameEngineMemoryCell aCell) {
        FCells.add(aCell);
    }

    public void Begin() {
        if (!FInTransaction) {
            FInTransaction = true;
            clear();
        }
    }

    public void End() {
        if (FInTransaction) {
            FInTransaction = false;
        }
    }

    public Boolean getInTransaction() {
        return FInTransaction;
    }

    public ArrayList<NGGameEngineMemoryCell> getCells() {
        return FCells;
    }

}
