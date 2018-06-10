package application;

import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
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


    private String fontStyle = "-fx-font-size: 13; -fx-font-weight: bold;";
    private String buttonStyle = "-fx-font-size: 12; -fx-background-color: grey; -fx-text-fill: black;";
    public static final int sizeOfSquare = 30;

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
            } catch (NullPointerException | MalformedURLException e1) {
                System.out.println("Blad w openFile: " + e);

                errorStage("You must choose a file");
                //e1.printStackTrace();
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
            volumeSlider.setMin(0);
            volumeSlider.setMax(100);
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

        balanceSlider.valueProperty().addListener(e -> Actions.balanceChange());


        Label f64_Label = new Label("64Hz:");
            f64_Label.setPrefSize(3 * sizeOfSquare, 1 * sizeOfSquare);
            f64_Label.relocate(1 * sizeOfSquare, 17 * sizeOfSquare);
            f64_Label.setTextFill(Color.LIGHTGRAY);
        Slider f64_Slider = createSlider(3, 1, 4, 17);

        f64_Slider.valueProperty().addListener(e -> Actions.equalizerBandChange(ChooseFile.getPlayer().getAudioEqualizer().getBands().get(1), f64_Slider));

        Label f125_Label = new Label("125Hz:");
            f125_Label.setPrefSize(3 * sizeOfSquare, 1 * sizeOfSquare);
            f125_Label.relocate(1 * sizeOfSquare, 19 * sizeOfSquare);
            f125_Label.setTextFill(Color.LIGHTGRAY);
        Slider f125_Slider = createSlider(3, 1, 4, 19);

        f125_Slider.valueProperty().addListener(e -> Actions.equalizerBandChange(ChooseFile.getPlayer().getAudioEqualizer().getBands().get(2), f125_Slider));

        Label f500_Label = new Label("" + "500Hz:");
            f500_Label.setPrefSize(3 * sizeOfSquare, 1 * sizeOfSquare);
            f500_Label.relocate(9 * sizeOfSquare, 13 * sizeOfSquare);
            f500_Label.setTextFill(Color.LIGHTGRAY);
        Slider f500_Slider = createSlider(3, 1, 12, 13);

        f500_Slider.valueProperty().addListener(e -> Actions.equalizerBandChange(ChooseFile.getPlayer().getAudioEqualizer().getBands().get(4), f500_Slider));

        Label f1k_Label = new Label("1kHz:");
            f1k_Label.setPrefSize(3 * sizeOfSquare, 1 * sizeOfSquare);
            f1k_Label.relocate(9 * sizeOfSquare, 15 * sizeOfSquare);
            f1k_Label.setTextFill(Color.LIGHTGRAY);
        Slider f1k_Slider = createSlider(3, 1, 12, 15);

        f1k_Slider.valueProperty().addListener(e -> Actions.equalizerBandChange(ChooseFile.getPlayer().getAudioEqualizer().getBands().get(5), f1k_Slider));

        Label f4k_Label = new Label("4kHz:");
            f4k_Label.setPrefSize(3 * sizeOfSquare, 1 * sizeOfSquare);
            f4k_Label.relocate(9 * sizeOfSquare, 17 * sizeOfSquare);
            f4k_Label.setTextFill(Color.LIGHTGRAY);
        Slider f4k_Slider = createSlider(3, 1, 12, 17);

        f4k_Slider.valueProperty().addListener(e -> Actions.equalizerBandChange(ChooseFile.getPlayer().getAudioEqualizer().getBands().get(7), f4k_Slider));

        Label f16k_Label = new Label("16kHz:");
            f16k_Label.setPrefSize(3 * sizeOfSquare, 1 * sizeOfSquare);
            f16k_Label.relocate(9 * sizeOfSquare, 19 * sizeOfSquare);
            f16k_Label.setTextFill(Color.LIGHTGRAY);
        Slider f16k_Slider = createSlider(3, 1, 12, 19);

        f16k_Slider.valueProperty().addListener(e -> Actions.equalizerBandChange(ChooseFile.getPlayer().getAudioEqualizer().getBands().get(9), f16k_Slider));


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
        group.getChildren().addAll(f64_Label, f64_Slider);
        group.getChildren().addAll(f125_Label, f125_Slider);
        group.getChildren().addAll(f500_Label, f500_Slider);
        group.getChildren().addAll(f1k_Label, f1k_Slider);
        group.getChildren().addAll(f4k_Label, f4k_Slider);
        group.getChildren().addAll(f16k_Label, f16k_Slider);

        return group;
    }

    private Slider createSlider(int width, int height, int x, int y) {
        Slider slider = new Slider();
        slider.setMin(-24);
        slider.setMax(12);
        slider.setValue(0);
        slider.setShowTickLabels(true);
        slider.setShowTickMarks(true);
        slider.setMajorTickUnit(50);
        slider.setMinorTickCount(5);
        slider.setBlockIncrement(10);
        slider.setPrefSize(width * sizeOfSquare, height * sizeOfSquare);
        slider.relocate(x * sizeOfSquare, y * sizeOfSquare);

        return slider;
    }

    public static Stage errorStage(String text){
        Stage errorStage = new Stage();
        errorStage.setTitle("Error");
        errorStage.setResizable(false);
        errorStage.setAlwaysOnTop(true);
        errorStage.setMaximized(false);
        //errorStage.setMaxHeight(7 * MyWindow.sizeOfSquare);
        // errorStage.setMaxWidth(5 * MyWindow.sizeOfSquare);

        Group panel = new Group();
        Label error = new Label(text);
        error.setWrapText(true);
        error.relocate(2 * sizeOfSquare, 1 * MyWindow.sizeOfSquare);
        error.setPrefSize(3 * sizeOfSquare, 3 * sizeOfSquare);
        error.setTextAlignment(TextAlignment.CENTER);
        panel.getChildren().add(error);

        Scene scene = new Scene(panel, 7 * sizeOfSquare, 5 * sizeOfSquare);


        errorStage.setScene(scene);
        errorStage.show();

        return errorStage;
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
