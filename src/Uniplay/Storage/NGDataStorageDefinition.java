package Uniplay.Storage;

import Uniplay.Kernel.NGGameEngineModuleDefinition;

import java.util.concurrent.CopyOnWriteArrayList;

public class NGDataStorageDefinition extends NGGameEngineModuleDefinition {

    protected CopyOnWriteArrayList<NGDataStorageDefinitionGameItem> Games;

    public NGDataStorageDefinition() {
        super();
    }

    public void setGames(CopyOnWriteArrayList<NGDataStorageDefinitionGameItem> value) { Games = value;}
    public CopyOnWriteArrayList<NGDataStorageDefinitionGameItem> getGames() { return Games; }

}
