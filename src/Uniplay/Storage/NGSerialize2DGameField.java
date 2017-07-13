package Uniplay.Storage;

import Uniplay.Base.NGUniplayObjectDefinition;
import Uniwork.Base.NGSerializePropertyItem;

import java.util.concurrent.CopyOnWriteArrayList;

public class NGSerialize2DGameField extends NGUniplayObjectDefinition {

    protected CopyOnWriteArrayList<NGSerialize2DGameFieldLayer> Layers;
    protected CopyOnWriteArrayList<NGSerializePropertyItem> Props;
    protected Double Width;
    protected Double Height;

    public NGSerialize2DGameField() {
        super();
    }

    public void setLayers(CopyOnWriteArrayList<NGSerialize2DGameFieldLayer> Value) {
        Layers = Value;
    }

    public CopyOnWriteArrayList<NGSerialize2DGameFieldLayer> getLayers() {
        return Layers;
    }

    public void setProps(CopyOnWriteArrayList<NGSerializePropertyItem> Value) {
        Props = Value;
    }

    public CopyOnWriteArrayList<NGSerializePropertyItem> getProps() {
        return Props;
    }

    public void setWidth(Double value) {
        Width = value;
    }

    public Double getWidth() {
        return Width;
    }

    public void setHeight(Double value) {
        Height = value;
    }

    public Double getHeight() {
        return Height;
    }

}
