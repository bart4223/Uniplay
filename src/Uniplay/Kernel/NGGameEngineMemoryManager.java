package Uniplay.Kernel;

import Uniplay.NGUniplayObject;
import Uniwork.Base.NGLogManager;

import java.util.ArrayList;

public class NGGameEngineMemoryManager extends NGUniplayObject {

    protected NGUniplayObject FOwner;
    protected ArrayList<NGGameEngineMemory> FMemoryList;
    protected NGLogManager FLogManager;
    protected int FChunkSize;

    protected void Reallocate(NGGameEngineMemory aMemory, int aPageSize, int aBaseSize, int aOffsetSize) {
        aMemory.Reallocate(aPageSize, aBaseSize, aOffsetSize);
        int index = FMemoryList.indexOf(aMemory);
        writeLog(String.format("Memory(%d) %d cells allocated.", index, aMemory.getAllocated()));
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
        addMemory(1, FChunkSize, FChunkSize);
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

    protected NGGameEngineMemory getMemory(int aIndex) {
        return FMemoryList.get(aIndex);
    }

    public NGGameEngineMemoryManager(NGUniplayObject aOwner) {
        super();
        FOwner = aOwner;
        FLogManager = null;
        FMemoryList = new ArrayList<NGGameEngineMemory>();
        FChunkSize = 16;
    }

    public void setLogManager(NGLogManager aLogManager) {
        FLogManager = aLogManager;
    }

    public NGLogManager getLogManager() {
        return FLogManager;
    }

    public void clearMemory(int aIndex) {
        NGGameEngineMemory memory = getMemory(aIndex);
        memory.clear();
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
        NGGameEngineMemory memory = new NGGameEngineMemory(this);
        FMemoryList.add(memory);
        Reallocate(memory, aPageSize, aBaseSize, aOffsetSize);
        return FMemoryList.size() - 1;
    }

}
