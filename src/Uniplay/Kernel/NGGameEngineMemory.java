package Uniplay.Kernel;

import Uniplay.Base.NGUniplayObject;

import java.util.ArrayList;

public class NGGameEngineMemory extends NGUniplayObject {

    public static String EVT_MEMORY_ALLOCATED = "Memory.Allocated";
    public static String EVT_MEMORY_CELLS_CHANGED = "Memory.Cells.Changed";

    protected String FName;
    protected int FPageSize;
    protected int FBaseSize;
    protected int FOffsetSize;
    protected NGGameEngineMemoryManager FManager;
    protected ArrayList<NGGameEngineMemoryCell> FCells;
    protected NGGameEngineMemoryTransaction FTransaction;

    protected void addCellTransaction(NGGameEngineMemoryCell aCell) {
        FTransaction.add(aCell);
    }

    protected NGGameEngineMemoryCell allocateCell(int aPage, int aBase, int aOffset) {
        NGGameEngineMemoryCell cell = new NGGameEngineMemoryCell(aPage, aBase, aOffset);
        FCells.add(cell);
        return cell;
    }

    protected void DoReallocate() {
        clearCells();
        DoAllocate();
    }

    protected void DoAllocate() {
        for (int page = 0; page < FPageSize; page++) {
            for (int base = 0; base < FBaseSize; base++) {
                for (int offset = 0; offset < FOffsetSize; offset++) {
                    allocateCell(page, base, offset);
                }
            }
        }
        raiseAllocatedEvent();
    }

    protected void InternalSetCellValue(NGGameEngineMemoryCell aCell, Object aValue) {
        aCell.setValue(aValue);
        if (getInTransaction()) {
            addCellTransaction(aCell);
        }
        else {
            ArrayList<NGGameEngineMemoryCell> cells = new ArrayList<NGGameEngineMemoryCell>();
            cells.add(aCell);
            raiseCellsChangedEvent(cells);
        }
    }

    protected void clearCell(NGGameEngineMemoryCell aCell) {
        aCell.clear();
        if (getInTransaction()) {
            FTransaction.add(aCell);
        }
        else {
            ArrayList<NGGameEngineMemoryCell> cells = new ArrayList<NGGameEngineMemoryCell>();
            cells.add(aCell);
            raiseCellsChangedEvent(cells);
        }
    }

    protected void raiseAllocatedEvent() {
        NGGameEngineEventMemoryAllocated event = new NGGameEngineEventMemoryAllocated(this, this);
        raiseEvent(EVT_MEMORY_ALLOCATED, event);
    }

    protected void raiseCellsChangedEvent(ArrayList<NGGameEngineMemoryCell> aCells) {
        NGGameEngineEventMemoryCellsChanged event = new NGGameEngineEventMemoryCellsChanged(this, aCells);
        raiseEvent(EVT_MEMORY_CELLS_CHANGED, event);
    }

    @Override
    protected void DoInitialize() {
        super.DoInitialize();
        DoAllocate();
    }

    public NGGameEngineMemory(NGGameEngineMemoryManager aManager,String aName) {
        super();
        FName = aName;
        FManager = aManager;
        FCells = new ArrayList<NGGameEngineMemoryCell>();
        FTransaction = new NGGameEngineMemoryTransaction();
        FPageSize = 0;
        FBaseSize = 0;
        FOffsetSize = 0;
    }

    public String getName() {
        return FName;
    }

    public int getAllocated() {
        return FCells.size();
    }

    public void Reallocate(int aPageSize, int aBaseSize, int aOffsetSize) {
        if (FPageSize != aPageSize || FBaseSize != aBaseSize || FOffsetSize != aOffsetSize) {
            FPageSize = aPageSize;
            FBaseSize = aBaseSize;
            FOffsetSize = aOffsetSize;
            DoReallocate();
        }
    }

    public void clearCells() {
        for (NGGameEngineMemoryCell cell : FCells) {
            clearCell(cell);
        }
    }

    public int getAbsolutCellAddress(NGGameEngineMemoryCell aCell) {
        return getAbsolutAddress(aCell.getAddress());
    }

    public int getAbsolutAddress(NGGameEngineMemoryAddress aAddress) {
        return ((aAddress.getPage() - 1) * FBaseSize * FOffsetSize) + (aAddress.getBase() * FOffsetSize) + aAddress.getOffset();
    }

    public NGGameEngineMemoryCell getCell(NGGameEngineMemoryAddress aAddress) {
        int index = getAbsolutAddress(aAddress);
        return getCell(index);
    }

    public NGGameEngineMemoryCell getCell(int aIndex) {
        return FCells.get(aIndex);
    }

    public NGGameEngineMemoryCell getCell(int aPage, int aBase, int aOffset) {
        NGGameEngineMemoryAddress address = new NGGameEngineMemoryAddress(aPage, aBase, aOffset);
        return getCell(address);
    }

    public void setCellValue(int aPage, int aBase, int aOffset, Object aValue) {
        NGGameEngineMemoryAddress address = new NGGameEngineMemoryAddress(aPage, aBase, aOffset);
        setCellValue(address, aValue);
    }

    public void setCellValue(NGGameEngineMemoryAddress aAddress, Object aValue) {
        NGGameEngineMemoryCell cell = getCell(aAddress);
        setCellValue(cell, aValue);
    }

    public void setCellValue(int aIndex, Object aValue) {
        NGGameEngineMemoryCell cell = FCells.get(aIndex);
        setCellValue(cell, aValue);
    }

    public void setCellValue(NGGameEngineMemoryCell aCell, Object aValue) {
        InternalSetCellValue(aCell, aValue);
    }

    public void BeginTransaction() {
        FTransaction.Begin();
    }

    public void EndTransaction() {
        raiseCellsChangedEvent(FTransaction.getCells());
        FTransaction.End();
    }

    public Boolean getInTransaction() {
        return FTransaction.getInTransaction();
    }

}
