package Uniplay.Workbench;

import Uniplay.Base.NGUniplayComponent;
import Uniplay.Base.NGUniplayObject;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class NGLevelDesigner extends NGUniplayComponent {

    protected Stage FStage;
    protected NGLevelDesignerStageController FStageController;

    protected void CreateStage(){
        FStage = new Stage();
        FXMLLoader lXMLLoader = new FXMLLoader(getClass().getResource("NGLevelDesignerStage.fxml"));
        try {
            lXMLLoader.load();
            FStageController = lXMLLoader.getController();
            FStageController.Designer = this;
            Parent lRoot = lXMLLoader.getRoot();
            FStage.setTitle("Uniplay.Workbench.LevelDesigner");
            FStage.setScene(new Scene(lRoot, 800, 1050, Color.WHITE));
            FStage.setResizable(false);
        }
        catch( Exception e) {
            writeError("CreateStage", e.getMessage());
        }
    }

    @Override
    protected void DoInitialize() {
        super.DoInitialize();
        CreateStage();
    }

    public NGLevelDesigner(NGUniplayObject aOwner, String aName) {
        super(aOwner, aName);
    }

    @Override
    protected void AfterInitialize() {
        super.AfterInitialize();
        showStage();
    }

    public void showStage() {
        FStage.show();
    }

    public void setStagePosition(int aX, int aY) {
        FStage.setX(aX);
        FStage.setY(aY);
    }

}
