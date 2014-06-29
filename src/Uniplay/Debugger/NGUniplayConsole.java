package Uniplay.Debugger;

import Uniplay.Base.NGUniplayComponent;
import Uniplay.Base.NGUniplayObject;
import Uniwork.Misc.NGLogEvent;
import Uniwork.Misc.NGLogEventListener;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class NGUniplayConsole extends NGUniplayComponent implements NGLogEventListener {

    public static final String FMT_DATETIME = "YYYY/MM/dd HH:mm:ss";

    protected Stage FStage;
    protected NGUniplayConsoleController FController;
    protected Boolean FShowLogEntrySource;

    protected void CreateStage(){
        FStage = new Stage();
        FXMLLoader lXMLLoader = new FXMLLoader(getClass().getResource("NGUniplayConsoleStage.fxml"));
        try {
            lXMLLoader.load();
            FController = lXMLLoader.getController();
            Parent lRoot = lXMLLoader.getRoot();
            FStage.setTitle("Uniplay.Console");
            FStage.setScene(new Scene(lRoot, 800, 200, Color.LIGHTGRAY));
            FStage.setResizable(false);
        }
        catch( Exception e) {
            e.printStackTrace();
        }
    }

    protected void showStage() {
        FStage.show();
    }

    @Override
    protected void DoInitialize() {
        super.DoInitialize();
        CreateStage();
    }

    @Override
    protected void AfterInitialize() {
        super.AfterInitialize();
        showStage();
    }

    public NGUniplayConsole(NGUniplayObject aOwner, String aName) {
        super(aOwner, aName);
        FShowLogEntrySource = false;
    }

    protected void setStagePosition(int aX, int aY) {
        FStage.setX(aX);
        FStage.setY(aY);
    }

    @Override
    public void handleAddLog(NGLogEvent e) {
        writeLog(e.LogEntry.GetFullAsString(FMT_DATETIME, getShowLogEntrySource()));
    }

    @Override
    public void handleClearLog() {
        FController.clearLog();
    }

    public void writeLog(String aLog) {
        FController.addLog(aLog);
    }

    public Boolean getShowLogEntrySource() {
        return FShowLogEntrySource;
    }

    public void setShowLogEntrySource(Boolean aWithSource) {
        FShowLogEntrySource = aWithSource;
    }

}
