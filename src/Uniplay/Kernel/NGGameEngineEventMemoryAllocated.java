package Uniplay.Kernel;

public class NGGameEngineEventMemoryAllocated extends NGGameEngineEvent {

    protected NGGameEngineMemory FMemory;

    public NGGameEngineEventMemoryAllocated(Object source, NGGameEngineMemory aMemory) {
        super(source);
        FMemory = aMemory;
    }

    public NGGameEngineMemory getMemory() {
        return FMemory;
    }

}
