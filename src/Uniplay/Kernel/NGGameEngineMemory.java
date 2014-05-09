package Uniplay.Kernel;

import Uniplay.NGUniplayObject;

import java.util.ArrayList;

public class NGGameEngineMemory extends NGUniplayObject {

    protected String FName;
    protected int FPageSize;
    protected int FBaseSize;
    protected int FOffsetSize;
    protected NGGameEngineMemoryManager FManager;
    protected ArrayList<NGGameEngineMemoryCell> FCells;
    protected NGGameEngineMemoryTransaction FTransaction;

    protected NGGameEngineMemoryCell allocateCell(int aPage, int aBase, int aOffset) {
        NGGameEngineMemoryCell cell = new NGGameEngineMemoryCell(aPage, aBase, aOffset);
        FCells.add(cell);
        return cell;
    }

    protected void DoReallocate() {
        clear();
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
    }

    protected void raiseCellChangedEvent(NGGameEngineMemoryCell aCell) {
        //ToDo
    }

    protected void InternalSetCellValue(NGGameEngineMemoryCell aCell, Object aValue) {
        aCell.setValue(aValue);
        if (FTransaction.getInTransaction()) {
            FTransaction.add(aCell);
        }
        else {
            raiseCellChangedEvent(aCell);
        }
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

    public void clear() {
        for (NGGameEngineMemoryCell cell : FCells) {
            cell.clear();
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
        FTransaction.End();
    }

}
