package Uniplay.Sound;

import Uniplay.Kernel.NGGameEngineModuleDefinition;

import java.util.ArrayList;

public class NGSoundSystemDefinition extends NGGameEngineModuleDefinition {

    protected ArrayList<NGSoundSystemDefinitionSoundItem> Sounds;

    public NGSoundSystemDefinition() {
        super();
    }

    public void setSounds(ArrayList<NGSoundSystemDefinitionSoundItem> value) { Sounds = value;}
    public ArrayList<NGSoundSystemDefinitionSoundItem> getSounds() { return Sounds; }

}
