package main.gaze.devicemanager;

import lombok.extern.slf4j.Slf4j;
import main.SecondStage;

import java.awt.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class TobiiGazeDeviceManager extends AbstractGazeDeviceManager {

    private ExecutorService executorService;

    private PositionPollerRunnable positionPollerRunnable;

    public TobiiGazeDeviceManager() {
        super();
    }

    public void init(SecondStage stage) {
        try {
            positionPollerRunnable = new PositionPollerRunnable(this, stage);
        } catch (AWTException e) {
            e.printStackTrace();
        }
        executorService = Executors.newSingleThreadExecutor();
        executorService.submit(positionPollerRunnable);
    }


    @Override
    public void destroy() {
        positionPollerRunnable.setStopRequested(true);
        ExecutorService executorService = this.executorService;
        if (executorService != null) {
            executorService.shutdown();
        }
    }

}
