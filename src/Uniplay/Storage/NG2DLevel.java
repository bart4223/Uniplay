package Uniplay.Storage;

import Uniplay.Base.NGUniplayObject;
import Uniwork.Base.NGPropertyList;
import Uniwork.Graphics.NGPoint2D;
import Uniwork.Graphics.NGSerializeGeometryObjectItem;
import Uniwork.Graphics.NGSerializeGeometryObjectList;
import Uniwork.Misc.NGLogManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class NG2DLevel extends NGUniplayObject {

    protected NG2DGameField FGameField;
    protected NGPropertyList FProps;
    protected String FName;
    protected String FCaption;
    protected NGLogManager FLogManager;

    protected void writeLog(String aLog) {
        writeLog(0, aLog);
    }

    protected void writeLog(Integer aDebugLevel, String aLog) {
        if (FLogManager != null) {
            FLogManager.writeLog(aDebugLevel, aLog);
        }
    }

    @Override
    protected void DoAssignFrom(Object aObject) {
        if (aObject instanceof NGSerializeGeometryObjectList) {
            clear();
            NGSerializeGeometryObjectList sgol = (NGSerializeGeometryObjectList)aObject;
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
            writeLog(String.format("Game field size width:%.0f, height:%.0f", getGameFieldDSize().getWidth(), getGameFieldDSize().getHeight()));
            Collections.sort(Points);
            NG2DGameFieldLayer layer = FGameField.addLayer("DEFAULT");
            Iterator lItr = Points.iterator();
            NGPoint2D point = null;
            if (lItr.hasNext())
                point = (NGPoint2D)lItr.next();
            int pointsadded = 0;
            for (double y = minY; y <= maxY; y++) {
                for (double x = minX; x <= maxX; x++) {
                    if (point != null && point.getX() == x && point.getY() == y) {
                        String str = point.getID().substring(point.getID().indexOf('_') + 1, point.getID().length());
                        layer.addCell(Integer.parseInt(str));
                        pointsadded++;
                        writeLog(10, String.format("%.0f,%.0f", point.getX(), point.getY()));
                        point = null;
                        while(lItr.hasNext()) {
                            point = (NGPoint2D)lItr.next();
                            if (point.getX() != x || point.getY() != y)
                                break;
                            else
                                writeLog(0, String.format("Duplex Point %.0f,%.0f with ID: %s", point.getX(), point.getY(), point.getID()));
                        }
                    }
                    else {
                        layer.addCell(0);
                    }
                }
            }
            writeLog(String.format("Points added: %d", pointsadded));
            writeLog(String.format("Cell count: %d", layer.getCellCount()));
        }
    }

    public NG2DLevel(String aName, NG2DGameFieldSize aSize) {
        super();
        FName = aName;
        FGameField = new NG2DGameField(aSize);
        FProps = new NGPropertyList();
        FCaption = "";
    }

    public String getName() {
        return FName;
    }

    public String getCaption() {
        return FCaption;
    }

    public void setCaption(String aCaption) {
        FCaption = aCaption;
    }

    public void clear() {
        FGameField.clear();
        FProps.clear();
    }

    public void setLogManager(NGLogManager aLogManager) {
        FLogManager = aLogManager;
    }

    public NGLogManager getLogManager() {
        return FLogManager;
    }

    public void setGameFieldSize(double aWidth, double aHeight) {
        FGameField.getSize().setWidth(aWidth);
        FGameField.getSize().setHeight(aHeight);
    }

    public NG2DGameFieldSize getGameFieldDSize() {
        return FGameField.getSize();
    }

}
