package Uniplay.Control;

import Uniplay.Storage.NGCustomGame;
import Uniplay.Storage.NGCustomGameObject;

import java.util.ArrayList;

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

        public Integer getCountDown() {
            return FCountDown;
        }

        public NGCustomGameObject getGameObject() {
            return FGameObject;
        }

    }

    protected ArrayList<GameObjectActivationItem> FItems;

    protected void addItem(NGCustomGameObject aGameObject) {
        GameObjectActivationItem item = new GameObjectActivationItem(aGameObject, CountDown);
        FItems.add(item);
        writeLog(String.format("Game object activation in %d ticks...", item.getCountDown()));
    }

    protected void itemCountDown(GameObjectActivationItem aItem) {
        aItem.Countdown();
        if (aItem.getCountDown() > 0) {
            writeLog(String.format("Game object activation in %d ticks...", aItem.getCountDown()));
        }
    }

    protected void itemMature(GameObjectActivationItem aItem) {
        writeLog(String.format("Game object activation!"));
    }

    protected void DoProcessItems() {
        ArrayList<GameObjectActivationItem> FMatures = new ArrayList<GameObjectActivationItem>();
        for (GameObjectActivationItem item : FItems) {
            itemCountDown(item);
            if (item.getCountDown() <= 0) {
                FMatures.add(item);
                itemMature(item);
            }
        }
        for (GameObjectActivationItem item : FMatures) {
            FItems.remove(item);
        }
        checkNewStartObject();
        if (FItems.size() == 0) {
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

    @Override
    protected void DoHandleTick() {
        super.DoHandleTick();
        DoProcessItems();
    }

    public NGControlMimicGameObjectActivation(NGControlMimicManager aManager, NGCustomGame aGame, String aName) {
        super(aManager, aGame, aName, Kind.temporary);
        FItems = new ArrayList<GameObjectActivationItem>();
        CountDown = 10;
        StartObject = null;
    }

    public Integer CountDown;
    public NGCustomGameObject StartObject;

}
