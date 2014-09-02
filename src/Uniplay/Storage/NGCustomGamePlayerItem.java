package Uniplay.Storage;

public abstract class NGCustomGamePlayerItem extends NGCustomGameObjectItem {

    protected NGCustomPlayer FPlayer;
    protected Integer FMaxLives;
    protected Integer FCurrentLives;

    public NGCustomGamePlayerItem(NGCustomGame aGame, NGCustomPlayer aPlayer, Integer aMaxLives) {
        super(aGame);
        FPlayer = aPlayer;
        FMaxLives = aMaxLives;
        reset();
    }

    public NGCustomPlayer getPlayer() {
        return FPlayer;
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
