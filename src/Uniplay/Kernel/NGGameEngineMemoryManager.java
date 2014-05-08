package Uniplay.Kernel;

import Uniplay.NGUniplayObject;
import Uniwork.Base.NGLogManager;

public class NGGameEngineMemoryManager extends NGUniplayObject {

    protected NGUniplayObject FOwner;
    protected NGGameEngineMemory FMemory;
    protected NGLogManager FLogManager;
    protected int FChunkSize;

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
        FMemory.Reallocate(1, FChunkSize, FChunkSize);
        writeLog(String.format("%d memory cells allocated.", FMemory.getAllocated()));
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
        FMemory = new NGGameEngineMemory(this);
        FChunkSize = 16;
    }

    public void setLogManager(NGLogManager aLogManager) {
        FLogManager = aLogManager;
    }

    public NGLogManager getLogManager() {
        return FLogManager;
    }

    public void clearMemory() {
        FMemory.clear();
    }

    public void BeginTransaction() {
        FMemory.BeginTransaction();
    }

    public void EndTransaction() {
        FMemory.EndTransaction();
    }

}
