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
        perfectLayout();
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
            FControlStage.setTitle("Uniplay.Workbench.Control");
            FControlStage.setScene(new Scene(lRoot, 800, 50, Color.WHITE));
            FControlStage.setResizable(false);
        }
        catch( Exception e) {
            writeError("CreateWorkbenchStage", e.getMessage());
        }
    }

    protected void perfectLayout() {
        FControlStage.setX(500);
        FControlStage.setY(150);
    }

    public NGWorkbenchManager(NGUniplayObject aOwner, String aName) {
        super(aOwner, aName);
    }

}
