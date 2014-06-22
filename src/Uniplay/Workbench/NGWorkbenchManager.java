package Uniplay.Workbench;

import Uniplay.Base.NGUniplayComponent;
import Uniplay.Base.NGUniplayObject;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class NGWorkbenchManager extends NGUniplayComponent {

    protected Stage FControlStage;
    protected NGWorkbenchControlController FControlController;

    @Override
    protected void DoInitialize() {
        super.DoInitialize();
        CreateWorkbenchStage();
    }

    @Override
    protected void AfterInitialize() {
        super.AfterInitialize();
        showControlStage();
    }

    protected void showControlStage() {
        FControlStage.show();
    }

    protected void CreateWorkbenchStage(){
        FControlStage = new Stage();
        FXMLLoader lXMLLoader = new FXMLLoader(getClass().getResource("NGWorkbenchControlStage.fxml"));
        try {
            lXMLLoader.load();
            FControlController = (NGWorkbenchControlController)lXMLLoader.getController();
            FControlController.Manager = this;
            Parent lRoot = lXMLLoader.getRoot();
            FControlStage.setTitle("Workbench.Control");
            FControlStage.setScene(new Scene(lRoot, 500, 500, Color.WHITE));
            FControlStage.setResizable(false);
            FControlStage.setX(300);
            FControlStage.setY(250);
        }
        catch( Exception e) {
            writeError("CreateWorkbenchStage", e.getMessage());
        }
    }

    public NGWorkbenchManager(NGUniplayObject aOwner, String aName) {
        super(aOwner, aName);
    }

}
