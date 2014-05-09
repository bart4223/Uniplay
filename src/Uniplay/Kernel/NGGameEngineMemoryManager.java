package Uniplay.Kernel;

import Uniplay.NGUniplayObject;
import Uniwork.Base.NGLogManager;

import java.util.ArrayList;

public class NGGameEngineMemoryManager extends NGUniplayObject {

    protected NGUniplayObject FOwner;
    protected ArrayList<NGGameEngineMemory> FMemoryList;
    protected NGLogManager FLogManager;

    protected void ReallocateMemory(NGGameEngineMemory aMemory, int aPageSize, int aBaseSize, int aOffsetSize) {
        aMemory.Reallocate(aPageSize, aBaseSize, aOffsetSize);
        int index = FMemoryList.indexOf(aMemory);
        writeLog(String.format("Memory(%d) %d cells allocated.", index, aMemory.getAllocated()));
    }

    protected NGGameEngineMemory getMemory(int aMemoryIndex) {
        return FMemoryList.get(aMemoryIndex);
    }

    protected NGGameEngineMemory newMemory() {
        NGGameEngineMemory memory = new NGGameEngineMemory(this);
        return memory;
    }

    protected int addMemory(NGGameEngineMemory aMemory) {
        FMemoryList.add(aMemory);
        return FMemoryList.indexOf(aMemory);
    }

    protected void writeLog(String aText) {
        if (FLogManager != null) {
            FLogManager.writeLog(aText, getClass().getSimpleName());
        }
    }

    @Override
    protected void BeforeInitialize() {
        writeLog("Start memory initialization...");
        super.BeforeInitialize();
    }

    @Override
    protected void DoInitialize() {
        super.DoInitialize();
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

    public void BeginTransaction(int aIndex) {
        NGGameEngineMemory memory = getMemory(aIndex);
        memory.BeginTransaction();
    }

    public void EndTransaction(int aIndex) {
        NGGameEngineMemory memory = getMemory(aIndex);
        memory.EndTransaction();
    }

    public int addMemory(int aPageSize, int aBaseSize, int aOffsetSize) {
        NGGameEngineMemory memory = newMemory();
        int index = addMemory(memory);
        ReallocateMemory(memory, aPageSize, aBaseSize, aOffsetSize);
        return index;
    }

    public void reallocateMemory(int aMemoryIndex, int aPageSize, int aBaseSize, int aOffsetSize) {
        NGGameEngineMemory memory = getMemory(aMemoryIndex);
        ReallocateMemory(memory, aPageSize, aBaseSize, aOffsetSize);
    }

    public void clearMemory(int aMemoryIndex) {
        NGGameEngineMemory memory = getMemory(aMemoryIndex);
        memory.clear();
    }

}
