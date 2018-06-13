package application;

import javafx.geometry.Orientation;
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
    private static ComboBox comboBox;
    private static TextField playlistName;
    private static Stage newPlaylistNameSt;
    private static Stage playlistSelectionToAdd;
    private static Stage playlistSelectionToPlay;

    private static String fontStyle = "-fx-font-size: 13; -fx-font-weight: bold;";
    private static String buttonStyle = "-fx-font-size: 12; -fx-background-color: grey; -fx-text-fill: black;";
    public static final int sizeOfSquare = 30;

    MyWindow(Stage primaryStage) {
        primaryStage.setTitle("MP3 Player");

        primaryStage.getIcons().add(new Image(MyWindow.class.getResource( "/data/key.png" ).toExternalForm()));

        Group panel = createGroup();

        Scene scene = new Scene(panel, 16 * sizeOfSquare, 22 * sizeOfSquare, Color.BLACK);
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
                Actions.openFile(true, null);
            } catch (NullPointerException | MalformedURLException e1) {
                System.out.println("Blad w openFile: " + e);

                errorStage("You must choose a file.");
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

        addToPlaylistButton.setOnAction(e -> Actions.addToPlaylist());

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

        openPlaylistButton.setOnAction(e -> playlistSelectionToPlayStage());


        Label volumeLabel = new Label("Volume:");
            volumeLabel.setPrefSize(3 * sizeOfSquare, 1 * sizeOfSquare);
            volumeLabel.relocate(2 * sizeOfSquare, 13 * sizeOfSquare);
            volumeLabel.setTextFill(Color.LIGHTGRAY);
        volumeSlider = createSlider(8, 1, 5, 13);
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
            balanceLabel.relocate(2 * sizeOfSquare, 15 * sizeOfSquare);
        balanceSlider = createSlider(8, 1, 5, 15);
            balanceSlider.setMin(-100);
            balanceSlider.setMax(100);

        balanceSlider.valueProperty().addListener(e -> Actions.balanceChange());


        Label f64_Label = new Label("64Hz:");
            f64_Label.setPrefSize(3 * sizeOfSquare, 1 * sizeOfSquare);
            f64_Label.relocate(2 * sizeOfSquare, 20 * sizeOfSquare);
            f64_Label.setTextFill(Color.LIGHTGRAY);
        Slider f64_Slider = createSlider(2, 3, 2, 17);
            f64_Slider.setOrientation(Orientation.VERTICAL);

        f64_Slider.valueProperty().addListener(e -> Actions.equalizerBandChange(ChooseFile.getPlayer().getAudioEqualizer().getBands().get(1), f64_Slider));

        Label f125_Label = new Label("125Hz:");
            f125_Label.setPrefSize(3 * sizeOfSquare, 1 * sizeOfSquare);
            f125_Label.relocate(4 * sizeOfSquare, 20 * sizeOfSquare);
            f125_Label.setTextFill(Color.LIGHTGRAY);
        Slider f125_Slider = createSlider(2, 3, 4, 17);
            f125_Slider.setOrientation(Orientation.VERTICAL);

        f125_Slider.valueProperty().addListener(e -> Actions.equalizerBandChange(ChooseFile.getPlayer().getAudioEqualizer().getBands().get(2), f125_Slider));

        Label f500_Label = new Label("" + "500Hz:");
            f500_Label.setPrefSize(3 * sizeOfSquare, 1 * sizeOfSquare);
            f500_Label.relocate(6 * sizeOfSquare, 20 * sizeOfSquare);
            f500_Label.setTextFill(Color.LIGHTGRAY);
        Slider f500_Slider = createSlider(2, 3, 6, 17);
            f500_Slider.setOrientation(Orientation.VERTICAL);

        f500_Slider.valueProperty().addListener(e -> Actions.equalizerBandChange(ChooseFile.getPlayer().getAudioEqualizer().getBands().get(4), f500_Slider));

        Label f1k_Label = new Label("1kHz:");
            f1k_Label.setPrefSize(3 * sizeOfSquare, 1 * sizeOfSquare);
            f1k_Label.relocate(8 * sizeOfSquare, 20 * sizeOfSquare);
            f1k_Label.setTextFill(Color.LIGHTGRAY);
        Slider f1k_Slider = createSlider(2, 3, 8, 17);
            f1k_Slider.setOrientation(Orientation.VERTICAL);

        f1k_Slider.valueProperty().addListener(e -> Actions.equalizerBandChange(ChooseFile.getPlayer().getAudioEqualizer().getBands().get(5), f1k_Slider));

        Label f4k_Label = new Label("4kHz:");
            f4k_Label.setPrefSize(3 * sizeOfSquare, 1 * sizeOfSquare);
            f4k_Label.relocate(10 * sizeOfSquare, 20 * sizeOfSquare);
            f4k_Label.setTextFill(Color.LIGHTGRAY);
        Slider f4k_Slider = createSlider(2, 3, 10, 17);
            f4k_Slider.setOrientation(Orientation.VERTICAL);

        f4k_Slider.valueProperty().addListener(e -> Actions.equalizerBandChange(ChooseFile.getPlayer().getAudioEqualizer().getBands().get(7), f4k_Slider));

        Label f16k_Label = new Label("16kHz:");
            f16k_Label.setPrefSize(3 * sizeOfSquare, 1 * sizeOfSquare);
            f16k_Label.relocate(12 * sizeOfSquare, 20 * sizeOfSquare);
            f16k_Label.setTextFill(Color.LIGHTGRAY);
        Slider f16k_Slider = createSlider(2, 3, 12, 17);
            f16k_Slider.setOrientation(Orientation.VERTICAL);

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

        Group panel = new Group();
        Label error = new Label(text);
        error.setWrapText(true);
        error.relocate(2 * sizeOfSquare, 1 * MyWindow.sizeOfSquare);
        error.setPrefSize(3 * sizeOfSquare, 3 * sizeOfSquare);
        error.setTextAlignment(TextAlignment.CENTER);
        error.setStyle(fontStyle);
        error.setTextFill(Color.RED);
        panel.getChildren().add(error);

        Scene scene = new Scene(panel, 7 * sizeOfSquare, 5 * sizeOfSquare, Color.BLACK);


        errorStage.setScene(scene);
        errorStage.show();

        return errorStage;
    }

    public static Stage playlistSelectionStageToAdd(){
        playlistSelectionToAdd = new Stage();
        playlistSelectionToAdd.setTitle("Add to playlist");
        playlistSelectionToAdd.setResizable(false);
        playlistSelectionToAdd.setAlwaysOnTop(true);
        playlistSelectionToAdd.setMaximized(false);

        Group panel = new Group();
        Label info = new Label("Choose playlist or creat a new one");
            info.setWrapText(true);
            info.relocate(2 * sizeOfSquare, 1 * MyWindow.sizeOfSquare);
            info.setPrefSize(4 * sizeOfSquare, 2 * sizeOfSquare);
            info.setTextAlignment(TextAlignment.CENTER);
            info.setTextFill(Color.LIGHTGRAY);

        comboBox = createComboBox(true);

        Button okButton = new Button("OK");
            okButton.setPrefSize(2 * sizeOfSquare, 1 * sizeOfSquare);
            okButton.relocate(5 * sizeOfSquare, 6 * sizeOfSquare);
            okButton.setStyle(buttonStyle);

            okButton.setOnAction(e -> Actions.addToPlaylistFunction());

        Button cancelButton = new Button("Cancel");
            cancelButton.setPrefSize(2 * sizeOfSquare, 1 * sizeOfSquare);
            cancelButton.relocate(1 * sizeOfSquare, 6 * sizeOfSquare);
            cancelButton.setStyle(buttonStyle);

        cancelButton.setOnAction(e -> playlistSelectionToAdd.close());


        panel.getChildren().add(info);
        panel.getChildren().add(comboBox);
        panel.getChildren().add(okButton);
        panel.getChildren().add(cancelButton);

        Scene scene = new Scene(panel, 8 * sizeOfSquare, 8 * sizeOfSquare, Color.BLACK);

        playlistSelectionToAdd.setScene(scene);
        playlistSelectionToAdd.show();

        return playlistSelectionToAdd;
    }

    private static ComboBox createComboBox(boolean create){
        ComboBox comboBox = new ComboBox();
        comboBox.relocate(1 * sizeOfSquare, 4 * sizeOfSquare);
        comboBox.setPrefSize(6 * sizeOfSquare, 1 * sizeOfSquare);
        comboBox.setStyle(buttonStyle);
        comboBox.setVisibleRowCount(5);

        if(create) {
            comboBox.getItems().add("<create a new playlist>");
            comboBox.setValue("<create a new playlist>");
        }

        PlaylistFolder folder = new PlaylistFolder();

        for (String name:PlaylistFolder.getFileNamesList()) {
            comboBox.getItems().add(name);
        }

        return comboBox;
    }

    public static Stage newPlaylistNameStage() {
        newPlaylistNameSt = new Stage();
        newPlaylistNameSt.setTitle("Set name of playlist");
        newPlaylistNameSt.setResizable(false);
        newPlaylistNameSt.setAlwaysOnTop(true);
        newPlaylistNameSt.setMaximized(false);

        Group panel = new Group();
        Label text = new Label("Set name of playlist");
        text.setWrapText(true);
        text.relocate(1 * sizeOfSquare, 1 * MyWindow.sizeOfSquare);
        text.setPrefSize(4 * sizeOfSquare, 2 * sizeOfSquare);
        text.setTextAlignment(TextAlignment.CENTER);
        text.setTextFill(Color.LIGHTGRAY);

        playlistName = new TextField();
        playlistName.relocate(1 * sizeOfSquare, 3 * sizeOfSquare);
        playlistName.setPrefSize(5 * sizeOfSquare, 1 * sizeOfSquare);
        playlistName.setStyle(buttonStyle);


        Button okButton = new Button("OK");
            okButton.setPrefSize(2 * sizeOfSquare, 1 * sizeOfSquare);
            okButton.relocate(4 * sizeOfSquare, 5 * sizeOfSquare);
            okButton.setStyle(buttonStyle);

        okButton.setOnAction(e -> Actions.createNewPlaylist());

        Button cancelButton = new Button("Cancel");
        cancelButton.setPrefSize(2 * sizeOfSquare, 1 * sizeOfSquare);
        cancelButton.relocate(1 * sizeOfSquare, 5 * sizeOfSquare);
        cancelButton.setStyle(buttonStyle);

        cancelButton.setOnAction(e -> newPlaylistNameSt.close());


        panel.getChildren().add(text);
        panel.getChildren().add(playlistName);
        panel.getChildren().add(okButton);
        panel.getChildren().add(cancelButton);

        Scene scene = new Scene(panel, 7 * sizeOfSquare, 7 * sizeOfSquare, Color.BLACK);

        newPlaylistNameSt.setScene(scene);
        newPlaylistNameSt.show();

        return newPlaylistNameSt;
    }

    public static Stage playlistSelectionToPlayStage() {
        playlistSelectionToPlay = new Stage();
        playlistSelectionToPlay.setTitle("Choose playlist");
        playlistSelectionToPlay.setResizable(false);
        playlistSelectionToPlay.setAlwaysOnTop(true);
        playlistSelectionToPlay.setMaximized(false);

        Group panel = new Group();
        Label info = new Label("Choose playlist");
        info.setWrapText(true);
        info.relocate(2 * sizeOfSquare, 1 * MyWindow.sizeOfSquare);
        info.setPrefSize(4 * sizeOfSquare, 2 * sizeOfSquare);
        info.setTextAlignment(TextAlignment.CENTER);
        info.setTextFill(Color.LIGHTGRAY);

        comboBox = createComboBox(false);

        Button okButton = new Button("OK");
        okButton.setPrefSize(2 * sizeOfSquare, 1 * sizeOfSquare);
        okButton.relocate(5 * sizeOfSquare, 6 * sizeOfSquare);
        okButton.setStyle(buttonStyle);

        okButton.setOnAction(e -> Actions.openPlaylistFunction());

        Button cancelButton = new Button("Cancel");
        cancelButton.setPrefSize(2 * sizeOfSquare, 1 * sizeOfSquare);
        cancelButton.relocate(1 * sizeOfSquare, 6 * sizeOfSquare);
        cancelButton.setStyle(buttonStyle);

            cancelButton.setOnAction(e -> playlistSelectionToPlay.close());

        panel.getChildren().add(info);
        panel.getChildren().add(comboBox);
        panel.getChildren().add(okButton);
        panel.getChildren().add(cancelButton);

        Scene scene = new Scene(panel, 8 * sizeOfSquare, 8 * sizeOfSquare, Color.BLACK);

        playlistSelectionToPlay.setScene(scene);
        playlistSelectionToPlay.show();

        return playlistSelectionToPlay;
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

    public static ComboBox getComboBox() {
        return comboBox;
    }

    public static void setComboBox(ComboBox cb){
        comboBox = cb;
    }

    public static TextField getPlaylistName() {
        return playlistName;
    }

    public static Stage getNewPlaylistNameSt() {
        return newPlaylistNameSt;
    }

    public static Stage getPlaylistSelectionToAddStage(){
        return playlistSelectionToAdd;
    }

    public static Stage getPlaylistSelectionToPlayStage(){
        return playlistSelectionToPlay;
    }
}
