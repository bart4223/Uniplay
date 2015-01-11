package Uniplay.Storage;

import Uniplay.Kernel.NGGameEngineModuleDefinition;

import java.util.ArrayList;

public class NGDataStorageDefinition extends NGGameEngineModuleDefinition {

    protected ArrayList<NGDataStorageDefinitionGameItem> Games;

    public NGDataStorageDefinition() {
        super();
    }

    public void setGames(ArrayList<NGDataStorageDefinitionGameItem> value) { Games = value;}
    public ArrayList<NGDataStorageDefinitionGameItem> getGames() { return Games; }

}
