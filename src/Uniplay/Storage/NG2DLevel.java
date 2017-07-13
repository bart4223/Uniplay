package Uniplay.Storage;

import Uniplay.Kernel.*;
import Uniwork.Base.NGSerializePropertyItem;
import Uniwork.Graphics.NGPoint2D;
import Uniwork.Graphics.NGSerializeGeometryObjectItem;
import Uniwork.Graphics.NGSerializeGeometryObjectList;

import java.util.Collections;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

public class NG2DLevel extends NGCustomLevel {

    protected NG2DGameField FGameField;

    protected void assignFromGOF(NGSerializeGeometryObjectList sgol) {
        CopyOnWriteArrayList<NGPoint2D> Points = new CopyOnWriteArrayList<NGPoint2D>();
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
                    NGGameEngineMemoryCell cell = layer.addCell(y, x, NGGameEngineMemoryIntegerCellValue.class);
                    cell.setValueAsInteger(Integer.parseInt(str));
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
                    NGGameEngineMemoryCell cell = layer.addCell(y, x, NGGameEngineMemoryIntegerCellValue.class);
                    cell.setValueAsInteger(0);
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
        else if (aObject instanceof NGSerialize2DLevel) {
            assignFromULF((NGSerializeLevel)aObject);
        }
    }

    @Override
    protected void assignToULF(NGSerializeLevel aLevel) {
        super.assignToULF(aLevel);
        if (aLevel instanceof NGSerialize2DLevel) {
            NGSerialize2DLevel level = (NGSerialize2DLevel)aLevel;
            level.setCaption(getCaption());
            level.setGameField(new NGSerialize2DGameField());
            level.getGameField().setHeight(getGameFieldSize().getHeight());
            level.getGameField().setWidth(getGameFieldSize().getWidth());
            level.getGameField().setProps(new CopyOnWriteArrayList<NGSerializePropertyItem>());
            getProps().AssignTo(level.getProps());
            level.getGameField().setLayers(new CopyOnWriteArrayList<NGSerialize2DGameFieldLayer>());
            for (NG2DGameFieldLayer layer : getGameField().getLayers()) {
                NGSerialize2DGameFieldLayer sl = new NGSerialize2DGameFieldLayer();
                sl.setName(layer.getName());
                sl.setProps(new CopyOnWriteArrayList<NGSerializePropertyItem>());
                layer.getProps().AssignTo(sl.getProps());
                sl.setCells(new CopyOnWriteArrayList<NGSerializeGameEngineMemoryCell>());
                level.getGameField().getLayers().add(sl);
                for (NGGameEngineMemoryCell cell : layer.getCells()) {
                    NGSerializeGameEngineMemoryCell sc = new NGSerializeGameEngineMemoryCell();
                    sc.setValue(cell.getValueAsInteger());
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
            setCaption(level.getCaption());
            getGameFieldSize().setHeight(level.getGameField().getHeight());
            getGameFieldSize().setWidth(level.getGameField().getWidth());
            getProps().AssignFrom(level.getProps());
            for (NGSerialize2DGameFieldLayer sl : level.getGameField().getLayers()) {
                Class cl;
                try {
                    cl = getClass().getClassLoader().loadClass(sl.getCellsclassname());
                }
                catch (Exception e) {
                    cl = NGGameEngineMemoryIntegerCellValue.class;
                }
                NG2DGameFieldLayer layer = getGameField().addLayer(sl.getName());
                layer.getProps().AssignFrom(sl.getProps());
                for (NGSerializeGameEngineMemoryCell sc : sl.getCells()) {
                    NGGameEngineMemoryCell cell = layer.addCell(sc.getBase(), sc.getOffset(), cl);
                    cell.setValueAsInteger(sc.getValue());
                }
            }
        }
    }

    public NG2DLevel(String aName) {
        this(aName, new NG2DGameFieldSize(0, 0));
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
