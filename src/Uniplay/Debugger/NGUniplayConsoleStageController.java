package Uniplay.Debugger;

import Uniwork.Misc.NGStrings;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;

import java.net.URL;
import java.util.ResourceBundle;

public class NGUniplayConsoleStageController implements Initializable {

    @FXML
    private TextArea Log;

    public NGUniplayConsole Console;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void addLog(String aText) {
        Log.setText(NGStrings.addString(Log.getText(), aText, "\n"));
        Log.end();
    }

    public void clearLog() {
        Log.clear();
    }

}
