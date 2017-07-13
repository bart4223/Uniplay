package Uniplay.Storage;

import Uniplay.Base.NGUniplayObjectDefinition;

import java.util.concurrent.CopyOnWriteArrayList;

public class NGDataStorageDefinitionGameItem extends NGUniplayObjectDefinition {

    protected String Name = "";
    protected String Classname = "";
    protected CopyOnWriteArrayList<NGDataStorageDefinitionGameObjectItem> GameObjects;

    public NGDataStorageDefinitionGameItem() {
        super();
    }

    public void setName(String value) { Name = value;}
    public String getName() { return Name; }

    public void setClassname(String value) { Classname = value;}
    public String getClassname() { return Classname; }

    public void setGameObjects(CopyOnWriteArrayList<NGDataStorageDefinitionGameObjectItem> value) { GameObjects = value;}
    public CopyOnWriteArrayList<NGDataStorageDefinitionGameObjectItem> getGameObjects() { return GameObjects; }

}
