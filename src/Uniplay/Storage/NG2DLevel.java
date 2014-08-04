package Uniplay.Storage;

import Uniplay.Kernel.NGGameEngineMemoryCell;
import Uniplay.Kernel.NGSerializeGameEngineMemoryCell;
import Uniwork.Base.NGSerializePropertyItem;
import Uniwork.Graphics.NGPoint2D;
import Uniwork.Graphics.NGSerializeGeometryObjectItem;
import Uniwork.Graphics.NGSerializeGeometryObjectList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class NG2DLevel extends NGCustomLevel {

    protected NG2DGameField FGameField;

    protected void assignFromGOF(NGSerializeGeometryObjectList sgol) {
        ArrayList<NGPoint2D> Points = new ArrayList<NGPoint2D>();
        double minX = -1;
        double maxX = 0;
        double minY = -1;
        double maxY = 0;
        for (NGSerializeGeometryObjectItem sgoi : sgol.getSGOS()) {
            if (sgoi.getGO() instanceof NGPoint2D) {
                NGPoint2D goPoint = (NGPoint2D)sgoi.getGO();
                Points.add(goPoint);
                if ((minX == -1) || (minX > goPoint.getX())) {
                    minX = goPoint.getX();
                }
                if (maxX < goPoint.getX()) {
                    maxX = goPoint.getX();
                }
                if ((minY == -1) || (minY > goPoint.getY())) {
                    minY = goPoint.getY();
                }
                if (maxY < goPoint.getY()) {
                    maxY = goPoint.getY();
                }
            }
        }
        writeLog(String.format("Point count: %d", Points.size()));
        writeLog(String.format("minX:%.0f, minY:%.0f / maxX:%.0f, maxY:%.0f", minX, minY, maxX, maxY));
        setGameFieldSize(maxX - minX + 1, maxY - minY + 1);
        writeLog(String.format("Game field size width:%.0f, height:%.0f", getGameFieldSize().getWidth(), getGameFieldSize().getHeight()));
        Collections.sort(Points);
        NG2DGameFieldLayer layer = FGameField.addLayer("DEFAULT");
        Iterator lItr = Points.iterator();
        NGPoint2D point = null;
        if (lItr.hasNext())
            point = (NGPoint2D)lItr.next();
        int pointsadded = 0;
        for (int y = 0; y <= maxY - minY; y++) {
            for (int x = 0; x <= maxX - minX; x++) {
                if (point != null && point.getX() - minX == x && point.getY() - minY == y) {
                    String str = point.getID().substring(point.getID().indexOf('_') + 1, point.getID().length());
                    layer.addCell(Integer.parseInt(str), y, x);
                    pointsadded++;
                    writeLog(10, String.format("%.0f,%.0f", point.getX(), point.getY()));
                    point = null;
                    while(lItr.hasNext()) {
                        point = (NGPoint2D)lItr.next();
                        if (point.getX() - minX != x || point.getY() - minY != y)
                            break;
                        else
                            writeLog(0, String.format("Duplex Point %.0f,%.0f with ID: %s", point.getX(), point.getY(), point.getID()));
                    }
                }
                else {
                    layer.addCell(0, y, x);
                }
            }
        }
        writeLog(String.format("Points added: %d", pointsadded));
        writeLog(String.format("Cell count: %d", layer.getCellCount()));
    }

    @Override
    protected void DoAssignFrom(Object aObject) {
        clear();
        if (aObject instanceof NGSerializeGeometryObjectList) {
            assignFromGOF((NGSerializeGeometryObjectList)aObject);
        }
    }

    @Override
    protected void assignToULF(NGSerializeLevel aLevel) {
        super.assignToULF(aLevel);
        if (aLevel instanceof NGSerialize2DLevel) {
            NGSerialize2DLevel level = (NGSerialize2DLevel)aLevel;
            level.setGameField(new NGSerialize2DGameField());
            level.getGameField().setHeight(getGameFieldSize().getHeight());
            level.getGameField().setWidth(getGameFieldSize().getWidth());
            level.getGameField().setProps(new ArrayList<NGSerializePropertyItem>());
            getProps().AssignTo(level.getGameField().getProps());
            level.getGameField().setLayers(new ArrayList<NGSerialize2DGameFieldLayer>());
            for (NG2DGameFieldLayer layer : getGameField().getLayers()) {
                NGSerialize2DGameFieldLayer sl = new NGSerialize2DGameFieldLayer();
                sl.setName(layer.getName());
                sl.setProps(new ArrayList<NGSerializePropertyItem>());
                layer.getProps().AssignTo(sl.getProps());
                sl.setCells(new ArrayList<NGSerializeGameEngineMemoryCell>());
                level.getGameField().getLayers().add(sl);
                for (NGGameEngineMemoryCell cell : layer.getCells()) {
                    NGSerializeGameEngineMemoryCell sc = new NGSerializeGameEngineMemoryCell();
                    sc.setValue(cell.getValue());
                    sc.setBase(cell.getAddress().getBase());
                    sc.setOffset(cell.getAddress().getOffset());
                    sl.getCells().add(sc);
                }
            }
        }
    }

    @Override
    protected void assignFromULF(NGSerializeLevel aLevel) {
        super.assignFromULF(aLevel);
        if (aLevel instanceof NGSerialize2DLevel) {
            NGSerialize2DLevel level = (NGSerialize2DLevel)aLevel;
            getGameFieldSize().setHeight(level.getGameField().getHeight());
            getGameFieldSize().setWidth(level.getGameField().getWidth());
            getProps().AssignFrom(level.getGameField().getProps());
            for (NGSerialize2DGameFieldLayer sl : level.getGameField().getLayers()) {
                NG2DGameFieldLayer layer = getGameField().addLayer(sl.getName());
                layer.getProps().AssignFrom(sl.getProps());
                for (NGSerializeGameEngineMemoryCell sc : sl.getCells()) {
                    layer.addCell(sc.getValue(), sc.getBase(), sc.getOffset());
                }
            }
        }
    }

    public NG2DLevel(String aName, NG2DGameFieldSize aSize) {
        super(aName);
        FGameField = new NG2DGameField(aSize);
    }

    @Override
    public void clear() {
        super.clear();
        FGameField.clear();
    }

    public void setGameFieldSize(double aWidth, double aHeight) {
        FGameField.getSize().setWidth(aWidth);
        FGameField.getSize().setHeight(aHeight);
    }

    public NG2DGameFieldSize getGameFieldSize() {
        return FGameField.getSize();
    }

    public NG2DGameField getGameField() {
        return FGameField;
    }

}
