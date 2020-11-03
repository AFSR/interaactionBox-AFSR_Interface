package gaze.devicemanager;

import gaze.GazeMotionListener;
import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;
import javafx.scene.Node;

import java.util.function.Supplier;

public interface GazeDeviceManager {

    void init(Supplier<Dimension2D> currentScreenDimensionSupplier, Supplier<Point2D> currentScreenPositionSupplier);

    void destroy();

    void addGazeMotionListener(GazeMotionListener listener);

    void removeGazeMotionListener(GazeMotionListener listener);

    void addEventFilter(Node gs);

    void addEventHandler(Node gs);

    void removeEventFilter(Node gs);

    void removeEventHandler(Node gs);

    void clear();

    void setInReplayMode(boolean b);
}
