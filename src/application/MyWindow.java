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

    MyWindow(Stage primaryStage) {
        primaryStage.setTitle("MP3 Player");

        Group panel = createGroup();



        Scene scene = new Scene(panel, 16 * GlobalData.sizeOfSquare, 24 * GlobalData.sizeOfSquare);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    private Group createGroup(){
        Group group = new Group();

        nowPlaying = new Label("Now playing: "/* + ChooseFile.getFileName()*/);
            nowPlaying.setPrefSize(14 * GlobalData.sizeOfSquare, 1 * GlobalData.sizeOfSquare);
            nowPlaying.relocate(1 * GlobalData.sizeOfSquare, 1 * GlobalData.sizeOfSquare);
        Label title = new Label("Title: " /*+ ChooseFile.getTitle()*/);
            title.setPrefSize(14 * GlobalData.sizeOfSquare, 1 * GlobalData.sizeOfSquare);
            title.relocate(1 * GlobalData.sizeOfSquare, 3 * GlobalData.sizeOfSquare);
        Label artist = new Label("Artist: " /*+ ChooseFile.getArtist()*/);
            artist.setPrefSize(14 * GlobalData.sizeOfSquare, 1 * GlobalData.sizeOfSquare);
            artist.relocate(1 * GlobalData.sizeOfSquare, 5 * GlobalData.sizeOfSquare);
        Label album = new Label("Album: " /*+ ChooseFile.getAlbum()*/);
            album.setPrefSize(14 * GlobalData.sizeOfSquare, 1 * GlobalData.sizeOfSquare);
            album.relocate(1 * GlobalData.sizeOfSquare, 7 * GlobalData.sizeOfSquare);

        Label currentTime = new Label(/*ChooseFile.getCurrentTime()*/);
            currentTime.setPrefSize(3 * GlobalData.sizeOfSquare, 1 * GlobalData.sizeOfSquare);
            currentTime.relocate(1 * GlobalData.sizeOfSquare, 9 * GlobalData.sizeOfSquare);

        ProgressBar progressBar = new ProgressBar();
            progressBar.setPrefSize(10 * GlobalData.sizeOfSquare, 1 * GlobalData.sizeOfSquare);
            progressBar.relocate(5 * GlobalData.sizeOfSquare, 9 * GlobalData.sizeOfSquare);

        Button chooseButton = new Button("Choose MP3 file");
            chooseButton.setPrefSize(4 * GlobalData.sizeOfSquare, 1 * GlobalData.sizeOfSquare);
            chooseButton.relocate(1 * GlobalData.sizeOfSquare, 11 * GlobalData.sizeOfSquare);

        chooseButton.setOnAction(e -> {
            try {
                Actions.openFile();
            } catch (MalformedURLException e1) {
                e1.printStackTrace();
            }
        });

        Button playButton = new Button("PLAY");
            playButton.setPrefSize(4 * GlobalData.sizeOfSquare, 1 * GlobalData.sizeOfSquare);
            playButton.relocate(6 * GlobalData.sizeOfSquare, 11 * GlobalData.sizeOfSquare);

        playButton.setOnAction(e -> Actions.playMusic());

        Button addToPlaylistButton = new Button("Add to playlist");
            addToPlaylistButton.setPrefSize(4 * GlobalData.sizeOfSquare, 1 * GlobalData.sizeOfSquare);
            addToPlaylistButton.relocate(11 * GlobalData.sizeOfSquare, 11 * GlobalData.sizeOfSquare);

        addToPlaylistButton.setOnAction(e -> Actions.addToPlaylistFunction());

        Button stopButton = new Button("STOP");
            stopButton.setPrefSize(4 * GlobalData.sizeOfSquare, 1 * GlobalData.sizeOfSquare);
            stopButton.relocate(1 * GlobalData.sizeOfSquare, 13 * GlobalData.sizeOfSquare);

        stopButton.setOnAction(e -> Actions.stopMusic());

        Button pauseButton = new Button("PAUSE");
            pauseButton.setPrefSize(4 * GlobalData.sizeOfSquare, 1 * GlobalData.sizeOfSquare);
            pauseButton.relocate(6 * GlobalData.sizeOfSquare, 13 * GlobalData.sizeOfSquare);

        pauseButton.setOnAction(e -> Actions.pauseMusic());

        Button openPlaylistButton = new Button("Open playlist");
            openPlaylistButton.setPrefSize(4 * GlobalData.sizeOfSquare, 1 * GlobalData.sizeOfSquare);
            openPlaylistButton.relocate(11 * GlobalData.sizeOfSquare, 13 * GlobalData.sizeOfSquare);

        openPlaylistButton.setOnAction(e -> Actions.openPlaylistFunction());


        Label volumeLabel = new Label("Volume:");
            volumeLabel.setPrefSize(3 * GlobalData.sizeOfSquare, 1 * GlobalData.sizeOfSquare);
            volumeLabel.relocate(1 * GlobalData.sizeOfSquare, 16 * GlobalData.sizeOfSquare);
        Slider volumeSlider = createSlider(3, 1, 4, 16);

        Label balanceLabel = new Label("Balance:");
            balanceLabel.setPrefSize(3 * GlobalData.sizeOfSquare, 1 * GlobalData.sizeOfSquare);
            balanceLabel.relocate(1 * GlobalData.sizeOfSquare, 18 * GlobalData.sizeOfSquare);
        Slider balanceSlider = createSlider(3, 1, 4, 18);

        Label f910_Label = new Label("910Hz:");
            f910_Label.setPrefSize(3 * GlobalData.sizeOfSquare, 1 * GlobalData.sizeOfSquare);
            f910_Label.relocate(1 * GlobalData.sizeOfSquare, 20 * GlobalData.sizeOfSquare);
        Slider f910_Slider = createSlider(3, 1, 4, 20);

        Label f36_Label = new Label("3,6kHz:");
            f36_Label.setPrefSize(3 * GlobalData.sizeOfSquare, 1 * GlobalData.sizeOfSquare);
            f36_Label.relocate(1 * GlobalData.sizeOfSquare, 22 * GlobalData.sizeOfSquare);
        Slider f36_Slider = createSlider(3, 1, 4, 22);

        Label f60_Label = new Label("60Hz:");
            f60_Label.setPrefSize(3 * GlobalData.sizeOfSquare, 1 * GlobalData.sizeOfSquare);
            f60_Label.relocate(9 * GlobalData.sizeOfSquare, 16 * GlobalData.sizeOfSquare);
        Slider f60_Slider = createSlider(3, 1, 12, 16);

        Label f230_Label = new Label("230Hz:");
            f230_Label.setPrefSize(3 * GlobalData.sizeOfSquare, 1 * GlobalData.sizeOfSquare);
            f230_Label.relocate(9 * GlobalData.sizeOfSquare, 18 * GlobalData.sizeOfSquare);
        Slider f230_Slider = createSlider(3, 1, 12, 18);

        Label f14_Label = new Label("14kHz:");
            f14_Label.setPrefSize(3 * GlobalData.sizeOfSquare, 1 * GlobalData.sizeOfSquare);
            f14_Label.relocate(9 * GlobalData.sizeOfSquare, 20 * GlobalData.sizeOfSquare);
        Slider f14_Slider = createSlider(3, 1, 12, 20);

        Label f30_Label = new Label("30kHz:");
            f30_Label.setPrefSize(3 * GlobalData.sizeOfSquare, 1 * GlobalData.sizeOfSquare);
            f30_Label.relocate(9 * GlobalData.sizeOfSquare, 22 * GlobalData.sizeOfSquare);
        Slider f30_Slider = createSlider(3, 1, 12, 22);





        group.getChildren().add(nowPlaying);
        group.getChildren().add(title);
        group.getChildren().add(artist);
        group.getChildren().add(album);
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
}
