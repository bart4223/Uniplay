package Uniplay.Storage;

import Uniplay.Base.NGUniplayObject;

public class NGPlayerStatisticItem extends NGUniplayObject {

    protected NGCustomPlayerStatistic FStatistic;

    public NGPlayerStatisticItem(NGCustomPlayerStatistic aStatistic) {
        super();
        FStatistic = aStatistic;
    }

    public NGCustomPlayerStatistic getStatistic() {
        return FStatistic;
    }

}
