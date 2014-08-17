package Uniplay.Storage;

import Uniplay.Base.NGUniplayComponent;
import Uniplay.Base.NGUniplayObject;

import java.util.ArrayList;

public class NGPlayerStatisticManager extends NGUniplayComponent {

    protected ArrayList<NGPlayerStatisticItem> FItems;

    public NGPlayerStatisticManager(NGUniplayObject aOwner, String aName) {
        super(aOwner, aName);
        FItems = new ArrayList<NGPlayerStatisticItem>();
    }

    public NGPlayerStatisticItem addStatistic(NGCustomPlayerStatistic aStatistic) {
        NGPlayerStatisticItem item = new NGPlayerStatisticItem(aStatistic);
        FItems.add(item);
        writeLog(String.format("Player statistic [%s] for player [%s] added.", aStatistic.getName(), aStatistic.getPlayer().getName()));
        return item;
    }

    public NGCustomPlayerStatistic getPlayerStatistic(String aPlayerName, String aStatisticname) {
        for (NGPlayerStatisticItem item : FItems) {
            NGCustomPlayerStatistic statistic = item.getStatistic();
            if (statistic.getPlayer().getName().equals(aPlayerName) && statistic.getName().equals(aStatisticname)) {
                return statistic;
            }
        }
        return null;
    }

    public NGCustomPlayerStatistic getPlayerStatistic(NGPlayer aPlayer, String aStatisticname) {
        for (NGPlayerStatisticItem item : FItems) {
            NGCustomPlayerStatistic statistic = item.getStatistic();
            if (statistic.getPlayer().equals(aPlayer) && statistic.getName().equals(aStatisticname)) {
                return statistic;
            }
        }
        return null;
    }

}
