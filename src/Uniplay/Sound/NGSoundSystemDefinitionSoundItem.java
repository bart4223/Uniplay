package Uniplay.Sound;

import Uniplay.Base.NGUniplayObjectDefinition;

import java.util.ArrayList;

public class NGSoundSystemDefinitionSoundItem extends NGUniplayObjectDefinition {

    protected String Name = "";
    protected String Filename = "";
    protected ArrayList<String> Runeventnames;
    protected ArrayList<String> Stopeventnames;

    public NGSoundSystemDefinitionSoundItem() {
        super();
    }

    public void setName(String value) { Name = value;}
    public String getName() { return Name; }

    public void setFilename(String value) { Filename = value;}
    public String getFilename() { return Filename; }

    public void setRuneventnames(ArrayList<String> value) { Runeventnames = value;}
    public ArrayList<String> getRuneventnames() { return Runeventnames; }

    public void setStopeventnames(ArrayList<String> value) { Stopeventnames = value;}
    public ArrayList<String> getStopeventnames() { return Stopeventnames; }

}
