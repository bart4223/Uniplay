package Uniplay.Sound;

import Uniplay.Base.NGUniplayObjectDefinition;

public class NGSoundSystemDefinitionSoundItem extends NGUniplayObjectDefinition {

    protected String Name = "";
    protected String Filename = "";
    protected String Eventname = "";

    public NGSoundSystemDefinitionSoundItem() {
        super();
    }

    public void setName(String value) { Name = value;}
    public String getName() { return Name; }
    public void setFilename(String value) { Filename = value;}
    public String getFilename() { return Filename; }
    public void setEventname(String value) { Eventname = value;}
    public String getEventname() { return Eventname; }

}
