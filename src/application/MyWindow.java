package application;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Slider;
import javafx.stage.Stage;

import java.net.MalformedURLException;


public class MyWindow {

    private static Label nowPlaying;
    private static ProgressBar progressBar;
    private static Label currentTime;

    MyWindow(Stage primaryStage) {
        primaryStage.setTitle("MP3 Player");

        Group panel = createGroup();



        Scene scene = new Scene(panel, 16 * GlobalData.sizeOfSquare, 21 * GlobalData.sizeOfSquare);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    private Group createGroup(){
        Group group = new Group();

        nowPlaying = new Label("Now playing: ");
            nowPlaying.setPrefSize(14 * GlobalData.sizeOfSquare, 1 * GlobalData.sizeOfSquare);
            nowPlaying.relocate(1 * GlobalData.sizeOfSquare, 1 * GlobalData.sizeOfSquare);
        Label title = new Label("Title: " /*+ ChooseFile.getTitle()*/);
            title.setPrefSize(7 * GlobalData.sizeOfSquare, 1 * GlobalData.sizeOfSquare);
            title.relocate(1 * GlobalData.sizeOfSquare, 3 * GlobalData.sizeOfSquare);
        Label artist = new Label("Artist: " /*+ ChooseFile.getArtist()*/);
            artist.setPrefSize(7 * GlobalData.sizeOfSquare, 1 * GlobalData.sizeOfSquare);
            artist.relocate(9 * GlobalData.sizeOfSquare, 3 * GlobalData.sizeOfSquare);
        Label album = new Label("Album: " /*+ ChooseFile.getAlbum()*/);
            album.setPrefSize(7 * GlobalData.sizeOfSquare, 1 * GlobalData.sizeOfSquare);
            album.relocate(1 * GlobalData.sizeOfSquare, 5 * GlobalData.sizeOfSquare);

        Label trackNumber = new Label("Track number: " /*+ ChooseFile.getAlbum()*/);
            trackNumber.setPrefSize(7 * GlobalData.sizeOfSquare, 1 * GlobalData.sizeOfSquare);
            trackNumber.relocate(9 * GlobalData.sizeOfSquare, 5 * GlobalData.sizeOfSquare);

        currentTime = new Label("00:00");
            currentTime.setPrefSize(2 * GlobalData.sizeOfSquare, 1 * GlobalData.sizeOfSquare);
            currentTime.relocate(1 * GlobalData.sizeOfSquare, 7 * GlobalData.sizeOfSquare);

        progressBar = new ProgressBar();
            progressBar.setPrefSize(10 * GlobalData.sizeOfSquare, 1 * GlobalData.sizeOfSquare);
            progressBar.relocate(3 * GlobalData.sizeOfSquare, 7 * GlobalData.sizeOfSquare);

        Button chooseButton = new Button("Choose MP3 file");
            chooseButton.setPrefSize(4 * GlobalData.sizeOfSquare, 1 * GlobalData.sizeOfSquare);
            chooseButton.relocate(1 * GlobalData.sizeOfSquare, 9 * GlobalData.sizeOfSquare);

        chooseButton.setOnAction(e -> {
            try {
                Actions.openFile();
            } catch (MalformedURLException e1) {
                e1.printStackTrace();
            }
        });

        Button playButton = new Button("PLAY");
            playButton.setPrefSize(4 * GlobalData.sizeOfSquare, 1 * GlobalData.sizeOfSquare);
            playButton.relocate(6 * GlobalData.sizeOfSquare, 9 * GlobalData.sizeOfSquare);

        playButton.setOnAction(e -> Actions.playMusic());

        Button addToPlaylistButton = new Button("Add to playlist");
            addToPlaylistButton.setPrefSize(4 * GlobalData.sizeOfSquare, 1 * GlobalData.sizeOfSquare);
            addToPlaylistButton.relocate(11 * GlobalData.sizeOfSquare, 9 * GlobalData.sizeOfSquare);

        addToPlaylistButton.setOnAction(e -> Actions.addToPlaylistFunction());

        Button stopButton = new Button("STOP");
            stopButton.setPrefSize(4 * GlobalData.sizeOfSquare, 1 * GlobalData.sizeOfSquare);
            stopButton.relocate(1 * GlobalData.sizeOfSquare, 11 * GlobalData.sizeOfSquare);

        stopButton.setOnAction(e -> Actions.stopMusic());

        Button pauseButton = new Button("PAUSE");
            pauseButton.setPrefSize(4 * GlobalData.sizeOfSquare, 1 * GlobalData.sizeOfSquare);
            pauseButton.relocate(6 * GlobalData.sizeOfSquare, 11 * GlobalData.sizeOfSquare);

        pauseButton.setOnAction(e -> Actions.pauseMusic());

        Button openPlaylistButton = new Button("Open playlist");
            openPlaylistButton.setPrefSize(4 * GlobalData.sizeOfSquare, 1 * GlobalData.sizeOfSquare);
            openPlaylistButton.relocate(11 * GlobalData.sizeOfSquare, 11 * GlobalData.sizeOfSquare);

        openPlaylistButton.setOnAction(e -> Actions.openPlaylistFunction());


        Label volumeLabel = new Label("Volume:");
            volumeLabel.setPrefSize(3 * GlobalData.sizeOfSquare, 1 * GlobalData.sizeOfSquare);
            volumeLabel.relocate(1 * GlobalData.sizeOfSquare, 13 * GlobalData.sizeOfSquare);
        Slider volumeSlider = createSlider(3, 1, 4, 13);

        Label balanceLabel = new Label("Balance:");
            balanceLabel.setPrefSize(3 * GlobalData.sizeOfSquare, 1 * GlobalData.sizeOfSquare);
            balanceLabel.relocate(1 * GlobalData.sizeOfSquare, 15 * GlobalData.sizeOfSquare);
        Slider balanceSlider = createSlider(3, 1, 4, 15);

        Label f910_Label = new Label("910Hz:");
            f910_Label.setPrefSize(3 * GlobalData.sizeOfSquare, 1 * GlobalData.sizeOfSquare);
            f910_Label.relocate(1 * GlobalData.sizeOfSquare, 17 * GlobalData.sizeOfSquare);
        Slider f910_Slider = createSlider(3, 1, 4, 17);

        Label f36_Label = new Label("3,6kHz:");
            f36_Label.setPrefSize(3 * GlobalData.sizeOfSquare, 1 * GlobalData.sizeOfSquare);
            f36_Label.relocate(1 * GlobalData.sizeOfSquare, 19 * GlobalData.sizeOfSquare);
        Slider f36_Slider = createSlider(3, 1, 4, 19);

        Label f60_Label = new Label("60Hz:");
            f60_Label.setPrefSize(3 * GlobalData.sizeOfSquare, 1 * GlobalData.sizeOfSquare);
            f60_Label.relocate(9 * GlobalData.sizeOfSquare, 13 * GlobalData.sizeOfSquare);
        Slider f60_Slider = createSlider(3, 1, 12, 13);

        Label f230_Label = new Label("230Hz:");
            f230_Label.setPrefSize(3 * GlobalData.sizeOfSquare, 1 * GlobalData.sizeOfSquare);
            f230_Label.relocate(9 * GlobalData.sizeOfSquare, 15 * GlobalData.sizeOfSquare);
        Slider f230_Slider = createSlider(3, 1, 12, 15);

        Label f14_Label = new Label("14kHz:");
            f14_Label.setPrefSize(3 * GlobalData.sizeOfSquare, 1 * GlobalData.sizeOfSquare);
            f14_Label.relocate(9 * GlobalData.sizeOfSquare, 17 * GlobalData.sizeOfSquare);
        Slider f14_Slider = createSlider(3, 1, 12, 17);

        Label f30_Label = new Label("30kHz:");
            f30_Label.setPrefSize(3 * GlobalData.sizeOfSquare, 1 * GlobalData.sizeOfSquare);
            f30_Label.relocate(9 * GlobalData.sizeOfSquare, 19 * GlobalData.sizeOfSquare);
        Slider f30_Slider = createSlider(3, 1, 12, 19);





        group.getChildren().add(nowPlaying);
        group.getChildren().add(title);
        group.getChildren().add(artist);
        group.getChildren().add(album);
        group.getChildren().add(trackNumber);
        group.getChildren().add(currentTime);
        group.getChildren().add(progressBar);
        group.getChildren().add(chooseButton);
        group.getChildren().add(playButton);
        group.getChildren().add(addToPlaylistButton);
        group.getChildren().add(stopButton);
        group.getChildren().add(pauseButton);
        group.getChildren().add(openPlaylistButton);
        group.getChildren().addAll(volumeLabel, volumeSlider);
        group.getChildren().addAll(balanceLabel, balanceSlider);
        group.getChildren().addAll(f910_Label, f910_Slider);
        group.getChildren().addAll(f36_Label, f36_Slider);
        group.getChildren().addAll(f60_Label, f60_Slider);
        group.getChildren().addAll(f230_Label, f230_Slider);
        group.getChildren().addAll(f14_Label, f14_Slider);
        group.getChildren().addAll(f30_Label, f30_Slider);


        return group;
    }

    private Slider createSlider(int width, int height, int x, int y) {
        Slider slider = new Slider();
        slider.setMin(0);
        slider.setMax(100);
        slider.setValue(100);
        slider.setShowTickLabels(true);
        slider.setShowTickMarks(true);
        slider.setMajorTickUnit(50);
        slider.setMinorTickCount(5);
        slider.setBlockIncrement(10);
        slider.setPrefSize(width * GlobalData.sizeOfSquare, height * GlobalData.sizeOfSquare);
        slider.relocate(x * GlobalData.sizeOfSquare, y * GlobalData.sizeOfSquare);

        return slider;
    }

    public static Label getNowPlayingLabel() {
        return nowPlaying;
    }

    public static void setNowPlayingLabel(String text) {
        nowPlaying.setText("Now playing: " + text);
    }

    public static ProgressBar getProgressBar() {
        return progressBar;
    }

    public static void setProgressBar(double value) {
        progressBar.setProgress(value);
    }

    public static Label getCurrentTime() {
        return currentTime;
    }

    public static void setCurrentTime(String text) {
        currentTime.setText(text);
    }
}
