package Uniplay.Kernel;

import Uniplay.Base.NGUniplayComponent;
import Uniplay.NGGameEngineConstants;

import java.util.ArrayList;

public class NGGameEngineMemory extends NGUniplayComponent {

    protected int FPageSize;
    protected int FBaseSize;
    protected int FOffsetSize;
    protected NGGameEngineMemoryManager FManager;
    protected ArrayList<NGGameEngineMemoryCell> FCells;

    protected void addCellTransaction(NGGameEngineMemoryTransaction aTransaction, NGGameEngineMemoryCell aCell) {
        aTransaction.add(aCell);
    }

    protected NGGameEngineMemoryCell allocateCell(int aPage, int aBase, int aOffset) {
        NGGameEngineMemoryCell cell = new NGGameEngineMemoryCell(aPage, aBase, aOffset);
        FCells.add(cell);
        return cell;
    }

    protected void DoReallocate(NGGameEngineMemoryTransaction aTransaction) {
        clearAllCells(aTransaction);
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

    protected void InternalSetCellValue(NGGameEngineMemoryTransaction aTransaction, NGGameEngineMemoryCell aCell, Integer aValue) {
        aCell.setValueAsInteger(aValue);
        addCellTransaction(aTransaction, aCell);
    }

    protected void InternalIncCellValue(NGGameEngineMemoryTransaction aTransaction, NGGameEngineMemoryCell aCell) {
        aCell.incValue();
        addCellTransaction(aTransaction, aCell);
    }

    protected void clearCell(NGGameEngineMemoryTransaction aTransaction, NGGameEngineMemoryCell aCell) {
        aCell.clear();
        addCellTransaction(aTransaction, aCell);
    }

    protected void raiseAllocatedEvent() {
        NGGameEngineEventMemoryAllocated event = new NGGameEngineEventMemoryAllocated(this, this);
        raiseEvent(NGGameEngineConstants.EVT_MEMORY_ALLOCATED, event);
    }

    protected void raiseCellsChangedEvent(ArrayList<NGGameEngineMemoryCell> aCells) {
        NGGameEngineEventMemoryCellsChanged event = new NGGameEngineEventMemoryCellsChanged(this, this, aCells);
        raiseEvent(NGGameEngineConstants.EVT_MEMORY_CELLS_CHANGED, event);
    }

    @Override
    protected void DoInitialize() {
        super.DoInitialize();
        DoAllocate();
    }

    public NGGameEngineMemory(NGGameEngineMemoryManager aManager, String aName) {
        super(aManager, aName);
        FManager = aManager;
        FCells = new ArrayList<NGGameEngineMemoryCell>();
        FPageSize = 0;
        FBaseSize = 0;
        FOffsetSize = 0;
    }

    public int getAllocated() {
        return FCells.size();
    }

    public void Reallocate(NGGameEngineMemoryTransaction aTransaction, int aPageSize, int aBaseSize, int aOffsetSize) {
        if (FPageSize != aPageSize || FBaseSize != aBaseSize || FOffsetSize != aOffsetSize) {
            FPageSize = aPageSize;
            FBaseSize = aBaseSize;
            FOffsetSize = aOffsetSize;
            DoReallocate(aTransaction);
        }
    }

    public void clearAllCells(NGGameEngineMemoryTransaction aTransaction) {
        for (NGGameEngineMemoryCell cell : FCells) {
            clearCell(aTransaction, cell);
        }
        raiseCellsChangedEvent(aTransaction.getCells());
    }

    public void incAllCellsValue(NGGameEngineMemoryTransaction aTransaction) {
        for (NGGameEngineMemoryCell cell : FCells) {
            incCellValue(aTransaction, cell);
        }
        raiseCellsChangedEvent(aTransaction.getCells());
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

    public void setCellValue(NGGameEngineMemoryTransaction aTransaction, int aPage, int aBase, int aOffset, Integer aValue) {
        NGGameEngineMemoryAddress address = new NGGameEngineMemoryAddress(aPage, aBase, aOffset);
        setCellValue(aTransaction, address, aValue);
    }

    public void setCellsValue(NGGameEngineMemoryTransaction aTransaction, ArrayList<NGGameEngineMemoryCellValueItem> aItems) {
        for (NGGameEngineMemoryCellValueItem item : aItems) {
            setCellValue(aTransaction, item);
        }
        raiseCellsChangedEvent(aTransaction.getCells());
        writeLog(10, String.format("%d cell(s) in Memory [%s] changed", aTransaction.getCells().size(), getName()));
    }

    public void setCellValue(NGGameEngineMemoryTransaction aTransaction, NGGameEngineMemoryCellValueItem aItem) {
        setCellValue(aTransaction, aItem.getAddress(), aItem.getValue().getInteger());
    }

    public void setCellValue(NGGameEngineMemoryTransaction aTransaction, NGGameEngineMemoryAddress aAddress, Integer aValue) {
        NGGameEngineMemoryCell cell = getCell(aAddress);
        setCellValue(aTransaction, cell, aValue);
    }

    public void setCellValue(NGGameEngineMemoryTransaction aTransaction, int aIndex, Integer aValue) {
        NGGameEngineMemoryCell cell = FCells.get(aIndex);
        setCellValue(aTransaction, cell, aValue);
    }

    public void setCellValue(NGGameEngineMemoryTransaction aTransaction, NGGameEngineMemoryCell aCell, Integer aValue) {
        InternalSetCellValue(aTransaction, aCell, aValue);
        writeLog(10, String.format("Memory [%s] address [%d,%d,%d] value %d stored.", getName(), aCell.getAddress().getPage(), aCell.getAddress().getBase(), aCell.getAddress().getOffset(), aValue));
    }

    public void incCellValue(NGGameEngineMemoryTransaction aTransaction, NGGameEngineMemoryCell aCell) {
        InternalIncCellValue(aTransaction, aCell);
    }

    public NGGameEngineMemoryManager getManager() {
        return FManager;
    }

}
