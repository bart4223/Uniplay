package Uniplay.Storage;

import Uniplay.Base.NGUniplayObjectDefinition;

import java.util.ArrayList;

public class NGDataStorageDefinitionGameItem extends NGUniplayObjectDefinition {

    protected String Name = "";
    protected String Classname = "";
    protected ArrayList<NGDataStorageDefinitionGameObjectItem> GameObjects;

    public NGDataStorageDefinitionGameItem() {
        super();
    }

    public void setName(String value) { Name = value;}
    public String getName() { return Name; }

    public void setClassname(String value) { Classname = value;}
    public String getClassname() { return Classname; }

    public void setGameObjects(ArrayList<NGDataStorageDefinitionGameObjectItem> value) { GameObjects = value;}
    public ArrayList<NGDataStorageDefinitionGameObjectItem> getGameObjects() { return GameObjects; }

}
