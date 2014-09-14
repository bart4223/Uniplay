package Uniplay.Storage;

public abstract class NGCustomGameCharacter extends NGCustomGameObject {

    protected NGCustomPlayer FPlayer;
    protected Integer FMaxLives;
    protected Integer FCurrentLives;

    public NGCustomGameCharacter(NGCustomGame aGame, NGCustomPlayer aPlayer) {
        super(aGame);
        FPlayer = aPlayer;
        FMaxLives = 0;
        FCurrentLives = 0;
    }

    public NGCustomPlayer getPlayer() {
        return FPlayer;
    }

    public void setMaxLives(Integer aValue) {
        FMaxLives = aValue;
    }

    public Integer getMaxLives() {
        return FMaxLives;
    }

    public Integer getCurrentLives() {
        return FCurrentLives;
    }

    public void reset() {
        FCurrentLives = FMaxLives;
    }

    public void subCurrentLives() {
        FCurrentLives = FCurrentLives - 1;
    }

}
