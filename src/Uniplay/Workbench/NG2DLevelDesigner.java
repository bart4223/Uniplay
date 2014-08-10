package Uniplay.Workbench;

import Uniplay.Base.NGUniplayObject;
import Uniplay.NGGameEngineConstants;
import Uniplay.Kernel.NGGameEngineMemoryCell;
import Uniplay.Storage.*;
import Uniwork.Base.*;
import Uniwork.Misc.NGStrings;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class NG2DLevelDesigner extends NGLevelDesigner {

    protected Stage FStage;
    protected NG2DLevelDesignerStageController FStageController;
    protected String FImageName;
    protected Integer FGridSize;

    protected void CreateStage(){
        FStage = new Stage();
        FXMLLoader lXMLLoader = new FXMLLoader(getClass().getResource("NG2DLevelDesignerStage.fxml"));
        try {
            lXMLLoader.load();
            FStageController = lXMLLoader.getController();
            FStageController.Designer = this;
            FStageController.Initialize();
            Parent lRoot = lXMLLoader.getRoot();
            FStage.setTitle("Uniplay.Workbench.LevelDesigner");
            FStage.setScene(new Scene(lRoot, 800, 800, Color.WHITE));
            FStage.setResizable(false);
        }
        catch( Exception e) {
            writeError("CreateStage", e.getMessage());
        }
    }

    protected NG2DLevelManager getLevelManager() {
        NG2DLevelManager levelManager = (NG2DLevelManager)ResolveObject(NGGameEngineConstants.CMP_2DLEVEL_MANAGER, NG2DLevelManager.class);
        return levelManager;
    }

    protected void showLevel(NG2DLevel aLevel) {
        FStageController.Level = aLevel;
        FStageController.RenderScene();
    }

    @Override
    protected void DoInitialize() {
        super.DoInitialize();
        CreateStage();
    }

    @Override
    protected void AfterInitialize() {
        super.AfterInitialize();
        FImageName = getConfigurationProperty("Imagename");
        FGridSize = Integer.parseInt(getConfigurationProperty("GridSize"));
    }

    public NG2DLevelDesigner(NGUniplayObject aOwner, String aName) {
        super(aOwner, aName);
        FImageName = "";
        FGridSize = 1;
    }

    public void showStage() {
        FStage.show();
        FStageController.RenderScene();
    }

    public void setStagePosition(int aX, int aY) {
        FStage.setX(aX);
        FStage.setY(aY);
    }

    public NG2DLevel getDesignLevel() {
        NG2DLevelManager manager = getLevelManager();
        NG2DLevel level = manager.getLevel(getLevelName());
        if (level == null) {
            level = manager.addLevel(getLevelName(), new NG2DGameFieldSize(0, 0));
            level.setCaption(getLevelCaption());
        }
        return level;
    }

    public String getImageName() {
        return FImageName;
    }

    public Integer getGridSize() {
        return FGridSize;
    }

    public void loadFromGOF() {
        try
        {
            FileChooser fileChooser = new FileChooser();
            String userDirectoryString = System.getProperty("user.home");
            File userDirectory = new File(userDirectoryString);
            fileChooser.setInitialDirectory(userDirectory);
            fileChooser.setTitle("Load from GOF");
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("GOF files (*.gof)", "*.gof");
            fileChooser.getExtensionFilters().add(extFilter);
            File chosenFile = fileChooser.showOpenDialog(FStage.getOwner());
            if (chosenFile != null) {
                NGObjectDeserializer Deserializer = new NGObjectXMLDeserializerFile(getDesignLevel(), chosenFile.getPath());
                Deserializer.setLogManager(getLogManager());
                Deserializer.deserializeObject();
                showLevel(getDesignLevel());
            }
            else {
                writeLog("Loading as GOF aborted...");
            }
        }
        catch (Exception e) {
            writeError("loadFromGOF", e.getMessage());
        }
    }

    public void setupLevel() {
        NG2DLevelManager manager = getLevelManager();
        NG2DLevel level = manager.getLevel(getLevelName());
        if (level != null) {
            for (NGPropertyItem prop : FProps.getItems()) {
                if (prop.getName().startsWith("GAMEFIELD.")) {
                    String levelname = NGStrings.getStringPos(prop.getName(), "\\.", 2);
                    NG2DGameFieldLayer layer = level.getGameField().getLayer(levelname);
                    if (layer != null) {
                        String op = NGStrings.getStringPos(prop.getName(), "\\.", 4);
                        if (op.equals("COUNT")) {
                            Integer count = 0;
                            for (NGGameEngineMemoryCell cell : layer.getCells()) {
                                if (cell.getValueAsInteger() == prop.getValue()) {
                                    count++;
                                }
                            }
                            layer.setProp(prop.getName(), count);
                            writeLog(String.format("SetupLevel-Prop [COUNT:%s=%d]", prop.getName(), count));
                        }
                        else if (op.equals("POSITION")) {
                            NG2DGamePlayerPosition pos = null;
                            for (NGGameEngineMemoryCell cell : layer.getCells()) {
                                if (cell.getValueAsInteger() == prop.getValue()) {
                                    pos = new NG2DGamePlayerPosition(cell.getAddress().getOffset(), cell.getAddress().getBase());
                                }
                            }
                            if (pos != null) {
                                layer.setProp(prop.getName(), pos);
                                writeLog(String.format("SetupLevel-Prop [POSITION:%s=(%.1f/%.1f)]", prop.getName(), pos.getX(), pos.getY()));
                            }
                        }
                    }
                }
            }
        }
    }

    public void saveAsULF() {
        try
        {
            FileChooser fileChooser = new FileChooser();
            String userDirectoryString = System.getProperty("user.home");
            File userDirectory = new File(userDirectoryString);
            fileChooser.setInitialDirectory(userDirectory);
            fileChooser.setTitle("Save as ULF");
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("ULF files (*.ulf)", "*.ulf");
            fileChooser.getExtensionFilters().add(extFilter);
            File chosenFile = fileChooser.showSaveDialog(FStage.getOwner());
            if (chosenFile != null) {
                NGObjectSerializer Serializer = new NGObjectXMLSerializerFile(getDesignLevel(), NGSerialize2DLevel.class, chosenFile.getPath());
                Serializer.setLogManager(getLogManager());
                Serializer.serializeObject();
            }
            else {
                writeLog("Saving as ULF aborted...");
            }
        }
        catch (Exception e) {
            writeError("loadFromGOF", e.getMessage());
        }
    }

}
