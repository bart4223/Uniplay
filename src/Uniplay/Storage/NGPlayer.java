package Uniplay.Storage;

import Uniplay.Base.NGUniplayObject;

public class NGPlayer extends NGUniplayObject {

    protected String FName;
    protected String FNickname;

    public NGPlayer(String aName) {
        super();
        FName = aName;
        FNickname = "";
    }

    public String getName() {
        return FName;
    }

    public void setNickname(String aNickname) {
        FNickname = aNickname;
    }

    public String getNickname() {
        return FNickname;
    }

}
