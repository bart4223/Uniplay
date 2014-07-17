package Uniplay.Storage;

import Uniplay.Base.NGUniplayObjectDefinition;
import Uniwork.Base.NGSerializePropertyItem;

import java.util.ArrayList;

public class NGSerialize2DGameField extends NGUniplayObjectDefinition {

    protected ArrayList<NGSerialize2DGameFieldLayer> Layers;
    protected ArrayList<NGSerializePropertyItem> Props;
    protected Double Width;
    protected Double Height;

    public NGSerialize2DGameField() {
        super();
    }

    public void setLayers(ArrayList<NGSerialize2DGameFieldLayer> Value) {
        Layers = Value;
    }

    public ArrayList<NGSerialize2DGameFieldLayer> getLayers() {
        return Layers;
    }

    public void setProps(ArrayList<NGSerializePropertyItem> Value) {
        Props = Value;
    }

    public ArrayList<NGSerializePropertyItem> getProps() {
        return Props;
    }

    public void setWidth(Double value) {
        Width = value;
    }

    public Double getWidth() {
        return Width;
    }

    public void setHeigth(Double value) {
        Height = value;
    }

    public Double getHeight() {
        return Height;
    }

}
