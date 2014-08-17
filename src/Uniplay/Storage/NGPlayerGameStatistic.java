package Uniplay.Storage;

public class NGPlayerGameStatistic extends NGCustomPlayerStatistic {

    public Integer FTotal;
    public Integer FVictories;

    public NGPlayerGameStatistic(NGPlayer aPlayer, String aName) {
        super(aPlayer, aName);
        FTotal = 0;
        FVictories = 0;
    }

    public Integer getTotal() {
        return FTotal;
    }

    public void setTotal(Integer aTotal) {
        FTotal = aTotal;
    }

    public void incTotal() {
        FTotal = FTotal + 1;
    }

    public Integer getVictories() {
        return FVictories;
    }

    public void setVictories(Integer aVictories) {
        FVictories = aVictories;
    }

    public void incVictories() {
        FVictories = FVictories + 1;
    }

    @Override
    public String toString() {
        return String.format("Player statistic [%s] from [%s]: total (%d), victories (%d).", getName(), getPlayer().getName(), getTotal(), getVictories());
    }

}
