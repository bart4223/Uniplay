package Uniplay.Debugger;

import Uniplay.Base.NGUniplayComponent;
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
    protected NGUniplayConsoleStageController FStageController;
    protected Boolean FShowLogEntrySource;

    protected void CreateStage(){
        FStage = new Stage();
        FXMLLoader lXMLLoader = new FXMLLoader(getClass().getResource("NGUniplayConsoleStage.fxml"));
        try {
            lXMLLoader.load();
            FStageController = lXMLLoader.getController();
            FStageController.Console = this;
            Parent lRoot = lXMLLoader.getRoot();
            FStage.setTitle("Uniplay.Console");
            FStage.setScene(new Scene(lRoot, 1780, 200, Color.LIGHTGRAY));
            FStage.setResizable(false);
        }
        catch( Exception e) {
            writeError("CreateStage", e.getMessage());
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

    public NGUniplayConsole(NGUniplayComponent aOwner, String aName) {
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
        FStageController.clearLog();
    }

    public void writeLog(String aLog) {
        FStageController.addLog(aLog);
    }

    public Boolean getShowLogEntrySource() {
        return FShowLogEntrySource;
    }

    public void setShowLogEntrySource(Boolean aWithSource) {
        FShowLogEntrySource = aWithSource;
    }

}
