package Uniplay.Control;

import Uniplay.Storage.NGCustomGame;
import Uniplay.Storage.NGCustomGameObject;

import java.util.concurrent.CopyOnWriteArrayList;

public class NGControlMimicGameObjectActivation extends NGControlMimicPeriodicAction {

    protected class GameObjectActivationItem {

        protected NGCustomGameObject FGameObject;
        protected Integer FCountDown;

        public GameObjectActivationItem(NGCustomGameObject aGameObject, Integer aCountdown) {
            FGameObject = aGameObject;
            FCountDown = aCountdown;
        }

        public void Countdown() {
            FCountDown = FCountDown - 1;
        }

        public void setCountDown(Integer aValue) {
            FCountDown = aValue;
        }

        public Integer getCountDown() {
            return FCountDown;
        }

        public NGCustomGameObject getGameObject() {
            return FGameObject;
        }

    }

    protected CopyOnWriteArrayList<GameObjectActivationItem> FItems;
    CopyOnWriteArrayList<GameObjectActivationItem> FMatureItems;

    protected void addItem(NGCustomGameObject aGameObject) {
        GameObjectActivationItem item = new GameObjectActivationItem(aGameObject, CountDown);
        FItems.add(item);
        writeLog(String.format("Game object activation in %d ticks...", item.getCountDown()));
    }

    protected void addMatureItem(GameObjectActivationItem aItem) {
        aItem.setCountDown(CountDown / 2);
        FMatureItems.add(aItem);
    }

    protected void itemMatureStart(GameObjectActivationItem aItem) {
        writeLog(String.format("Game object mature start..."));
    }

    protected void itemMatureEnd(GameObjectActivationItem aItem) {
        writeLog(String.format("Game object mature end"));
    }

    protected void itemActivationCountDown(GameObjectActivationItem aItem) {
        aItem.Countdown();
        if (aItem.getCountDown() > 0) {
            writeLog(String.format("Game object activation in %d ticks...", aItem.getCountDown()));
        }
    }

    protected void itemMatureCountDown(GameObjectActivationItem aItem) {
        aItem.Countdown();
        if (aItem.getCountDown() > 0) {
            writeLog(String.format("Game object mature in %d ticks...", aItem.getCountDown()));
        }
    }

    protected void DoProcessItems() {
        for (GameObjectActivationItem item : FItems) {
            itemActivationCountDown(item);
            if (item.getCountDown() <= 0) {
                addMatureItem(item);
                itemMatureStart(item);
            }
        }
        for (GameObjectActivationItem item : FMatureItems) {
            FItems.remove(item);
        }
        checkNewStartObject();
        checkMatureItems();
        if (FItems.size() == 0 && FMatureItems.size() == 0) {
            Deactivate();
        }
    }

    protected void checkNewStartObject() {
        if (StartObject != null) {
            Boolean add = true;
            for (GameObjectActivationItem item : FItems) {
                add = !item.getGameObject().equals(StartObject);
                if (add) {
                  break;
                }
            }
            if (add) {
                addItem(StartObject);
            }
            StartObject = null;
        }
    }

    protected void checkMatureItems() {
        CopyOnWriteArrayList<GameObjectActivationItem> items = new CopyOnWriteArrayList<GameObjectActivationItem>();
        for (GameObjectActivationItem item : FMatureItems) {
            itemMatureCountDown(item);
            if (item.getCountDown() <= 0) {
                itemMatureEnd(item);
                items.add(item);
            }
        }
        for (GameObjectActivationItem item : items) {
            FMatureItems.remove(item);
        }
    }

    @Override
    protected void DoHandleTick() {
        super.DoHandleTick();
        DoProcessItems();
    }

    public NGControlMimicGameObjectActivation(NGControlMimicManager aManager, NGCustomGame aGame, String aName) {
        super(aManager, aGame, aName, Kind.temporary);
        FItems = new CopyOnWriteArrayList<GameObjectActivationItem>();
        FMatureItems = new CopyOnWriteArrayList<GameObjectActivationItem>();
        CountDown = 10;
        StartObject = null;
    }

    public Integer CountDown;
    public NGCustomGameObject StartObject;

}
