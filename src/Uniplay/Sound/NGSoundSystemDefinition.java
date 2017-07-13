package Uniplay.Sound;

import Uniplay.Kernel.NGGameEngineModuleDefinition;

import java.util.concurrent.CopyOnWriteArrayList;

public class NGSoundSystemDefinition extends NGGameEngineModuleDefinition {

    protected CopyOnWriteArrayList<NGSoundSystemDefinitionSoundItem> Sounds;

    public NGSoundSystemDefinition() {
        super();
    }

    public void setSounds(CopyOnWriteArrayList<NGSoundSystemDefinitionSoundItem> value) { Sounds = value;}
    public CopyOnWriteArrayList<NGSoundSystemDefinitionSoundItem> getSounds() { return Sounds; }

}
