package Uniplay.Kernel;

import Uniplay.Base.NGUniplayObject;
import Uniwork.Base.NGLogManager;

import java.util.ArrayList;

public class NGGameEngineMemoryManager extends NGUniplayObject implements NGGameEngineEventListener {

    protected NGUniplayObject FOwner;
    protected ArrayList<NGGameEngineMemory> FMemoryList;
    protected NGLogManager FLogManager;

    protected void ReallocateMemory(NGGameEngineMemory aMemory, int aPageSize, int aBaseSize, int aOffsetSize) {
        aMemory.Reallocate(aPageSize, aBaseSize, aOffsetSize);
        writeLog(String.format("Memory[%s] %d cells allocated.", aMemory.getName(), aMemory.getAllocated()));
    }

    protected NGGameEngineMemory getMemory(String aName) {
        for (NGGameEngineMemory memory : FMemoryList) {
            if (memory.getName().equals(aName)) {
                return memory;
            }
        }
        return null;
    }

    protected NGGameEngineMemory newMemory(String aName) {
        NGGameEngineMemory memory = new NGGameEngineMemory(this, aName);
        memory.addEventListener(this);
        return memory;
    }

    protected void addMemory(NGGameEngineMemory aMemory, int aPageSize, int aBaseSize, int aOffsetSize) {
        FMemoryList.add(aMemory);
        ReallocateMemory(aMemory, aPageSize, aBaseSize, aOffsetSize);
    }

    protected void clearMemory(NGGameEngineMemory aMemory) {
        BeginTransaction(aMemory);
        try{
            aMemory.clearCells();
        }
        finally {
            EndTransaction(aMemory);
        }
    }

    protected void BeginTransaction(NGGameEngineMemory aMemory) {
        aMemory.BeginTransaction();
    }

    protected void EndTransaction(NGGameEngineMemory aMemory) {
        aMemory.EndTransaction();
    }

    protected void writeLog(String aText) {
        writeLog(0, aText);
    }

    protected void writeLog(int aLogLevel, String aText) {
        if (FLogManager != null) {
            FLogManager.writeLog(aLogLevel, aText, getClass().getName());
        }
    }

    @Override
    protected void BeforeInitialize() {
        writeLog("Start memory initialization...");
        super.BeforeInitialize();
    }

    @Override
    protected void AfterInitialize() {
        super.AfterInitialize();
        writeLog("Memory initialized!");
    }

    @Override
    protected void BeforeFinalize() {
        writeLog("Start memory release...");
        super.BeforeFinalize();
    }

    @Override
    protected void AfterFinalize() {
        super.AfterFinalize();
        writeLog("Memory released!");
    }

    public NGGameEngineMemoryManager(NGUniplayObject aOwner) {
        super();
        FOwner = aOwner;
        FLogManager = null;
        FMemoryList = new ArrayList<NGGameEngineMemory>();
    }

    public void setLogManager(NGLogManager aLogManager) {
        FLogManager = aLogManager;
    }

    public NGLogManager getLogManager() {
        return FLogManager;
    }

    public void BeginTransaction(String aName) {
        NGGameEngineMemory memory = getMemory(aName);
        BeginTransaction(memory);
    }

    public void EndTransaction(String aName) {
        NGGameEngineMemory memory = getMemory(aName);
        EndTransaction(memory);
    }

    public void addMemory(String aName, int aPageSize, int aBaseSize, int aOffsetSize) {
        NGGameEngineMemory memory = newMemory(aName);
        addMemory(memory, aPageSize, aBaseSize, aOffsetSize);
    }

    public void reallocateMemory(String aName, int aPageSize, int aBaseSize, int aOffsetSize) {
        NGGameEngineMemory memory = getMemory(aName);
        ReallocateMemory(memory, aPageSize, aBaseSize, aOffsetSize);
    }

    public void clearMemory(String aName) {
        NGGameEngineMemory memory = getMemory(aName);
        clearMemory(memory);
    }

    @Override
    public void handleEvent(String name, NGGameEngineEvent e) {
        for (NGGameEngineEventListener listener : FEventListeners) {
            listener.handleEvent(name, e);
        }
    }

}
