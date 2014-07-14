package Uniplay.Base;

public abstract class NGUniplayComponentDefinition extends NGUniplayObjectDefinition {

    protected String Name = "";

    public NGUniplayComponentDefinition() {
        super();
    }

    public void setName(String value) {
        Name = value;
    }
    public String getName() {
        return Name;
    }

}
