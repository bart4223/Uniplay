package Uniplay.Storage;

import Uniplay.Base.NGUniplayObject;

public abstract class NGGamePlayerItem extends NGUniplayObject {

    protected NGCustomPlayer FPlayer;
    protected Integer FMaxLives;
    protected Integer FCurrentLives;

    public NGGamePlayerItem(NGCustomPlayer aPlayer, Integer aMaxLives) {
        super();
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
