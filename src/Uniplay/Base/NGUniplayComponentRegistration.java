package Uniplay.Base;

public interface NGUniplayComponentRegistration {

    public void registerComponent(String aName, NGUniplayComponent aComponent);
    public void unregisterComponent(String aName, NGUniplayComponent aComponent);

}
