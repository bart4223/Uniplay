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
    protected Class FCellValueClass;

    protected void addCellTransaction(NGGameEngineMemoryTransaction aTransaction, NGGameEngineMemoryCell aCell) {
        aTransaction.add(aCell);
    }

    protected NGGameEngineMemoryCell allocateCell(int aPage, int aBase, int aOffset, Class aCellValueClass) {
        NGGameEngineMemoryCell cell = new NGGameEngineMemoryCell(aPage, aBase, aOffset, aCellValueClass);
        FCells.add(cell);
        return cell;
    }

    protected void DoReallocate(NGGameEngineMemoryTransaction aTransaction, Class aCellValueClass) {
        clearAllCells(aTransaction);
        FCells.clear();
        DoAllocate(aCellValueClass);
    }

    protected void DoAllocate(Class<NGGameEngineMemoryObjectCellValue> aCellValueClass) {
        for (int page = 0; page < FPageSize; page++) {
            for (int base = 0; base < FBaseSize; base++) {
                for (int offset = 0; offset < FOffsetSize; offset++) {
                    allocateCell(page, base, offset, aCellValueClass);
                }
            }
        }
        raiseAllocatedEvent();
    }

    protected void InternalSetCellValueAsObject(NGGameEngineMemoryTransaction aTransaction, NGGameEngineMemoryCell aCell, Object aValue) {
        aCell.setValueAsObject(aValue);
        addCellTransaction(aTransaction, aCell);
    }

    protected void InternalSetCellValueAsInteger(NGGameEngineMemoryTransaction aTransaction, NGGameEngineMemoryCell aCell, Integer aValue) {
        aCell.setValueAsInteger(aValue);
        addCellTransaction(aTransaction, aCell);
    }

    protected void InternalSetCellValueAsIntegerByAddress(NGGameEngineMemoryTransaction aTransaction, NGGameEngineMemoryAddress aAddress, Integer aValue) {
        NGGameEngineMemoryCell cell = getCell(aAddress);
        if (cell != null) {
            setCellValueAsInteger(aTransaction, cell, aValue);
        }
        else {
            writeWarning("InternalSetCellValueAsIntegerByAddress", String.format("Memory address access error at %d/%d/%d", aAddress.getPage(), aAddress.getBase(), aAddress.getOffset()));
        }
    }

    protected void InternalSetCellValueByAddress(NGGameEngineMemoryTransaction aTransaction, NGGameEngineMemoryAddress aAddress, Object aValue) {
        NGGameEngineMemoryCell cell = getCell(aAddress);
        if (cell != null) {
            setCellValueAsObject(aTransaction, cell, aValue);
        }
        else {
            writeWarning("InternalSetCellValueByAddress", String.format("Memory address access error at %d/%d/%d", aAddress.getPage(), aAddress.getBase(), aAddress.getOffset()));
        }
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
        DoAllocate(FCellValueClass);
    }

    protected void setCellValueAsObject(NGGameEngineMemoryTransaction aTransaction, int aIndex, Object aValue) {
        NGGameEngineMemoryCell cell = FCells.get(aIndex);
        setCellValueAsObject(aTransaction, cell, aValue);
    }

    protected void setCellValueAsInteger(NGGameEngineMemoryTransaction aTransaction, int aIndex, Integer aValue) {
        NGGameEngineMemoryCell cell = FCells.get(aIndex);
        setCellValueAsInteger(aTransaction, cell, aValue);
    }

    protected void setCellValueAsObject(NGGameEngineMemoryTransaction aTransaction, NGGameEngineMemoryAddress aAddress, Object aValue) {
        NGGameEngineMemoryCell cell = getCell(aAddress);
        if (cell != null) {
            setCellValueAsObject(aTransaction, cell, aValue);
        }
        else {
            writeWarning("setCellValueAsObject", String.format("Memory address access error at %d/%d/%d", aAddress.getPage(), aAddress.getBase(), aAddress.getOffset()));
        }
    }

    protected void setCellValueAsObject(NGGameEngineMemoryTransaction aTransaction, NGGameEngineMemoryCell aCell, Object aValue) {
        InternalSetCellValueAsObject(aTransaction, aCell, aValue);
        writeLog(NGGameEngineConstants.DEBUG_LEVEL_MEMORY, String.format("Memory [%s] cell with address [%d,%d,%d] value %s stored.", getName(), aCell.getAddress().getPage(), aCell.getAddress().getBase(), aCell.getAddress().getOffset(), aValue.toString()));
    }

    protected void setCellValueAsInteger(NGGameEngineMemoryTransaction aTransaction, NGGameEngineMemoryCell aCell, Integer aValue) {
        InternalSetCellValueAsInteger(aTransaction, aCell, aValue);
        writeLog(NGGameEngineConstants.DEBUG_LEVEL_MEMORY, String.format("Memory [%s] cell with address [%d,%d,%d] value %d stored.", getName(), aCell.getAddress().getPage(), aCell.getAddress().getBase(), aCell.getAddress().getOffset(), aValue));
    }

    protected void incCellValue(NGGameEngineMemoryTransaction aTransaction, NGGameEngineMemoryCell aCell) {
        InternalIncCellValue(aTransaction, aCell);
    }

    protected void setCellValueAsInteger(NGGameEngineMemoryTransaction aTransaction, int aPage, int aBase, int aOffset, Integer aValue) {
        NGGameEngineMemoryAddress address = new NGGameEngineMemoryAddress(aPage, aBase, aOffset);
        InternalSetCellValueAsIntegerByAddress(aTransaction, address, aValue);
    }

    public NGGameEngineMemory(NGGameEngineMemoryManager aManager, String aName, Class aCellValueClass) {
        super(aManager, aName);
        FManager = aManager;
        FCells = new ArrayList<NGGameEngineMemoryCell>();
        FCellValueClass = aCellValueClass;
        FPageSize = 0;
        FBaseSize = 0;
        FOffsetSize = 0;
    }

    public int getPageSize() {
        return FPageSize;
    }

    public int getBaseSize() {
        return FBaseSize;
    }

    public int getOffsetSize() {
        return FOffsetSize;
    }

    public int getAllocated() {
        return FCells.size();
    }

    public void Reallocate(NGGameEngineMemoryTransaction aTransaction, int aPageSize, int aBaseSize, int aOffsetSize) {
        if (FPageSize != aPageSize || FBaseSize != aBaseSize || FOffsetSize != aOffsetSize) {
            FPageSize = aPageSize;
            FBaseSize = aBaseSize;
            FOffsetSize = aOffsetSize;
            DoReallocate(aTransaction, FCellValueClass);
        }
    }

    public void clearAllCells(NGGameEngineMemoryTransaction aTransaction) {
        for (NGGameEngineMemoryCell cell : FCells) {
            clearCell(aTransaction, cell);
        }
        raiseCellsChangedEvent(aTransaction.getCells());
    }

    public int getAbsolutCellAddress(NGGameEngineMemoryCell aCell) {
        return getAbsolutAddress(aCell.getAddress());
    }

    public int getAbsolutAddress(NGGameEngineMemoryAddress aAddress) {
        return (FBaseSize * FOffsetSize * (aAddress.getPage() - 1)) + (FOffsetSize * aAddress.getBase()) + aAddress.getOffset();
    }

    public NGGameEngineMemoryCell getCell(NGGameEngineMemoryAddress aAddress) {
        int index = getAbsolutAddress(aAddress);
        return getCell(index);
    }

    public NGGameEngineMemoryCell getCell(int aIndex) {
        if (0 <= aIndex && aIndex < FCells.size()) {
            return FCells.get(aIndex);
        }
        return null;
    }

    public NGGameEngineMemoryCell getCell(int aPage, int aBase, int aOffset) {
        NGGameEngineMemoryAddress address = new NGGameEngineMemoryAddress(aPage, aBase, aOffset);
        return getCell(address);
    }

    public Object getCellValue(NGGameEngineMemoryAddress aAddress) {
        NGGameEngineMemoryCell cell = getCell(aAddress);
        if (cell != null) {
            return cell.getValue();
        }
        else {
            writeWarning("getCellValue", String.format("Memory address access error at %d/%d/%d", aAddress.getPage(), aAddress.getBase(), aAddress.getOffset()));
        }
        return null;
    }

    public Integer getCellValueAsInteger(NGGameEngineMemoryAddress aAddress) {
        NGGameEngineMemoryCell cell = getCell(aAddress);
        if (cell != null) {
            return cell.getValueAsInteger();
        }
        else {
            writeWarning("getCellValueAsInteger", String.format("Memory address access error at %d/%d/%d", aAddress.getPage(), aAddress.getBase(), aAddress.getOffset()));
        }
        return 0;
    }

    public Integer getCellValueAsInteger(int aPage, int aBase, int aOffset) {
        NGGameEngineMemoryCell cell = getCell(aPage, aBase, aOffset);
        if (cell != null) {
            return cell.getValueAsInteger();
        }
        else {
            writeWarning("getCellValueAsInteger", String.format("Memory address access error at %d/%d/%d", aPage, aBase, aOffset));
        }
        return null;
    }

    public void setCellsValue(NGGameEngineMemoryTransaction aTransaction, ArrayList<NGGameEngineMemoryCellValueItem> aItems) {
        for (NGGameEngineMemoryCellValueItem item : aItems) {
            InternalSetCellValueByAddress(aTransaction, item.getAddress(), item.getValue().getObject());
        }
        raiseCellsChangedEvent(aTransaction.getCells());
        writeLog(NGGameEngineConstants.DEBUG_LEVEL_MEMORY, String.format("%d cell(s) in Memory [%s] changed", aTransaction.getCells().size(), getName()));
    }

    public void setCellValue(NGGameEngineMemoryTransaction aTransaction, NGGameEngineMemoryCellValueItem aItem) {
        InternalSetCellValueByAddress(aTransaction, aItem.getAddress(), aItem.getValue().getObject());
        raiseCellsChangedEvent(aTransaction.getCells());
        writeLog(NGGameEngineConstants.DEBUG_LEVEL_MEMORY, String.format("Cell in Memory [%s] changed", getName()));
    }

    public void setCellValueAsInteger(NGGameEngineMemoryTransaction aTransaction, NGGameEngineMemoryAddress aAddress, Integer aValue) {
        InternalSetCellValueAsIntegerByAddress(aTransaction, aAddress, aValue);
        raiseCellsChangedEvent(aTransaction.getCells());
        writeLog(NGGameEngineConstants.DEBUG_LEVEL_MEMORY, String.format("Cell in Memory [%s] changed", getName()));
    }

    public void incAllCellsValue(NGGameEngineMemoryTransaction aTransaction) {
        for (NGGameEngineMemoryCell cell : FCells) {
            incCellValue(aTransaction, cell);
        }
        raiseCellsChangedEvent(aTransaction.getCells());
    }

    public NGGameEngineMemoryManager getManager() {
        return FManager;
    }

    public void refreshAllCells() {
        raiseCellsChangedEvent(FCells);
    }

    public void refreshCell(NGGameEngineMemoryAddress aAddress) {
        NGGameEngineMemoryCell cell = getCell(aAddress);
        if (cell != null) {
            ArrayList<NGGameEngineMemoryCell> cells = new ArrayList<NGGameEngineMemoryCell>();
            cells.add(cell);
            raiseCellsChangedEvent(cells);
        }
        else {
            writeWarning("refreshCell", String.format("Memory address access error at %d/%d/%d", aAddress.getPage(), aAddress.getBase(), aAddress.getOffset()));
        }
    }

}
