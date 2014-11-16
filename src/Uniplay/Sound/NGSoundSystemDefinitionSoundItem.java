package Uniplay.Sound;

import Uniplay.Base.NGUniplayObjectDefinition;

import java.util.ArrayList;

public class NGSoundSystemDefinitionSoundItem extends NGUniplayObjectDefinition {

    protected String Name = "";
    protected String Filename = "";
    protected ArrayList<String> Runeventnames;
    protected ArrayList<String> Stopeventnames;
    protected Double Volume;
    protected Integer Duration;

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

    public void setVolume(Double value) { Volume = value;}
    public Double getVolume() { return Volume; }

    public void setDuration(Integer value) { Duration = value;}
    public Integer getDuration() { return  Duration; }

}
