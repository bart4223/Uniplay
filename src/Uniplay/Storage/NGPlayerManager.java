package Uniplay.Storage;

import Uniplay.Base.NGUniplayComponent;
import Uniplay.Base.NGUniplayObject;
import Uniplay.NGGameEngineConstants;

import java.util.ArrayList;

public class NGPlayerManager extends NGUniplayComponent {

    protected ArrayList<NGPlayerItem> FPlayers;
    protected NGPlayer FCurrentPlayer;

    protected NGPlayer getPlayer(String aName) {
        for (NGPlayerItem item : FPlayers) {
            NGPlayer player = item.getPlayer();
            if (player.getName().equals(aName)) {
                return player;
            }
        }
        return null;
    }

    protected NGPlayer setCurrentPlayer(NGPlayer aPlayer) {
        if (FCurrentPlayer != null && FCurrentPlayer.equals(aPlayer))
            return FCurrentPlayer;
        FCurrentPlayer = aPlayer;
        if (FCurrentPlayer != null)
            writeLog(String.format("Current player is %s[%s].", FCurrentPlayer.getName(), FCurrentPlayer.getNickname()));
        else
            writeLog("No current player available!");
        return FCurrentPlayer;
    }

    @Override
    protected void CreateSubComponents() {
        super.CreateSubComponents();
        NGUniplayComponent component = new NGPlayerStatisticManager(this, NGGameEngineConstants.CMP_PLAYER_STATISTIC_MANAGER);
        addSubComponent(component);
    }

    protected NGPlayerStatisticManager getStatisticManager() {
        return (NGPlayerStatisticManager)ResolveObject(NGGameEngineConstants.CMP_PLAYER_STATISTIC_MANAGER, NGPlayerStatisticManager.class);
    }

    public NGPlayerManager(NGUniplayObject aOwner, String aName) {
        super(aOwner, aName);
        FPlayers = new ArrayList<NGPlayerItem>();
    }

    public NGPlayer newPlayer(Class aPlayerClass, String aName, String aNickname) {
        try {
            NGPlayer player = (NGPlayer)(aPlayerClass.getConstructor(String.class, String.class).newInstance(aName, aNickname));
            FPlayers.add(new NGPlayerItem(player));
            writeLog(String.format("Player %s[%s] added.", player.getName(), player.getNickname()));
            return player;
        }
        catch (Exception e) {
            writeError("newPlayer", e.getMessage());
            return null;
        }
    }

    public NGPlayerStatisticItem addStatistic(NGCustomPlayerStatistic aStatistic) {
        return getStatisticManager().addStatistic(aStatistic);
    }

    public NGCustomPlayerStatistic getStatistic(NGPlayer aPlayer, String aName) {
        return getStatisticManager().getPlayerStatistic(aPlayer, aName);
    }

    public NGPlayer setCurrentPlayer(String aName) {
        return setCurrentPlayer(getPlayer(aName));
    }

    public NGPlayer getCurrentPlayer() {
        return FCurrentPlayer;
    }

    public ArrayList<NGPlayerItem> getPlayers() {
        return FPlayers;
    }

}
