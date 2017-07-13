package Uniplay.Sound;

import Uniplay.Base.NGUniplayObjectDefinition;

import java.util.concurrent.CopyOnWriteArrayList;

public class NGSoundSystemDefinitionSoundItem extends NGUniplayObjectDefinition {

    protected String Name = "";
    protected String Filename = "";
    protected CopyOnWriteArrayList<String> Runeventnames;
    protected CopyOnWriteArrayList<String> Stopeventnames;
    protected Double Volume;
    protected Integer Duration;

    public NGSoundSystemDefinitionSoundItem() {
        super();
    }

    public void setName(String value) { Name = value;}
    public String getName() { return Name; }

    public void setFilename(String value) { Filename = value;}
    public String getFilename() { return Filename; }

    public void setRuneventnames(CopyOnWriteArrayList<String> value) { Runeventnames = value;}
    public CopyOnWriteArrayList<String> getRuneventnames() { return Runeventnames; }

    public void setStopeventnames(CopyOnWriteArrayList<String> value) { Stopeventnames = value;}
    public CopyOnWriteArrayList<String> getStopeventnames() { return Stopeventnames; }

    public void setVolume(Double value) { Volume = value;}
    public Double getVolume() { return Volume; }

    public void setDuration(Integer value) { Duration = value;}
    public Integer getDuration() { return  Duration; }

}
