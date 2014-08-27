package Uniplay.Kernel;

import Uniplay.Base.NGUniplayComponent;
import Uniplay.NGGameEngineConstants;

import java.util.ArrayList;

public class NGGameEngineMemoryManager extends NGUniplayComponent {

    protected void reallocateMemory(NGGameEngineMemory aMemory, NGGameEngineMemoryTransaction aTransaction, int aPageSize, int aBaseSize, int aOffsetSize) {
        aMemory.Reallocate(aTransaction, aPageSize, aBaseSize, aOffsetSize);
        writeLog(String.format("Memory [%s] %d cells allocated.", aMemory.getName(), aMemory.getAllocated()));
    }

    protected NGGameEngineMemory newMemory(String aName, Class<NGGameEngineMemoryCustomCellValue> aCellValueClass) {
        NGGameEngineMemory memory = new NGGameEngineMemory(this, aName, aCellValueClass);
        writeLog(String.format("Memory [%s] created.", aName));
        return memory;
    }

    protected void addMemory(NGGameEngineMemory aMemory, NGGameEngineMemoryTransaction aTransaction, int aPageSize, int aBaseSize, int aOffsetSize) {
        addSubComponent(aMemory);
        writeLog(String.format("Memory [%s] added.", aMemory.getName()));
        reallocateMemory(aMemory, aTransaction, aPageSize, aBaseSize, aOffsetSize);
    }

    protected void clearMemory(NGGameEngineMemory aMemory, NGGameEngineMemoryTransaction aTransaction) {
        aMemory.clearAllCells(aTransaction);
    }

    protected void incAllMemoryCellsValue(NGGameEngineMemory aMemory, NGGameEngineMemoryTransaction aTransaction) {
        aMemory.incAllCellsValue(aTransaction);
    }

    protected void setCellsValue(NGGameEngineMemory aMemory, NGGameEngineMemoryTransaction aTransaction, ArrayList<NGGameEngineMemoryCellValueItem> aItems) {
        aMemory.setCellsValue(aTransaction, aItems);
    }

    protected void setCellValueAsObject(NGGameEngineMemory aMemory, NGGameEngineMemoryTransaction aTransaction, NGGameEngineMemoryAddress aAddress, Object aValue) {
        aMemory.setCellValueAsObject(aTransaction, aAddress, aValue);
    }

    protected void setCellValueAsInteger(NGGameEngineMemory aMemory, NGGameEngineMemoryTransaction aTransaction, NGGameEngineMemoryAddress aAddress, Integer aValue) {
        aMemory.setCellValueAsInteger(aTransaction, aAddress, aValue);
    }

    protected NGGameEngineMemoryTransaction BeginTransaction(NGGameEngineMemory aMemory) {
        return getTransactionManager().BeginTransaction(aMemory);
    }

    protected void EndTransaction(NGGameEngineMemoryTransaction aTransaction) {
        getTransactionManager().EndTransaction(aTransaction);
    }

    @Override
    protected void BeforeInitialize() {
        writeLog("Start memory manager initialization...");
        super.BeforeInitialize();
    }

    @Override
    protected void BeforeFinalize() {
        writeLog("Start memory manager release...");
        super.BeforeFinalize();
    }

    @Override
    protected void AfterFinalize() {
        super.AfterFinalize();
        writeLog("Memory manager released!");
    }

    @Override
    protected void CreateSubComponents() {
        super.CreateSubComponents();
        NGUniplayComponent component = new NGGameEngineMemoryTransactionManager(this, NGGameEngineConstants.CMP_MEMORY_TRANSACTION_MANAGER);
        addSubComponent(component);
    }

    protected NGGameEngineMemoryTransactionManager getTransactionManager() {
        return (NGGameEngineMemoryTransactionManager)getSubComponent(NGGameEngineConstants.CMP_MEMORY_TRANSACTION_MANAGER);
    }

    protected NGGameEngineMemory getMemory(String aName) {
        return (NGGameEngineMemory)getSubComponent(aName);
    }

