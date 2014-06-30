package Uniplay.Storage;

import Uniplay.Base.NGUniplayObject;
import Uniwork.Base.NGPropertyList;

import java.util.ArrayList;

public class NG2DGameField extends NGUniplayObject {

    protected ArrayList<NG2DGameFieldLayer> FLayers;
    protected NGPropertyList FProps;
    protected NG2DGameFieldSize FSize;

    public NG2DGameField(NG2DGameFieldSize aSize) {
        super();
        FSize = aSize;
        FLayers = new ArrayList<NG2DGameFieldLayer>();
        FProps = new NGPropertyList();
    }

    public NG2DGameFieldLayer addLayer(String aName) {
        NG2DGameFieldLayer layer = new NG2DGameFieldLayer(this, aName);
        FLayers.add(layer);
        return layer;
    }

    public NG2DGameFieldSize getSize() {
        return FSize;
    }

    public void clear() {
        FLayers.clear();
        FProps.clear();
        FSize.clear();
    }

}
