package Uniplay.Storage;

public class NGPlayer extends NGCustomPlayer {

    protected String FNickname;

    public NGPlayer(String aName, String aNickName) {
        super(aName);
        FNickname = aNickName;
    }

    public void setNickname(String aNickname) {
        FNickname = aNickname;
    }

    public String getNickname() {
        return FNickname;
    }

}