    public NGGameEngineMemoryManager(NGUniplayComponent aOwner, String aName) {
        super(aOwner, aName);
    }

    public void addMemory(String aName, Integer aPageSize, Integer aBaseSize, Integer aOffsetSize, Class aCellValueClass) {
        NGGameEngineMemory memory = newMemory(aName, aCellValueClass);
        NGGameEngineMemoryTransaction transaction = BeginTransaction(memory);
        try{
            addMemory(memory, transaction, aPageSize, aBaseSize, aOffsetSize);
        }
        finally {
            EndTransaction(transaction);
        }
    }

    public void reallocateMemory(String aName, Integer aPageSize, Integer aBaseSize, Integer aOffsetSize) {
        NGGameEngineMemory memory = getMemory(aName);
        NGGameEngineMemoryTransaction transaction = BeginTransaction(memory);
        try{
            reallocateMemory(memory, transaction, aPageSize, aBaseSize, aOffsetSize);
        }
        finally {
            EndTransaction(transaction);
        }
    }

    public void clearMemory(String aName) {
        NGGameEngineMemory memory = getMemory(aName);
        NGGameEngineMemoryTransaction transaction = BeginTransaction(memory);
        try{
            clearMemory(memory, transaction);
        }
        finally {
            EndTransaction(transaction);
        }
    }

    public void incAllMemoryCellsValue(String aName) {
        NGGameEngineMemory memory = getMemory(aName);
        NGGameEngineMemoryTransaction transaction = BeginTransaction(memory);
        try{
            incAllMemoryCellsValue(memory, transaction);
        }
        finally {
            EndTransaction(transaction);
        }
    }

    public void setCellsValue(String aName, ArrayList<NGGameEngineMemoryCellValueItem> aItems) {
        NGGameEngineMemory memory = getMemory(aName);
        NGGameEngineMemoryTransaction transaction = BeginTransaction(memory);
        try{
            setCellsValue(memory, transaction, aItems);
        }
        finally {
            EndTransaction(transaction);
        }
    }

    public void setCellValueAsObject(String aName, NGGameEngineMemoryAddress aAddress, Object aValue) {
        NGGameEngineMemory memory = getMemory(aName);
        NGGameEngineMemoryTransaction transaction = BeginTransaction(memory);
        try{
            setCellValueAsObject(memory, transaction, aAddress, aValue);
        }
        finally {
            EndTransaction(transaction);
        }
    }

    public void setCellValueAsInteger(String aName, NGGameEngineMemoryAddress aAddress, Integer aValue) {
        NGGameEngineMemory memory = getMemory(aName);
        NGGameEngineMemoryTransaction transaction = BeginTransaction(memory);
        try{
            setCellValueAsInteger(memory, transaction, aAddress, aValue);
        }
        finally {
            EndTransaction(transaction);
        }
    }

    public Object getCellValue(String aName, NGGameEngineMemoryAddress aAddress) {
        NGGameEngineMemory memory = getMemory(aName);
        return memory.getCellValue(aAddress);
    }

    public Integer getCellValueAsInteger(String aName, NGGameEngineMemoryAddress aAddress) {
        NGGameEngineMemory memory = getMemory(aName);
        return memory.getCellValueAsInteger(aAddress);
    }

    public void refreshAllCells(String aName) {
        NGGameEngineMemory memory = getMemory(aName);
        NGGameEngineMemoryTransaction transaction = BeginTransaction(memory);
        try{
            memory.refreshAllCells();
        }
        finally {
            EndTransaction(transaction);
        }
    }

    public void refreshCell(String aName, NGGameEngineMemoryAddress aAddress) {
        NGGameEngineMemory memory = getMemory(aName);
        NGGameEngineMemoryTransaction transaction = BeginTransaction(memory);
        try{
            memory.refreshCell(aAddress);
        }
        finally {
            EndTransaction(transaction);
        }
    }

}
