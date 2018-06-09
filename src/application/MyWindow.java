package application;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.net.MalformedURLException;


public class MyWindow {

    private static Label nowPlaying;
    private static Label title;
    private static Label artist;
    private static Label album;
    private static Label yearNumber;
    private static Label currentTime;
    private static ProgressBar progressBar;
    private static Label length;
    private Font font = Font.font("Comic Sans", 13);

    MyWindow(Stage primaryStage) {
        primaryStage.setTitle("MP3 Player");

        primaryStage.getIcons().add(new Image(MyWindow.class.getResource( "/data/key.png" ).toExternalForm()));

        Group panel = createGroup();

        Scene scene = new Scene(panel, 16 * GlobalData.sizeOfSquare, 21 * GlobalData.sizeOfSquare, Color.BLACK);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    private Group createGroup(){
        Group group = new Group();

        nowPlaying = new Label("Now playing: ");
            nowPlaying.setPrefSize(14 * GlobalData.sizeOfSquare, 1 * GlobalData.sizeOfSquare);
            nowPlaying.relocate(1 * GlobalData.sizeOfSquare, 1 * GlobalData.sizeOfSquare);
            nowPlaying.setTextOverrun(OverrunStyle.ELLIPSIS);
            nowPlaying.setFont(font);
            nowPlaying.setTextFill(Color.LIGHTGRAY);

        title = new Label("Title: ");
            title.setPrefSize(7 * GlobalData.sizeOfSquare, 1 * GlobalData.sizeOfSquare);
            title.relocate(1 * GlobalData.sizeOfSquare, 3 * GlobalData.sizeOfSquare);
            title.setTextOverrun(OverrunStyle.ELLIPSIS);
            title.setFont(font);
            title.setTextFill(Color.LIGHTGRAY);
        artist = new Label("Artist: ");
            artist.setPrefSize(7 * GlobalData.sizeOfSquare, 1 * GlobalData.sizeOfSquare);
            artist.relocate(9 * GlobalData.sizeOfSquare, 3 * GlobalData.sizeOfSquare);
            artist.setTextOverrun(OverrunStyle.ELLIPSIS);
            artist.setFont(font);
            artist.setTextFill(Color.LIGHTGRAY);
        album = new Label("Album: ");
            album.setPrefSize(7 * GlobalData.sizeOfSquare, 1 * GlobalData.sizeOfSquare);
            album.relocate(1 * GlobalData.sizeOfSquare, 5 * GlobalData.sizeOfSquare);
            album.setTextOverrun(OverrunStyle.ELLIPSIS);
            album.setFont(font);
            album.setTextFill(Color.LIGHTGRAY);

        yearNumber = new Label("Year: ");
            yearNumber.setPrefSize(7 * GlobalData.sizeOfSquare, 1 * GlobalData.sizeOfSquare);
            yearNumber.relocate(9 * GlobalData.sizeOfSquare, 5 * GlobalData.sizeOfSquare);
            yearNumber.setFont(font);
            yearNumber.setTextFill(Color.LIGHTGRAY);

        currentTime = new Label("00:00");
            currentTime.setPrefSize(2 * GlobalData.sizeOfSquare, 1 * GlobalData.sizeOfSquare);
            currentTime.relocate(1 * GlobalData.sizeOfSquare, 7 * GlobalData.sizeOfSquare);
            currentTime.setFont(font);
            currentTime.setTextFill(Color.LIGHTGRAY);

        progressBar = new ProgressBar();
            progressBar.setPrefSize(10 * GlobalData.sizeOfSquare, 1 * GlobalData.sizeOfSquare);
            progressBar.relocate(3 * GlobalData.sizeOfSquare, 7 * GlobalData.sizeOfSquare);
            progressBar.setProgress(0);

        length = new Label("00:00");
            length.setPrefSize(2 * GlobalData.sizeOfSquare, 1 * GlobalData.sizeOfSquare);
            length.relocate(14 * GlobalData.sizeOfSquare, 7 * GlobalData.sizeOfSquare);
            length.setFont(font);
            length.setTextFill(Color.LIGHTGRAY);

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
            volumeLabel.setFont(font);
            volumeLabel.setTextFill(Color.LIGHTGRAY);
        Slider volumeSlider = createSlider(3, 1, 4, 13);

        Label balanceLabel = new Label("Balance:");
            balanceLabel.setPrefSize(3 * GlobalData.sizeOfSquare, 1 * GlobalData.sizeOfSquare);
            balanceLabel.setFont(font);
            balanceLabel.setTextFill(Color.LIGHTGRAY);
            balanceLabel.relocate(1 * GlobalData.sizeOfSquare, 15 * GlobalData.sizeOfSquare);
        Slider balanceSlider = createSlider(3, 1, 4, 15);

        Label f910_Label = new Label("910Hz:");
            f910_Label.setPrefSize(3 * GlobalData.sizeOfSquare, 1 * GlobalData.sizeOfSquare);
            f910_Label.relocate(1 * GlobalData.sizeOfSquare, 17 * GlobalData.sizeOfSquare);
            f910_Label.setFont(font);
            f910_Label.setTextFill(Color.LIGHTGRAY);
        Slider f910_Slider = createSlider(3, 1, 4, 17);

        Label f36_Label = new Label("3,6kHz:");
            f36_Label.setPrefSize(3 * GlobalData.sizeOfSquare, 1 * GlobalData.sizeOfSquare);
            f36_Label.relocate(1 * GlobalData.sizeOfSquare, 19 * GlobalData.sizeOfSquare);
            f36_Label.setFont(font);
            f36_Label.setTextFill(Color.LIGHTGRAY);
        Slider f36_Slider = createSlider(3, 1, 4, 19);

        Label f60_Label = new Label("60Hz:");
            f60_Label.setPrefSize(3 * GlobalData.sizeOfSquare, 1 * GlobalData.sizeOfSquare);
            f60_Label.relocate(9 * GlobalData.sizeOfSquare, 13 * GlobalData.sizeOfSquare);
            f60_Label.setFont(font);
            f60_Label.setTextFill(Color.LIGHTGRAY);
        Slider f60_Slider = createSlider(3, 1, 12, 13);

        Label f230_Label = new Label("230Hz:");
            f230_Label.setPrefSize(3 * GlobalData.sizeOfSquare, 1 * GlobalData.sizeOfSquare);
            f230_Label.relocate(9 * GlobalData.sizeOfSquare, 15 * GlobalData.sizeOfSquare);
            f230_Label.setFont(font);
            f230_Label.setTextFill(Color.LIGHTGRAY);
        Slider f230_Slider = createSlider(3, 1, 12, 15);

        Label f14_Label = new Label("14kHz:");
            f14_Label.setPrefSize(3 * GlobalData.sizeOfSquare, 1 * GlobalData.sizeOfSquare);
            f14_Label.relocate(9 * GlobalData.sizeOfSquare, 17 * GlobalData.sizeOfSquare);
            f14_Label.setFont(font);
            f14_Label.setTextFill(Color.LIGHTGRAY);
        Slider f14_Slider = createSlider(3, 1, 12, 17);

        Label f30_Label = new Label("30kHz:");
            f30_Label.setPrefSize(3 * GlobalData.sizeOfSquare, 1 * GlobalData.sizeOfSquare);
            f30_Label.relocate(9 * GlobalData.sizeOfSquare, 19 * GlobalData.sizeOfSquare);
            f30_Label.setFont(font);
            f30_Label.setTextFill(Color.LIGHTGRAY);
        Slider f30_Slider = createSlider(3, 1, 12, 19);





        group.getChildren().add(nowPlaying);
        group.getChildren().add(title);
        group.getChildren().add(artist);
        group.getChildren().add(album);
        group.getChildren().add(yearNumber);
        group.getChildren().add(currentTime);
        group.getChildren().add(progressBar);
        group.getChildren().add(length);
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
        nowPlaying.setText("Now playing: \t" + text);
    }

    public static Label getTitleLabel() {
        return title;
    }

    public static void setTitleLabel(String text) {
        title.setText("Title: \t" + text);
    }

    public static Label getArtistLabel() {
        return artist;
    }

    public static void setArtistLabel(String text) {
        artist.setText("Artist: \t" + text);
    }

    public static Label getAlbumLabel() {
        return album;
    }

    public static void setAlbumLabel(String text) {
        album.setText("Album: \t" + text);
    }

    public static Label getYearLabel() {
        return yearNumber;
    }

    public static void setYearLabel(String text) {
        yearNumber.setText("Year: \t" + text);
    }

    public static Label getCurrentTime() {
        return currentTime;
    }

    public static void setCurrentTime(String text) {
        currentTime.setText(text);
    }

    public static ProgressBar getProgressBar() {
        return progressBar;
    }

    public static void setProgressBar(double value) {
        progressBar.setProgress(value);
    }

    public static Label getLengthLabel() {
        return length;
    }

    public static void setLengthLabel(String text) {
        length.setText(text);
    }

}
