package Uniplay.Kernel;

public interface NGGameEngineEventHandlerRegistration {

    public void registerEventHandler(NGGameEngineEventHandler aHandler);
    public void unregisterEventHandler(NGGameEngineEventHandler aHandler);

}
