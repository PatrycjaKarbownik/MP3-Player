package application;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.media.EqualizerBand;
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
    private static Slider volumeSlider;
    private static Slider balanceSlider;

    public static Slider f910_Slider;

    private String fontStyle = "-fx-font-size: 13; -fx-font-weight: bold;";
    private String buttonStyle = "-fx-font-size: 12; -fx-background-color: grey; -fx-text-fill: black;";
    private static final int sizeOfSquare = 30;

    MyWindow(Stage primaryStage) {
        primaryStage.setTitle("MP3 Player");

        primaryStage.getIcons().add(new Image(MyWindow.class.getResource( "/data/key.png" ).toExternalForm()));

        Group panel = createGroup();

        Scene scene = new Scene(panel, 16 * sizeOfSquare, 21 * sizeOfSquare, Color.BLACK);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    private Group createGroup(){
        Group group = new Group();

        nowPlaying = new Label("Now playing: ");
            nowPlaying.setPrefSize(14 * sizeOfSquare, 1 * sizeOfSquare);
            nowPlaying.relocate(1 * sizeOfSquare, 1 * sizeOfSquare);
            nowPlaying.setTextOverrun(OverrunStyle.ELLIPSIS);
            nowPlaying.setStyle(fontStyle);
            nowPlaying.setTextFill(Color.LIGHTGRAY);

        title = new Label("Title: ");
            title.setPrefSize(7 * sizeOfSquare, 1 * sizeOfSquare);
            title.relocate(1 * sizeOfSquare, 3 * sizeOfSquare);
            title.setTextOverrun(OverrunStyle.ELLIPSIS);
            title.setStyle(fontStyle);
            title.setTextFill(Color.LIGHTGRAY);
        artist = new Label("Artist: ");
            artist.setPrefSize(7 * sizeOfSquare, 1 * sizeOfSquare);
            artist.relocate(9 * sizeOfSquare, 3 * sizeOfSquare);
            artist.setTextOverrun(OverrunStyle.ELLIPSIS);
            artist.setStyle(fontStyle);
            artist.setTextFill(Color.LIGHTGRAY);
        album = new Label("Album: ");
            album.setPrefSize(7 * sizeOfSquare, 1 * sizeOfSquare);
            album.relocate(1 * sizeOfSquare, 5 * sizeOfSquare);
            album.setTextOverrun(OverrunStyle.ELLIPSIS);
            album.setStyle(fontStyle);
            album.setTextFill(Color.LIGHTGRAY);

        yearNumber = new Label("Year: ");
            yearNumber.setPrefSize(7 * sizeOfSquare, 1 * sizeOfSquare);
            yearNumber.relocate(9 * sizeOfSquare, 5 * sizeOfSquare);
            yearNumber.setStyle(fontStyle);
            yearNumber.setTextFill(Color.LIGHTGRAY);

        currentTime = new Label("00:00");
            currentTime.setPrefSize(2 * sizeOfSquare, 1 * sizeOfSquare);
            currentTime.relocate(1 * sizeOfSquare, 7 * sizeOfSquare);
            currentTime.setTextFill(Color.LIGHTGRAY);

        progressBar = new ProgressBar();
            progressBar.setPrefSize(10 * sizeOfSquare, 1 * sizeOfSquare);
            progressBar.relocate(3 * sizeOfSquare, 7 * sizeOfSquare);
            progressBar.setProgress(0);

        length = new Label("00:00");
            length.setPrefSize(2 * sizeOfSquare, 1 * sizeOfSquare);
            length.relocate(14 * sizeOfSquare, 7 * sizeOfSquare);
            length.setTextFill(Color.LIGHTGRAY);

        Button chooseButton = new Button("Choose MP3 file");
            chooseButton.setPrefSize(4 * sizeOfSquare, 1 * sizeOfSquare);
            chooseButton.relocate(1 * sizeOfSquare, 9 * sizeOfSquare);
            chooseButton.setStyle(buttonStyle);

        chooseButton.setOnAction(e -> {
            try {
                Actions.openFile();
            } catch (MalformedURLException e1) {
                e1.printStackTrace();
            }
        });

        Button playButton = new Button("PLAY");
            playButton.setPrefSize(4 * sizeOfSquare, 1 * sizeOfSquare);
            playButton.relocate(6 * sizeOfSquare, 9 * sizeOfSquare);
            playButton.setStyle(buttonStyle);

        playButton.setOnAction(e -> Actions.playMusic());

        Button addToPlaylistButton = new Button("Add to playlist");
            addToPlaylistButton.setPrefSize(4 * sizeOfSquare, 1 * sizeOfSquare);
            addToPlaylistButton.relocate(11 * sizeOfSquare, 9 * sizeOfSquare);
            addToPlaylistButton.setStyle(buttonStyle);

        addToPlaylistButton.setOnAction(e -> Actions.addToPlaylistFunction());

        Button stopButton = new Button("STOP");
            stopButton.setPrefSize(4 * sizeOfSquare, 1 * sizeOfSquare);
            stopButton.relocate(1 * sizeOfSquare, 11 * sizeOfSquare);
            stopButton.setStyle(buttonStyle);

        stopButton.setOnAction(e -> Actions.stopMusic());

        Button pauseButton = new Button("PAUSE");
            pauseButton.setPrefSize(4 * sizeOfSquare, 1 * sizeOfSquare);
            pauseButton.relocate(6 * sizeOfSquare, 11 * sizeOfSquare);
            pauseButton.setStyle(buttonStyle);

        pauseButton.setOnAction(e -> Actions.pauseMusic());

        Button openPlaylistButton = new Button("Open playlist");
            openPlaylistButton.setPrefSize(4 * sizeOfSquare, 1 * sizeOfSquare);
            openPlaylistButton.relocate(11 * sizeOfSquare, 11 * sizeOfSquare);
            openPlaylistButton.setStyle(buttonStyle);

        openPlaylistButton.setOnAction(e -> Actions.openPlaylistFunction());


        Label volumeLabel = new Label("Volume:");
            volumeLabel.setPrefSize(3 * sizeOfSquare, 1 * sizeOfSquare);
            volumeLabel.relocate(1 * sizeOfSquare, 13 * sizeOfSquare);
            volumeLabel.setStyle(fontStyle);
            volumeLabel.setTextFill(Color.LIGHTGRAY);
        volumeSlider = createSlider(3, 1, 4, 13);
            volumeSlider.setValue(100);

            volumeSlider.valueProperty().addListener(e -> Actions.volumeChange());
        /*volumeSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                Actions.volumeChange(newValue);
            }
        });*/


        Label balanceLabel = new Label("Balance:");
            balanceLabel.setPrefSize(3 * sizeOfSquare, 1 * sizeOfSquare);
            balanceLabel.setTextFill(Color.LIGHTGRAY);
            balanceLabel.relocate(1 * sizeOfSquare, 15 * sizeOfSquare);
        balanceSlider = createSlider(3, 1, 4, 15);
            balanceSlider.setMin(-100);
            balanceSlider.setMax(100);
            balanceSlider.setValue(0);

        balanceSlider.valueProperty().addListener(e -> Actions.balanceChange());

        Label f910_Label = new Label("910Hz:");
            f910_Label.setPrefSize(3 * sizeOfSquare, 1 * sizeOfSquare);
            f910_Label.relocate(1 * sizeOfSquare, 17 * sizeOfSquare);
            f910_Label.setTextFill(Color.LIGHTGRAY);
        /*Slider */f910_Slider = createSlider(3, 1, 4, 17);
            f910_Slider.setMin(-24);
            f910_Slider.setMax(12);
            f910_Slider.setValue(0);

        //balanceSlider.valueProperty().addListener(e -> Actions.equalizerBandChange(f910_eq));

        EqualizerBand f910_eq = new EqualizerBand(910, 1000, 0);
//        ChooseFile.getPlayer().getAudioEqualizer();
     //   ChooseFile.getPlayer().getAudioEqualizer().getBands().add(f910_eq);
      //  balanceSlider.valueProperty().addListener(e -> Actions.equalizerBandChange(ChooseFile.getPlayer().getAudioEqualizer().getBands().get(9), f910_Slider/*, f910_eq*/));


        Label f36_Label = new Label("3,6kHz:");
            f36_Label.setPrefSize(3 * sizeOfSquare, 1 * sizeOfSquare);
            f36_Label.relocate(1 * sizeOfSquare, 19 * sizeOfSquare);
            f36_Label.setTextFill(Color.LIGHTGRAY);
        Slider f36_Slider = createSlider(3, 1, 4, 19);
            f36_Slider.setValue(50);

        Label f60_Label = new Label("60Hz:");
            f60_Label.setPrefSize(3 * sizeOfSquare, 1 * sizeOfSquare);
            f60_Label.relocate(9 * sizeOfSquare, 13 * sizeOfSquare);
            f60_Label.setTextFill(Color.LIGHTGRAY);
        Slider f60_Slider = createSlider(3, 1, 12, 13);
            f60_Slider.setValue(50);

        Label f230_Label = new Label("230Hz:");
            f230_Label.setPrefSize(3 * sizeOfSquare, 1 * sizeOfSquare);
            f230_Label.relocate(9 * sizeOfSquare, 15 * sizeOfSquare);
            f230_Label.setTextFill(Color.LIGHTGRAY);
        Slider f230_Slider = createSlider(3, 1, 12, 15);
            f230_Slider.setValue(50);

        Label f14_Label = new Label("14kHz:");
            f14_Label.setPrefSize(3 * sizeOfSquare, 1 * sizeOfSquare);
            f14_Label.relocate(9 * sizeOfSquare, 17 * sizeOfSquare);
            f14_Label.setTextFill(Color.LIGHTGRAY);
        Slider f14_Slider = createSlider(3, 1, 12, 17);
            f14_Slider.setValue(50);

        Label f30_Label = new Label("30kHz:");
            f30_Label.setPrefSize(3 * sizeOfSquare, 1 * sizeOfSquare);
            f30_Label.relocate(9 * sizeOfSquare, 19 * sizeOfSquare);
            f30_Label.setTextFill(Color.LIGHTGRAY);
        Slider f30_Slider = createSlider(3, 1, 12, 19);
            f30_Slider.setValue(50);





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
        slider.setPrefSize(width * sizeOfSquare, height * sizeOfSquare);
        slider.relocate(x * sizeOfSquare, y * sizeOfSquare);

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

    public static double getVolumeSlider(){
        return volumeSlider.getValue();
    }

    public static double getBalanceSlider(){
        return balanceSlider.getValue();
    }

}
