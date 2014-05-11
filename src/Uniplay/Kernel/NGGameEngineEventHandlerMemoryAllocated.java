package Uniplay.Kernel;

public abstract class NGGameEngineEventHandlerMemoryAllocated extends NGGameEngineEventHandler {

    protected NGGameEngineMemory FMemory;

    @Override
    protected void BeforeHandleEvent() {
        super.BeforeHandleEvent();
        NGGameEngineEventMemoryAllocated event = (NGGameEngineEventMemoryAllocated)FEvent;
        FMemory = event.getMemory();
    }

    public NGGameEngineEventHandlerMemoryAllocated() {
        this("Memory.Allocated");
    }

    public NGGameEngineEventHandlerMemoryAllocated(String aName) {
        super(aName);
    }

}
