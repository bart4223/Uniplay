package Uniplay.Kernel;

import Uniplay.Base.NGUniplayComponent;
import Uniplay.Base.NGUniplayObject;

import java.util.ArrayList;

public class NGGameEngineMemoryManager extends NGUniplayComponent {

    protected ArrayList<NGGameEngineMemory> FMemoryList;

    protected void reallocateMemory(NGGameEngineMemory aMemory, int aPageSize, int aBaseSize, int aOffsetSize) {
        aMemory.Reallocate(aPageSize, aBaseSize, aOffsetSize);
        writeLog(String.format("Memory [%s] %d cells allocated.", aMemory.getName(), aMemory.getAllocated()));
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
        writeLog(String.format("Memory [%s] created.", aName));
        return memory;
    }

    protected void addMemory(NGGameEngineMemory aMemory, int aPageSize, int aBaseSize, int aOffsetSize) {
        aMemory.addEventListener(this);
        FMemoryList.add(aMemory);
        writeLog(String.format("Memory [%s] added.", aMemory.getName()));
        reallocateMemory(aMemory, aPageSize, aBaseSize, aOffsetSize);
    }

    protected void clearMemory(NGGameEngineMemory aMemory) {
        aMemory.clearCells();
    }

    protected void BeginTransaction(NGGameEngineMemory aMemory) {
        aMemory.BeginTransaction();
    }

    protected void EndTransaction(NGGameEngineMemory aMemory) {
        aMemory.EndTransaction();
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

    public NGGameEngineMemoryManager(NGUniplayObject aOwner, String aName) {
        super(aOwner, aName);
        FMemoryList = new ArrayList<NGGameEngineMemory>();
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
        BeginTransaction(memory);
        try{
            addMemory(memory, aPageSize, aBaseSize, aOffsetSize);
        }
        finally {
            //EndTransaction(memory);
        }
    }

    public void reallocateMemory(String aName, int aPageSize, int aBaseSize, int aOffsetSize) {
        NGGameEngineMemory memory = getMemory(aName);
        BeginTransaction(memory);
        try{
            reallocateMemory(memory, aPageSize, aBaseSize, aOffsetSize);
        }
        finally {
            EndTransaction(memory);
        }
    }

    public void clearMemory(String aName) {
        NGGameEngineMemory memory = getMemory(aName);
        BeginTransaction(memory);
        try{
            clearMemory(memory);
        }
        finally {
            EndTransaction(memory);
        }
    }

}
