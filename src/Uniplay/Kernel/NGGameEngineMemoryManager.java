package Uniplay.Kernel;

import Uniplay.Base.NGUniplayComponent;

public class NGGameEngineMemoryManager extends NGUniplayComponent {

    protected void reallocateMemory(NGGameEngineMemory aMemory, NGGameEngineMemoryTransaction aTransaction, int aPageSize, int aBaseSize, int aOffsetSize) {
        aMemory.Reallocate(aTransaction, aPageSize, aBaseSize, aOffsetSize);
        writeLog(String.format("Memory [%s] %d cells allocated.", aMemory.getName(), aMemory.getAllocated()));
    }

    protected NGGameEngineMemory newMemory(String aName) {
        NGGameEngineMemory memory = new NGGameEngineMemory(this, aName);
        writeLog(String.format("Memory [%s] created.", aName));
        return memory;
    }

    protected void addMemory(NGGameEngineMemory aMemory, NGGameEngineMemoryTransaction aTransaction, int aPageSize, int aBaseSize, int aOffsetSize) {
        aMemory.addEventListener(this);
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

    protected NGGameEngineMemoryTransaction BeginTransaction(NGGameEngineMemory aMemory) {
        return getTransactionManager().BeginTransaction(aMemory);
    }

    protected void EndTransaction(NGGameEngineMemoryTransaction aTransaction) {
        getTransactionManager().EndTransaction(aTransaction);
    }

    protected NGGameEngineMemory getMemory(String aName) {
        return (NGGameEngineMemory)getSubComponent(aName);
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

    public NGGameEngineMemoryManager(NGUniplayComponent aOwner, String aName) {
        super(aOwner, aName);
    }

    public void addMemory(String aName, int aPageSize, int aBaseSize, int aOffsetSize) {
        NGGameEngineMemory memory = newMemory(aName);
        NGGameEngineMemoryTransaction transaction = BeginTransaction(memory);
        try{
            addMemory(memory, transaction, aPageSize, aBaseSize, aOffsetSize);
        }
        finally {
            EndTransaction(transaction);
        }
    }

    public void reallocateMemory(String aName, int aPageSize, int aBaseSize, int aOffsetSize) {
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

}
