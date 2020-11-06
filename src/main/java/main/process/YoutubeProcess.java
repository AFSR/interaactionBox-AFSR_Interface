package main.process;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import main.ProgressButton;
import main.SecondStage;
import main.gaze.devicemanager.AbstractGazeDeviceManager;
import main.gaze.devicemanager.TobiiGazeDeviceManager;

import java.io.File;
import java.io.IOException;

public class YoutubeProcess implements AppProcess {

    ProcessBuilder pb;

    @Override
    public Process start() {
        try {
            return pb.inheritIO().start();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void init() {
        pb = new ProcessBuilder("C:/Program Files (x86)/Google/Chrome/Application/chrome.exe",
                // "--fullscreen","--app="+"https://www.youtube.com");
                //"--new-window", "--disable-pinch", "--overscroll-history-navigation=0",
                "--kiosk", "--window-position=0,0", "--disable-gpu", "--no-sandbox", "--fullscreen", "https://www.youtube.com");
        // "--start-fullscreen", "--new-window","--window-position=0,0", "--disable-gpu", "--no-sandbox",   "https://www.youtube.com");
    }

    @Override
    public ProgressButton createButton(BorderPane borderPane, SecondStage stage, AbstractGazeDeviceManager tgdm) {
        ProgressButton pb = new ProgressButton();
        File f = new File("src/ressources/images/yt.png");
        ImageView logo = new ImageView(new Image("file:" + f.getAbsolutePath()));
        pb.getButton().setRadius(100);
        logo.setFitWidth(pb.getButton().getRadius() * 0.7);
        logo.setFitHeight(pb.getButton().getRadius() * 0.7);
        logo.fitWidthProperty().bind(pb.getButton().radiusProperty().multiply(0.7));
        logo.fitHeightProperty().bind(pb.getButton().radiusProperty().multiply(0.7));
        logo.setPreserveRatio(true);
        pb.setImage(logo);
        pb.assignIndicator((e) -> {
            stage.proc = this.start();
        },500);
        // Button button = initButton("src/ressources/images/angular.png", borderPane);
        this.init();
        pb.active();
        return pb;
    }

}
